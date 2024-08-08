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
; import androidx.recyclerview.widget.DiffUtil;
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

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.MyViewHolder>{
    List<Review> reviewList;
    public ReviewListAdapter (){
        this.reviewList = new ArrayList<>();
    }
// demande à Android de redessiner les éléments du recyclerView
    public void updateList(List<Review>reviews){
        Log.d("Adapter",reviews.size()+"");
        this.reviewList = reviews;
        notifyDataSetChanged();//il ne redessine pas les éléments comparé au diffutils
    }

   // public class ReviewDiffCallback extends DiffUtil.Callback {
      //  private final List<Review> oldList;
       // private final List<Review> newList;
        //public ReviewDiffCallback(List<Review> oldList, List<Review> newList) {
           // this.oldList = oldList; this.newList = newList; }

       // @Override
       // public int getOldListSize() { return oldList.size();
        //}

        //@Override
        //public int getNewListSize() { return newList.size();
       // }
        //@Override
       // public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            //return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
        //}
        //@Override public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            //return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
        //} }

    //public void updateList(List<Review> newList) {
        // Méthode NotifyDataSetChanged : entraine un rafraichissement complet de la liste côté UI //
        //this.listReview = newList; //notifyDataSetChanged();
        // privilégier le diffUtil pour regénérer à l'écran uniquement les éléments de la liste qui ont changé // Méthode DiffUtil : entraine un rafraichissement partiel de la liste côté UI
        //DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ReviewDiffCallback(listReview, newList));
        //listReview.clear();
        //listReview.addAll(newList);
        //diffResult.dispatchUpdatesTo(this);
    //}

// View Holder pour Recycler View
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_reviews, parent,false);

        return new ReviewListAdapter.MyViewHolder(view);
    }

   


    // binding des éléments du View Holder (Reviews) sur les éléments du Fragment (fragment_review)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(holder.userPicture.getContext())
         .load(reviewList.get(position).getPicture())
         .into(holder.userPicture);
// bibliothèque Glide pour recup URL image et target vers userPicture
        holder.userName.setText(reviewList.get(position).getUsername());
        holder.userReview.setText(reviewList.get(position).getComment());
        holder.userRate.setRating(reviewList.get(position).getRate());
        //holder.userPicture.setImageResource(Integer.parseInt(reviewList.get(position).getPicture()));


//affecte les elements de list reviewModel aux éléments XML, cree methode set element

    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }
//retourne au recyclerview le nombre d elements à afficher
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView userPicture;

       TextView userName, userReview;
       RatingBar userRate;

//indique la nature des éléments à afficher et affectation des éléments par nature

        @SuppressLint("WrongViewCast")
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
           userPicture = itemView.findViewById(pictureReview);
           userName= itemView.findViewById(R.id.usernameReview);
           userReview = itemView.findViewById(R.id.commentReview);
           userRate = itemView.findViewById(R.id.ratingBarReview);

//associe les items de MyViewHolder au élément XML du layout fragment_reviews
        }
    }
}
