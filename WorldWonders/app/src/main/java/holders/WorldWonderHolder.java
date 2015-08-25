package holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ciandt.worldwonders.R;

/**
 * Created by jfranco on 8/25/15.
 */
public class WorldWonderHolder extends RecyclerView.ViewHolder {

    private Context context;
    public TextView worldWonderTitle;
    public ImageView worldWonderImage;

    public WorldWonderHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        worldWonderTitle = (TextView) itemView.findViewById(R.id.imageItemTitle);
        worldWonderImage = (ImageView) itemView.findViewById(R.id.imageItemView);
    }
}
