package com.openclassrooms.tajmahal.ui.restaurant;

import static com.openclassrooms.tajmahal.R.id.*;
import static com.openclassrooms.tajmahal.R.id.image;
import static com.openclassrooms.tajmahal.R.id.pictureReview;
import static com.openclassrooms.tajmahal.R.id.userPictureReview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
;import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.Target;
import com.openclassrooms.tajmahal.R;
import com.openclassrooms.tajmahal.databinding.FragmentReviewsBinding;
import com.openclassrooms.tajmahal.domain.model.Review;
import com.openclassrooms.tajmahal.ui.MainActivity;
import com.openclassrooms.tajmahal.domain.model.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * ReviewListAdapter is the class containing the adapter and the view holder present on
 * the NoticeFragment layout.
 */
public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.MyViewHolder> {
    /**
     * Fetches the list of reviews
     *
     * @return object contained the list of reviews.
     */
    List<Review> reviewList;

    /**
     * Transform the list of reviews on a arrayList
     *
     * @return ArrayList contained the reviews.
     */
    public ReviewListAdapter() {
        this.reviewList = new ArrayList<>();
    }

    /**
     * Asks Android to redraw the elements of the recyclerView.
     *
     * @param reviews contained the ArrayList of reviews
     */
    public void updateList(List<Review> reviews) {
        Log.d("Adapter", reviews.size() + "");
        this.reviewList = reviews;
        notifyDataSetChanged();
    }


    /**
     * Create the View Holder with the layout manager fragment_reviews.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return the view holder with the layout manager fragment_reviews.
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_reviews, parent, false);

        return new ReviewListAdapter.MyViewHolder(view);
    }

    /**
     * Binding elements of ViewHolder on the XML elements of the layout fragment_review.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(holder.userPicture.getContext()).load(reviewList.get(position).getPicture()).into(holder.userPicture);
        holder.userName.setText(reviewList.get(position).getUsername());
        holder.userReview.setText(reviewList.get(position).getComment());
        holder.userRate.setRating(reviewList.get(position).getRate());

    }

    /**
     * returns to the recyclerview the number of elements to display
     *
     * @return size of reviewList
     */
    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    /**
     * Indicates the nature of the elements to display and assignment of the elements by nature.
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView userPicture;
        TextView userName, userReview;
        RatingBar userRate;


        /**
         * Associates the items of MyViewHolder with the XML element of the fragment_reviews layout.
         */
        @SuppressLint("WrongViewCast")
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userPicture = itemView.findViewById(pictureReview);
            userName = itemView.findViewById(R.id.usernameReview);
            userReview = itemView.findViewById(R.id.commentReview);
            userRate = itemView.findViewById(R.id.ratingBarReview);

        }
    }
}
