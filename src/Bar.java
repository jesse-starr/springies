import java.awt.geom.Point2D;


public class Bar extends Spring {

    public Bar (Mass start, Mass end, double length, double kVal) {
        super(start, end, length, kVal);
    }

    @Override
    public void update (Simulation canvas, double dt) {
        Point2D start = getMyStart().getCenter();
        Point2D end = getMyEnd().getCenter();
        double dx = start.getX() - end.getX();
        double dy = start.getY() - end.getY();

        // apply hooke's law to each attached mass
        Force f = new Force(Force.angleBetween(dx, dy),
                0 * (getMyLength() - Force.distanceBetween(dx, dy)));
        getMyStart().applyForce(f);
        f.negate();
        getMyEnd().applyForce(f);
    }
}
