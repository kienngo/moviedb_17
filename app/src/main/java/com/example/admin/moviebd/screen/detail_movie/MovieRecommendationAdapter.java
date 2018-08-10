package com.example.admin.moviebd.screen.detail_movie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.moviebd.R;
import com.example.admin.moviebd.data.model.MovieRecommendation;
import com.example.admin.moviebd.utils.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieRecommendationAdapter extends RecyclerView.Adapter<MovieRecommendationAdapter.ViewHolder> {
    private List<MovieRecommendation> mRecommendations;
    private ItemClickMovieRecommendation mClickMovieRecommendation;
    private Context mContext;

    public MovieRecommendationAdapter(List<MovieRecommendation> mRecommendations, Context context, ItemClickMovieRecommendation clickMovieRecommendation) {
        this.mRecommendations = mRecommendations;
        this.mContext = context;
        this.mClickMovieRecommendation = clickMovieRecommendation;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommendation, parent,
                false);
        return new ViewHolder(view, mRecommendations, mClickMovieRecommendation);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieRecommendation mRecommendation = mRecommendations.get(position);
        holder.bindData(mRecommendation);
    }

    @Override
    public int getItemCount() {
        return mRecommendations != null ? mRecommendations.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImage;
        private TextView mTextName, mTextDate, mTextVote;
        private List<MovieRecommendation> mRecommendations;

        private ItemClickMovieRecommendation mItemClickListener;

        ViewHolder(View itemView, List<MovieRecommendation> recommendations, ItemClickMovieRecommendation itemClickListener) {
            super(itemView);
            this.mRecommendations = recommendations;
            this.mItemClickListener = itemClickListener;
            mImage = itemView.findViewById(R.id.image_recommendation);
            mTextDate = itemView.findViewById(R.id.text_date);
            mTextVote = itemView.findViewById(R.id.text_vote);
            mTextName = itemView.findViewById(R.id.text_recommendations_name);
            mImage.setOnClickListener(this);
        }

        public void bindData(MovieRecommendation mRecommendation) {
            mTextName.setText(mRecommendation.getName());
            mTextDate.setText(mRecommendation.getDate());
            mTextVote.setText(String.valueOf(mRecommendation.getVote()));
            Picasso.get().load(String.format(Constant.BaseApiUrl.IMAGE_URL, mRecommendation.getPathImage()))
                    .placeholder(R.drawable.image_no_image)
                    .error(R.drawable.image_error)
                    .into(mImage);
        }

        @Override
        public void onClick(View view) {
            mItemClickListener.onItemClick(mRecommendations.get(getLayoutPosition()).getId());
        }
    }

    public interface ItemClickMovieRecommendation {
        void onItemClick(int idMovie);
    }
}
