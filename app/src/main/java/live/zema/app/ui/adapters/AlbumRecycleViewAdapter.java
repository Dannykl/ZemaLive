package live.zema.app.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import live.zema.app.R;
import live.zema.app.ui.features.album.AlbumInfo;
import live.zema.app.data.entity.Music;
import live.zema.app.signature.HeaderItem;
import live.zema.app.signature.SimpleItem;
import live.zema.app.utility.ImageLoadUtils;


public class AlbumRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
      private List<SimpleItem> musics;
      private Context context;
      private MediaPlayer mediaPlayer;
      private String url = "";
      private int selectedPosition;
      private RecyclerItemClickListener listener;

      public AlbumRecycleViewAdapter(List<SimpleItem> musics, Context context, RecyclerItemClickListener listener) {
            this.musics = musics;
            this.context = context;
            this.listener=listener;
      }
      @Override
      public int getItemViewType(int position)
      {
            return musics.get(position).getType();
      }

      @NonNull
      @Override
      public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = null;
            if(i== SimpleItem.TYPE_HEADER)
            {
                return new HeaderViewHolder (LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.album_header_layout,viewGroup,false));

            }else
            {     view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.each_song,viewGroup,false);
                  return new ViewHolder(view);
            }
      }

      @Override
      public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            if(getItemViewType(i)== SimpleItem.TYPE_HEADER)
            {
                  HeaderViewHolder hvh = (HeaderViewHolder) viewHolder;
                  HeaderItem hi = (HeaderItem) musics.get(i);
                  hvh.setHeaderItems(hi);
            }else {
//                  selectedPosition = 1;
                  ViewHolder vh = (ViewHolder) viewHolder;
                  final Music track = (Music) musics.get(i);
                  vh.song_index.setText(track.getSongIndex());
                  vh.song_name.setText(track.getSongName());
                  vh.bind(track, listener);

            }
      }
      @Override
      public int getItemCount() {
            return musics.size();
      }


      public class HeaderViewHolder extends RecyclerView.ViewHolder
      {
            private ImageView ivPoster;
            private TextView tvAlbumName,tvArtist,tvReleasedDate;
            private Button play,shuffle;
            protected ImageView ivInfo;


            public HeaderViewHolder(@NonNull View itemView) {
                  super(itemView);
                  this.ivPoster = itemView.findViewById(R.id.poster);
                  this.tvAlbumName = itemView.findViewById(R.id.tv_albumName);
                  this.ivInfo = itemView.findViewById(R.id.album_info);
                  this.tvArtist = itemView.findViewById(R.id.tv_artist);
                  this.tvReleasedDate = itemView.findViewById(R.id.tv_release_date);
            }

            void setHeaderItems(HeaderItem hi)
            {
                  tvArtist.setText(hi.artist);
                  tvAlbumName.setText(hi.albumName);
                  tvReleasedDate.setText(hi.releasedDate);

                  ImageLoadUtils.loadImage(context, hi.poster, ivPoster);

                  final String _poster = hi.poster;
                  final String about_album = "Hardcoded string The service is Hardcoded string The service is Hardcoded string The service is Hardcoded string The service is referred to by different colloquialisms depending on the regionThe service is referred to by different colloquialisms depending on the regionThe service is referred to by different colloquialisms depending on the regionThe service is referred to by different colloquialisms depending on the regionThe service is referred to by different colloquialisms depending on the regionThe service is referred to by different colloquialisms depending on the regionThe service is referred to by different colloquialisms depending on the region. It may simply be referred to as a \"text\" in North America, the United Kingdom, Australia, New Zealand and the Philippines, an \"SMS\" in most of mainland Europe, or an \"MMS\" or \"SMS\" in the Middle East, Africa, and Asia. The sender of a text message is commonly referred to as a , should use @string resource";
                  final String comment = "hello this is comment";
                  final String score = "4.5";
                  ivInfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                              Toast.makeText(context, "info was Clicked" + ivInfo, Toast.LENGTH_SHORT).show();
                              Context context = v.getContext();
                              Intent intent = new Intent(context, AlbumInfo.class);
                              intent.putExtra("artist",tvArtist.getText().toString());
                              intent.putExtra("albumName",tvAlbumName.getText().toString());
                              intent.putExtra("poster",_poster);
                              intent.putExtra("about_album",about_album);
                              intent.putExtra("score",score);
                              intent.putExtra("comment",comment);

//                              Toast.makeText(context, "poster" + tvAlbumName.getText().toString(), Toast.LENGTH_SHORT).show();
                              context.startActivity(intent);

                        }
                  });
            }
      }

      public class ViewHolder extends RecyclerView.ViewHolder
      {
            protected TextView song_name,song_index;
            
            public ViewHolder(@NonNull View itemView) {
                  super(itemView);
                  song_index = itemView.findViewById(R.id.song_index);
                  song_name = itemView.findViewById(R.id.song_name);

            }

            public void bind(final Music track, final RecyclerItemClickListener listener){
                  song_name.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                              Toast.makeText(context, "Music Click" + song_name, Toast.LENGTH_SHORT).show();
                              listener.onClickListener(track, getLayoutPosition());
//
                        }
                  });
            }
      }

      public interface RecyclerItemClickListener{
            void onClickListener(Music track, int position);
      }

      public void setSelectedPosition(int selectedPosition) {
            this.selectedPosition = selectedPosition;
      }

      public int getSelectedPosition() {
            return selectedPosition;
      }
}






















