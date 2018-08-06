package com.example.admin.moviebd.screen.movie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.moviebd.R;
import com.example.admin.moviebd.data.model.Movie;
import com.example.admin.moviebd.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private List<Movie> mMovies;
    private OnMovieItemClickListener mOnMovieItemClickListener;
    private Context mContext;

    public MovieAdapter(Context context, List<Movie> movies, OnMovieItemClickListener onMovieItemClickListener) {
        this.mContext = context;
        this.mMovies = movies;
        this.mOnMovieItemClickListener = onMovieItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent,
                false);
        return new ViewHolder(view, mMovies, mOnMovieItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = mMovies.get(position);
        holder.mTextTitleMovie.setText(movie.getTitleVideo());
        holder.mTextVoteAverage.setText(mContext.getString(R.string.star, movie.getVoteAverage()));
        Picasso.get().load(String.format(Constants.BaseApiUrl.IMAGE_URL, movie.getPosterPath())).
                into(holder.mImageMovie);
    }

    @Override
    public int getItemCount() {
        return mMovies != null ? mMovies.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageMovie, mImageOption;
        private TextView mTextTitleMovie, mTextVoteAverage;
        private List<Movie> mMovies;
        private OnMovieItemClickListener mOnMovieItemClickListener;

        ViewHolder(final View itemView, List<Movie> movies, OnMovieItemClickListener onMovieItemClickListener) {
            super(itemView);
            this.mMovies = movies;
            this.mOnMovieItemClickListener = onMovieItemClickListener;
            mImageMovie = itemView.findViewById(R.id.image_movie);
            mImageOption = itemView.findViewById(R.id.image_option);
            mTextTitleMovie = itemView.findViewById(R.id.text_title);
            mTextVoteAverage = itemView.findViewById(R.id.text_vote_average);
            mImageOption.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.image_movie:
                    mOnMovieItemClickListener.onItemClick(mMovies.get(getLayoutPosition()).getId());
                    break;
                case R.id.image_option:
                    mOnMovieItemClickListener.onShowOption(mImageOption);
                    break;
            }
        }
    }

    public interface OnMovieItemClickListener {
        void onItemClick(int movieId);

        void onShowOption(View view);
    }
}
