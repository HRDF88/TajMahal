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
        //
        RestaurantApi api = new RestaurantFakeApi();
        int reviewsCount = api.getReviews().size();
        assertEquals(6, reviewsCount);
    }
    @Test
    public void addReview() {
        // 6 +1 avec count, vérification de nombre de reviews après l'éxécution de la méthode addReview
        RestaurantApi api = new RestaurantFakeApi();
        api.addReview("Test",5,"https://www.fine-s.fr/9959/test.jpg","Jocelin Testing");
        int reviewsCountAdd = api.getReviews().size();
        assertEquals(7, reviewsCountAdd);

    }

    @Test
    public void addEmptyCommentReview() {
        //verifier avec comment null
        RestaurantApi api = new RestaurantFakeApi();
        api.addReview("",5,"https://www.fine-s.fr/9959/test.jpg","Jocelin Testing");
        int reviewCountAdd = api.getReviews().size();
        assertEquals(6, reviewCountAdd);

    }

    @Test
    public void addEmptyRatingReview() {
        // verifié avec rate<0
        RestaurantApi api = new RestaurantFakeApi();
        api.addReview("Test",-1,"https://www.fine-s.fr/9959/test.jpg","Jocelin Testing");
        int reviewCountAdd = api.getReviews().size();
        assertEquals(6,reviewCountAdd);


    }
}

