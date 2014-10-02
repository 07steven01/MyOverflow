package org.janusz.steven.myoverflow.stackoverflow.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by marek on 03.10.14.
 */
public class Owner  {
    private long reputation;
    @SerializedName("user_id") private long userId;
    @SerializedName("user_type") private String userType;
    @SerializedName("accept_rate") private long acceptRate;
    @SerializedName("profile_image") private String profileImageURL;
    @SerializedName("display_name") private String displayName;
    private String link;

    public Owner() {}

    public long getReputation() {
        return reputation;
    }

    public void setReputation(long reputation) {
        this.reputation = reputation;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public long getAcceptRate() {
        return acceptRate;
    }

    public void setAcceptRate(long acceptRate) {
        this.acceptRate = acceptRate;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
