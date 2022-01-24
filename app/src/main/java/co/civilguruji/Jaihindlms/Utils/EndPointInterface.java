package co.civilguruji.Jaihindlms.Utils;

import co.civilguruji.Jaihindlms.ApiRespose.ChannelVideoResponse;
import co.civilguruji.Jaihindlms.ApiRespose.LoginResponse;
import co.civilguruji.Jaihindlms.Banner.VideoGalleryResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EndPointInterface {

    @GET("/api/v1/sliders")
    Call<VideoGalleryResponse> Banner();

    @FormUrlEncoded
    @POST("/api/v1/user/login")
    Call<LoginResponse> secureLogin(@Field("username") String email,
                                    @Field("password") String password,
                                    @Field("collage_id") String collage_id);


    //  buyer_id  content_id   user_id  type   content_type


    @FormUrlEncoded
    @POST("/api/v1/product-get-free")
    Call<LoginResponse> productgetfree(@Field("buyer_id") String buyer_id,
                                    @Field("content_id") String content_id,
                                    @Field("user_id") String user_id,
                                    @Field("type") String type,
                                    @Field("content_type") String content_type);

    @FormUrlEncoded
    @POST("/api/v1/product-get-paid")
    Call<LoginResponse> productpaid(@Field("buyer_id") String buyer_id,
                                    @Field("user_id") String user_id,
                                    @Field("content_id") String content_id,
                                    @Field("type") String type,
                                    @Field("price") String price,
                                    @Field("content_type") String content_type,
                                    @Field("transaction_id") String transaction_id);


    @FormUrlEncoded
    @POST("/api/v1/user/content-user-buy-check")
    Call<LoginResponse> BuyCheck(@Field("user_id") String user_id,
                                     @Field("content_id") String content_id);



    @FormUrlEncoded
    @POST("/api/v1/user/change_password")
    Call<LoginResponse> change_password(@Field("user_id") String user_id,
                                    @Field("old_password") String old_password,
                                    @Field("new_password") String new_password);

    @FormUrlEncoded
    @POST("/api/v1/user/profile")
    Call<LoginResponse> myprofile(@Field("id") String id);

   /* @Multipart
    @POST("/api/v1/user/register")
    Call<LoginResponse> signup(@Part("name") RequestBody name ,
                               @Part("username")  RequestBody username ,
                               @Part("email")  RequestBody email,
                               @Part("password")  RequestBody password,
                                @Part("re_password")  RequestBody re_password );*/

    @FormUrlEncoded
    @POST("/api/v1/user/register")
    Call<LoginResponse> signup(@Field("name") String name ,
                               @Field("username")  String username ,
                               @Field("email")  String email,
                               @Field("password")  String password,
                                @Field("re_password")  String re_password ,
                                @Field("collage_id")  String collage_id ,
                                @Field("contact")  String contact );


    /*name:Student
email:student@gmail.com
contact:999999999
collage_id:4
id:3*/

      @FormUrlEncoded
    @POST("/api/v1/user/ProfileUpdate")
    Call<LoginResponse> Editprofile(@Field("name") String name ,
                               @Field("email")  String email ,
                               @Field("contact")  String contact,
                               @Field("collage_id")  String collage_id,
                                    @Field("id")  String id );






    @FormUrlEncoded
    @POST("/api/v1/user/social_login")
    Call<LoginResponse> sociallogin(@Field("email") String email ,
                               @Field("type")  String type ,
                               @Field("name")  String name ,
                               @Field("collage_id")  String collage_id );


    /*collage_id:4
user_id:3
name:student
message:message*/


    @FormUrlEncoded
    @POST("/api/v1/user/contact")
    Call<LoginResponse> contactUs(@Field("collage_id") String collage_id ,
                               @Field("user_id")  String user_id ,
                               @Field("name")  String name ,
                               @Field("message")  String message );

    @FormUrlEncoded
    @POST("/api/v1/user/generate-token")
    Call<LoginResponse>notification(@Field("collage_id") String collage_id,
                                    @Field("user_id")  String user_id ,
                                    @Field("token_id")  String token_id );

    @FormUrlEncoded
    @POST("/api/v1/user/forget_password")
    Call<LoginResponse> ForgetPassword(@Field("email") String email);

  /*  email:abhinictjsr@gmail.com
    type:facebook_login
    name:Abhishek Kumar*/

    @GET("/api/v1/user/channel")
    Call<LoginResponse> channel();

    @GET("/api/v1/content")
    Call<ChannelVideoResponse> contentAllCourse();

    @GET("/api/v1/cat")
    Call<ChannelVideoResponse> Course_cat();

    @GET("/api/v1/getpackage")
    Call<PackageRespose> getpackage(@Query("id")  String category);

    @FormUrlEncoded
    @POST("/api/v1/package-course")
    Call<ChannelVideoResponse> packagecourse(@Field("course_id")  String course_id);

    @GET("/api/v1/user/content-user-buy")
    Call<ChannelVideoResponse> contentuserbuy(@Query("user_id")  String user_id,
                                              @Query("content_type")  String content_type);

    @FormUrlEncoded
    @POST("/api/v1/user/productComment")
    Call<LoginResponse> productComment(@Field("user_id")  String user_id,
                                              @Field("comment")  String comment,
                                              @Field("name")  String name,
                                              @Field("email")  String email,
                                              @Field("content_id")  String content_id,
                                              @Field("type")  String type);

    /*user_id:3
comment:Message
name:student
email:student@gmail.com
content_id:3*/


    @GET("/api/v1/user/package-user-buy")
    Call<ChannelVideoResponse> packageuserbuy(@Query("user_id")  String user_id,
                                              @Query("content_type")  String content_type,
                                              @Query("buyer_id")  String buyer_id);

    @GET("/api/v1/blog/posts")
    Call<ChannelVideoResponse> Blogposts();

    @GET("/api/v1/blog/postsById")
    Call<ChannelVideoResponse> postsById(@Query("id")  String id);

    @GET("/api/v1/user/part-user-buy")
    Call<ContentdetailsAll> postsuserbuy(@Query("content_id")  String content_id);

    @FormUrlEncoded
    @POST("/api/v1/user/notification")
    Call<ContentdetailsAll> Get_Notification(@Field("user_id")  String user_id);

    @GET("/api/v1/blog/category")
    Call<ChannelVideoResponse> blogcategory();

    @FormUrlEncoded
    @POST("/api/v1/user/channel/videos")
    Call<ChannelVideoResponse> channelvideos(@Field("chanel_id") String chanel_id);

    @GET("/api/v1/subcat")
    Call<ChannelVideoResponse> CouressSubCat(@Query("id")  String id);

    @GET("/api/v1/content-details")
    Call<ContentdetailsAll> contentdetails(@Query("id")  String id);

    @GET("/api/v1/user/getproductComment")
    Call<ContentdetailsAll> getproductComment(@Query("content_id")  String content_id);

    @GET("/api/v1/user/productReply")
    Call<ContentdetailsAll> productReply(@Query("comment_id")  String content_id);

    @GET("/api/v1/category")
    Call<ChannelVideoResponse> categoryCouress(@Query("id")  String id);

    @GET("/api/v1/main_cat")
    Call<ChannelVideoResponse> All_categoryCouress(@Query("id")  String id);



}
