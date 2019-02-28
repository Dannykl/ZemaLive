package live.zema.app.data.api;

import io.reactivex.Single;
import live.zema.app.data.model.LoginAndSignupResponse;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginApiService {

    @FormUrlEncoded
    @POST("/tune/api/album/login.php")
    Single<LoginAndSignupResponse> doLogin(@Field("email") String email,
                                           @Field("password") String password
    );
}
