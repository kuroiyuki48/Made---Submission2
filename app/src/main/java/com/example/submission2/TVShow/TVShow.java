package com.example.submission2.TVShow;

import android.os.Parcel;
import android.os.Parcelable;

public class TVShow implements Parcelable {
    private String image, name, release, score, genre, overview, photo, trailer;

    protected TVShow(Parcel in) {
        image = in.readString();
        name = in.readString();
        release = in.readString();
        score = in.readString();
        genre = in.readString();
        overview = in.readString();
        photo = in.readString();
        trailer = in.readString();
    }

    public static final Creator<TVShow> CREATOR = new Creator<TVShow>() {
        @Override
        public TVShow createFromParcel(Parcel in) {
            return new TVShow(in);
        }

        @Override
        public TVShow[] newArray(int size) {
            return new TVShow[size];
        }
    };

    public TVShow() {

    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getRelease() {
        return release;
    }
    public void setRelease(String release) {
        this.release = release;
    }

    public String getScore() {
        return score;
    }
    public void setScore(String score) {
        this.score = score;
    }

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getOverview() {
        return overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTrailer() {
        return trailer;
    }
    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.image);
        parcel.writeString(this.name);
        parcel.writeString(this.release);
        parcel.writeString(this.score);
        parcel.writeString(this.genre);
        parcel.writeString(this.overview);
        parcel.writeString(this.photo);
        parcel.writeString(this.trailer);
    }
}
