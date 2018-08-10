package com.example.admin.moviebd.screen.movies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.admin.moviebd.R;
import com.example.admin.moviebd.data.model.Movie;
import com.example.admin.moviebd.screen.BaseRecyclerViewAdapter;
import com.example.admin.moviebd.utils.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.admin.moviebd.screen.search.result.MediaType.MEDIA_TYPE_MOVIE;

public class MoviesAdapter extends BaseRecyclerViewAdapter<Movie> {
    private static final int VIEW_TYPE_ITEM = 1;
    private static final int VIEW_TYPE_LOADING = 2;
    private Context mContext;
    private List<Movie> mMovies;
    private OnMovieItemClickListener mOnMovieItemClickListener;
    private MoviesPresenter mMoviesPresenter;

    public MoviesAdapter(Context context, List<Movie> movies,
                         OnMovieItemClickListener onMovieItemClickListener,
                         MoviesPresenter moviesPresenter) {
        super(context, movies);
        this.mContext = context;
        this.mMovies = movies;
        this.mOnMovieItemClickListener = onMovieItemClickListener;
        this.mMoviesPresenter = moviesPresenter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_ITEM:
                View viewItem = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_movie_vertical, parent, false);
                return new ViewHolder(viewItem, mContext, mMovies, mOnMovieItemClickListener);
            case VIEW_TYPE_LOADING:
                View viewLoading = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_progressbar_loadmore, parent, false);
                return new LoadMoreViewHolder(viewLoading);
            default:
                View viewDefault = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_movie_vertical, parent, false);
                return new ViewHolder(viewDefault, mContext, mMovies, mOnMovieItemClickListener);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            Movie movie = mMovies.get(position);
            ((ViewHolder) holder).setData(movie);
            ((ViewHolder) holder).setImageFavorites(mMoviesPresenter.isFavoritesLocal(String.valueOf(movie.getId())));
        } else if (holder instanceof LoadMoreViewHolder) {
            ((LoadMoreViewHolder) holder).setIndeterminateImage();
        }
    }

    @Override
    public int getItemCount() {
        return mMovies != null ? mMovies.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return mMovies.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageMovie, mImageOption;
        private TextView mTextTitleMovie, mTextMediaType, mTextDate;
        private Context mContext;
        private List<Movie> mMovies;
        private OnMovieItemClickListener mOnMovieItemClickListener;

        public ViewHolder(final View itemView, Context context, List<Movie> movies,
                          OnMovieItemClickListener onMovieItemClickListener) {
            super(itemView);
            this.mContext = context;
            this.mMovies = movies;
            this.mOnMovieItemClickListener = onMovieItemClickListener;
            mImageMovie = itemView.findViewById(R.id.image_movie);
            mImageOption = itemView.findViewById(R.id.image_option);
            mTextTitleMovie = itemView.findViewById(R.id.text_title);
            mTextMediaType = itemView.findViewById(R.id.text_media_type);
            mTextDate = itemView.findViewById(R.id.text_date);
            mImageOption.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.image_option:
                    mOnMovieItemClickListener.onOptionClickListener(mMovies.get(getLayoutPosition()));
                    break;
            }
        }

        void setData(Movie movie) {
            mTextTitleMovie.setText(movie.getTitleVideo());
            Picasso.get().load(String.format(Constant.BaseApiUrl.IMAGE_URL, movie.getPosterPath())).
                    into(mImageMovie);
            mTextMediaType.setText(MEDIA_TYPE_MOVIE);
            mTextDate.setText(mContext.getString(R.string.release_date, movie.getReleaseDate()));
        }

        void setImageFavorites(boolean isState) {
            if (isState) {
                mImageOption.setImageResource(R.drawable.ic_favorites_selected);
            } else {
                mImageOption.setImageResource(R.drawable.ic_favories_normal);
            }
        }
    }

    static class LoadMoreViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar mLoadMore;

        public LoadMoreViewHolder(View itemView) {
            super(itemView);
            mLoadMore = itemView.findViewById(R.id.progress_loadmore);
        }

        void setIndeterminateImage() {
            mLoadMore.setIndeterminate(true);
        }
    }

    public interface OnMovieItemClickListener {
        void onOptionClickListener(Movie movie);
    }
}
