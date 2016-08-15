package com.kvinzanekar.kvin.showtime.Model;


import java.io.Serializable;
import java.io.SerializablePermission;

/**
 * Created by kvin on 5/25/16.
 */
public class Movie implements Serializable {

    private static final long id = 1L;
    private String title;
    private String description;
    private String thumbnail;
    private String actor_details;
    private String releaseDate;
    private String genre;
    private String yurl;
    private String rating;

    public String getActor_details() {
        return actor_details;
    }

    public void setActor_details(String actor_details) {
        this.actor_details = actor_details;
    }

    public static long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    public String getYurl() {
        return yurl;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setYurl(String yurl) {
        this.yurl = yurl;
    }

}
