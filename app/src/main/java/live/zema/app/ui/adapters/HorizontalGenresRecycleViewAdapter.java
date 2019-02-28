package live.zema.app.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import live.zema.app.R;
import live.zema.app.data.entity.Artist;
import live.zema.app.data.entity.Music;
import live.zema.app.data.model.AlbumResponse;
import live.zema.app.signature.Pair;
import live.zema.app.ui.features.album.AlbumDetails;
import live.zema.app.utility.ImageLoadUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HorizontalGenresRecycleViewAdapter extends RecyclerView.Adapter<HorizontalGenresRecycleViewAdapter.ViewHolder> {

    private final List<AlbumResponse> albums = new ArrayList<>();
    private Context context;

    public HorizontalGenresRecycleViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_album_in_section, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        AlbumResponse singleAlbum = albums.get(i);
        viewHolder.tvSampleMusic.setText(singleAlbum.getAlbum_name());

        ImageLoadUtils.loadImage(context, albums.get(i).getPoster(), viewHolder.itemImage);
    }

    @Override
    public int getItemCount() {
        return (albums == null) ? 0 : albums.size();
    }

    public void setAlbums(List<AlbumResponse> albums) {
        this.albums.addAll(albums);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvSampleMusic;
        private final ImageView itemImage;

        ViewHolder(View view) {
            super(view);
            tvSampleMusic = (TextView) view.findViewById(R.id.tvSampleAlbum);
            itemImage = view.findViewById(R.id.ivAlbum);
            itemImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                              Context context = v.getContext();
                    Intent intent = new Intent(context, AlbumDetails.class);

                    String allArtists = "";
                    int index = 0;
                    int position = getAdapterPosition();

                    for (Artist art : albums.get(position).getArtists()) {
                        if ((index > 0) & (index != albums.get(position).getArtists().size() - 1)) {
                            allArtists = allArtists + "," + art.getName();
                        } else if ((index > 0) & (index == albums.get(position).getArtists().size() - 1)) {
                            allArtists = allArtists + " and " + art.getName();
                        } else {
                            allArtists += art.getName();
                        }
                        index++;
                    }
                    HashMap<String, Pair> allSongs = new HashMap<>();
                    intent.putExtra("artists", allArtists);
                    intent.putExtra("album_name", albums.get(position).getAlbum_name());
                    intent.putExtra("released_date", albums.get(position).getReleasedDate());
                    intent.putExtra("poster", albums.get(position).getPoster());

                    for (Music mu : albums.get(position).getTracks()) {
                        allSongs.put(mu.getSongName(), new Pair(mu.getSongLocation(), mu.getSong_duration()));
                    }
                    intent.putExtra("musics", allSongs);
//                              int input = allSongs.size();
                    context.startActivity(intent);
                }
            });
        }
    }
}
