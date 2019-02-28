package live.zema.app.ui.features.album;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import live.zema.app.R;
import live.zema.app.ui.features.search.AlbumSearch;
import live.zema.app.ui.adapters.PageAdapter;
import live.zema.app.ui.features.home.HomeActivity;

public class AlbumInfo extends AppCompatActivity implements TabLayout.OnTabSelectedListener
{
      Toolbar toolbar;
      TabLayout tabLayout;
      ViewPager viewPager;



      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_album_description);

            //finding toobar and setting it support
//            toolbar = (Toolbar) findViewById(R.id.toolbar);
//            setSupportActionBar(toolbar);

            //find TabLayout and set tabs
            tabLayout = (TabLayout) findViewById(R.id.tablayout);

            //add tabs
            tabLayout.addTab(tabLayout.newTab().setText("Description"));
            tabLayout.addTab(tabLayout.newTab().setText("Review"));
            tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);

            Intent intent = getIntent();
            String authorName = intent.getStringExtra("artist");
            String albumName = intent.getStringExtra("albumName");
            String poster = intent.getStringExtra("poster");
            String about_album = intent.getStringExtra("about_album");
            String  score = intent.getStringExtra("score");
            String comment = intent.getStringExtra("comment");



            //find view pager
            viewPager = (ViewPager) findViewById(R.id.viewpager);

            PageAdapter adapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount(),authorName,albumName,poster, about_album,score,comment);

            viewPager.setAdapter(adapter);


            //tablayout listener for selected
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(this);


            //bottom menu navigation implementation
            BottomNavigationView bottomNavView = findViewById(R.id.navigation);
            bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView
                    .OnNavigationItemSelectedListener() {

                  @Override
                  public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
                        int id = item.getItemId();
                        if (id == R.id.navigation_home) {

//                            Toast.makeText(AlbumInfo.this, "home is Clicked", Toast.LENGTH_LONG).show();
                              Intent intent = new Intent(AlbumInfo.this, HomeActivity.class);
                              startActivity(intent);

                              return true;
                        }
                        if (id == R.id.navigation_search) {
//                              Toast.makeText(AlbumInfo.this, "Search is Clicked", Toast.LENGTH_LONG).show();
                              Intent intent = new Intent(AlbumInfo.this, AlbumSearch.class);
                              startActivity(intent);
                              return true;
                        }
                        if (id == R.id.navigation_more) {
                              Toast.makeText(AlbumInfo.this, "menu is Clicked", Toast.LENGTH_LONG).show();
                              return true;
                        }
                        return false;
                  }
            });

      }

      @Override
      public void onTabSelected(TabLayout.Tab tab) {
            viewPager.setCurrentItem(tab.getPosition());
      }

      @Override
      public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override
      public void onTabReselected(TabLayout.Tab tab) {

      }

}



