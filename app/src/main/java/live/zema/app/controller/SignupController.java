package live.zema.app.controller;

import android.text.TextUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import live.zema.app.data.api.SignupApiService;
import live.zema.app.data.model.LoginAndSignupResponse;
import live.zema.app.ui.features.signup.SignupView;

public class SignupController extends BaseUIController<SignupView>
{
      private final SignupApiService apiService;

      public SignupController(SignupView view, SignupApiService service) {
            super(view);
            this.apiService = service;
      }

      public void doSignup(String firstName, String lastName, String email, String password) {
            if (TextUtils.isEmpty(firstName)) {
                  SignupView view = getView();
                  if (view != null) {
                        view.onSignupResponse(false, "First Name field is empty.");
                  }
                  return;
            }
            if (TextUtils.isEmpty(lastName)) {
                  SignupView view = getView();
                  if (view != null) {
                        view.onSignupResponse(false, "Last name field is empty.");
                  }
                  return;
            }
            if (TextUtils.isEmpty(email)) {
                  SignupView view = getView();
                  if (view != null) {
                        view.onSignupResponse(false, "Email field is empty.");
                  }
                  return;
            }
            if (TextUtils.isEmpty(password)) {
                  SignupView view = getView();
                  if (view != null) {
                        view.onSignupResponse(false, "Password field is empty.");
                  }
                  return;
            }

            apiService.doSignup(firstName, lastName, email, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableSingleObserver<LoginAndSignupResponse>() {

                          @Override
                          public void onSuccess(LoginAndSignupResponse loginAndSignupResponse) {
                                SignupView view = getView();
                                if (view != null) {
                                      view.onSignupResponse(loginAndSignupResponse.isError(), loginAndSignupResponse.getErrorMessage());
                                }
                          }
                          @Override
                          public void onError(Throwable failure) {
                                SignupView view = getView();
                                if (view != null) {
                                      view.onSignupResponse(false, failure.getMessage());
                                }
                          }
                    });
      }
}
