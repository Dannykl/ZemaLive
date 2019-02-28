package live.zema.app.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import live.zema.app.ui.features.album.fragment.AlbumDescription;
import live.zema.app.ui.features.album.fragment.AlbumReview;

public class PageAdapter extends FragmentStatePagerAdapter {
      int tabCount;
      String authorName,albumName,poster,aboutAlbum, comment,numberOfScore;

      public PageAdapter(FragmentManager fm, int tabCount, String authorName, String albumName, String poster,String aboutAlbum,String numberOfScore,String comment) {
            super(fm);
            this.tabCount = tabCount;
            this.authorName=authorName;
            this.albumName=albumName;
            this.poster=poster;
            this.aboutAlbum=aboutAlbum;
            this.numberOfScore=numberOfScore;
            this.comment=comment;

      }

      @Override
      public Fragment getItem(int position) {
            switch (position){
                  case 0:
//                ReviewFragment tab1 = new ReviewFragment();
                        return AlbumDescription.newInstance(authorName,albumName,poster,aboutAlbum);
                  case 1:
//                BookInfoFragment tab2 = new BookInfoFragment();
                        return AlbumReview.newInstance(albumName,numberOfScore,comment);
                  default:
                        return null;
            }

      }

      @Override
      public int getCount() {
            return tabCount;
      }
}
