package mimly.brown.view.screen;

import mimly.brown.model.Particle;

public class CircleArea implements ScreenArea {

    private int width;
    private int height;

    CircleArea(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int getOriginX() {
        return (int) (this.width / 2f);
    }

    @Override
    public int getOriginY() {
        return (int) (this.height / 2f);
    }

    @Override
    public boolean includes(final Particle particle) {
        return getDistanceToOrigin(particle) <= 0.4f * this.width;
    }

    @Override
    public boolean excludes(final Particle particle) {
        return getDistanceToOrigin(particle) > 0.4f * this.width;
    }

    private double getDistanceToOrigin(final Particle particle) {
        return Math.sqrt(Math.pow(particle.getX() - getOriginX(), 2) + Math.pow(particle.getY() - getOriginY(), 2));
    }

}

