package mimly.brown.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Configuration;

import androidx.annotation.StringRes;

import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;
import com.warkiz.widget.SeekParams;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import mimly.brown.MainActivity;
import mimly.brown.R;
import mimly.brown.controller.state.ModelState;
import mimly.brown.controller.state.Stopped;
import mimly.brown.model.Model;
import mimly.brown.model.NullableModel;
import mimly.brown.model.event.EventManager;
import mimly.brown.view.Particle2D;
import mimly.brown.view.View;
import mimly.brown.view.screen.ScreenAreaFactory;

public class BrownianController implements Controller {

    private Model model = new NullableModel();
    private ModelState modelState;
    private final View view;
    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    private ScheduledFuture<?> scheduledFuture = null;
    private final MainActivity mainActivity;

    public BrownianController(Context context, View view) {
        this.mainActivity = (MainActivity) context;
        this.view = view;
        this.modelState = new Stopped(this);
    }

    @Override
    public void updateModel(final Model model) {
        this.model = model;
        EventManager eventManager = this.model.getEventManager();
        eventManager.addOnMotionStateUpdateEventListener(this.view::drawParticlesAndGraphs);
        eventManager.addOnTimestepUpdateEventListener(model1 -> {
            cancelScheduledTask();
            scheduleTask();
        });
        eventManager.addOnRadiusUpdateEventListener(Model::updateAliveParticlesRadius);
        eventManager.addOnNumberOfParticlesUpdateEventListener(model1 -> model1.createNewParticles(ScreenAreaFactory.getInstance()));
    }

    @Override
    public void updateModelState(ModelState modelState) {
        this.modelState = modelState;
    }

    @Override
    public void scheduleTask() {
        this.scheduledFuture = this.scheduledExecutorService.scheduleWithFixedDelay(() -> {
            this.model.moveAll();
            this.model.updateMotionState(ScreenAreaFactory.getInstance());
        }, 0, this.model.getCurrentTimestep(), TimeUnit.MILLISECONDS);
    }

    @Override
    public void cancelScheduledTask() {
        if (this.scheduledFuture != null)
            this.scheduledFuture.cancel(false);
    }

    @Override
    public void setStartButtonText(@StringRes int resid) {
        this.mainActivity.startButton.setText(resid);
    }

    @Override
    public void onStartButtonClick(android.view.View view) {
        this.modelState.perform();
    }

    @Override
    public void onStopButtonClick(android.view.View view) {
        cancelScheduledTask();
        updateModel(new NullableModel());
        updateModelState(new Stopped(this));
        setStartButtonText(R.string.start);
        resetUIControls();
    }

    @Override
    public void onDistanceSeekBarChange(SeekParams seekParams) {
        this.model.setCurrentDistance(seekParams.progress);
    }

    @Override
    public void onTimestepSeekBarChange(SeekParams seekParams) {
        this.model.setCurrentTimestep(seekParams.progress);
    }

    @Override
    public void onRadiusSeekBarChange(SeekParams seekParams) {
        this.model.setCurrentRadius(seekParams.progress);
    }

    @Override
    public void onNumberOfParticlesSeekBarChange(SeekParams seekParams) {
        this.model.setCurrentNumberOfParticles(seekParams.progress);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onColorPickerClick(android.view.View view) {
        new ColorPickerDialog.Builder(this.mainActivity, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                .setTitle("Choose particles color")
                .setPreferenceName("Choose particles color")
                .setPositiveButton(
                        this.mainActivity.getString(R.string.confirm),
                        (ColorEnvelopeListener) (envelope, fromUser) -> this.model.setCurrentColorOfParticles(envelope.getColor())
                )
                .setNegativeButton(
                        this.mainActivity.getString(R.string.cancel),
                        (dialogInterface, i) -> dialogInterface.dismiss()
                )
                .attachAlphaSlideBar(true)
                .attachBrightnessSlideBar(true)
                .show();
    }

    @Override
    public void onSwitchButtonClick(android.view.View view) {
        this.mainActivity.switchButton.setText(this.mainActivity.switchButton.isChecked() ?
                this.mainActivity.getResources().getString(R.string.on) :
                this.mainActivity.getResources().getString(R.string.off)
        );

        if (this.mainActivity.switchButton.isChecked())
            this.model.stream().forEach(Particle2D::setGraphClosed);
        else this.model.stream().forEach(Particle2D::setGraphOpen);
    }

    /**
     * Resets all controls to their default values that are stored in the model.
     */
    @Override
    public void resetUIControls() {
        this.mainActivity.distanceSeekBar.setProgress(Model.DEFAULT_DISTANCE);
        this.mainActivity.timestepSeekBar.setProgress(Model.DEFAULT_TIMESTEP);

        if (this.mainActivity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            this.mainActivity.radiusSeekBar.setProgress(Model.DEFAULT_RADIUS);
            this.mainActivity.numberOfParticlesSeekBar.setProgress(Model.DEFAULT_NUMBER_OF_PARTICLES);

            this.mainActivity.switchButton.setChecked(false);
            this.mainActivity.switchButton.setText(this.mainActivity.getString(R.string.off));
        }
    }

    @Override
    public List<Particle2D> getTraceableParticles() {
        return this.model.stream().limit(this.model.getCurrentNumberOfTraceableParticles()).collect(Collectors.toList());
    }

}