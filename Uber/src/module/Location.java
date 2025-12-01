package module;

public class Location {

    private final double X;
    private final double Y;

    public Location(double x, double y) {
        X = x;
        Y = y;
    }

    public double getY() {
        return Y;
    }

    public double getX() {
        return X;
    }

    public double calculateDist(Location riderLocation) {
        double dx = riderLocation.getX() - this.getX();
        double dy = riderLocation.getY() - this.getY();

        return Math.sqrt((dx * dx) + (dy * dy));
    }

    @Override
    public String toString() {
        return "(" + this.getX() + ", " + this.getY() + ")";
    }
}
