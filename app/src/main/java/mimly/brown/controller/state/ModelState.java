package mimly.brown.controller.state;

import mimly.brown.controller.Controller;

public abstract class ModelState {

    protected final Controller controller;

    public ModelState(Controller controller) {
        this.controller = controller;
    }

    public abstract void perform();

}
