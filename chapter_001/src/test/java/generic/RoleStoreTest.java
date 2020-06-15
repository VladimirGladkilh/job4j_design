package generic;

import junit.framework.TestCase;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class RoleStoreTest extends TestCase {
    @Test
    public void testAdd() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("first"));
        roleStore.add(new Role("second"));
    }

    public void testReplace() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("first"));
        roleStore.add(new Role("second"));
        Role replacer = new Role("Replace");
        roleStore.replace("first", replacer);
        assertThat(roleStore.findById("first"), is(nullValue()));
        assertThat(roleStore.findById("Replace"), is(replacer));
    }

    public void testDelete() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("first"));
        roleStore.add(new Role("second"));
        roleStore.delete("first");
        assertThat(roleStore.findById("first"), is(nullValue()));
    }

    public void testFindById() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("first"));
        roleStore.add(new Role("second"));
        assertThat(roleStore.findById("first"), is(notNullValue()));
    }
}