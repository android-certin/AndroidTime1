package com.ciandt.worldwonders.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ciandt.worldwonders.R;

/**
 * Created by jfranco on 8/25/15.
 */
public class WorldWonderHolder extends RecyclerView.ViewHolder {

    public TextView worldWonderTitle;
    public ImageView worldWonderImage;

    public WorldWonderHolder(View itemView) {
        super(itemView);
        worldWonderTitle = (TextView) itemView.findViewById(R.id.imageItemTitle);
        worldWonderImage = (ImageView) itemView.findViewById(R.id.imageItemView);
    }
}
