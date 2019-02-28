package live.zema.app.ui.features.more;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import live.zema.app.R;
import live.zema.app.data.model.AppConstants;
import live.zema.app.ui.features.login.LoginActivity;
import live.zema.app.ui.features.login.LoginView;

public class more extends AppCompatActivity implements LoginView{

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
                        startActivity(new Intent(more.this, LoginActivity.class));
                        finish();
                  }
            });
      }

      @Override
      public void onLoginResponse(boolean success, String message) {

      }
}
