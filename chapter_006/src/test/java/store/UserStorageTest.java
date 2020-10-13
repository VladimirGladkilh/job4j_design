package store;

import junit.framework.TestCase;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
public class UserStorageTest extends TestCase {

    @Test
    public void testTransfer() {
        StorageInterface userStorage = new UserStorage();
        userStorage.add(new User(1, 100));
        userStorage.add(new User(2, 200));
        userStorage.transfer(1, 2, 50);
        assertThat(userStorage.getUserById(1).getAmount(), is(50));
    }
}