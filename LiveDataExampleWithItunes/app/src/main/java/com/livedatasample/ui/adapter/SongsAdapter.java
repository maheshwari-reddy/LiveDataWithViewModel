package com.livedatasample.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.livedatasample.R;
import com.livedatasample.domain.Songs;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolder> {
    
    private Context context;
    private ISongsAdapterCallBack callback;
    private List<Songs> songs = new ArrayList<>();

    public SongsAdapter(Context context, List<Songs> songsList, ISongsAdapterCallBack callback) {
        this.context = context;
        this.callback = callback;
        this.songs = songsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_single_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Songs song = songs.get(position);
        if (!TextUtils.isEmpty(song.getArtworkUrl100())) {
            Glide.with(context)
                    .load(song.getArtworkUrl100())
                    .apply(new RequestOptions().error(R.mipmap.ic_launcher))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.thumbImg);
        }
        holder.artistName.setText(song.getArtistName());
        holder.price.setText(song.getTrackPrice() + " " + song.getCurrency());
        holder.trackName.setText(song.getTrackName());
        holder.CollectionCensoredName.setText(song.getCollectionCensoredName());
        holder.genere.setText(song.getPrimaryGenreName());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.selectedSong(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public interface ISongsAdapterCallBack {

        void selectedSong(int id);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.thumb_img)
        AppCompatImageView thumbImg;
        @BindView(R.id.artistName)
        AppCompatTextView artistName;
        @BindView(R.id.trackName)
        AppCompatTextView trackName;
        @BindView(R.id.genere)
        AppCompatTextView genere;
        @BindView(R.id.price)
        AppCompatTextView price;
        @BindView(R.id.CollectionCensoredName)
        AppCompatTextView CollectionCensoredName;
        @BindView(R.id.root)
        LinearLayout root;
        @BindView(R.id.parentLayout)
        CardView parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
