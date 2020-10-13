package store;

public interface StorageInterface {
    boolean add (User user);
    boolean update(User user);
    boolean delete(User user);
    boolean checkAmount(User user, int amount);
    User getUserById(int userId);
    void transfer(int fromId, int toId, int amount);
}
