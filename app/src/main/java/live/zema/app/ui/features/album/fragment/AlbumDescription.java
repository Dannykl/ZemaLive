package live.zema.app.ui.features.album.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import live.zema.app.R;
import live.zema.app.utility.ImageLoadUtils;

public class AlbumDescription extends Fragment {

      private TextView about_album_title, about_author_name;
      private WebView aboutAlbum;
      String htmlText = " %s ";
      private ImageView about_poster;
      public static final String EXTRA_AUTHOR_NAME = "extra.authorName";
      public static final String EXTRA_ALBUM_NAME = "extra.albumName";
      public static final String EXTRA_POSTER = "extra.poster";
      public static final String EXTRA_ABOUT_ALBUM = "extra.aboutAlbum";

      public static AlbumDescription newInstance(String authorName,String albumName,String poster,String aboutAlbum ) {

            Bundle args = new Bundle();
            args.putString(EXTRA_AUTHOR_NAME,authorName);
            args.putString(EXTRA_ALBUM_NAME,albumName);
            args.putString(EXTRA_POSTER,poster);
            args.putString(EXTRA_ABOUT_ALBUM,aboutAlbum);

            AlbumDescription fragment = new AlbumDescription();
            fragment.setArguments(args);
            return fragment;
      }

      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
      {
            View v = inflater.inflate(R.layout.fragment_album_description,container,false);
            about_album_title = v.findViewById(R.id.about_album_title);
            about_author_name = v.findViewById(R.id.about_author_name);
            about_poster = v.findViewById(R.id.about_poster);
//            aboutAlbum = v.findViewById(R.id.about_album);
            WebView aboutAlbum =  v.findViewById(R.id.about_album);

            String authorName = getArguments().getString(EXTRA_AUTHOR_NAME);
            String albumName = getArguments().getString(EXTRA_ALBUM_NAME);
            String _poster = getArguments().getString(EXTRA_POSTER);
            String _about_album = getArguments().getString(EXTRA_ABOUT_ALBUM);

            about_album_title.setText(albumName);
            about_author_name.setText(authorName);
            ImageLoadUtils.loadImage(getContext(), _poster, about_poster);
//            aboutAlbum.setText(_about_album);

            aboutAlbum.loadData(String.format(htmlText, _about_album), "text/html", "utf-8");

            return v;

      }

}
