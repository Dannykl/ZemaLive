package live.zema.app.ui.features.signup;

import live.zema.app.ui.base.UIView;

public interface SignupView extends UIView {
      void onSignupResponse(boolean success, String message);
}
