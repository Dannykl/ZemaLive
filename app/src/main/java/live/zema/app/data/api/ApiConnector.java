package live.zema.app.data.api;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import live.zema.app.data.model.AppConstants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConnector {

      private static Retrofit retrofit;

      public static Retrofit getRetrofitInstance(Context context) {
            if (retrofit == null) {
                  OkHttpClient client = new OkHttpClient.Builder()
                          .readTimeout(25L, TimeUnit.SECONDS)//MoreActivity http configs here
                          .build();
                  retrofit = new Retrofit.Builder()
                          .baseUrl(AppConstants.BASE_URL)
                          .addConverterFactory(GsonConverterFactory.create())
                          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                          .client(client)
                          .build();
            }
            return retrofit;
      }
}
