package lsp2.cars;

/**
 * Класс легковых авто
 */
public class SmallCar implements CarInterface {

    private final String id;
    private final int parkingSize = 1;

    public SmallCar(String id) {
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
