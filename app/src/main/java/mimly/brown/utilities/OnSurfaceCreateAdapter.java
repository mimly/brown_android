package mimly.brown.utilities;

import android.view.SurfaceHolder;

public interface OnSurfaceCreateAdapter extends SurfaceHolder.Callback {
    @Override
    void surfaceCreated(SurfaceHolder holder);

    @Override
    default void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    default void surfaceDestroyed(SurfaceHolder holder) {
    }
}
