package mimly.brown.view;

import android.graphics.Canvas;

import mimly.brown.model.Model;
import mimly.brown.model.Particle;

public interface Particle2D extends Particle {

    boolean isBeingTraced();

    void setTraceOn();

    void setTraceOff();

    boolean isGraphClosed();

    void setGraphClosed();

    void setGraphOpen();

    void drawParticle(final Model model, final Canvas canvas);

    void drawGraph(final Model model, final Canvas canvas);

}
