package co.civilguruji.Jaihindlms.ApiRespose;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Setcontents {

    @SerializedName("metas")
    @Expose
    private ArrayList<MetasRespose> metas = null;

    public ArrayList<MetasRespose> getMetas() {
        return metas;
    }

    public void setMetas(ArrayList<MetasRespose> metas) {
        this.metas = metas;
    }

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("course_tagline")
    @Expose
    private Object courseTagline;
    @SerializedName("how_to_use")
    @Expose
    private Object howToUse;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("content_type")
    @Expose
    private Object contentType;
    @SerializedName("support")
    @Expose
    private Object support;
    @SerializedName("document")
    @Expose
    private Object document;
    @SerializedName("post")
    @Expose
    private Object post;
    @SerializedName("price")
    @Expose
    private Object price;
    @SerializedName("price_post")
    @Expose
    private Object pricePost;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("mode")
    @Expose
    private String mode;
    @SerializedName("private")
    @Expose
    private Integer _private;
    @SerializedName("request")
    @Expose
    private Object request;
    @SerializedName("prerequisite")
    @Expose
    private Object prerequisite;
    @SerializedName("tag")
    @Expose
    private Object tag;
    @SerializedName("view")
    @Expose
    private Integer view;
    @SerializedName("support_rate")
    @Expose
    private Integer supportRate;
    @SerializedName("download")
    @Expose
    private Integer download;
    @SerializedName("price_3")
    @Expose
    private Object price3;
    @SerializedName("price_6")
    @Expose
    private Object price6;
    @SerializedName("price_9")
    @Expose
    private Object price9;
    @SerializedName("price_12")
    @Expose
    private Object price12;
    @SerializedName("subscribe_3")
    @Expose
    private Object subscribe3;
    @SerializedName("subscribe_6")
    @Expose
    private Object subscribe6;
    @SerializedName("subscribe_9")
    @Expose
    private Object subscribe9;
    @SerializedName("subscribe_12")
    @Expose
    private Object subscribe12;
    @SerializedName("created_at")
    @Expose
    private Integer createdAt;
    @SerializedName("updated_at")
    @Expose
    private Integer updatedAt;
    @SerializedName("meeting_type")
    @Expose
    private Object meetingType;
    @SerializedName("meeting_join_url")
    @Expose
    private Object meetingJoinUrl;
    @SerializedName("meeting_start_url")
    @Expose
    private Object meetingStartUrl;
    @SerializedName("meeting_mode")
    @Expose
    private Object meetingMode;
    @SerializedName("meeting_password")
    @Expose
    private Object meetingPassword;
    @SerializedName("meta_description")
    @Expose
    private Object metaDescription;
    @SerializedName("meta_keyword")
    @Expose
    private Object metaKeyword;
    @SerializedName("discount")
    @Expose
    private Object discount;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Object getCourseTagline() {
        return courseTagline;
    }

    public void setCourseTagline(Object courseTagline) {
        this.courseTagline = courseTagline;
    }

    public Object getHowToUse() {
        return howToUse;
    }

    public void setHowToUse(Object howToUse) {
        this.howToUse = howToUse;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getContentType() {
        return contentType;
    }

    public void setContentType(Object contentType) {
        this.contentType = contentType;
    }

    public Object getSupport() {
        return support;
    }

    public void setSupport(Object support) {
        this.support = support;
    }

    public Object getDocument() {
        return document;
    }

    public void setDocument(Object document) {
        this.document = document;
    }

    public Object getPost() {
        return post;
    }

    public void setPost(Object post) {
        this.post = post;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public Object getPricePost() {
        return pricePost;
    }

    public void setPricePost(Object pricePost) {
        this.pricePost = pricePost;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Integer getPrivate() {
        return _private;
    }

    public void setPrivate(Integer _private) {
        this._private = _private;
    }

    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    public Object getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(Object prerequisite) {
        this.prerequisite = prerequisite;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public Integer getSupportRate() {
        return supportRate;
    }

    public void setSupportRate(Integer supportRate) {
        this.supportRate = supportRate;
    }

    public Integer getDownload() {
        return download;
    }

    public void setDownload(Integer download) {
        this.download = download;
    }

    public Object getPrice3() {
        return price3;
    }

    public void setPrice3(Object price3) {
        this.price3 = price3;
    }

    public Object getPrice6() {
        return price6;
    }

    public void setPrice6(Object price6) {
        this.price6 = price6;
    }

    public Object getPrice9() {
        return price9;
    }

    public void setPrice9(Object price9) {
        this.price9 = price9;
    }

    public Object getPrice12() {
        return price12;
    }

    public void setPrice12(Object price12) {
        this.price12 = price12;
    }

    public Object getSubscribe3() {
        return subscribe3;
    }

    public void setSubscribe3(Object subscribe3) {
        this.subscribe3 = subscribe3;
    }

    public Object getSubscribe6() {
        return subscribe6;
    }

    public void setSubscribe6(Object subscribe6) {
        this.subscribe6 = subscribe6;
    }

    public Object getSubscribe9() {
        return subscribe9;
    }

    public void setSubscribe9(Object subscribe9) {
        this.subscribe9 = subscribe9;
    }

    public Object getSubscribe12() {
        return subscribe12;
    }

    public void setSubscribe12(Object subscribe12) {
        this.subscribe12 = subscribe12;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Integer updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(Object meetingType) {
        this.meetingType = meetingType;
    }

    public Object getMeetingJoinUrl() {
        return meetingJoinUrl;
    }

    public void setMeetingJoinUrl(Object meetingJoinUrl) {
        this.meetingJoinUrl = meetingJoinUrl;
    }

    public Object getMeetingStartUrl() {
        return meetingStartUrl;
    }

    public void setMeetingStartUrl(Object meetingStartUrl) {
        this.meetingStartUrl = meetingStartUrl;
    }

    public Object getMeetingMode() {
        return meetingMode;
    }

    public void setMeetingMode(Object meetingMode) {
        this.meetingMode = meetingMode;
    }

    public Object getMeetingPassword() {
        return meetingPassword;
    }

    public void setMeetingPassword(Object meetingPassword) {
        this.meetingPassword = meetingPassword;
    }

    public Object getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(Object metaDescription) {
        this.metaDescription = metaDescription;
    }

    public Object getMetaKeyword() {
        return metaKeyword;
    }

    public void setMetaKeyword(Object metaKeyword) {
        this.metaKeyword = metaKeyword;
    }

    public Object getDiscount() {
        return discount;
    }

    public void setDiscount(Object discount) {
        this.discount = discount;
    }


}
