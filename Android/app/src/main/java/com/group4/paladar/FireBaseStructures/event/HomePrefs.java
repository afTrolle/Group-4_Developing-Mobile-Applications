package com.group4.paladar.FireBaseStructures.event;

/**
 * Created by Surface on 2016-03-07.
 */
public class HomePrefs {

    //stuff allowed to bring
    private Boolean isDogsAllowed = false;
    private Boolean isCatsAllowed = false;
    private Boolean isChildrenAllowed = false;
    private Boolean isAlcholAllowed = false;

    //stuff on Location
    private Boolean isChildrenOnLocation = false;
    private Boolean isDogsOnLocation = false;
    private Boolean isCatsOnLocation = false;

    public HomePrefs() {

    }

    public Boolean getIsDogsAllowed() {
        return isDogsAllowed;
    }

    public void setIsDogsAllowed(Boolean isDogsAllowed) {
        this.isDogsAllowed = isDogsAllowed;
    }

    public Boolean getIsCatsAllowed() {
        return isCatsAllowed;
    }

    public void setIsCatsAllowed(Boolean isCatsAllowed) {
        this.isCatsAllowed = isCatsAllowed;
    }

    public Boolean getIsChildrenAllowed() {
        return isChildrenAllowed;
    }

    public void setIsChildrenAllowed(Boolean isChildrenAllowed) {
        this.isChildrenAllowed = isChildrenAllowed;
    }

    public Boolean getIsAlcholAllowed() {
        return isAlcholAllowed;
    }

    public void setIsAlcholAllowed(Boolean isAlcholAllowed) {
        this.isAlcholAllowed = isAlcholAllowed;
    }

    public Boolean getIsChildrenOnLocation() {
        return isChildrenOnLocation;
    }

    public void setIsChildrenOnLocation(Boolean isChildrenOnLocation) {
        this.isChildrenOnLocation = isChildrenOnLocation;
    }

    public Boolean getIsDogsOnLocation() {
        return isDogsOnLocation;
    }

    public void setIsDogsOnLocation(Boolean isDogsOnLocation) {
        this.isDogsOnLocation = isDogsOnLocation;
    }

    public Boolean getIsCatsOnLocation() {
        return isCatsOnLocation;
    }

    public void setIsCatsOnLocation(Boolean isCatsOnLocation) {
        this.isCatsOnLocation = isCatsOnLocation;
    }

}
