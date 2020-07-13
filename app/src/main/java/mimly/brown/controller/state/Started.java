package mimly.brown.controller.state;

import mimly.brown.R;
import mimly.brown.controller.Controller;

public class Started extends ModelState {

    public Started(Controller controller) {
        super(controller);
    }

    @Override
    public void perform() {
        this.controller.cancelScheduledTask();
        this.controller.updateModelState(new Paused(this.controller));
        this.controller.setStartButtonText(R.string.resume);
    }

}
