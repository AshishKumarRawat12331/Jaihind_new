package co.civilguruji.Jaihindlms.ApiRespose;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SetChannelVideo {



    @SerializedName("day")
    @Expose
    private String day;

     String cover;
    String course_id;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @SerializedName("price")
    @Expose
    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @SerializedName("parts")
    @Expose
    private ArrayList<PartsChannelVideo> parts = null;

    public ArrayList<PartsChannelVideo> getParts() {
        return parts;
    }

    public void setParts(ArrayList<PartsChannelVideo> parts) {
        this.parts = parts;
    }

    @SerializedName("pre_content")
    @Expose
    private String pre_content;


    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("category_id")
    @Expose
    private String category_id;


    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;


    public String getPre_content() {
        return pre_content;
    }

    public void setPre_content(String pre_content) {
        this.pre_content = pre_content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }



     @SerializedName("list_price")
    @Expose
    private String list_price;

     @SerializedName("final_price")
    @Expose
    private String final_price;

    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("support")
    @Expose
    private String support;
    @SerializedName("post")
    @Expose
    private String post;

    @SerializedName("posts_count")
    @Expose
    private String posts_count;

    public String getPosts_count() {
        return posts_count;
    }

    public void setPosts_count(String posts_count) {
        this.posts_count = posts_count;
    }

    @SerializedName("discount")
    @Expose
    private Object discount;



    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }



    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Object getDiscount() {
        return discount;
    }

    public void setDiscount(Object discount) {
        this.discount = discount;
    }

String upload_video;

    public String getUpload_video() {
        return upload_video;
    }

    public void setUpload_video(String upload_video) {
        this.upload_video = upload_video;
    }

    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("parent_id")
    @Expose
    private String parentId;


    @SerializedName("class")
    @Expose
    private String _class;

 @SerializedName("description")
    @Expose
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }


    @SerializedName("id")
    @Expose
    private String id;


    @SerializedName("title")
    @Expose
    private String title;


    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("viedo_link")
    @Expose
    private String viedoLink;
    @SerializedName("mode")
    @Expose
    private String mode;
    @SerializedName("content_id")
    @Expose
    private Object contentId;
    @SerializedName("chanel_id")
    @Expose
    private String chanelId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getViedoLink() {
        return viedoLink;
    }

    public void setViedoLink(String viedoLink) {
        this.viedoLink = viedoLink;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Object getContentId() {
        return contentId;
    }

    public void setContentId(Object contentId) {
        this.contentId = contentId;
    }

    public String getChanelId() {
        return chanelId;
    }

    public void setChanelId(String chanelId) {
        this.chanelId = chanelId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getList_price() {
        return list_price;
    }

    public void setList_price(String list_price) {
        this.list_price = list_price;
    }

    public String getFinal_price() {
        return final_price;
    }

    public void setFinal_price(String final_price) {
        this.final_price = final_price;
    }


/////////////////////////



    @SerializedName("metas")
    @Expose
    private ArrayList<MetasRespose> metas = null;

    public ArrayList<MetasRespose> getMetas() {
        return metas;
    }

    public void setMetas(ArrayList<MetasRespose> metas) {
        this.metas = metas;
    }




}
