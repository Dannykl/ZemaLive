package live.zema.app.data.api;


import io.reactivex.Single;
import live.zema.app.data.entity.AlbumList;
import retrofit2.http.GET;

public interface HeaderApiService
{
      @GET("")
      Single<AlbumList> getNewAlbumData();
}
