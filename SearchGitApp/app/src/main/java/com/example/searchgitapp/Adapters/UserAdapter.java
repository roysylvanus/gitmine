package com.example.searchgitapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchgitapp.Model.GithubTopic;
import com.example.searchgitapp.Model.GithubUser;
import com.example.searchgitapp.Model.ItemTopic;
import com.example.searchgitapp.Model.ItemUser;
import com.example.searchgitapp.R;

import java.io.InputStream;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.RespoViewHolder> {

    GithubUser repList ;
    private Context context;
    private int nums;


    public UserAdapter(GithubUser repList, Context context, int nums) {
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

        ItemUser item = repList.getItems().get(position);

        holder.tvDescription.setText(item.getLogin());
        holder.tvOwner.setText(String.valueOf(item.getScore()));
        new DownloadImageTask(holder.ivUser)
                .execute(item.getAvatarUrl());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url =  String.valueOf(item.getHtmlUrl());

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
        ImageView ivUser;


        public RespoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivUser = itemView.findViewById(R.id.ivUser);
            repLayout = itemView.findViewById(R.id.repLay);
            tvSearchPhrase = itemView.findViewById(R.id.tvSearchPhrase);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOwner = itemView.findViewById(R.id.tvOwner);
            tvDescription = itemView.findViewById(R.id.tvDescription);


        }
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}


