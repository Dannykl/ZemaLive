package live.zema.app.controller;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import live.zema.app.data.api.HeaderApiService;
import live.zema.app.data.entity.AlbumList;
import live.zema.app.data.model.AlbumResponse;
import live.zema.app.signature.SimpleItem;
import live.zema.app.ui.features.Header.HeaderView;
import live.zema.app.ui.features.home.HomeView;

public class HeaderController extends BaseUIController<HeaderView> {

      private HeaderApiService apiService;

      public HeaderController(HeaderView view, HeaderApiService apiService) {
            super(view);
            this.apiService = apiService;
      }

      public void fetchNewAlbums()
      {
            apiService.getNewAlbumData()
                    .map(new Function<AlbumList, List<SimpleItem>>() {
                          @Override
                          public List<SimpleItem> apply(AlbumList albumList) throws Exception {
                                return mapAlbums(albumList.getListOfAlbums());
                          }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableSingleObserver<List<SimpleItem>>() {
                          @Override
                          public void onSuccess(List<SimpleItem> albumList) {
                                HeaderView view = getView();
                                if (view != null) {
                                      view.onLoadNewAlbums(albumList);
                                }
                          }

                          @Override
                          public void onError(Throwable e) {
                                HeaderView view = getView();
                                if (view != null) {
                                      view.onError(e.getMessage());
                                }
                          }
                    });
      }
      private List<SimpleItem> mapAlbums(List<AlbumResponse> listOfAlbums) {
            List<SimpleItem> items = new ArrayList<>();
            items.add(new SimpleItem() {});

            for (int i = 0; i < listOfAlbums.size(); i++) {
                  items.add(listOfAlbums.get(i));
            }

//            List<String> genresList = new ArrayList<>();
//            for (int i = 0; i < listOfAlbums.size(); i++) {
//                  AlbumList dm = new AlbumList();
//                  String cat = listOfAlbums.get(i).getGenres();
//
//                  dm.setCatagoryName(listOfAlbums.get(i).getGenres());
//
//                  List<AlbumResponse> singleAlbum = new ArrayList<>();
//                  if (!genresList.contains(cat)) {
//                        for (int j = 0; j < listOfAlbums.size(); j++) {
//                              genresList.add(cat);
//                              if (listOfAlbums.get(j).getGenres().equals(cat)) {
//                                    singleAlbum.add(listOfAlbums.get(j));
//                                    dm.setListOfAlbums(singleAlbum);
//                              }
//
//                        }
//                        items.add(dm);
//                  }
//            }
            return items;
      }


}
