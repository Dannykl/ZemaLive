package live.zema.app.ui.features.Header;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

import live.zema.app.R;
import live.zema.app.controller.HeaderController;
import live.zema.app.data.api.ApiConnector;
import live.zema.app.data.api.HeaderApiService;
import live.zema.app.signature.AndroidVersion;
import live.zema.app.signature.SimpleItem;
import live.zema.app.ui.adapters.HeaderFlipperAdapter;
import live.zema.app.ui.base.BaseActivity;

public class HeaderActivity extends BaseActivity implements HeaderView{
//      ArrayList<SimpleItem> androidVersions = new ArrayList<>();
      Button bt_flipper;
      HeaderController headerController;
      HeaderFlipperAdapter adapter;
      AdapterViewFlipper adapterViewFlipper;

      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_flipper);

//            populateData();

            adapterViewFlipper = findViewById(R.id.adapterViewFlipper);
            bt_flipper = findViewById(R.id.bt_flipper);

            adapter = new HeaderFlipperAdapter(this);
            adapterViewFlipper.setAdapter(adapter);
            adapterViewFlipper.setFlipInterval(500);
            adapterViewFlipper.startFlipping();

            headerController = new HeaderController(this, ApiConnector.getRetrofitInstance(getApplicationContext()).create(HeaderApiService.class));
            headerController.fetchNewAlbums();

//            bt_flipper.setOnClickListener(new View.OnClickListener() {
//                  @Override
//                  public void onClick(View view) {
//                        if (adapterViewFlipper.isFlipping())
//                              adapterViewFlipper.stopFlipping();
//                        else
//                              adapterViewFlipper.startFlipping();
//                  }
//            });
      }


      @Override
      public void onLoadNewAlbums(List<SimpleItem> albumList) {
            adapter.setData(albumList);
      }

      @Override
      public void onError(String message) {
            showToast(message);
      }




      @Override
      protected void onResume() {
            super.onResume();
            if (adapterViewFlipper != null && !adapterViewFlipper.isFlipping())
                  adapterViewFlipper.startFlipping();
      }

      @Override
      protected void onPause() {
            super.onPause();
            if (adapterViewFlipper != null && adapterViewFlipper.isFlipping())
                  adapterViewFlipper.stopFlipping();
      }

      private void populateData() {
//            androidVersions.add(new AndroidVersion(R.drawable.android_e, "Android Eclair"));
      }



}



