package com.openclassrooms.tajmahal.domain.model;

import java.util.Objects;

/**
 * Represents a user.
 * This class encapsulates all the details of a user, including the username of the reviewer and
 * their profile picture.
 */
public class User {

    /**
     * The name of the user.
     */
    private String username;

    /**
     * The profile picture of the user.
     */
    private String picture;

    /**
     * Constructs a new user.
     *
     * @param username the name of the user
     * @param picture  the profile picture URL or path of the user
     */
    public User(String username, String picture) {
        this.username = username;
        this.picture = picture;

    }

    /**
     * Returns the username of the user.
     *
     * @return a String representing the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the profile picture of the user.
     *
     * @return a String representing the picture's URL or path
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Generates a hash code for this review based on its fields.
     *
     * @return the generated hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(username, picture);
    }
}
