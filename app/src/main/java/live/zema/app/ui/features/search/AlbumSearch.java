package live.zema.app.ui.features.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import live.zema.app.R;


public class AlbumSearch extends AppCompatActivity
{
      private ImageView ivBack;
      @Override
      protected void onCreate(Bundle savedInstanceState)
      {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_search);
            ivBack = findViewById(R.id.ivBack);

            ivBack.setOnClickListener(new View.OnClickListener() {
                  public void onClick(View v) {
                        finish();
                  }
            });
      }
}
