package live.zema.app.ui.features.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import live.zema.app.R;
import live.zema.app.data.api.ApiConnector;
import live.zema.app.ui.features.Reset.ResetPassword;
import live.zema.app.ui.features.home.HomeActivity;
import live.zema.app.ui.features.signup.SignupActivity;
import live.zema.app.controller.LoginController;
import live.zema.app.data.api.LoginApiService;
import live.zema.app.data.model.AppConstants;
import live.zema.app.ui.base.BaseActivity;

public class LoginActivity extends BaseActivity implements LoginView {

    private LoginController loginController;
    private EditText inputEmail, inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = findViewById(R.id.text_login_email);
        inputPassword = findViewById(R.id.text_login_password);

//        OkHttpClient client = new OkHttpClient.Builder()
//                .readTimeout(25L, TimeUnit.SECONDS)//more http configs here
//                .build();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(AppConstants.BASE_URL)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build();

        LoginApiService service = ApiConnector.getRetrofitInstance(getApplicationContext()).create(LoginApiService.class);
        loginController = new LoginController(this, service);

        findViewById(R.id.button_login)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loginController.doLogin(
                                getValue(inputEmail), getValue(inputPassword)
                        );
                    }
                });

        findViewById(R.id.link_create_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("sign up was pressed");
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.link_forgot_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("reset was pressed");
                Intent intent = new Intent(LoginActivity.this, ResetPassword.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onLoginResponse(boolean error, String message) {
        if(!error) {
            storeBool(AppConstants.LOGGED_IN_PREF, true);
//            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
            //route to home page....
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        } else {
            showToast(message);//more options to handle error
        }
    }
}
