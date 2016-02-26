package com.group4.paladar.FireBaseStructures.user;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Surface on 2016-02-23.
 */
public class UserFirebase {

    private String provider;
    private String displayName;
    private String email;
    private String profileImageUrl;
    private Chef chef;

    private List<String> attendingIds;
    private List<String> hostingIds;

    public UserFirebase(){}

    public UserFirebase(String provider,
                        String displayName,
                        String email,
                        String profileImageUrl,
                        Boolean isChefEnabled,
                        String chefAlias,
                        String chefSummary,
                        String chefImage ,
                        List<String> attendingIds,
                        List<String> hostingIds) {


        this.provider = provider;
        this.displayName = displayName;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        chef = new Chef(isChefEnabled,chefAlias,chefSummary,chefImage);

        this.attendingIds = attendingIds;
        this.hostingIds = hostingIds;
    }



    public String getProvider() {
        return provider;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public Chef getChef() {
        return chef;
    }

    public List<String> getAttendingIds() {
        return attendingIds;
    }

    public List<String> getHostingIds() {
        return hostingIds;
    }

    public class Chef{
        Boolean isChefEnabled;
        String alias;
        String summary;
        String image;

        public Chef(){}

        public Chef(Boolean isChefEnabled,String alias, String summary, String image) {
            this.isChefEnabled = isChefEnabled;
            this.alias = alias;
            this.summary = summary;
            this.image = image;
        }

        public String getAlias() {
            return alias;
        }

        public String getSummary() {
            return summary;
        }

        public String getImage() {
            return image;
        }

        public Boolean getIsChefEnabled() {
            return isChefEnabled;
        }

        public void setIsChefEnabled(Boolean isChefEnabled) {
            this.isChefEnabled = isChefEnabled;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }



}
