package co.civilguruji.Jaihindlms.ApiRespose;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataLogin {

    String msg;
     String status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    ArrayList<Showchannels>channels;

    public ArrayList<Showchannels> getChannels() {
        return channels;
    }

    public void setChannels(ArrayList<Showchannels> channels) {
        this.channels = channels;
    }

    @SerializedName("user")
    @Expose
    private DataUser user;


    public DataUser getUser() {
        return user;
    }

    public void setUser(DataUser user) {
        this.user = user;
    }



    ///////////////////////////




    @SerializedName("id")
    @Expose
    private Integer id;


    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("description")
    @Expose
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("bio")
    @Expose
    private Object bio;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("follower")
    @Expose
    private Integer follower;
    @SerializedName("follow")
    @Expose
    private Integer follow;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("courses")
    @Expose
    private Integer courses;
    @SerializedName("rates")
    @Expose
    private ArrayList<RateVale> rates = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getBio() {
        return bio;
    }

    public void setBio(Object bio) {
        this.bio = bio;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getFollower() {
        return follower;
    }

    public void setFollower(Integer follower) {
        this.follower = follower;
    }

    public Integer getFollow() {
        return follow;
    }

    public void setFollow(Integer follow) {
        this.follow = follow;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getCourses() {
        return courses;
    }

    public void setCourses(Integer courses) {
        this.courses = courses;
    }

    public ArrayList<RateVale> getRates() {
        return rates;
    }

    public void setRates(ArrayList<RateVale> rates) {
        this.rates = rates;
    }


}
