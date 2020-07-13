package mimly.brown.model;

import java.util.Objects;
import java.util.Random;

import mimly.brown.view.screen.ScreenArea;

public class BrownianParticle implements Particle {

    private final int ID;
    protected int x, y;
    protected int radius;
    private boolean inMotion;

    public BrownianParticle(int x, int y, int radius) {
        this.ID = BrownianModel.particleID++;
        this.radius = radius;
        Random random = new Random();
        this.x += x + radius * random.nextFloat();
        this.y += y + radius * random.nextFloat();
        this.inMotion = true;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public int getRadius() {
        return this.radius;
    }

    @Override
    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public boolean isAlive() {
        return this.inMotion;
    }

    @Override
    public boolean isDead() {
        return !this.inMotion;
    }

    @Override
    public void setAlive() {
        this.inMotion = true;
    }

    @Override
    public void setDead() {
        this.inMotion = false;
    }

    @Override
    public void randomMove(int distance) {
        Random random = new Random();
        double v = 2 * Math.PI * random.nextDouble();
        this.x += distance * Math.cos(v);
        this.y += distance * Math.sin(v);
    }

    @Override
    public boolean isInsideOf(ScreenArea screenArea) {
        return screenArea.includes(this);
    }

    @Override
    public boolean isOutsideOf(ScreenArea screenArea) {
        return screenArea.excludes(this);
    }

    @Override
    public int getDistanceTo(Particle particle) {
        return (int) Math.sqrt(Math.pow((this.x - particle.getX()), 2) + Math.pow((this.y - particle.getY()), 2));
    }

    @Override
    public boolean isCollidingWith(Particle particle) {
        return getDistanceTo(particle) <= this.radius + particle.getRadius();
    }

    @Override
    public String toString() {
        return String.valueOf(this.ID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrownianParticle that = (BrownianParticle) o;
        return this.ID == that.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.ID);
    }

}