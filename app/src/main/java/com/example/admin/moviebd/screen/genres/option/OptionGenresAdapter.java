package com.example.admin.moviebd.screen.genres.option;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.admin.moviebd.R;
import com.example.admin.moviebd.data.model.Genre;

import java.util.List;

public class OptionGenresAdapter extends RecyclerView.Adapter<OptionGenresAdapter.ViewHolder> {
    private List<Genre> mGenres;

    public OptionGenresAdapter(List<Genre> genres) {
        this.mGenres = genres;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_genres, parent, false);
        return new ViewHolder(view, mGenres);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Genre genre = mGenres.get(position);
        holder.mTextGenres.setText(genre.getName());
    }

    @Override
    public int getItemCount() {
        return mGenres != null ? mGenres.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {
        private TextView mTextGenres;
        private CheckBox mCheckBoxGenres;
        private List<Genre> mGenres;

        public ViewHolder(View itemView, List<Genre> genres) {
            super(itemView);
            this.mGenres = genres;
            mTextGenres = itemView.findViewById(R.id.text_genres);
            mCheckBoxGenres = itemView.findViewById(R.id.checkbox_genres);
            mCheckBoxGenres.setOnCheckedChangeListener(this);
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            mGenres.get(getLayoutPosition()).setStateGenres(b);
        }
    }
}
