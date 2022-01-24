package co.civilguruji.Jaihindlms.Utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import co.civilguruji.Jaihindlms.ApiRespose.PartsChannelVideo;

public class Channelsub {

    String content;
    String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("support")
    @Expose
    private Object support;
    @SerializedName("post")
    @Expose
    private Object post;
    @SerializedName("discount")
    @Expose
    private Object discount;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("price")
    @Expose
    private Integer price;


    @SerializedName("parts")
    @Expose
    private ArrayList<PartsChannelVideo> parts = null;


    @SerializedName("contents_meta")
    @Expose
    private ArrayList<PartsChannelVideo> contents_meta = null;

    public ArrayList<PartsChannelVideo> getContents_meta() {
        return contents_meta;
    }

    public void setContents_meta(ArrayList<PartsChannelVideo> contents_meta) {
        this.contents_meta = contents_meta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Object getSupport() {
        return support;
    }

    public void setSupport(Object support) {
        this.support = support;
    }

    public Object getPost() {
        return post;
    }

    public void setPost(Object post) {
        this.post = post;
    }

    public Object getDiscount() {
        return discount;
    }

    public void setDiscount(Object discount) {
        this.discount = discount;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public ArrayList<PartsChannelVideo> getParts() {
        return parts;
    }

    public void setParts(ArrayList<PartsChannelVideo> parts) {
        this.parts = parts;
    }

}
