package mimly.brown.controller;

import android.view.View;

import androidx.annotation.StringRes;

import com.warkiz.widget.SeekParams;

import java.util.List;

import mimly.brown.controller.state.ModelState;
import mimly.brown.model.Model;
import mimly.brown.view.Particle2D;

public interface Controller {

    void updateModel(final Model model);

    void updateModelState(final ModelState modelState);

    void scheduleTask();

    void cancelScheduledTask();

    void setStartButtonText(final @StringRes int resid);

    void onStartButtonClick(final View view);

    void onStopButtonClick(final View view);

    void onDistanceSeekBarChange(final SeekParams seekParams);

    void onTimestepSeekBarChange(final SeekParams seekParams);

    void onRadiusSeekBarChange(final SeekParams seekParams);

    void onNumberOfParticlesSeekBarChange(final SeekParams seekParams);

    void onColorPickerClick(final View view);

    void onSwitchButtonClick(final View view);

    void resetUIControls();

    List<Particle2D> getTraceableParticles();

}
