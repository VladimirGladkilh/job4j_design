package lspparking;

import lspparking.cars.CarInterface;
import lspparking.parking.ParkingPlaceInterface;
import lspparking.parking.ParkingPlaceInterfaceImpl;
import lspparking.parking.ParkingStore;

import java.util.*;

/**
 * Последовательность малых пароковочных мест
 */
public class SmallParkingPlaces implements ParkingStore {
    //ID парковки н.п. A/B/C/D
    private String parkingName;
    //число парковочных мест
    private int placesCount = 1;
    private int parkingPlaceSize = 1;
    //карта с местами
    Map<String, ParkingPlaceInterface> parkingMap = new LinkedHashMap<>();

    public SmallParkingPlaces() {
        this.parkingName = "Small";
        for (int i = 0; i < this.placesCount; i++) {
            this.parkingMap.put(this.parkingName + i, new ParkingPlaceInterfaceImpl(this.parkingPlaceSize));
        }
    }

    public SmallParkingPlaces(String parkingName) {
        this.parkingName = parkingName;
        for (int i = 0; i < this.placesCount; i++) {
            this.parkingMap.put(this.parkingName + i, new ParkingPlaceInterfaceImpl(this.parkingPlaceSize));
        }
    }

    public SmallParkingPlaces(String parkingName, int placesCount) {
        this.parkingName = parkingName;
        this.placesCount = placesCount;
        for (int i = 0; i < this.placesCount; i++) {
            this.parkingMap.put(this.parkingName + i, new ParkingPlaceInterfaceImpl(this.parkingPlaceSize));
        }
    }

    public SmallParkingPlaces(String pName, int pCount, int pPlaceSize) {
        this.parkingName = pName;
        this.placesCount = pCount;
        this.parkingPlaceSize = pPlaceSize;
        for (int i = 0; i < this.placesCount; i++) {
            this.parkingMap.put(this.parkingName + i, new ParkingPlaceInterfaceImpl(this.parkingPlaceSize));
        }
    }

    @Override
    public void add(CarInterface car) {
        String[] places = haveFreePlace(car);
        for (String key: places) {
            ParkingPlaceInterface ppi = this.parkingMap.get(key);
            ppi.add(car);
        }
    }

    @Override
    public String[] haveFreePlace(CarInterface car) {
        //ищем свободное место "по размеру машины"
        String[] findInFilter = findInFiltered(car);
        if (findInFilter.length > 0) {
            return findInFilter;
        }
        return findInSomePlaces(car);
    }

    @Override
    public String[] findInFiltered(CarInterface car) {
        int pSize = car.getParkingSize();
        Optional<Map.Entry<String, ParkingPlaceInterface>> filteredPlace = this.parkingMap.entrySet()
                .stream()
                .filter(sPPPIEntry -> sPPPIEntry.getValue().isFree()
                        && sPPPIEntry.getValue().placeSize() == pSize)
                .findFirst();
        if (filteredPlace.isPresent()) {
            return new String[]{filteredPlace.get().getKey()};
        }
        return new String[]{};
    }

    @Override
    public String[] findInSomePlaces(CarInterface car) {
        int pSize = car.getParkingSize();
        int maxPlaceSize = 0;
        List<String> retList = new LinkedList<>();
        for (Map.Entry<String, ParkingPlaceInterface> entry: this.parkingMap.entrySet()) {
            ParkingPlaceInterface parkingPlaceInterface = entry.getValue();
            //если нашли свободное место меньшего размера
            if (parkingPlaceInterface.isFree() && parkingPlaceInterface.placeSize() < pSize) {
                //то собираем их в лист и считаем совокупный объем
                maxPlaceSize = maxPlaceSize + parkingPlaceInterface.placeSize();
                retList.add(entry.getKey());
            } else {
                maxPlaceSize = 0;
                retList.clear();
            }
            //если набралось достаточно места
            if (maxPlaceSize >= pSize) {
                return retList.toArray(new String[0]);
            }
        }
        return retList.toArray(new String[0]);
    }

    @Override
    public String showParking() {
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        this.parkingMap.forEach((s, parkingPlaceInterface) -> sj.add(s + "=" + (!parkingPlaceInterface.isFree() ? parkingPlaceInterface.whoIsThere().getId() : "isFree")));
        return sj.toString();
    }

    @Override
    public Map<String, ParkingPlaceInterface> getParkingMap() {
        return this.parkingMap;
    }
}
