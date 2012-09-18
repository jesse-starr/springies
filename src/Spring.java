import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;


/**
 * 
 * @author Robert C. Duvall
 */
public class Spring {
    private Mass myStart;
    private Mass myEnd;
    private double myLength;
    private double myK;

    public Spring (Mass start, Mass end, double length, double kVal) {
        setMyStart(start);
        setMyEnd(end);
        setMyLength(length);
        setMyK(kVal);
    }

    public void paint (Graphics2D pen) {
        int xStart = (int) getMyStart().getCenter().getX();
        int yStart = (int) getMyStart().getCenter().getY();
        int xEnd = (int) getMyEnd().getCenter().getX();
        int yEnd = (int) getMyEnd().getCenter().getY();
        double dx = xStart - xEnd;
        double dy = yStart - yEnd;
        double len = Math.sqrt(dx * dx + dy * dy) - getMyLength();

        if (Math.abs(len) < 0.001) {
            pen.setColor(Color.WHITE);
        }
        else if (len < 0.0) {
            pen.setColor(Color.BLUE);
        }
        else {
            pen.setColor(Color.RED);
        }
        pen.drawLine(xStart, yStart, xEnd, yEnd);
    }

    public void update (Simulation canvas, double dt) {
        Point2D start = getMyStart().getCenter();
        Point2D end = getMyEnd().getCenter();
        double dx = start.getX() - end.getX();
        double dy = start.getY() - end.getY();

        // apply hooke's law to each attached mass
        Force f = new Force(Force.angleBetween(dx, dy), getMyK()
                * (getMyLength() - Force.distanceBetween(dx, dy)));
        getMyStart().applyForce(f);
        f.negate();
        getMyEnd().applyForce(f);
    }

    public void setMyStart (Mass myStart) {
        this.myStart = myStart;
    }

    public Mass getMyStart () {
        return myStart;
    }

    public void setMyEnd (Mass myEnd) {
        this.myEnd = myEnd;
    }

    public Mass getMyEnd () {
        return myEnd;
    }

    public void setMyLength (double myLength) {
        this.myLength = myLength;
    }

    public double getMyLength () {
        return myLength;
    }

    public void setMyK (double myK) {
        this.myK = myK;
    }

    public double getMyK () {
        return myK;
    }
}
