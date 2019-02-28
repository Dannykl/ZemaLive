package live.zema.app.ui.features.album.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import live.zema.app.R;


public class AlbumReview extends Fragment {
      public static final String SCORE = "extra.score";
      public static final String COMMENT = "extra.comment";
      private TextView tvReviewScore,tvReviewHeader, tv_review_comment;

      public static AlbumReview newInstance(String albumName, String numberOfScore,String comment) {

            Bundle args = new Bundle();
            args.putString(AlbumDescription.EXTRA_ALBUM_NAME, albumName);
            args.putString(SCORE, numberOfScore);
            args.putString(COMMENT, comment);

            AlbumReview fragment = new AlbumReview();
            fragment.setArguments(args);
            return fragment;
      }

      @Nullable
      @Override
      public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_album_review, container, false);
            tvReviewHeader = v.findViewById(R.id.tvReviewHeader);
            tvReviewScore = v.findViewById(R.id.total_score);
//            tv_review_comment = v.findViewById(R.id.comment_body);

            String albumName = getArguments().getString(AlbumDescription.EXTRA_ALBUM_NAME);
            String numberOfScore = getArguments().getString(SCORE);
            String comment = getArguments().getString(COMMENT);

            tvReviewHeader.setText(albumName);
            tvReviewScore.setText(numberOfScore);


            return v;
      }
}
