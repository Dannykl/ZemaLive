package live.zema.app.ui.features.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.Random;

import live.zema.app.R;
import live.zema.app.ui.features.home.HomeActivity;
import live.zema.app.data.model.AppConstants;
import live.zema.app.ui.base.BaseActivity;
import live.zema.app.ui.features.login.LoginActivity;

public class SplashActivity extends BaseActivity {

    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler.postDelayed(splashTask, new Random().nextInt(3000));
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(splashTask);
        super.onDestroy();
    }

    private final Runnable splashTask = new Runnable() {
        @Override
        public void run() {
            if (getBool(AppConstants.LOGGED_IN_PREF)) {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
//                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            } else {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
            finish();
        }
    };
}
