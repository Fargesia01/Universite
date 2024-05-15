package clock.spider;

import java.awt.Point;

/**
 *
 * Very slow and poorly optimized but a very user friendly Point implementation
 *
 * @author Cobra_8
 */
public class SpiderClockPoint implements Cloneable {

    private float x;
    private float y;

    public SpiderClockPoint() {
        this(0, 0);
    }

    public SpiderClockPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getXAsInt() {
        return (int) getX();
    }

    public int getYAsInt() {
        return (int) getY();
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public SpiderClockPoint add(SpiderClockPoint point) {
        return new SpiderClockPoint(getX() + point.getX(), getY() + point.getY());
    }

    public SpiderClockPoint sub(SpiderClockPoint point) {
        return new SpiderClockPoint(getX() - point.getX(), getY() - point.getY());
    }

    public SpiderClockPoint mul(SpiderClockPoint point) {
        return new SpiderClockPoint(getX() * point.getX(), getY() * point.getY());
    }

    public SpiderClockPoint div(SpiderClockPoint point) {
        return new SpiderClockPoint(getX() / point.getX(), getY() / point.getY());
    }

    public Point toPoint() {
        return new Point((int) getX(), (int) getY());
    }

    @Override
    protected SpiderClockPoint clone() throws CloneNotSupportedException {
        return (SpiderClockPoint) super.clone(); // we have only primitives, shallow copy is fine
    }

}
