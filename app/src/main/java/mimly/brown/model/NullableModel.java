package mimly.brown.model;

import androidx.annotation.NonNull;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import mimly.brown.model.event.BrownianEventManager;
import mimly.brown.model.event.EventManager;
import mimly.brown.view.Particle2D;
import mimly.brown.view.screen.ScreenArea;

public class NullableModel implements Model {

    private final List<Particle2D> particles = Collections.emptyList();
    private final EventManager eventManager = new BrownianEventManager();

    @Override
    public EventManager getEventManager() {
        return this.eventManager;
    }

    @Override
    public void createNewParticles(ScreenArea screenArea) {

    }

    @Override
    public void moveAll() {

    }

    @Override
    public void updateMotionState(ScreenArea screenArea) {

    }

    @Override
    public void updateAliveParticlesRadius() {

    }

    @Override
    public int getCurrentDistance() {
        return 0;
    }

    @Override
    public void setCurrentDistance(int currentDistance) {

    }

    @Override
    public int getCurrentTimestep() {
        return 0;
    }

    @Override
    public void setCurrentTimestep(int currentTimestep) {

    }

    @Override
    public int getCurrentRadius() {
        return 0;
    }

    @Override
    public void setCurrentRadius(int currentRadius) {

    }

    @Override
    public int getCurrentNumberOfParticles() {
        return 0;
    }

    @Override
    public void setCurrentNumberOfParticles(int currentNumberOfParticles) {

    }

    @Override
    public int getCurrentNumberOfTraceableParticles() {
        return 0;
    }

    @Override
    public void setCurrentNumberOfTraceableParticles(int currentNumberOfTraceableParticles) {

    }

    @Override
    public int getCurrentColorOfParticles() {
        return 0;
    }

    @Override
    public void setCurrentColorOfParticles(int currentColorOfParticles) {

    }

    @Override
    public int getCurrentColorOfDeadParticles() {
        return 0;
    }

    @Override
    public void setCurrentColorOfDeadParticles(int currentColorOfDeadParticles) {

    }

    @NonNull
    @Override
    public Iterator<Particle2D> iterator() {
        return new Iterator<Particle2D>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Particle2D next() {
                return null;
            }
        };
    }

    @Override
    public Stream<Particle2D> stream() {
        return this.particles.stream();
    }

}
