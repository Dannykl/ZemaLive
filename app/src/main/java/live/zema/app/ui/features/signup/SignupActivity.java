package live.zema.app.ui.features.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import live.zema.app.R;
import live.zema.app.controller.SignupController;
import live.zema.app.data.api.ApiConnector;
import live.zema.app.data.api.SignupApiService;

import live.zema.app.ui.base.BaseActivity;
import live.zema.app.ui.features.login.LoginActivity;


import static live.zema.app.data.model.AppConstants.SUCCESSFULLY_SIGNED;

public class SignupActivity extends BaseActivity implements SignupView {

    private EditText firstName, lastName, emailAddress, password;
    private Button signUp;
    private TextView logIn;
    private SignupController signupController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        resetting();

        SignupApiService service = ApiConnector.getRetrofitInstance(getApplicationContext()).create(SignupApiService.class);
        signupController = new SignupController(this, service);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupController.doSignup(getValue(firstName), getValue(lastName), getValue(emailAddress), getValue(password));
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
            }
        });
    }

    @Override
    public void onSignupResponse(boolean error, String message) {
        if(!error) {
//            storeBool(AppConstants.LOGGED_IN_PREF, true);
            Toast.makeText(SignupActivity.this, SUCCESSFULLY_SIGNED, Toast.LENGTH_LONG).show();
            //route to home page....
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            showToast(message);//MoreActivity options to handle error
        }
    }

    void resetting() {

        firstName = findViewById(R.id.f_name_signup);
        lastName = findViewById(R.id.l_name_signup);
        emailAddress = findViewById(R.id.email_signup);
        password = findViewById(R.id.pass_signup);
        signUp = findViewById(R.id.submit_signup);
        logIn = findViewById(R.id.log_in_signup);
    }
}
