package live.zema.app.ui.features.login;

import live.zema.app.ui.base.UIView;

public interface LoginView extends UIView {

    void onLoginResponse(boolean error, String message);
}
