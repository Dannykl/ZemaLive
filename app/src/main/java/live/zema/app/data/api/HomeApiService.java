package live.zema.app.data.api;

import io.reactivex.Single;
import live.zema.app.data.entity.AlbumList;
import retrofit2.http.GET;

public interface HomeApiService {

    @GET("/tune/api/album/")
    Single<AlbumList> getAlbumData();

//      @GET("/tune/api/album/")
//      Observable<List<AlbumResponse>> getAlbumData();
}
