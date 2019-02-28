package live.zema.app.controller;

import android.text.TextUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import live.zema.app.data.api.LoginApiService;
import live.zema.app.data.model.LoginAndSignupResponse;
import live.zema.app.ui.features.login.LoginView;

public class LoginController extends BaseUIController<LoginView> {

    private final LoginApiService apiService;

    public LoginController(LoginView view, LoginApiService apiService) {
        super(view);
        this.apiService = apiService;
    }

    public void doLogin(String email, String password) {
        if(TextUtils.isEmpty(email)) {
            LoginView view = getView();
            if (view != null) {
                view.onLoginResponse(false, "Email field is empty.");
            }
            return;
        }
        if(TextUtils.isEmpty(password)) {
            LoginView view = getView();
            if (view != null) {
                view.onLoginResponse(false, "Password field is empty.");
            }
            return;
        }

        apiService.doLogin(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<LoginAndSignupResponse>() {

                    @Override
                    public void onSuccess(LoginAndSignupResponse loginResponse) {
                        LoginView view = getView();
                        if (view != null) {
                            view.onLoginResponse(loginResponse.isError(), loginResponse.getErrorMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable failure) {
                        LoginView view = getView();
                        if (view != null) {
                            view.onLoginResponse(false, failure.getMessage());
                        }
                    }
                });
    }
}
