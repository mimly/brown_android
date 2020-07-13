package mimly.brown.model;

import mimly.brown.view.screen.ScreenArea;

public interface Particle {

    int getX();

    int getY();

    int getRadius();

    void setRadius(final int radius);

    boolean isAlive();

    boolean isDead();

    void setAlive();

    void setDead();

    void randomMove(final int distance);

    boolean isInsideOf(final ScreenArea screenArea);

    boolean isOutsideOf(final ScreenArea screenArea);

    int getDistanceTo(final Particle particle);

    boolean isCollidingWith(final Particle particle);

}
