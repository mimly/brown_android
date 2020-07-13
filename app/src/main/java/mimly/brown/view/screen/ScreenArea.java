package mimly.brown.view.screen;

import mimly.brown.model.Particle;

public interface ScreenArea {

    int getWidth();

    int getHeight();

    int getOriginX();

    int getOriginY();

    boolean includes(final Particle particle);

    boolean excludes(final Particle particle);

}
