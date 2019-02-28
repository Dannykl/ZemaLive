package live.zema.app.data.api;

import live.zema.app.data.model.LoginAndSignupResponse;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SignupApiService {
      @FormUrlEncoded
      @POST("/tune/api/album/signup.php")
      Single<LoginAndSignupResponse> doSignup(@Field("firstName") String firstName, @Field("lastName") String lastName,
                                              @Field("email") String email, @Field("password") String password);
}
