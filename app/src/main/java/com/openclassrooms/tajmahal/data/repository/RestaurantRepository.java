package com.openclassrooms.tajmahal.data.repository;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.openclassrooms.tajmahal.data.service.RestaurantApi;
import com.openclassrooms.tajmahal.domain.model.Restaurant;
import com.openclassrooms.tajmahal.domain.model.Review;
import com.openclassrooms.tajmahal.domain.model.User;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * This is the repository class for managing restaurant data. Repositories are responsible
 * for coordinating data operations from data sources such as network APIs, databases, etc.
 *
 * Typically in an Android app built with architecture components, the repository will handle
 * the logic for deciding whether to fetch data from a network source or use data from a local cache.
 *
 *
 * @see Restaurant
 * @see RestaurantApi
 */
@Singleton
public class RestaurantRepository {

    // The API interface instance that will be used for network requests related to restaurant data.
    private final RestaurantApi restaurantApi;

    /**
     * Constructs a new instance of {@link RestaurantRepository} with the given {@link RestaurantApi}.
     *
     * @param restaurantApi The network API interface for fetching restaurant data.
     */
    @Inject
    public RestaurantRepository(RestaurantApi restaurantApi) {
        this.restaurantApi = restaurantApi;
    }

    /**
     * Fetches the restaurant details.
     *
     * This method will make a network call using the provided {@link RestaurantApi} instance
     * to fetch restaurant data. Note that error handling and any transformations on the data
     * would need to be managed.
     *
     *
     * @return LiveData holding the restaurant details.
     */
    public LiveData<Restaurant> getRestaurant() {
        return new MutableLiveData<>(restaurantApi.getRestaurant());
    }
// mutableLiveData car on peux faire un set values car juste observe sur un LiveData
    public LiveData<List<Review>> getReviews() {
        return new MutableLiveData<>(restaurantApi.getReviews());
//ne pas attendre le résultat d"un service (API, base de données)...
    }

// récupération de l'user et de la méthode addReview de l'API
    public LiveData<User> getUser() {
        return new MutableLiveData<>(restaurantApi.getUser());
    }

    public void addReview(String comment, Integer rate, String picture, String userName){
        restaurantApi.addReview(comment,rate,picture,userName);
    }
}
