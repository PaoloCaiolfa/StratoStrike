package stratostrike.Domain;

public class Position {
    private int x;
    private int y;
    private int z;
    private StratoShip ship;
  

    public Position(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.ship = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public StratoShip getShip() {
        return ship;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void setShip(StratoShip ship) {
        this.ship = ship;
    }

    public boolean equals(Position other) {
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public String toString() {
        return " Position(" +
                "x=" + x +
                ", y=" + y +
                ", level=" + (z == 0 ? "CIELO" : "SPAZIO") +
                ')';
    }
}