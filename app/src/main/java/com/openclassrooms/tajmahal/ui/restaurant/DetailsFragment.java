package com.openclassrooms.tajmahal.ui.restaurant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.openclassrooms.tajmahal.R;
import com.openclassrooms.tajmahal.databinding.FragmentDetailsBinding;
import com.openclassrooms.tajmahal.domain.model.Restaurant;
import com.openclassrooms.tajmahal.domain.model.Review;
import com.openclassrooms.tajmahal.domain.model.User;


import dagger.hilt.android.AndroidEntryPoint;

/**
 * DetailsFragment is the entry point of the application and serves as the primary UI.
 * It displays details about a restaurant and provides functionality to open its location
 * in a map, call its phone number, or view its website.
 * <p>
 * This class uses {@link FragmentDetailsBinding} for data binding to its layout and
 * {@link DetailsViewModel} to interact with data sources and manage UI-related data.
 */
@AndroidEntryPoint
public class DetailsFragment extends Fragment {

    private FragmentDetailsBinding binding;

    private DetailsViewModel detailsViewModel;

    /**
     * This method is called when the fragment is first created.
     * It's used to perform one-time initialization.
     *
     * @param savedInstanceState A bundle containing previously saved instance state.
     * If the fragment is being re-created from a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * This method is called immediately after `onCreateView()`.
     * Use this method to perform final initialization once the fragment views have been inflated.
     *
     * @param view The View returned by `onCreateView()`.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupUI(); // Sets up user interface components.
        setupViewModel(); // Prepares the ViewModel for the fragment.
        detailsViewModel.getTajMahalRestaurant().observe(requireActivity(), this::updateUIWithRestaurant); // Observes changes in the restaurant data and updates the UI accordingly.
    }

    /**
     * Creates and returns the view hierarchy associated with the fragment.
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container If non-null, this is the parent view that the fragment's UI should be attached to.
     * The fragment should not add the view itself but return it.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     * @return Returns the View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false); // Binds the layout using view binding.
        return binding.getRoot(); // Returns the root view.
    }


    /**
     * Sets up the UI-specific properties, such as system UI flags and status bar color.
     */
    private void setupUI() {
        Window window = requireActivity().getWindow();
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    /**
     * Initializes the ViewModel for this activity.
     */
    private void setupViewModel() {
        detailsViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
    }

    /**
     * Updates the UI components with the provided restaurant data.
     *
     * @param restaurant The restaurant object containing details to be displayed.
     */
    @SuppressLint("SetTextI18n")
    private void updateUIWithRestaurant(Restaurant restaurant) {
        if (restaurant == null) return;

        binding.tvRestaurantName.setText(restaurant.getName());
        binding.tvRestaurantDay.setText(detailsViewModel.getCurrentDay(requireContext()));
        binding.tvRestaurantType.setText(String.format("%s %s", getString(R.string.restaurant), restaurant.getType()));
        binding.tvRestaurantHours.setText(restaurant.getHours());
        binding.tvRestaurantAddress.setText(restaurant.getAddress());
        binding.tvRestaurantWebsite.setText(restaurant.getWebsite());
        binding.tvRestaurantPhoneNumber.setText(restaurant.getPhoneNumber());
        binding.chipOnPremise.setVisibility(restaurant.isDineIn() ? View.VISIBLE : View.GONE);
        binding.chipTakeAway.setVisibility(restaurant.isTakeAway() ? View.VISIBLE : View.GONE);

        binding.buttonAdress.setOnClickListener(v -> openMap(restaurant.getAddress()));
        binding.buttonPhone.setOnClickListener(v -> dialPhoneNumber(restaurant.getPhoneNumber()));
        binding.buttonWebsite.setOnClickListener(v -> openBrowser(restaurant.getWebsite()));
        detailsViewModel.getReviews().observe(this, reviews->{
            int countReviews = reviews.size();
            int countRateOneStar = 0;
            int countRateTwoStar = 0;
            int countRateThreeStar =0;
            int countRateFourStar = 0;
            int countRateFiveStar = 0;
            for (Review review:reviews){
                if (review.getRate() == 5) {
                    countRateFiveStar++;
                }
                else if (review.getRate() == 4) {
                    countRateFourStar++;
                }
                else if (review.getRate()==3) {
                    countRateThreeStar++;
                } else if (review.getRate()==2) {
                    countRateTwoStar++;
                }
                else countRateOneStar++;
            }

            //1 etape : recup taille total du nb de reviews,
            //2 etape initialiser 5 compteur à 0 (int)
            //je boucle sur les reviews, pour chaque reviews faire incrementation ++

            binding.progressBar5.setProgress(countRateFiveStar);
            binding.progressBar5.setMax(countReviews);
            binding.progressBar4.setProgress(countRateFourStar);
            binding.progressBar4.setMax(countReviews);
            binding.progressBar3.setProgress(countRateThreeStar);
            binding.progressBar3.setMax(countReviews);
            binding.progressBar2.setProgress(countRateTwoStar);
            binding.progressBar2.setMax(countReviews);
            binding.progressBar.setProgress(countRateOneStar);
            binding.progressBar.setMax(countReviews);
            //met à jour la progression des progressBar + les valeurs Max de celles-ci

            float rateAverage=((float) ((countRateOneStar) + (countRateTwoStar * 2) + (countRateThreeStar * 3) + (countRateFourStar * 4) + (countRateFiveStar * 5)) /countReviews);
            binding.toiles.setRating(rateAverage);//changement pour setRating de façon à utiliser un float, binding.toiles.setMax(5) a utiliser si int
            //calcul de la moyenne des notes plus affichage de celle-ci sur la ratingBar
            binding.someId.setText(""+rateAverage+"");
            //affichage de la moyenne des notes dans le textView en dessous de la ratingBar
            binding.averageRate.setText("("+countReviews+")");
            //affichage du nombre de reviews dans le textView countReview

        });

        binding.goReview.setOnClickListener(view -> {
            changerFragment();
        });

    }
//* permet de naviguer entre les fragment grace a la methode changer fragment crée ci-dessous
    private void changerFragment() {
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, NoticeFragment.newInstance())
                .addToBackStack("Retour")
                .commit();
    }

    /**
     * Opens the provided address in Google Maps or shows an error if Google Maps
     * is not installed.
     *
     * @param address The address to be shown in Google Maps.
     */
    private void openMap(String address) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(address));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Toast.makeText(requireActivity(), R.string.maps_not_installed, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Dials the provided phone number or shows an error if there's no dialing application
     * installed.
     *
     * @param phoneNumber The phone number to be dialed.
     */
    private void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(requireActivity(), R.string.phone_not_found, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Opens the provided website URL in a browser or shows an error if there's no
     * browser installed.
     *
     * @param websiteUrl The URL of the website to be opened.
     */
    private void openBrowser(String websiteUrl) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl));
        if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(requireActivity(), R.string.no_browser_found, Toast.LENGTH_SHORT).show();
        }
    }

    public static DetailsFragment newInstance() {
        return new DetailsFragment();
    }

}