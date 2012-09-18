import java.awt.geom.Point2D;


public class Muscle extends Spring {

    private double myAmp;

    public Muscle (Mass start, Mass end, double length, double kVal, double amp) {
        super(start, end, length, kVal);
        myAmp = amp;
    }

    @Override
    public void update (Simulation canvas, double dt) {
        Point2D start = getMyStart().getCenter();
        Point2D end = getMyEnd().getCenter();
        double dx = start.getX() - end.getX();
        double dy = start.getY() - end.getY();
        double redmass = (getMyStart().getMass() * getMyEnd().getMass())
                / (getMyStart().getMass() + getMyEnd().getMass());

        // apply hooke's law to each attached mass
        Force f = new Force(Force.angleBetween(dx, dy), getMyK() * myAmp
                * Math.sin(Math.sqrt(getMyK() / redmass))
                * (getMyLength() - Force.distanceBetween(dx, dy)));
        getMyStart().applyForce(f);
        f.negate();
        getMyEnd().applyForce(f);
    }
}
