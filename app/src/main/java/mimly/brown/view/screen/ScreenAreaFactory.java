package mimly.brown.view.screen;

public class ScreenAreaFactory {

    private static ScreenArea instance = null;

    public static ScreenArea getInstance() throws ScreenInitException {
        if (ScreenAreaFactory.instance == null)
            throw new ScreenInitException("Screen is not initialized yet.");
        return ScreenAreaFactory.instance;
    }

    public static void initializeInstance(int width, int height) {
        ScreenAreaFactory.instance = new CircleArea(width, height);
    }

    public static class ScreenInitException extends RuntimeException {
        public ScreenInitException(String s) {
            super(s);
        }
    }
}
