package com.example.admin.moviebd.screen.detail_tv;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.moviebd.R;
import com.example.admin.moviebd.data.model.Trailer;
import com.example.admin.moviebd.screen.trailer.TrailerActivity;
import com.example.admin.moviebd.utils.Constant;

import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {
    private List<Trailer> mTrailers;
    private ItemTrailerClick mItemTrailerClick;
    private Context mContext;

    public TrailerAdapter(List<Trailer> mTrailers, Context mContext, ItemTrailerClick itemTrailerClick) {
        this.mTrailers = mTrailers;
        this.mContext = mContext;
        this.mItemTrailerClick = itemTrailerClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trailer, parent,
                false);
        return new ViewHolder(view, mTrailers, mItemTrailerClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Trailer trailer = mTrailers.get(position);
        holder.bindData(trailer);
    }

    @Override
    public int getItemCount() {
        return mTrailers != null ? mTrailers.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextTrailer;
        private List<Trailer> mTrailers;
        private ItemTrailerClick mItemClickListener;

        ViewHolder(View itemView, List<Trailer> trailers, ItemTrailerClick itemClickListener) {
            super(itemView);
            this.mTrailers = trailers;
            this.mItemClickListener = itemClickListener;
            mTextTrailer = itemView.findViewById(R.id.text_trailer_name);
            mTextTrailer.setOnClickListener(this);
        }

        public void bindData(Trailer trailer) {
            mTextTrailer.setText(trailer.getName());
        }

        @Override
        public void onClick(View view) {
            mItemClickListener.onClickTrailer(mTrailers.get(getLayoutPosition()).getId());
        }
    }

    public interface ItemTrailerClick {
        void onClickTrailer(String idTrailer);
    }
}
