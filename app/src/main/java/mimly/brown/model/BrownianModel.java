package mimly.brown.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import mimly.brown.model.event.BrownianEventManager;
import mimly.brown.model.event.EventManager;
import mimly.brown.model.event.listener.ColorOfDeadParticlesUpdateEventListener;
import mimly.brown.model.event.listener.ColorOfParticlesUpdateEventListener;
import mimly.brown.model.event.listener.DistanceUpdateEventListener;
import mimly.brown.model.event.listener.MotionStateUpdateEventListener;
import mimly.brown.model.event.listener.NumberOfParticlesUpdateEventListener;
import mimly.brown.model.event.listener.NumberOfTraceableParticlesUpdateEventListener;
import mimly.brown.model.event.listener.RadiusUpdateEventListener;
import mimly.brown.model.event.listener.TimestepUpdateEventListener;
import mimly.brown.view.BrownianParticle2D;
import mimly.brown.view.Particle2D;
import mimly.brown.view.screen.ScreenArea;

public class BrownianModel implements Model {

    private int currentDistance = Model.DEFAULT_DISTANCE;
    private int currentTimestep = Model.DEFAULT_TIMESTEP;
    private int currentRadius = Model.DEFAULT_RADIUS;
    private int currentNumberOfParticles = Model.DEFAULT_NUMBER_OF_PARTICLES;
    private int currentNumberOfTraceableParticles = Model.DEFAULT_NUMBER_OF_TRACEABLE_PARTICLES;
    private int currentColorOfParticles = Model.DEFAULT_COLOR_OF_PARTICLES;
    private int currentColorOfDeadParticles = Model.DEFAULT_COLOR_OF_DEAD_PARTICLES;

    private List<Particle2D> particles;
    private final EventManager eventManager = new BrownianEventManager();
    /**
     * ID counter starts at 1.
     */
    static int particleID = 1;

    public BrownianModel(ScreenArea screenArea) {
        createNewParticles(screenArea);
    }

    @Override
    public EventManager getEventManager() {
        return this.eventManager;
    }

    @Override
    public void createNewParticles(final ScreenArea screenArea) {
        int x, y;
        x = screenArea.getOriginX();
        y = screenArea.getOriginY();

        BrownianModel.particleID = 1;
        this.particles = new ArrayList<>();
        for (int i = 0; i < this.currentNumberOfParticles; ++i) {
            this.particles.add(new BrownianParticle2D(x, y, this.currentRadius));
        }
    }

    @Override
    public void moveAll() {
        this.particles.stream().filter(Particle::isAlive).forEach(particle -> particle.randomMove(currentDistance));
    }

    @Override
    public void updateMotionState(final ScreenArea screenArea) {
        updateMotionStateIfOutside(screenArea);
        updateMotionStateIfColliding();
        this.eventManager.notifyOn(MotionStateUpdateEventListener.class, this);
    }

    @Override
    public void updateAliveParticlesRadius() {
        this.particles.stream().filter(Particle::isAlive).forEach(particle2D -> particle2D.setRadius(getCurrentRadius()));
    }

    private void updateMotionStateIfOutside(final ScreenArea screenArea) {
        this.particles.stream().filter(particle -> particle.isOutsideOf(screenArea)).forEach(Particle::setDead);
    }

    private void updateMotionStateIfColliding() {
        Map<Boolean, List<Particle>> particles = this.particles.stream().collect(Collectors.partitioningBy(Particle::isAlive));
        final List<Particle> aliveParticles = particles.get(Boolean.TRUE);
        final List<Particle> deadParticles = particles.get(Boolean.FALSE);
        deadParticles.forEach(deadParticle ->
                aliveParticles.stream().filter(particle -> particle.isCollidingWith(deadParticle)).forEach(Particle::setDead)
        );
    }

    @Override
    public int getCurrentDistance() {
        return currentDistance;
    }

    @Override
    public void setCurrentDistance(int currentDistance) {
        this.currentDistance = currentDistance;
        this.eventManager.notifyOn(DistanceUpdateEventListener.class, this);
    }

    @Override
    public int getCurrentTimestep() {
        return currentTimestep;
    }

    @Override
    public void setCurrentTimestep(int currentTimestep) {
        this.currentTimestep = currentTimestep;
        this.eventManager.notifyOn(TimestepUpdateEventListener.class, this);
    }

    @Override
    public int getCurrentRadius() {
        return currentRadius;
    }

    @Override
    public void setCurrentRadius(int currentRadius) {
        this.currentRadius = currentRadius;
        this.eventManager.notifyOn(RadiusUpdateEventListener.class, this);
    }

    @Override
    public int getCurrentNumberOfParticles() {
        return currentNumberOfParticles;
    }

    @Override
    public void setCurrentNumberOfParticles(int currentNumberOfParticles) {
        this.currentNumberOfParticles = currentNumberOfParticles;
        this.eventManager.notifyOn(NumberOfParticlesUpdateEventListener.class, this);

    }

    @Override
    public int getCurrentNumberOfTraceableParticles() {
        return currentNumberOfTraceableParticles;
    }

    @Override
    public void setCurrentNumberOfTraceableParticles(int currentNumberOfTraceableParticles) {
        this.currentNumberOfTraceableParticles = currentNumberOfTraceableParticles;
        this.eventManager.notifyOn(NumberOfTraceableParticlesUpdateEventListener.class, this);
    }

    @Override
    public int getCurrentColorOfParticles() {
        return currentColorOfParticles;
    }

    @Override
    public void setCurrentColorOfParticles(int currentColorOfParticles) {
        this.currentColorOfParticles = currentColorOfParticles;
        this.eventManager.notifyOn(ColorOfParticlesUpdateEventListener.class, this);
    }

    @Override
    public int getCurrentColorOfDeadParticles() {
        return currentColorOfDeadParticles;
    }

    @Override
    public void setCurrentColorOfDeadParticles(int currentColorOfDeadParticles) {
        this.currentColorOfDeadParticles = currentColorOfDeadParticles;
        this.eventManager.notifyOn(ColorOfDeadParticlesUpdateEventListener.class, this);
    }

    @NonNull
    @Override
    public Iterator<Particle2D> iterator() {
        return new Iterator<Particle2D>() {
            private int currentParticle = 0;

            @Override
            public boolean hasNext() {
                return this.currentParticle < particles.size();
            }

            @Override
            public Particle2D next() {
                return particles.get(this.currentParticle++);
            }
        };
    }

    @Override
    public Stream<Particle2D> stream() {
        return this.particles.stream();
    }

}