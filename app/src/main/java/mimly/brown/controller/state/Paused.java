package mimly.brown.controller.state;

import mimly.brown.R;
import mimly.brown.controller.Controller;

public class Paused extends ModelState {

    public Paused(Controller controller) {
        super(controller);
    }

    @Override
    public void perform() {
        this.controller.scheduleTask();
        this.controller.updateModelState(new Started(this.controller));
        this.controller.setStartButtonText(R.string.pause);
    }

}
