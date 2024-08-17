package com.openclassrooms.tajmahal.domain.model;

import java.util.Objects;


/**
 * Represents a user review.
 * This class encapsulates all the details of a review, including the username of the reviewer,
 * their profile picture, the comment they left, and the rating they gave.
 */
public class Review {

    /**
     * The name of the user who left the review.
     */
    private String username;

    /**
     * The profile picture of the user who left the review.
     */
    private String picture;

    /**
     * The comment or feedback given by the user.
     */
    private String comment;

    /**
     * The rating provided by the user. Typically out of 5 or 10.
     */
    private int rate;

    /**
     * Constructs a new Review instance.
     *
     * @param username the name of the user leaving the review
     * @param picture  the profile picture URL or path of the user
     * @param comment  the feedback or comment from the user
     * @param rate     the rating given by the user
     */
    public Review(String username, String picture, String comment, int rate) {
        this.username = username;
        this.picture = picture;
        this.comment = comment;
        this.rate = rate;
    }

    /**
     * Returns the username of the reviewer.
     *
     * @return a String representing the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets or updates the username of the reviewer.
     *
     * @param username the new username to be set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the profile picture of the reviewer.
     *
     * @return a String representing the picture's URL or path
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Sets or updates the profile picture of the reviewer.
     *
     * @param picture the new profile picture's URL or path to be set
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * Returns the comment left by the reviewer.
     *
     * @return a String containing the feedback or comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets or updates the comment left by the reviewer.
     *
     * @param comment the new comment or feedback to be set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Returns the rating given by the reviewer.
     *
     * @return an integer representing the rating value
     */
    public int getRate() {
        return rate;
    }

    /**
     * Sets or updates the rating given by the reviewer.
     *
     * @param rate the new rating value to be set
     */
    public void setRate(int rate) {
        this.rate = rate;
    }

    /**
     * Compares this review with another object for equality.
     * Two reviews are considered equal if all their fields are identical.
     *
     * @param o the object to be compared with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return rate == review.rate && Objects.equals(username, review.username) && Objects.equals(picture, review.picture) && Objects.equals(comment, review.comment);
    }

    /**
     * Generates a hash code for this review based on its fields.
     *
     * @return the generated hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(username, picture, comment, rate);
    }
}
