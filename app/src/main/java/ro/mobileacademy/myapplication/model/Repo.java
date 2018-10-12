package ro.mobileacademy.myapplication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by andrei on 06/10/2018.
 * 2018
 */
public class Repo {

    private String name;

    private String description;

    @SerializedName("full_name")
    private String fullName;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getFullName() {
        return fullName;
    }
}
