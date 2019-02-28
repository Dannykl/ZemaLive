package live.zema.app.ui.features.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import live.zema.app.R;
import live.zema.app.controller.HomeController;
import live.zema.app.data.api.ApiConnector;
import live.zema.app.data.api.HomeApiService;
import live.zema.app.signature.SimpleItem;
import live.zema.app.ui.adapters.VerticalRecycleViewAdapter;
import live.zema.app.ui.base.BaseActivity;
import live.zema.app.ui.features.more.more;
import live.zema.app.ui.features.search.AlbumSearch;

import java.util.List;

public class HomeActivity extends BaseActivity implements HomeView {

    private HomeController homeController;
    private VerticalRecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        BottomNavigationView bnv = findViewById(R.id.navigation);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
                        int id = item.getItemId();
                        switch (id) {
                            case R.id.navigation_search:
                                Intent intent = new Intent(HomeActivity.this, AlbumSearch.class);
                                startActivity(intent);
                                return true;
                            case R.id.navigation_more:
                                startActivity(new Intent(HomeActivity.this, more.class));
                                return true;
                        }
                        return false;
                    }
                });

        RecyclerView recyclerView = findViewById(R.id.mainRecycleView);
        recyclerView.setHasFixedSize(true);

        adapter = new VerticalRecycleViewAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        homeController = new HomeController(this, ApiConnector.getRetrofitInstance(getApplicationContext()).create(HomeApiService.class));
        homeController.fetchAlbums();

    }

    @Override
    public void onLoadAlbums(List<SimpleItem> albumList) {
        adapter.setData(albumList);
    }

    @Override
    public void onError(String message) {
        showToast(message);
    }
}

