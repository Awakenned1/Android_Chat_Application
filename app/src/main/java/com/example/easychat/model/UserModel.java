package com.example.easychat.model;

import com.google.firebase.Timestamp;


    public class UserModel {
        private String userId;
        private String userName;
        private String profilePicUrl;
        private String email;
        private Timestamp createdTimestamp;

        public UserModel() {

        }
        public UserModel(String userId, String userName, String profilePicUrl,Timestamp createdTimestamp) {
            this.userId = userId;
            this.userName = userName;
            this.email = email;
            this.createdTimestamp = createdTimestamp;
            this.profilePicUrl = profilePicUrl;
        }



        public Timestamp getCreatedTimestamp() {
            return createdTimestamp;
        }
        public void setCreatedTimestamp(Timestamp createdTimestamp) {
            this.createdTimestamp = createdTimestamp;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getProfilePicUrl() {
            return profilePicUrl;
        }


        public void setProfilePicUrl(String profilePicUrl) {
            this.profilePicUrl = profilePicUrl;
        }



        public void setEmail(String email) {
            this.email = email;
        }

        public String getEmail() {
            return email;
        }
    }
