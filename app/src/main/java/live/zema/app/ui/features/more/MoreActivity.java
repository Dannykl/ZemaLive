package live.zema.app.ui.features.more;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import live.zema.app.R;
import live.zema.app.data.model.AppConstants;
import live.zema.app.ui.base.BaseActivity;
import live.zema.app.ui.features.login.LoginActivity;
import live.zema.app.ui.features.login.LoginView;

public class MoreActivity extends BaseActivity implements LoginView{

      private TextView logout;
      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_more);

            logout = findViewById(R.id.logout);
            logout.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        onLoginResponse(false, AppConstants.LOGGED_OUT);
                        //route to home page....
                        startActivity(new Intent(MoreActivity.this, LoginActivity.class));
                        finish();
                  }
            });
      }

      @Override
      public void onLoginResponse(boolean isLoggedIn, String message) {
            if(!isLoggedIn)
            {
                  storeBool(AppConstants.LOGGED_IN_PREF, false);
            }else {
                  showToast(message);//more options to handle error
            }
      }

}
