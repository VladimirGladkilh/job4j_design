package isp;

public interface UserAction {
    String name();

    boolean execute(Input input, Store memTracker);
}
