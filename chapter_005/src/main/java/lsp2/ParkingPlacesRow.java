package lsp2;

import lsp2.cars.CarInterface;
import lsp2.parking.ParkingPlaceInterface;
import lsp2.parking.ParkingStore;

import java.util.*;

/**
 * Класс реализует работу с парковочным рядом.
 * Каждый ряд может состоять из отдельных последовательностей парковочных мест
 * различных размеров (Big/Small parking places).
 * Границы последовательностей физически не разделены т.е.
 * в реале это чистое поле расчерченное на фрагменты. Если же границы есть, то
 * метод haveFreePlace использовать без метода findInSomePlaces
 */
public class ParkingPlacesRow implements ParkingStore{
    List<ParkingStore> parkingStoreList = new LinkedList<>();
    Map<String, ParkingPlaceInterface> parkingMap = new LinkedHashMap<>();

    public ParkingPlacesRow(List<ParkingStore> parkingPlace) {
        this.parkingStoreList = parkingPlace;
        parkingPlace.forEach(parkingStore -> this.parkingMap.putAll(parkingStore.getParkingMap()));
    }

    @Override
    public void add(CarInterface car) {
        String[] places = haveFreePlace(car);
        for (String key: places) {
           // System.out.println(car.getId() + " Key = " + key);
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
        //если не нашли свободное место нужного размера,
        // то перебираем все подряд (фильтр по свободности тут не подойдет
        // т.к. будет разрыв в местах соседних парковочных мест)
        for (ParkingStore ps: this.parkingStoreList) {
            String[] freePlace = ps.haveFreePlace(car);
            if (ps.haveFreePlace(car).length > 0) {
                return freePlace;
            }
        }
        // пробуем поставить на несколько мест разного размера т.е. если машина=3
        // то ее поставить на места 2 и 1
        return findInSomePlaces(car);
    }
    @Override
    public String[] findInSomePlaces(CarInterface car) {
        int pSize = car.getParkingSize();
        int maxPlaceSize = 0;
        List<String> retList = new LinkedList<>();
        for (ParkingStore ps: this.parkingStoreList) {
            for (Map.Entry<String, ParkingPlaceInterface> entry: ps.getParkingMap().entrySet()) {
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
                //если набраболь достаточно места
                if (maxPlaceSize >= pSize) {
                    return retList.toArray(new String[0]);
                }
            }
        }
        return retList.toArray(new String[0]);
    }

    @Override
    public String[] findInFiltered(CarInterface car) {
        int pSize = car.getParkingSize();
        Optional<Map.Entry<String, ParkingPlaceInterface>> filteredPlace = this.parkingMap.entrySet().stream().filter(sPPPIEntry -> sPPPIEntry.getValue().isFree() && sPPPIEntry.getValue().placeSize() == pSize).findFirst();
        if (filteredPlace.isPresent()) {
            return new String[]{filteredPlace.get().getKey()};
        }
       return new String[]{};
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
