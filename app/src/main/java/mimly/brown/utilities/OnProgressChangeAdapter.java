package mimly.brown.utilities;

import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

public interface OnProgressChangeAdapter extends OnSeekChangeListener {

    @Override
    void onSeeking(SeekParams seekParams);

    @Override
    default void onStartTrackingTouch(IndicatorSeekBar seekBar) {

    }

    @Override
    default void onStopTrackingTouch(IndicatorSeekBar seekBar) {

    }
}