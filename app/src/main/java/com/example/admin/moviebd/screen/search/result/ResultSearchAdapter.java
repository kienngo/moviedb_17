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
import com.example.admin.moviebd.data.model.Movie;
import com.example.admin.moviebd.data.model.SearchResult;
import com.example.admin.moviebd.utils.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.admin.moviebd.screen.search.result.MediaType.MEDIA_TYPE_MOVIE;
import static com.example.admin.moviebd.screen.search.result.MediaType.MEDIA_TYPE_TV;
import static com.example.admin.moviebd.utils.Constant.ApiAddContent.SEARCH_MOVIE;
import static com.example.admin.moviebd.utils.Constant.ApiAddContent.SEARCH_TELEVISION;

public class ResultSearchAdapter extends RecyclerView.Adapter<ResultSearchAdapter.ViewHolder> {
    private List<SearchResult> mSearchResults;
    private String mSearchCondition;
    private Context mContext;
    private OnResultSearchItemClickListener mOnResultSearchItemClickListener;
    private ResultSearchPresenter mResultSearchPresenter;

    public ResultSearchAdapter(Context context,
                               List<SearchResult> searchResults, String searchCondition,
                               OnResultSearchItemClickListener onResultSearchItemClickListener,
                               ResultSearchPresenter resultSearchPresenter) {
        this.mContext = context;
        this.mSearchResults = searchResults;
        this.mSearchCondition = searchCondition;
        this.mOnResultSearchItemClickListener = onResultSearchItemClickListener;
        this.mResultSearchPresenter = resultSearchPresenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_movie_vertical, parent, false);
        return new ViewHolder(view, mSearchResults);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SearchResult searchResult = mSearchResults.get(position);
        if (searchResult.getMediaType() == null) {
            switch (mSearchCondition) {
                case SEARCH_MOVIE:
                    holder.setDataMovie(searchResult);
                    break;
                case SEARCH_TELEVISION:
                    holder.setDataTelevision(searchResult);
                    break;
            }
        } else {
            switch (searchResult.getMediaType()) {
                case MEDIA_TYPE_MOVIE:
                    holder.setDataMovie(searchResult);
                    break;
                case MEDIA_TYPE_TV:
                    holder.setDataTelevision(searchResult);
                    break;
            }
        }
        holder.setImageFavorites(mResultSearchPresenter.isFavoritesLocal(
                String.valueOf(searchResult.getId())));
        holder.setCommonView(searchResult);
    }

    @Override
    public int getItemCount() {
        return mSearchResults != null ? mSearchResults.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageMovie, mImageOption;
        private TextView mTextTitleMovie, mTextMediaType, mTextVoteAverage, mTextDate;
        private List<SearchResult> mSearchResults;

        public ViewHolder(View itemView, List<SearchResult> searchResults) {
            super(itemView);
            this.mSearchResults = searchResults;
            mImageMovie = itemView.findViewById(R.id.image_movie);
            mImageOption = itemView.findViewById(R.id.image_option);
            mTextTitleMovie = itemView.findViewById(R.id.text_title);
            mTextMediaType = itemView.findViewById(R.id.text_media_type);
            mTextVoteAverage = itemView.findViewById(R.id.text_vote_average);
            mTextDate = itemView.findViewById(R.id.text_date);
            mImageOption.setOnClickListener(this);
        }

        void setDataMovie(SearchResult searchResult) {
            mTextTitleMovie.setText(searchResult.getTitleMovie());
            mTextMediaType.setText(MEDIA_TYPE_MOVIE);
            mTextDate.setText(mContext.getString(R.string.release_date,
                    searchResult.getReleaseDate()));
        }

        void setDataTelevision(SearchResult searchResult) {
            mTextTitleMovie.setText(searchResult.getNameTelevision());
            mTextMediaType.setText(MEDIA_TYPE_TV);
            mTextDate.setText(mContext.getString(R.string.first_air_date,
                    searchResult.getFirstAirDate()));
        }

        void setCommonView(SearchResult searchResult){
            mTextVoteAverage.setText(mContext.getString(R.string.star, searchResult.getVoteAverage()));
            Picasso.get().load(String.format(Constant.BaseApiUrl.IMAGE_URL, searchResult.getPostPath())).
                    into(mImageMovie);
        }

        void setImageFavorites(boolean isState) {
            if (isState) {
                mImageOption.setImageResource(R.drawable.ic_favorites_selected);
            } else {
                mImageOption.setImageResource(R.drawable.ic_favories_normal);
            }
        }

        @Override
        public void onClick(View view) {
            mOnResultSearchItemClickListener.
                    onItemClick(mSearchResults.get(getLayoutPosition()));
        }
    }

    public interface OnResultSearchItemClickListener {
        void onItemClick(SearchResult searchResult);
    }
}
