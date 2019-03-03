package live.zema.app.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import live.zema.app.R;
import live.zema.app.signature.AndroidVersion;
import live.zema.app.signature.SimpleItem;


public class HeaderFlipperAdapter extends BaseAdapter
{

      private Context context;
      private TextView tv_flipper;
      private ImageView iv_flipper;
      private final List<SimpleItem> items = new ArrayList<>();
      private final LayoutInflater inflater;

      public HeaderFlipperAdapter(Context context) {
            this.context = context;
            this.inflater = LayoutInflater.from(context);
      }

      public void setData(List<SimpleItem> data) {
            items.addAll(data);
            notifyDataSetChanged();
      }

      @Override
      public int getCount() {
            return items.size();
      }

      @Override
      public Object getItem(int position) {
            return items.get(position);
      }

      @Override
      public long getItemId(int position) {
            return position;
      }


      @Override
      public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null)
            {
                  convertView = LayoutInflater.from(context).inflate(R.layout.row_view_item,parent,false);
            }

            items.get(position);

            tv_flipper = convertView.findViewById(R.id.tv_flipper);
            iv_flipper = convertView.findViewById(R.id.iv_flipper);

//            tv_flipper.setText(items.add(items.get(position)));
//            iv_flipper.setImageResource(version.drawableInt);

//            convertView = inflater.inflate(R.layout.item, null);
//            TextView nameView = convertView.findViewById(R.id.name);
////            ImageView imageView = convertView.findViewById(R.id.image);
//            nameView.setText(nameList[position]);
//            imageView.setImageResource(imageList[position]);
//            return convertView;


            return convertView;
      }
}


