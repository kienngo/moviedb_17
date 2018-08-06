package com.example.admin.moviebd.screen.search.result;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.moviebd.R;
import com.example.admin.moviebd.data.model.SearchResult;
import com.example.admin.moviebd.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.admin.moviebd.screen.search.SearchCondition.SEARCH_MOVIE;
import static com.example.admin.moviebd.screen.search.SearchCondition.SEARCH_TELEVISION;
import static com.example.admin.moviebd.screen.search.result.MediaType.MEDIA_TYPE_MOVIE;
import static com.example.admin.moviebd.screen.search.result.MediaType.MEDIA_TYPE_TV;

public class ResultSearchAdapter extends RecyclerView.Adapter<ResultSearchAdapter.ViewHolder> {
    private List<SearchResult> mSearchResults;
    private int mSearchCondition;
    private Context mContext;

    public ResultSearchAdapter(Context context, List<SearchResult> searchResults, int searchCondition) {
        this.mContext = context;
        this.mSearchResults = searchResults;
        this.mSearchCondition = searchCondition;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_vertical, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SearchResult searchResult = mSearchResults.get(position);
        if (searchResult.getMediaType() == null) {
            switch (mSearchCondition) {
                case SEARCH_MOVIE:
                    holder.mTextTitleMovie.setText(searchResult.getTitleMovie());
                    holder.mTextMediaType.setText(MEDIA_TYPE_MOVIE);
                    holder.mTextDate.setText(mContext.getString(R.string.release_date, searchResult.getReleaseDate()));
                    break;
                case SEARCH_TELEVISION:
                    holder.mTextTitleMovie.setText(searchResult.getNameTelevision());
                    holder.mTextMediaType.setText(MEDIA_TYPE_TV);
                    holder.mTextDate.setText(mContext.getString(R.string.first_air_date, searchResult.getFirstAirDate()));
                    break;
            }
        } else {
            switch (searchResult.getMediaType()) {
                case MEDIA_TYPE_MOVIE:
                    holder.mTextTitleMovie.setText(searchResult.getTitleMovie());
                    holder.mTextMediaType.setText(searchResult.getMediaType());
                    holder.mTextDate.setText(mContext.getString(R.string.release_date, searchResult.getReleaseDate()));
                    break;
                case MEDIA_TYPE_TV:
                    holder.mTextTitleMovie.setText(searchResult.getNameTelevision());
                    holder.mTextMediaType.setText(searchResult.getMediaType());
                    holder.mTextDate.setText(mContext.getString(R.string.first_air_date, searchResult.getFirstAirDate()));
                    break;
            }
        }
        holder.mTextVoteAverage.setText(mContext.getString(R.string.star, searchResult.getVoteAverage()));
        Picasso.get().load(String.format(Constants.BaseApiUrl.IMAGE_URL, searchResult.getPostPath())).
                into(holder.mImageMovie);
    }

    @Override
    public int getItemCount() {
        return mSearchResults != null ? mSearchResults.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageMovie, mImageOption;
        private TextView mTextTitleMovie, mTextMediaType, mTextVoteAverage, mTextDate;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageMovie = itemView.findViewById(R.id.image_movie);
            mImageOption = itemView.findViewById(R.id.image_option);
            mTextTitleMovie = itemView.findViewById(R.id.text_title);
            mTextMediaType = itemView.findViewById(R.id.text_media_type);
            mTextVoteAverage = itemView.findViewById(R.id.text_vote_average);
            mTextDate = itemView.findViewById(R.id.text_date);
        }
    }
}
