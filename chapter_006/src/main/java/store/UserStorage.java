package store;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.NoSuchElementException;

@ThreadSafe
public class UserStorage implements StorageInterface {
    @GuardedBy("this")
    private final HashMap<Integer, User> userSore;

    public UserStorage() {
        this.userSore = new HashMap<>();
    }

    @Override
    public synchronized boolean add(User user) {
        if (userSore.containsKey(user.getId())) {
            return false;
        }
        this.userSore.put(user.getId(), user);
        return true;
    }

    @Override
    public synchronized boolean update(User user) {
        if (userSore.containsKey(user.getId())) {
            this.userSore.put(user.getId(), user);
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean delete(User user) {
        if (userSore.containsKey(user.getId())) {
            this.userSore.remove(user.getId());
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean checkAmount(User user, int amount) {
        return user.getAmount() >= amount;
    }

    @Override
    public synchronized User getUserById(int userId) {
        if (userSore.containsKey(userId)) {
            return userSore.get(userId);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public synchronized void transfer(int fromId, int toId, int amount) {
        if (userSore.containsKey(fromId) && userSore.containsKey(toId)) {
            User from = userSore.get(fromId);
            User to = userSore.get(toId);
            if (checkAmount(from, amount)) {
                from.setAmount(from.getAmount() - amount);
                to.setAmount(to.getAmount() + amount);
            }
        }
    }
}
