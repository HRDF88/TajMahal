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

import dagger.hilt.android.AndroidEntryPoint;

/**
 * This class uses {@link com.openclassrooms.tajmahal.databinding.FragmentNoticeBinding} for data binding to its layout and
 * * {@link DetailsViewModel} to interact with data sources and manage UI-related data.
 */

@AndroidEntryPoint
public class NoticeFragment extends Fragment {
    private DetailsViewModel detailsViewModel;
    private @NonNull FragmentNoticeBinding binding;

    private ReviewListAdapter adapter;
    private User user;

    /**
     * To call the method of the immediate parent class.
     */
    public NoticeFragment() {
        super();
    }

    public static Fragment newInstance() {
        NoticeFragment fragment = new NoticeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;

    }

    /**
     * This method is called when the fragment is first created.
     * It's used to perform one-time initialization.
     *
     * @param savedInstanceState A bundle containing previously saved instance state.
     *                           If the fragment is being re-created from a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViewModel();
    }

    /**
     * Initializes the ViewModel for this activity.
     */
    private void setupViewModel() {
        detailsViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
    }

    /**
     * Creates and returns the view hierarchy associated with the fragment.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container          If non-null, this is the parent view that the fragment's UI should be attached to.
     *                           The fragment should not add the view itself but return it.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Returns the View for the fragment's UI, or null and call others methods for update UI.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNoticeBinding.inflate(inflater, container, false); // Binds the layout using view binding.
        setupAdapter();
        setupUI();
        setupUser();
        createReview();
        return binding.getRoot(); // Returns the root view.
    }

    /**
     * Sets up the UI-specific properties, {@link DetailsViewModel} for update reviews UI.
     */
    private void setupUI() {
        detailsViewModel.getReviews().observe(getViewLifecycleOwner(), reviews -> {
            adapter.updateList(reviews);
        });

    }

    /**
     * associates the adaptor with the recyclerview XML present on the fragment_notice layout.
     */
    private void setupAdapter() {
        adapter = new ReviewListAdapter();
        binding.recyclerViewReviews.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerViewReviews.setAdapter(adapter);
    }

    /**
     * allows you to retrieve the comment and note to transmit to the ViewModel.
     * (arguments for addReview method).
     */
    private void createReview() {
        binding.userButton.setOnClickListener(view -> {
            String comment = Objects.requireNonNull(binding.userComment.getText()).toString();
            float rate = binding.userRatingBar.getRating();
            detailsViewModel.addReview(comment, (int) rate, user.getPicture(), user.getUsername());
            setupUI();
        });
    }


    /**
     * Get elements in the viewmodel for User UI.
     */
    private void setupUser() {
        detailsViewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            this.user = user;
            binding.userName.setText(user.getUsername());
            Glide.with(binding.userPictureReview.getContext())
                    .load(user.getPicture())
                    .into(binding.userPictureReview);
        });


    }
}

