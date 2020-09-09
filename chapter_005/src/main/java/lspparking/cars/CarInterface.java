package lspparking.cars;

/**
 * Описание машины. Если дополнить его сеттером ИД то можно будет создавать машины произвольных размеров
 */
public interface CarInterface {
    //Номер машины
    String getId();
    //занимаемый размер
    int getParkingSize();
}
