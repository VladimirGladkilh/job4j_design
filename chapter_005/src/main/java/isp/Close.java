package isp;

public class Close implements UserAction {
    @Override
    public String name() {
        return "===Close===";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        return false;
    }
}
