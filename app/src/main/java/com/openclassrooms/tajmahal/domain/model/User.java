package com.openclassrooms.tajmahal.domain.model;

import java.util.Objects;

public class User {


        private String username;


        private  String picture;



        public User(String username, String picture) {
           this.username = username;
            this.picture = picture;

        }

        public String getUsername() {
            return username;
        }


        public void setUsername(String username) {
            this.username = username;
        }

        public  String getPicture() {
            return picture;
        }


        public void setPicture(String picture) {
            this.picture = picture;
        }

        @Override
        public int hashCode() {
            return Objects.hash(username, picture);
        }
}
