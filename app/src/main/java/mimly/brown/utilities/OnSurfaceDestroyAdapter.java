package mimly.brown.utilities;

import android.view.SurfaceHolder;

public interface OnSurfaceDestroyAdapter extends SurfaceHolder.Callback {
    @Override
    default void surfaceCreated(SurfaceHolder holder) {
    }

    @Override
    default void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    void surfaceDestroyed(SurfaceHolder holder);
}