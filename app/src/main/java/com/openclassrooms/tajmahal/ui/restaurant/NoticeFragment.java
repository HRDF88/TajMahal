package com.openclassrooms.tajmahal.ui.restaurant;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.openclassrooms.tajmahal.R;
import com.openclassrooms.tajmahal.databinding.FragmentNoticeBinding;
import com.openclassrooms.tajmahal.domain.model.Review;
import com.openclassrooms.tajmahal.domain.model.User;

import org.jetbrains.annotations.Contract;

import java.util.Objects;

public class NoticeFragment extends Fragment {
    private DetailsViewModel detailsViewModel;
    private @NonNull FragmentNoticeBinding binding;

    private ReviewListAdapter adapter;
    private Context context;

    private User user;

    public NoticeFragment() {
        super();
        //quand je construit mon objet,j'appel la méthode du parent (classe)
    }

    public NoticeFragment(DetailsViewModel detailsViewModel) {
        this.detailsViewModel = detailsViewModel;
    }
    //*permet d'utiliser binding des éléments xml


    public static Fragment newInstance() {
        NoticeFragment fragment = new NoticeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViewModel();
    }

    private void setupViewModel() {
        detailsViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNoticeBinding.inflate(inflater, container, false); // Binds the layout using view binding.
        setupAdapter();
        setupUI();
        setupUser();
        createReview();
        return binding.getRoot(); // Returns the root view.
    }
// méthode permetant la MAJ UI des reviews
    private void setupUI() {
        detailsViewModel.getReviews().observe(getViewLifecycleOwner(),reviews -> {
            adapter.updateList(reviews);
        });

    }

    private void setupAdapter() {
        adapter = new ReviewListAdapter();
        binding.recyclerViewReviews.setLayoutManager(new LinearLayoutManager(getActivity()));
    binding.recyclerViewReviews.setAdapter(adapter);
    }

    // permet de recupérer le commentaire et note pour transmettre au view model (arguments ===> pour méthode addReview)
    private void createReview() {
        binding.userButton.setOnClickListener(view -> {
            String comment = Objects.requireNonNull(binding.userComment.getText()).toString();
            float rate = binding.userRatingBar.getRating();


            detailsViewModel.addReview(comment,(int)rate,user.getPicture(),user.getUsername());
            setupUI();
        });
    }

    @NonNull
    @Contract(" -> new")
    private Object reviewElement() {

        String commentUser = "";
        String nameUser="";
        int rateUser=0;
        String pictureUser="";
// utiliser la bibliotheque pour retourner url pictures et non image ressource
        // binding des elements de l user pour recuperer les éléments saisies
        binding.userComment.setText(commentUser);
       // binding.userPictureReview.setText(pictureUser);
        binding.userRatingBar.setRating(rateUser);
        binding.userName.setText(nameUser);
        if (rateUser==0){
            AlertDialog alert11 = getAlertDialogNoRate();
            alert11.show();
        }
        if (commentUser.isEmpty()){
            AlertDialog alert11 = getAlertDialogNoComment();
            alert11.show();
        }
        return new Review(commentUser,nameUser,pictureUser,rateUser);
        



    };
// boite de dialog si pas de note de saisie dans la rating bar
    private AlertDialog getAlertDialogNoRate() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage(R.string.alert_no_rate);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                (dialog, id) -> dialog.cancel());


        return builder1.create();
    }
    // boite de dialog si pas de commentaire de saisie
    private AlertDialog getAlertDialogNoComment() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage(R.string.alert_no_comment);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                (dialog, id) -> dialog.cancel());


        return builder1.create();
    }
    private void setupUser(){
        detailsViewModel.getUser().observe(getViewLifecycleOwner(),user -> {
            this.user = user;
            binding.userName.setText(user.getUsername());
            Glide.with(binding.userPictureReview.getContext())
                    .load(user.getPicture())
                    .into(binding.userPictureReview);
        });
        //permet d'aller cher les éléments au viewmodel


    }
}

