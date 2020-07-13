package mimly.brown.utilities;

import android.view.SurfaceHolder;

public interface OnSurfaceChangeAdapter extends SurfaceHolder.Callback {
    @Override
    default void surfaceCreated(SurfaceHolder holder) {
    }

    @Override
    void surfaceChanged(SurfaceHolder holder, int format, int width, int height);

    @Override
    default void surfaceDestroyed(SurfaceHolder holder) {
    }
}