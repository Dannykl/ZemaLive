package live.zema.app.controller;

import android.support.annotation.Nullable;
import live.zema.app.ui.base.UIView;

import java.lang.ref.WeakReference;

public abstract class BaseUIController<V extends UIView> {

    private final WeakReference<V> viewRef;

    protected BaseUIController(V view) {
        this.viewRef = new WeakReference<>(view);
    }

    public void onViewCreated() {
    }

    @Nullable
    public V getView() {
        return viewRef.get();
    }

    public void onViewDestroyed() {
    }
}
