package live.zema.app.ui.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;
import live.zema.app.data.model.AppConstants;

public abstract class BaseActivity extends AppCompatActivity {

    protected String getValue(EditText et) {
        return String.valueOf(et.getText());
    }

    protected SharedPreferences getPreferences() {
        return getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE);
    }

    protected void storeBool(String key, boolean value) {
        getPreferences().edit().putBoolean(key, value).apply();
    }

    protected boolean getBool(String key) {
        return getPreferences().getBoolean(key, false);
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
