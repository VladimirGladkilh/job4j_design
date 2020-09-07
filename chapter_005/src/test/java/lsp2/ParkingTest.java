package lsp2;

import lsp2.cars.BigCar;
import lsp2.cars.CarInterface;
import lsp2.cars.SmallCar;
import lsp2.parking.ParkingStore;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ParkingTest {
    @Test
    public void testAddInBigParking() {
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        sj.add("BigParking0=B1");
        sj.add("BigParking1=isFree");
        sj.add("BigParking2=isFree");
        ParkingStore big = new BigParkingPlaces("BigParking", 3, 3);
        CarInterface bigCar =  new BigCar("B1");
        CarInterface smallCar = new SmallCar("S1");
        big.add(bigCar);
        big.add(smallCar);
        assertThat(big.showParking(), is(sj.toString()));
    }

    @Test
    public void testAddInSmallParking() {
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        sj.add("BigParking0=B1");
        sj.add("BigParking1=B1");
        sj.add("BigParking2=B1");
        sj.add("BigParking3=S1");
        ParkingStore big = new SmallParkingPlaces("BigParking", 4, 1);
        CarInterface bigCar =  new BigCar("B1");
        CarInterface smallCar = new SmallCar("S1");
        big.add(bigCar);
        big.add(smallCar);
        assertThat(big.showParking(), is(sj.toString()));
    }

    @Test
    public void testHaveFreePlace() {
        ParkingStore ps = new BigParkingPlaces("A", 3, 3);
        CarInterface car = new BigCar("B1");
        ps.add(car);
        CarInterface small = new SmallCar("S1");
        String[] freePaces = ps.haveFreePlace(car);
        assertThat(freePaces, is(new String[]{"A1"}));
    }

    @Test
    public void testHaveNotFreePlace() {
        ParkingStore ps = new BigParkingPlaces("A", 3, 3);
        CarInterface car = new BigCar("B1");
        ps.add(car);
        CarInterface small = new SmallCar("S1");
        String[] freePaces = ps.haveFreePlace(small);
        assertThat(freePaces, is(new String[]{}));
    }

    @Test
    public void testFindInFiltered() {
        ParkingStore ps = new BigParkingPlaces("A", 3, 3);
        CarInterface car = new BigCar("B1");
        assertThat(ps.findInFiltered(car), is(new String[]{"A0"}));
    }

    @Test
    public void testNotFindInFiltered() {
        ParkingStore ps = new BigParkingPlaces("A", 3, 3);
        CarInterface car = new SmallCar("B1");
        assertThat(ps.findInFiltered(car), is(new String[]{}));
    }

    @Test
    public void testFindInSomePlaces() {
        ParkingStore ps = new BigParkingPlaces("A", 5, 2);
        CarInterface car = new BigCar("B1");
        assertThat(ps.findInSomePlaces(car), is(new String[]{"A0","A1"}));
    }

    @Test
    public void whenTest() {
        List<CarInterface> carInterfaceList = new LinkedList<>();
        carInterfaceList.add(new BigCar("B1"));
        carInterfaceList.add(new BigCar("B2"));
        carInterfaceList.add(new BigCar("B3"));
        carInterfaceList.add(new SmallCar("S1"));
        carInterfaceList.add(new SmallCar("S2"));
        carInterfaceList.add(new SmallCar("S3"));
        List<ParkingStore> parkingStores = new LinkedList<>();
        ParkingStore spp = new BigParkingPlaces();

        parkingStores.add(spp);
        parkingStores.add(new SmallParkingPlaces("Small", 2));
        parkingStores.add(new BigParkingPlaces("Max", 3, 3));
        parkingStores.add(new SmallParkingPlaces("Sm", 2));
        ParkingPlacesRow ppw = new ParkingPlacesRow(parkingStores);
        carInterfaceList.forEach(carInterface -> ppw.add(carInterface));
        System.out.println(ppw.showParking());

    }

}