package live.zema.app.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import live.zema.app.R;
import live.zema.app.data.entity.AlbumList;
import live.zema.app.signature.SimpleItem;

public class VerticalRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<SimpleItem> items = new ArrayList<>();
    private final Context context;
    private final LayoutInflater inflater;

    public VerticalRecycleViewAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? SimpleItem.TYPE_HEADER : SimpleItem.TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder vh;
        if (SimpleItem.TYPE_ITEM == i) {
            vh = new ViewHolder(inflater.inflate(R.layout.vertical_albums_cat, viewGroup, false));
        } else {
            vh = new RecyclerView.ViewHolder(
//                    inflater.inflate(R.layout.home_header_layout, viewGroup, false)
                    inflater.inflate(R.layout.activity_flipper, viewGroup, false)
            ) {
            };
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder view, int i) {
        int viewType = getItemViewType(i);
        if (viewType == SimpleItem.TYPE_HEADER) {
            //handle header view
        } else {
            ViewHolder irh = (ViewHolder) view;
            AlbumList al = (AlbumList) items.get(i);
            irh.bind(al);
            Log.i("this ois i" + i, ">>>>");
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setData(List<SimpleItem> data) {
        items.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final HorizontalGenresRecycleViewAdapter adapter;
        private final TextView itemsCatagory;
        private final RecyclerView recycler_view_list;
        private final Button btnMore;
        private String sectionName;

        ViewHolder(View view) {
            super(view);
            this.adapter = new HorizontalGenresRecycleViewAdapter(context);
            this.itemsCatagory = (TextView) view.findViewById(R.id.tvCatagory);
            this.recycler_view_list = (RecyclerView) view.findViewById(R.id.recycleVList);
            this.btnMore = (Button) view.findViewById(R.id.btMore);

            recycler_view_list.setHasFixedSize(true);
            recycler_view_list.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            recycler_view_list.setAdapter(adapter);

            btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "click event on MoreActivity, " + sectionName, Toast.LENGTH_SHORT).show();
                }
            });
        }

        void bind(AlbumList albums) {
            this.itemsCatagory.setText(this.sectionName = albums.getCatagoryName());
            this.adapter.setAlbums(albums.getListOfAlbums());
        }
    }
}
