package com.example.admin.moviebd.screen.detail_movie;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.moviebd.R;
import com.example.admin.moviebd.data.model.MovieRecommendation;
import com.example.admin.moviebd.data.model.ProductionCompany;
import com.example.admin.moviebd.utils.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieCompanyAdapter extends RecyclerView.Adapter<MovieCompanyAdapter.ViewHolder> {
    private List<ProductionCompany> mCompanies;
    private Context mContext;

    public MovieCompanyAdapter(List<ProductionCompany> mCompanies, Context context) {
        this.mCompanies = mCompanies;
        this.mContext = context;
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
        ProductionCompany company = mCompanies.get(position);
        holder.bindData(company);
    }

    @Override
    public int getItemCount() {
        return mCompanies != null ? mCompanies.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImage;
        private TextView mTextName;

        ViewHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image_movie);
            mTextName = itemView.findViewById(R.id.text_title);
        }

        public void bindData(ProductionCompany company) {
            mTextName.setText(company.getName());
            Picasso.get().load(String.format(Constant.BaseApiUrl.IMAGE_URL, company.getLogoPath()))
                    .placeholder(R.drawable.image_no_image)
                    .error(R.drawable.image_error)
                    .into(mImage);
        }
    }

}
