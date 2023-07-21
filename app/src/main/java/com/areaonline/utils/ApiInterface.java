package com.areaonline.utils;

import com.areaonline.Notification_Response;
import com.areaonline.channelpartner.modal.EditMember_Response;
import com.areaonline.channelpartner.modal.Profile_Response;
import com.areaonline.channelpartner.modal.Register_Channel_Response;
import com.areaonline.channelpartner.modal.ResetPass_Response;
import com.areaonline.channelpartner.modal.Show_member_Response;
import com.areaonline.channelpartner.modal.Trans_History_cha_Response;
import com.areaonline.channelpartner.modal.View_MemberDetail_Response;
import com.areaonline.shopowner.modal.Add_Bank_Response;
import com.areaonline.shopowner.modal.Approve_Response;
import com.areaonline.shopowner.modal.BankDetail_Response;
import com.areaonline.shopowner.modal.ChatSend_Response;
import com.areaonline.shopowner.modal.ChequesPay_Response;
import com.areaonline.shopowner.modal.Detail_Order_Response;
import com.areaonline.shopowner.modal.EditProduct_Response;
import com.areaonline.shopowner.modal.EditShop_Response;
import com.areaonline.shopowner.modal.FreePurchase_Response;
import com.areaonline.shopowner.modal.InitializeSub_Response;
import com.areaonline.shopowner.modal.ListingPS_Response;
import com.areaonline.shopowner.modal.MemberConfirmOTP;
import com.areaonline.shopowner.modal.Myorder_Response;
import com.areaonline.shopowner.modal.Online_paymenthis_Response;
import com.areaonline.shopowner.modal.CreateProduct_Response;
import com.areaonline.shopowner.modal.Create_Shop_Response;
import com.areaonline.shopowner.modal.Delete_Product;
import com.areaonline.shopowner.modal.Chequepayment_Response;
import com.areaonline.shopowner.modal.Rating_Response;
import com.areaonline.shopowner.modal.Receivedorder_Response;
import com.areaonline.shopowner.modal.Reset_Pass_Response;
import com.areaonline.shopowner.modal.Searchdropdown;
import com.areaonline.shopowner.modal.ShowProduct_Response;
import com.areaonline.shopowner.modal.Show_Shop_Response;
import com.areaonline.shopowner.modal.Tracking_Response;
import com.areaonline.shopowner.modal.UpdateShop_Response;
import com.areaonline.shopowner.modal.Vend_Dashboard_Response;
import com.areaonline.shopowner.modal.View_Shop_Response;
import com.areaonline.user.modal.AddCart_Response;
import com.areaonline.user.modal.AllMsg_Response;
import com.areaonline.user.modal.Checkout_Response;
import com.areaonline.user.modal.FCM_Response;
import com.areaonline.user.modal.Forgotpass_Response;
import com.areaonline.user.modal.GetCategory_Response;
import com.areaonline.user.modal.GetData_Response;
import com.areaonline.user.modal.GetLat_Response;
import com.areaonline.user.modal.Get_Msg_Response;
import com.areaonline.user.modal.ListDetail_Response;
import com.areaonline.user.modal.Listing_data_Response;
import com.areaonline.user.modal.Login_Response;
import com.areaonline.user.modal.Mem_Dashboard_Response;
import com.areaonline.user.modal.MyCart_Response;
import com.areaonline.user.modal.OTP_Response;
import com.areaonline.user.modal.PlaceName_Response;
import com.areaonline.user.modal.Placeorder_Response;
import com.areaonline.user.modal.Pop_Cat_Response;
import com.areaonline.user.modal.PostRating_Response;
import com.areaonline.user.modal.Register_Response;
import com.areaonline.user.modal.Search_Response;
import com.areaonline.user.modal.Send_form_Response;
import com.areaonline.user.modal.ShopCategory_Response;
import com.areaonline.user.modal.ShopSubCategory_Response;
import com.areaonline.user.modal.Sub_Cat_Response;
import com.areaonline.user.modal.VendorConfirm_OTP;
import com.areaonline.user.modal.VerifyMemberOTP;
import com.areaonline.user.modal.VerifyVendor_OTP;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST("Auth/member_register")
    @FormUrlEncoded
    Call<Register_Response> createUser(@FieldMap Map<String, String> params);

    @POST("auth/vendor-register")
    @FormUrlEncoded
    Call<Register_Channel_Response> registervendor(@FieldMap Map<String, String> params);

    @POST("Vendor/dashboard")
    @FormUrlEncoded
    Call<Vend_Dashboard_Response> vdashboard(@FieldMap Map<String, String> params);

    @POST("Member/free_package")
    @FormUrlEncoded
    Call<FreePurchase_Response> freepurchase(@FieldMap Map<String, String> params);

    @POST("Member/insert_check_payment")
    @FormUrlEncoded
    Call<ChequesPay_Response> chequepay(@FieldMap Map<String, String> params);

    @POST("Member/dashboard")
    @FormUrlEncoded
    Call<Mem_Dashboard_Response> dashboard(@FieldMap Map<String, String> params);

    @POST("auth/member_otp_conformation")
    @FormUrlEncoded
    Call<MemberConfirmOTP> memberconfirmotp(@FieldMap Map<String, String> params);

    @POST("auth/otp_login_member")
    @FormUrlEncoded
    Call<VerifyMemberOTP> verifymemberotp(@FieldMap Map<String, String> params);

    @POST("auth/otp_login_vendor")
    @FormUrlEncoded
    Call<VerifyVendor_OTP> verifyvendorotp(@FieldMap Map<String, String> params);

    @POST("User/post_review")
    @FormUrlEncoded
    Call<PostRating_Response> postrating(@FieldMap Map<String, String> params);

    @POST("auth/vendor_otp_conformation")
    @FormUrlEncoded
    Call<VendorConfirm_OTP> vendorconfirmotp(@FieldMap Map<String, String> params);

    @POST("Vendor/show_member")
    @FormUrlEncoded
    Call<Show_member_Response> showmember(@FieldMap Map<String, String> params);

    @POST("Member/forgotPassword")
    @FormUrlEncoded
    Call<Forgotpass_Response> forgotpass(@FieldMap Map<String, String> params);

    @POST("Vendor/transaction_history")
    @FormUrlEncoded
    Call<Trans_History_cha_Response> history(@FieldMap Map<String, String> params);

    @POST("Vendor/delete_member/{mid}")
    Call<Delete_Product> deleteshowmemeber(@Path("mid") String mld);

    @POST("Vendor/member_details/{mid}")
    Call<View_MemberDetail_Response> viewmemberdetail(@Path("mid") String mld);

    @POST("Vendor/edit_member/{mid}")
    Call<EditMember_Response> editmember(@Path("mid") String mld);

    @POST("Member/edit_product_services/{pid}")
    Call<EditProduct_Response> editproduct(@Path("pid") String pid);

    @POST("Member/edit_shop/{lid}")
    Call<EditShop_Response> beforeeditshop(@Path("lid") String lid);

    @POST("Vendor/reset_password")
    @FormUrlEncoded
    Call<ResetPass_Response> changepass(@FieldMap Map<String, String> params);

    @POST("Home/send_form")
    @FormUrlEncoded
    Call<Send_form_Response> sendform(@FieldMap Map<String, String> params);

    @POST("Auth/otp_confirmation_submit")
    @FormUrlEncoded
    Call<OTP_Response> verifymember(@FieldMap Map<String, String> params);

    @POST("Listing/loadDataApi")
    @FormUrlEncoded
    Call<Listing_data_Response> listingdata(@FieldMap Map<String, String> params);

    @POST("Listing/loadcatData")
    @FormUrlEncoded
    Call<Listing_data_Response> loadcatdata(@FieldMap Map<String, String> params);

    @POST("Listing/loadSubcatData")
    @FormUrlEncoded
    Call<Listing_data_Response> loadsubcatdata(@FieldMap Map<String, String> params);

    @POST("Listing/getCategory")
    Call<GetCategory_Response> getcategory();

    @POST("User/get_notifications")
    Call<Notification_Response> getnotification();

    @POST("Listing/getPopularCategory")
    Call<Pop_Cat_Response> getpopcat();

    @POST("Listing/getPopularSubCategory")
    Call<Sub_Cat_Response> getsubpopcat();

    @POST("Listing/listing_details/{company_slub}")
    @FormUrlEncoded
    Call<ListDetail_Response> listdetail(@Path("company_slub") String company_slub, @FieldMap Map<String, String> params);

    @POST("listing/loadDataNew")
    @FormUrlEncoded
    Call<Search_Response> searvvch(@FieldMap Map<String, String> params);

    @POST("Auth/check_login_auth")
    @FormUrlEncoded
    Call<Login_Response> loginUser(@FieldMap Map<String, String> params);

    @POST("Member/show_shop")
    @FormUrlEncoded
    Call<Show_Shop_Response> showshop(@FieldMap Map<String, String> params);

    @POST("Member/get_category")
    Call<ShopCategory_Response> getshopcategory();

    @POST("Member/get_sub_category")
    @FormUrlEncoded
    Call<ShopSubCategory_Response> getshopsubcategory(@FieldMap Map<String, String> params);

    @POST("Member/view_shop/{l_id}")
    Call<View_Shop_Response> viewshop(@Path("l_id") String l_id);

    @POST("Member/delete_product_services/{p_id}")
    Call<Delete_Product> deleteproduct(@Path("p_id") String p_id);

    @POST("Member/delete_shop/{p_id}")
    Call<Delete_Product> shopdelete(@Path("p_id") String p_id);

    @POST("Member/create_shop")
    @Multipart
    Call<Create_Shop_Response> shopcreate(@PartMap Map<String, String> params, @Part MultipartBody.Part file, @Part MultipartBody.Part file2);

    @POST("Member/update_shop/{l_id}")
    @Multipart
    Call<UpdateShop_Response> editshop(@Path("l_id") String l_id, @PartMap Map<String, String> params, @Part("listing_img") RequestBody file, @Part("cover_img") RequestBody file2);

    @POST("Member/update_shop/{l_id}")
    @Multipart
    Call<String> editshop1(@Path("l_id") String l_id, @PartMap Map<String, String> params);

    @POST("Member/show_product_services")
    @FormUrlEncoded
    Call<ShowProduct_Response> productshow(@FieldMap Map<String, String> params);

    @POST("Member/check_payment_history")
    @FormUrlEncoded
    Call<Chequepayment_Response> checkpayment(@FieldMap Map<String, String> params);

    @POST("Member/transaction_history")
    @FormUrlEncoded
    Call<Online_paymenthis_Response> onlinepayment(@FieldMap Map<String, String> params);

    @POST("Member/failed_transaction_history")
    @FormUrlEncoded
    Call<Online_paymenthis_Response> failed_transaction_history(@FieldMap Map<String, String> params);

    @POST("Member/rating_review")
    @FormUrlEncoded
    Call<Rating_Response> getrating(@FieldMap Map<String, String> params);

    @POST("Vendor/profile")
    @FormUrlEncoded
    Call<Profile_Response> profile(@FieldMap Map<String, String> params);

    @POST("Member/reset_password")
    @FormUrlEncoded
    Call<Reset_Pass_Response> resetpass(@FieldMap Map<String, String> params);

    @POST("Member/create_product_services")
    @Multipart
    Call<CreateProduct_Response> productcreate(@PartMap Map<String, String> params, @Part("product_img") RequestBody file, @Part("product_images") RequestBody file2);

    @POST("Member/update_product_services/{p_id}")
    @Multipart
    Call<UpdateShop_Response> updateproduct(@Path("p_id") String p_id, @PartMap Map<String, String> params, @Part("product_img") RequestBody file, @Part("product_images") RequestBody file2);

    @POST("User/post_fcm_token")
    @FormUrlEncoded
    Call<JSONObject> fcmget(@FieldMap Map<String, String> params);

    @POST("listing/custom_search")
    @FormUrlEncoded
    Call<Searchdropdown> searcnn(@FieldMap Map<String, String> params);

    @POST("home/latlngplacename")
    @FormUrlEncoded
    Call<PlaceName_Response> placename(@FieldMap Map<String, String> params);

    @POST("Chat/send_message")
    @FormUrlEncoded
    Call<ChatSend_Response> send_notification(@FieldMap Map<String, String> params);

    @POST("Chat/get_my_message")
    @FormUrlEncoded
    Call<Get_Msg_Response> get_msg(@FieldMap Map<String, String> params);

    @POST("Chat/get_message")
    @FormUrlEncoded
    Call<AllMsg_Response> allmsg(@FieldMap Map<String, String> params);

    @POST("Listing/listing_details_by_mid")
    @FormUrlEncoded
    Call<GetData_Response> getdata(@FieldMap Map<String, String> params);

    @POST("marketing/add_product_services")
    @FormUrlEncoded
    Call<ListingPS_Response> listingshow(@FieldMap Map<String, String> params);

    @POST("Chat/update_msg_status")
    @FormUrlEncoded
    Call<JSONObject> addseen(@FieldMap Map<String, String> params);

    @POST("Subscription/initiateSubscriptions")
    @FormUrlEncoded
    Call<InitializeSub_Response> getsubid(@FieldMap Map<String, String> params);


    @POST("listing/checkcart")
    @FormUrlEncoded
    Call<AddCart_Response> checkcart(@FieldMap Map<String, String> params);


    @POST("listing/removecart")
    @FormUrlEncoded
    Call<AddCart_Response> removecart(@FieldMap Map<String, String> params);


    @POST("listing/cart")
    @FormUrlEncoded
    Call<MyCart_Response> viewcart(@FieldMap Map<String, String> params);


    @POST("listing/place_order_with_pickup")
    @FormUrlEncoded
    Call<Checkout_Response> placeorder(@FieldMap Map<String, String> params);

    @POST("home/elaclatlng")
    @FormUrlEncoded
    Call<GetLat_Response> getlat(@FieldMap Map<String, String> params);

    @POST("listing/place_order")
    @FormUrlEncoded
    Call<Placeorder_Response> placeyourorder(@FieldMap Map<String, String> params);


    @POST("member/my_purchase")
    @FormUrlEncoded
    Call<Myorder_Response> muorders(@FieldMap Map<String, String> params);

    @POST("member/my_orders")
    @FormUrlEncoded
    Call<Receivedorder_Response> receiveorder(@FieldMap Map<String, String> params);

    @POST("orders/order_tracking")
    @FormUrlEncoded
    Call<Tracking_Response> tracking(@FieldMap Map<String, String> params);

    @POST("orders/order_details")
    @FormUrlEncoded
    Call<Detail_Order_Response> getorderdetail(@FieldMap Map<String, String> params);

    @POST("member/bank_process")
    @FormUrlEncoded
    Call<Add_Bank_Response> addbank(@FieldMap Map<String, String> params);

    @POST("member/bank_details")
    @FormUrlEncoded
    Call<BankDetail_Response> bankdetail(@FieldMap Map<String, String> params);

    @POST("member/approve_order")
    @FormUrlEncoded
    Call<Approve_Response> getapprove(@FieldMap Map<String, String> params);


    @POST("orders/cancel_order/{orderid}")
    Call<Approve_Response> getcancel(@Path("orderid") String orderid);
}
