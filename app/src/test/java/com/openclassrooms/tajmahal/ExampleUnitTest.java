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

    @Test
    public void countReview() {
        RestaurantApi api = new RestaurantFakeApi();
        int reviewsCount = api.getReviews().size();
        assertEquals(6, reviewsCount);
    }
    @Test
    public void addReview() {
// 6 +1 avec count

    }

    @Test
    public void addEmptyCommentReview() {
//verifier avec comment null

    }

    @Test
    public void addEmptyRatingReview() {

// verifié avec rate<0
    }
}

