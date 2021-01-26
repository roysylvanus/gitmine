package com.example.searchgitapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchgitapp.Model.GithubCode;
import com.example.searchgitapp.Model.GithubLabel;
import com.example.searchgitapp.Model.ItemCode;
import com.example.searchgitapp.Model.ItemLabel;
import com.example.searchgitapp.R;

public class CodeAdapter extends RecyclerView.Adapter<CodeAdapter.RespoViewHolder> {

    GithubCode repList ;
    private Context context;
    private int nums;


    public CodeAdapter(GithubCode repList, Context context, int nums) {
        this.repList = repList;
        this.context = context;
        this.nums = nums;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    @NonNull
    @Override
    public RespoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(nums,parent,false);

        return new RespoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RespoViewHolder holder, final int position) {

        ItemCode item = repList.getItems().get(position);

        holder.tvDescription.setText(item.getGitUrl());
        holder.tvOwner.setText(item.getScore());
        holder.tvTitle.setText(item.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url =  item.getUrl();

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(browserIntent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return repList.getItems().size();
    }

    public static class RespoViewHolder extends RecyclerView.ViewHolder {
        CardView repLayout;
        TextView tvSearchPhrase, tvTitle, tvOwner, tvDescription;


        public RespoViewHolder(@NonNull View itemView) {
            super(itemView);
            repLayout = itemView.findViewById(R.id.repLay);
            tvSearchPhrase = itemView.findViewById(R.id.tvSearchPhrase);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOwner = itemView.findViewById(R.id.tvOwner);
            tvDescription = itemView.findViewById(R.id.tvDescription);


        }
    }
}


