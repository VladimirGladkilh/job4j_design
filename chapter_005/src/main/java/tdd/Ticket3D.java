package tdd;

public class Ticket3D implements Ticket {
    public Ticket3D() {
    }

    @Override
    public String toString() {
        return "Ticket3D{}";
    }

    @Override
    public int hashCode() {
        return 123;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
