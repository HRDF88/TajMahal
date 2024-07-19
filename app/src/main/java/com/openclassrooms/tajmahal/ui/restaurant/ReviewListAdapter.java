package com.openclassrooms.tajmahal.ui.restaurant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.tajmahal.R;
import com.openclassrooms.tajmahal.domain.model.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.MyViewHolder>{


    List<Review> reviewList;
    public ReviewListAdapter (){
        this.reviewList = new ArrayList<>();
    }

    public void updateList(List<Review>reviews){
        Log.d("Adapter",reviews.size()+"");
        this.reviewList = reviews;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_reviews, parent,false);

        return new ReviewListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //holder.userPictureURL.setText(reviewList.get(position).getPicture());
        holder.userName.setText(reviewList.get(position).getUsername());
        holder.userReview.setText(reviewList.get(position).getComment());
        holder.userRate.setRating(reviewList.get(position).getRate());
       // int userPictureURL = 0;
        //holder.userPicture.setImageResource(userPictureURL);

//affecte les elements de list reviewModel aux éléments XML, cree methode set element

    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }
//retourne au recyclerview le nombre d elements
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView userPicture;

       TextView userName, userReview, userPictureURL;
       RatingBar userRate;

//indique quel sorte d'élément

        @SuppressLint("WrongViewCast")
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
           userPicture = itemView.findViewById(R.id.pictureReview);
           userName= itemView.findViewById(R.id.usernameReview);
           userReview = itemView.findViewById(R.id.commentReview);
           userRate = itemView.findViewById(R.id.ratingBarReview);
//associe les items de MyViewHolder au élément XML du layout fragment_reviews
        }
    }
}
