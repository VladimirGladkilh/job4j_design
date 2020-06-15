package generic;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserStoreTest {
    @Test
    public void testAdd() {
        UserStore userStore = new UserStore();
        userStore.add(new User("123"));
        userStore.add(new User("456"));

    }
    @Test
    public void testReplace() {
        UserStore userStore = new UserStore();
        userStore.add(new User("123"));
        userStore.add(new User("456"));
        assertThat(userStore.replace("123", new User("789")), is(true));
    }
    @Test
    public void testDelete() {
        UserStore userStore = new UserStore();
        userStore.add(new User("123"));
        userStore.add(new User("456"));
        assertThat(userStore.delete("45656"), is(false));
    }
    @Test
    public void testFindById() {
        UserStore userStore = new UserStore();
        userStore.add(new User("123"));
        userStore.add(new User("456"));
        assertThat(userStore.delete("123"), is(true));
    }
}