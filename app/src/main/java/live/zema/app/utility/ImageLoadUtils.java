package live.zema.app.utility;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import live.zema.app.R;

public final class ImageLoadUtils {

    private ImageLoadUtils() {
    }

    public static void loadImage(Context context, Object url, ImageView iv) {
        Glide.with(context.getApplicationContext())
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                .into(iv);
    }
}