package lsp2.parking;

import lsp2.cars.CarInterface;

/**
 * Класс для реализации парковочного места размера size
 */
public class ParkingPlaceInterfaceImpl implements ParkingPlaceInterface {
    int size = 1;
    private CarInterface car = null;

    public ParkingPlaceInterfaceImpl() {
    }

    public ParkingPlaceInterfaceImpl(int size) {
        this.size = size;
    }

    @Override
    public void add(CarInterface car) {
        if (isFree()) {
            this.car = car;
        }
    }

    @Override
    public boolean isFree() {
        return whoIsThere() == null;
    }

    @Override
    public CarInterface whoIsThere() {
        return this.car;
    }

    @Override
    public int placeSize() {
        return this.size;
    }
}
