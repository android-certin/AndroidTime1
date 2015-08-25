package com.ciandt.worldwonders.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.helpers.Helpers;
import com.ciandt.worldwonders.model.Wonder;
import java.util.ArrayList;
import holders.WorldWonderHolder;
import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by jfranco on 8/25/15.
 */
public class WorldWonderAdapter extends RecyclerView.Adapter<WorldWonderHolder> {

    public Context context;
    public ArrayList<Wonder> wonders;
    private OnSelectItem onSelectItem;

    public WorldWonderAdapter(Context context, ArrayList<Wonder> wonders) {
        this.context = context;
        this.wonders = wonders;
    }

    @Override
    public WorldWonderHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wonder, viewGroup, false);
        WorldWonderHolder holder = new WorldWonderHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(WorldWonderHolder worldWonderHolder, int position) {
        final Wonder wonder = wonders.get(position);

        setListView(worldWonderHolder, wonder);

        onClickListView(worldWonderHolder, wonder);
    }

    private void setListView(WorldWonderHolder worldWonderHolder, Wonder wonder) {
        ImageView imageView = worldWonderHolder.worldWonderImage;

        Picasso.with(context)
                .load(Helpers.getRawResourceID(context, wonder.getPhoto().split("\\.")[0]))
                .config(Bitmap.Config.RGB_565)
                .placeholder(R.raw.place_holder)
                .error(R.raw.place_holder)
                .centerCrop()
                .resize(250, 165)
                .into(imageView);

        worldWonderHolder.worldWonderTitle.setText(wonder.getName());
    }

    private void onClickListView(WorldWonderHolder worldWonderHolder, final Wonder wonder) {
        worldWonderHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelectItem.onSelectItem(wonder);
            }
        });
    }

    public interface OnSelectItem {
        void onSelectItem(Wonder wonder);
    }

    public void setOnSelectItem(OnSelectItem onSelectItem) {
        this.onSelectItem = onSelectItem;
    }

    @Override
    public int getItemCount() {
        return wonders.size();
    }
}
