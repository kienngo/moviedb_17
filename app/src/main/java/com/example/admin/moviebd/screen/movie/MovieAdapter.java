package com.example.admin.moviebd.screen.movie;

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

    public MovieAdapter(List<Movie> movies) {
        this.mMovies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = mMovies.get(position);
        holder.mTextTitleMovie.setText(movie.getTitleVideo());
        Picasso.get().load(String.format(Constants.BaseApiUrl.IMAGE_URL, movie.getPosterPath())).
                into(holder.mImageMovie);
    }

    @Override
    public int getItemCount() {
        return mMovies != null ? mMovies.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageMovie;
        private TextView mTextTitleMovie;

        ViewHolder(View itemView) {
            super(itemView);
            mImageMovie = itemView.findViewById(R.id.image_movie);
            mTextTitleMovie = itemView.findViewById(R.id.textview_title_movie);
        }
    }
}
