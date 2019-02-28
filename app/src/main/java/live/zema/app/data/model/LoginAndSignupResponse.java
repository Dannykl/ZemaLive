package live.zema.app.data.model;

import com.google.gson.annotations.SerializedName;

public class LoginAndSignupResponse {

    @SerializedName("error_msg") private String errorMessage;
    @SerializedName("error") private boolean error;

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isError() {
        return error;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "errorMessage='" + errorMessage + '\'' +
                ", error=" + error +
                '}';
    }
}
