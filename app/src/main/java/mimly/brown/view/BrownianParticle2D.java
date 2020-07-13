package mimly.brown.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;

import java.util.Random;

import mimly.brown.model.BrownianParticle;
import mimly.brown.model.Model;
import mimly.brown.view.screen.ScreenAreaFactory;

public class BrownianParticle2D extends BrownianParticle implements Particle2D {

    private boolean traced;
    private final Path graph;
    private final int graphColor;
    private boolean closedGraph;

    public BrownianParticle2D(int x, int y, int radius) {
        super(x, y, radius);
        this.traced = false;
        this.graph = new Path();
        this.graph.moveTo(super.x, super.y);
        this.graphColor = getRandomColor();
        this.closedGraph = false;
    }

    private int getRandomColor() {
        Random random = new Random();
        return Color.argb(0.6f, random.nextFloat(), random.nextFloat(), random.nextFloat());
    }

    private Paint getPaint(Paint.Style style, int strokeWidth) {
        Paint paint = new Paint();
        paint.setStyle(style);
        paint.setStrokeWidth(strokeWidth);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(80);
        return paint;
    }

    @Override
    public void randomMove(int distance) {
        super.randomMove(distance);
        if (isBeingTraced()) this.graph.lineTo(super.x, super.y);
    }

    @Override
    public boolean isBeingTraced() {
        return this.traced;
    }

    @Override
    public void setTraceOn() {
        this.traced = true;
    }

    @Override
    public void setTraceOff() {
        this.traced = false;
    }

    @Override
    public boolean isGraphClosed() {
        return this.closedGraph;
    }

    @Override
    public void setGraphClosed() {
        this.closedGraph = true;
    }

    @Override
    public void setGraphOpen() {
        this.closedGraph = false;
    }

    @Override
    public void drawParticle(final Model model, final Canvas canvas) {
        final Paint particlePaint = getPaint(Paint.Style.FILL_AND_STROKE, 6);

        /**
         * Set the color due to the motion and trace status
         */
        particlePaint.setColor(isBeingTraced() ? this.graphColor
                : isAlive() ? model.getCurrentColorOfParticles()
                : model.getCurrentColorOfDeadParticles());

        /**
         * Set the graphical representation of the particle and draw it
         */
        canvas.drawCircle(super.x, super.y, super.radius, particlePaint);
    }

    @Override
    public void drawGraph(final Model model, final Canvas canvas) {
        final Paint graphPaint = getPaint(Paint.Style.STROKE, 6);

        /**
         * Set the color and draw the graph
         */
        graphPaint.setColor(this.graphColor);
        Path path = this.graph;
        if (this.closedGraph) path.close();
        canvas.drawPath(path, graphPaint);

        /**
         * Draw perpendicular lines
         */
        canvas.drawLine(0, super.y, ScreenAreaFactory.getInstance().getWidth(), super.y, graphPaint);
        canvas.drawLine(super.x, 0, super.x, ScreenAreaFactory.getInstance().getHeight(), graphPaint);

        /**
         * Draw the rectangle with the particle ID
         */
        Rect rect = new Rect(super.x, super.y, super.x + 100, super.y + 100);
        canvas.drawRect(rect, graphPaint);
        int xPos = rect.centerX();
        int yPos = (int) ((rect.centerY()) - ((graphPaint.descent() + graphPaint.ascent()) / 2));
        canvas.drawText(String.valueOf(this), xPos, yPos, graphPaint);
    }

}
