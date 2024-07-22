package com.openclassrooms.tajmahal.domain.model;

import java.util.Objects;

public class User {

        /** The name of the user who left the review. */
        private static String username;

        /** The profile picture of the user who left the review. */
        private static String picture;

        /** The comment or feedback given by the user. */


        /**
         * Constructs a new Review instance.
         *
         * @param username the name of the user leaving the review
         * @param picture  the profile picture URL or path of the user
         */
        public User(String username, String picture) {
            this.username = username;
            this.picture = picture;

        }



    /**
         * Returns the username of the reviewer.
         *
         * @return a String representing the username
         */
        public static String getUsername() {
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
        public static String getPicture() {
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



        @Override
        public int hashCode() {
            return Objects.hash(username, picture);
        }
}
