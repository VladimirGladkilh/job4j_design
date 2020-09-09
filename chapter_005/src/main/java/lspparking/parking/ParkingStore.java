package lspparking.parking;

import lspparking.cars.CarInterface;

import java.util.Map;

/**
 * интерфейс парковки. описываем поведение парковки
 */
public interface ParkingStore {
    //поставить машину в паркинг
    void add(CarInterface car);
    //список ближайших свободных мест
    String[] haveFreePlace(CarInterface car);
    //найти места под машину определенного размера
    String[] findInFiltered(CarInterface car);
    //найти свободные места
    String[] findInSomePlaces(CarInterface car);

    //Список парковки для показа
    String showParking();
    //карта парковки
    Map<String, ParkingPlaceInterface> getParkingMap();
}
