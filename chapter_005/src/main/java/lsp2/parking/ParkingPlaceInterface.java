package lsp2.parking;

import lsp2.cars.CarInterface;

/**
 * Интерфейс для описания парковочного места
 */
public interface ParkingPlaceInterface {
    void add(CarInterface car);
    boolean isFree();
    CarInterface whoIsThere();
    int placeSize();
}
