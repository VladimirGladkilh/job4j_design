package lsp2.cars;

/**
 * Большая машина
 */
public class BigCar implements CarInterface {

    private final String id;
    private final int parkingSize = 3;

    public BigCar(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getParkingSize() {
        return this.parkingSize;
    }
}