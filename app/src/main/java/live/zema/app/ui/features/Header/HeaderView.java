package live.zema.app.ui.features.Header;

import java.util.List;

import live.zema.app.signature.SimpleItem;
import live.zema.app.ui.base.UIView;

public interface HeaderView extends UIView{

      void onLoadNewAlbums(List<SimpleItem> newAlbumList);
      void onError(String message);
}