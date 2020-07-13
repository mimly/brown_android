package mimly.brown.controller.state;

import mimly.brown.R;
import mimly.brown.controller.Controller;
import mimly.brown.model.BrownianModel;
import mimly.brown.view.screen.ScreenAreaFactory;

public class Stopped extends ModelState {

    public Stopped(Controller controller) {
        super(controller);
    }

    @Override
    public void perform() {
        this.controller.updateModel(new BrownianModel(ScreenAreaFactory.getInstance()));
        this.controller.scheduleTask();
        this.controller.updateModelState(new Started(this.controller));
        this.controller.setStartButtonText(R.string.pause);
    }

}
