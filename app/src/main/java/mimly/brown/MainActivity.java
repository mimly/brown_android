package mimly.brown;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.warkiz.widget.IndicatorSeekBar;

import java.util.Arrays;
import java.util.List;

import mimly.brown.controller.BrownianController;
import mimly.brown.controller.Controller;
import mimly.brown.utilities.OnProgressChangeAdapter;
import mimly.brown.view.Particle2D;

public class MainActivity extends AppCompatActivity {

    private Controller controller = null;
    private TraceableParticlesDialogFragment traceableParticlesDialogFragment = null;

    public Button startButton = null;
    public Button stopButton = null;
    public IndicatorSeekBar distanceSeekBar = null;
    public IndicatorSeekBar timestepSeekBar = null;
    public IndicatorSeekBar radiusSeekBar = null;
    public IndicatorSeekBar numberOfParticlesSeekBar = null;
    public ImageButton colorPicker = null;
    public Switch switchButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayHomeAsUpEnabled(false);

        this.controller = new BrownianController(MainActivity.this, findViewById(R.id.brownianView));
        this.traceableParticlesDialogFragment = new TraceableParticlesDialogFragment(this.controller);

        this.startButton = findViewById(R.id.startButton);
        this.stopButton = findViewById(R.id.stopButton);
        this.distanceSeekBar = findViewById(R.id.distanceSeekBar);
        this.timestepSeekBar = findViewById(R.id.timestepSeekBar);
        this.radiusSeekBar = findViewById(R.id.radiusSeekBar);
        this.numberOfParticlesSeekBar = findViewById(R.id.numberOfParticlesSeekBar);
        this.colorPicker = findViewById(R.id.colorPicker);
        this.switchButton = findViewById(R.id.switchButton);

        this.startButton.setOnClickListener(this.controller::onStartButtonClick);
        this.stopButton.setOnClickListener(this.controller::onStopButtonClick);

        this.distanceSeekBar.setOnSeekChangeListener((OnProgressChangeAdapter) this.controller::onDistanceSeekBarChange);
        this.timestepSeekBar.setOnSeekChangeListener((OnProgressChangeAdapter) this.controller::onTimestepSeekBarChange);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            this.radiusSeekBar.setOnSeekChangeListener((OnProgressChangeAdapter) this.controller::onRadiusSeekBarChange);
            this.numberOfParticlesSeekBar.setOnSeekChangeListener((OnProgressChangeAdapter) this.controller::onNumberOfParticlesSeekBarChange);

            this.colorPicker.setOnClickListener(this.controller::onColorPickerClick);
            this.switchButton.setOnClickListener(this.controller::onSwitchButtonClick);
        }

        this.controller.resetUIControls();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setTitle(R.string.actionBarTitle);
        getMenuInflater().inflate(R.menu.main, menu);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Arrays.asList(R.id.track, R.id.about, R.id.close).forEach(id -> menu.findItem(id).setShowAsAction(R.id.always));
        }
        return true;
    }

    public void onFollowPress(MenuItem item) {
        this.traceableParticlesDialogFragment.show(getSupportFragmentManager(), "NOTAG");
    }

    public void onTrackPress(MenuItem item) {
        Toast.makeText(MainActivity.this, "Not implemented yet.", Toast.LENGTH_LONG).show();
    }

    public void onAboutPress(MenuItem item) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Brownian_motion")));
    }

    public void onClosePress(MenuItem item) {
        finishAndRemoveTask();
    }

}