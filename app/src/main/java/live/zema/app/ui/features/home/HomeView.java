package live.zema.app.ui.features.home;

import live.zema.app.signature.SimpleItem;
import live.zema.app.ui.base.UIView;

import java.util.List;

public interface HomeView extends UIView {

    void onLoadAlbums(List<SimpleItem> albumList);

    void onError(String message);
}
