package com.openclassrooms.tajmahal.ui.restaurant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.openclassrooms.tajmahal.R;
import com.openclassrooms.tajmahal.data.repository.RestaurantRepository;
import com.openclassrooms.tajmahal.data.service.RestaurantApi;
import com.openclassrooms.tajmahal.data.service.RestaurantFakeApi;
import com.openclassrooms.tajmahal.domain.model.Restaurant;
import com.openclassrooms.tajmahal.domain.model.Review;
import com.openclassrooms.tajmahal.domain.model.User;

import javax.inject.Inject;

import java.util.Calendar;
import java.util.List;

import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * MainViewModel is responsible for preparing and managing the data for the {@link DetailsFragment}.
 * It communicates with the {@link RestaurantRepository} to fetch restaurant details and provides
 * utility methods related to the restaurant UI.
 * <p>
 * This ViewModel is integrated with Hilt for dependency injection.
 */
@HiltViewModel
public class DetailsViewModel extends ViewModel {

    private RestaurantRepository restaurantRepository;

    /**
     * Constructor that Hilt will use to create an instance of MainViewModel.
     *
     * @param restaurantRepository The repository which will provide restaurant data.
     */
    @Inject
    public DetailsViewModel(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * Fetches the details of the Taj Mahal restaurant.
     *
     * @return LiveData object containing the details of the Taj Mahal restaurant.
     */
    public LiveData<Restaurant> getTajMahalRestaurant() {
        return restaurantRepository.getRestaurant();
    }

    /**
     * Retrieves the current day of the week in French.
     *
     * @return A string representing the current day of the week in French.
     */
    public String getCurrentDay(Context context) {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String dayString;

        switch (dayOfWeek) {
            case Calendar.MONDAY:
                dayString = context.getString(R.string.monday);
                break;
            case Calendar.TUESDAY:
                dayString = context.getString(R.string.tuesday);
                break;
            case Calendar.WEDNESDAY:
                dayString = context.getString(R.string.wednesday);
                break;
            case Calendar.THURSDAY:
                dayString = context.getString(R.string.thursday);
                break;
            case Calendar.FRIDAY:
                dayString = context.getString(R.string.friday);
                break;
            case Calendar.SATURDAY:
                dayString = context.getString(R.string.saturday);
                break;
            case Calendar.SUNDAY:
                dayString = context.getString(R.string.sunday);
                break;
            default:
                dayString = "";
        }
        return dayString;
    }

    /**
     * Fetches the list of the reviews.
     *
     * @return LiveData object containing the list of reviews.
     */
    public LiveData<List<Review>> getReviews() {
        return restaurantRepository.getReviews();
    }

    /**
     * Fetches the details of the user
     *
     * @return LiveData object containing th details of the user.
     */
    public LiveData<User> getUser() {
        return restaurantRepository.getUser();
    }

    /**
     * Sending parameters for a new review
     * <p>
     * This method will make a network call using the provided {@link RestaurantRepository} instance
     * to add a new review.
     *
     * @param comment  String, user comment for a new review.
     * @param rate     Integer, user rate for a new review.
     * @param picture  String, user URL profile Picture for a new review.
     * @param userName String, username for a new review.
     */
    public void addReview(String comment, Integer rate, String picture, String userName) {
        restaurantRepository.addReview(comment, rate, picture, userName);
    }


}


