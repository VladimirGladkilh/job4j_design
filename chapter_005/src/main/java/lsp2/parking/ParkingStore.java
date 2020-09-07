package lsp2.parking;

import lsp2.cars.CarInterface;

import java.util.Map;

/**
 * интерфейс парковки
 */
public interface ParkingStore {
    //поставить машину в паркинг
    void add(CarInterface car);
    //список ближайших свободных мест
    String[] haveFreePlace(CarInterface car);

    String[] findInFiltered(CarInterface car);

    String[] findInSomePlaces(CarInterface car);

    //Список парковки для показа
    String showParking();
    //карта парковки
    Map<String, ParkingPlaceInterface> getParkingMap();
}
