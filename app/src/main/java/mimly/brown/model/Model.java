package mimly.brown.model;

import android.graphics.Color;

import mimly.brown.model.event.EventManager;
import mimly.brown.utilities.Streamable;
import mimly.brown.view.Particle2D;
import mimly.brown.view.screen.ScreenArea;

public interface Model extends Iterable<Particle2D>, Streamable<Particle2D> {

    int DEFAULT_DISTANCE = 12;
    int DEFAULT_TIMESTEP = 100;
    int DEFAULT_RADIUS = 8;
    int DEFAULT_NUMBER_OF_PARTICLES = 300;
    int DEFAULT_NUMBER_OF_TRACEABLE_PARTICLES = 10;
    int DEFAULT_COLOR_OF_PARTICLES = Color.argb(0.6f, 0f, 1f, 0f);
    int DEFAULT_COLOR_OF_DEAD_PARTICLES = Color.argb(0.6f, 1f, 0f, 0f);

    EventManager getEventManager();

    /**
     * Creates new particles accordingly to model parameters.
     */
    void createNewParticles(final ScreenArea screenArea);

    /**
     * Moves all of the particles in a random direction.
     */
    void moveAll();

    /**
     * Updates motion state of the particles.
     */
    void updateMotionState(final ScreenArea screenArea);

    /**
     * Updates radius of the particles that are alive.
     */
    void updateAliveParticlesRadius();

    /**
     * Getters and setters for model parameters.
     */
    int getCurrentDistance();

    void setCurrentDistance(int currentDistance);

    int getCurrentTimestep();

    void setCurrentTimestep(int currentTimestep);

    int getCurrentRadius();

    void setCurrentRadius(int currentRadius);

    int getCurrentNumberOfParticles();

    void setCurrentNumberOfParticles(int currentNumberOfParticles);

    int getCurrentNumberOfTraceableParticles();

    void setCurrentNumberOfTraceableParticles(int currentNumberOfTraceableParticles);

    int getCurrentColorOfParticles();

    void setCurrentColorOfParticles(int currentColorOfParticles);

    int getCurrentColorOfDeadParticles();

    void setCurrentColorOfDeadParticles(int currentColorOfDeadParticles);

}
