package com.openclassrooms.tajmahal.ui.restaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.openclassrooms.tajmahal.databinding.FragmentDetailsBinding;
import com.openclassrooms.tajmahal.databinding.FragmentNoticeBinding;

public class NoticeFragment extends Fragment {
    private DetailsViewModel detailsViewModel;
    private @NonNull FragmentNoticeBinding binding;

    private ReviewListAdapter adapter;

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
        return binding.getRoot(); // Returns the root view.
    }

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


}

