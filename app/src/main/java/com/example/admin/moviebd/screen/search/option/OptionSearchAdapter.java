package com.example.admin.moviebd.screen.search.option;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.moviebd.R;
import com.example.admin.moviebd.data.model.SearchParameter;

import java.util.List;

public class OptionSearchAdapter extends RecyclerView.Adapter<OptionSearchAdapter.ViewHolder> {
    private List<SearchParameter> mSearchParameters;
    private OnSearchItemClickListener mOnSearchItemClickListener;

    public OptionSearchAdapter(List<SearchParameter> searchParameters, OnSearchItemClickListener onSearchItemClickListener) {
        this.mSearchParameters = searchParameters;
        this.mOnSearchItemClickListener = onSearchItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_return_option, parent, false);
        return new ViewHolder(view, mSearchParameters, mOnSearchItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SearchParameter searchParameter = mSearchParameters.get(position);
        holder.mTextOption.setText(searchParameter.getValueSearch());
    }

    @Override
    public int getItemCount() {
        return mSearchParameters != null ? mSearchParameters.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextOption;
        private List<SearchParameter> mSearchParameters;
        private OnSearchItemClickListener mOnSearchItemClickListener;

        public ViewHolder(View itemView, List<SearchParameter> searchParameters, OnSearchItemClickListener onSearchItemClickListener) {
            super(itemView);
            this.mSearchParameters = searchParameters;
            this.mOnSearchItemClickListener = onSearchItemClickListener;
            mTextOption = itemView.findViewById(R.id.textview_option);
            mTextOption.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnSearchItemClickListener.onItemClick(mSearchParameters.get(getLayoutPosition()));
        }
    }

    public interface OnSearchItemClickListener {
        void onItemClick(SearchParameter searchParameter);
    }
}
