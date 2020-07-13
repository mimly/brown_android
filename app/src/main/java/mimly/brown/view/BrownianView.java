package mimly.brown.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceView;

import mimly.brown.model.Model;
import mimly.brown.utilities.OnSurfaceChangeAdapter;
import mimly.brown.view.screen.ScreenAreaFactory;

public class BrownianView extends SurfaceView implements View {

    public BrownianView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        setZOrderOnTop(true);
        getHolder().addCallback((OnSurfaceChangeAdapter) (holder, i, i1, i2) -> {
            /**
             * First at this point getWidth() and getHeight() do NOT return 0 and the model can be initialized.
             */
            ScreenAreaFactory.initializeInstance(i1, i2);
        });
    }

    @Override
    public void drawParticlesAndGraphs(final Model model) {
        Canvas canvas = getHolder().lockCanvas();
        drawBackground(canvas);
        drawParticles(model, canvas);
        drawGraphs(model, canvas);
        getHolder().unlockCanvasAndPost(canvas);
    }

    private void drawBackground(final Canvas canvas) {
        canvas.drawColor(Color.BLACK);
    }

    private void drawParticles(final Model model, final Canvas canvas) {
        model.stream().forEach(particle2D -> particle2D.drawParticle(model, canvas));
    }

    private void drawGraphs(final Model model, final Canvas canvas) {
        model.stream().filter(Particle2D::isBeingTraced).forEach(particle2D -> particle2D.drawGraph(model, canvas));
    }

}