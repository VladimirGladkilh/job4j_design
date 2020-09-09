package lspparking.parking;

import lspparking.cars.CarInterface;

/**
 * Интерфейс для описания парковочного места.
 * при необходимости можно добавить методы с наличием границ по трем сторонам парковочного места
 * чтобы в дальнейшем обрабатывать их как доп условие на возмоность поставить большую машину заняв
 * часть соседних мест
 */
public interface ParkingPlaceInterface {
    void add(CarInterface car);
    boolean isFree();
    CarInterface whoIsThere();
    int placeSize();
}
