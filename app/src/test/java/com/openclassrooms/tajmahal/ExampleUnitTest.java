package com.openclassrooms.tajmahal;

import org.junit.Test;

import static org.junit.Assert.*;

import com.openclassrooms.tajmahal.data.service.RestaurantApi;
import com.openclassrooms.tajmahal.data.service.RestaurantFakeApi;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    /**
     * Checks if the number of hardcoded reviews is equal to 6.
     */
    @Test
    public void countReview() {
        RestaurantApi api = new RestaurantFakeApi();
        int reviewsCount = api.getReviews().size();
        assertEquals(6, reviewsCount);
    }

    /**
     * Checks the number of a reviews after executing the addReview method
     */
    @Test
    public void addReview() {
        RestaurantApi api = new RestaurantFakeApi();
        api.addReview("Test", 5, "https://www.fine-s.fr/9959/test.jpg", "Jocelin Testing");
        int reviewsCountAdd = api.getReviews().size();
        assertEquals(7, reviewsCountAdd);

    }

    /**
     * Checks with a empty comment that the review is not added after executing the addReview method.
     */
    @Test
    public void addEmptyCommentReview() {
        RestaurantApi api = new RestaurantFakeApi();
        api.addReview("", 5, "https://www.fine-s.fr/9959/test.jpg", "Jocelin Testing");
        int reviewCountAdd = api.getReviews().size();
        assertEquals(6, reviewCountAdd);

    }

    /**
     * Checks with a negative rating that the review is not added after executing the addReview method.
     */
    @Test
    public void addEmptyRatingReview() {
        RestaurantApi api = new RestaurantFakeApi();
        api.addReview("Test", -1, "https://www.fine-s.fr/9959/test.jpg", "Jocelin Testing");
        int reviewCountAdd = api.getReviews().size();
        assertEquals(6, reviewCountAdd);


    }
}

