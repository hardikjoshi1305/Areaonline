package com.areaonline.user.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Search_Response {

    public class Data {

        @SerializedName("listing")
        @Expose
        private List<Listing> listing = null;

        public List<Listing> getListing() {
            return listing;
        }

        public void setListing(List<Listing> listing) {
            this.listing = listing;
        }

    }

        @SerializedName("success")
        @Expose
        private Boolean success;
        @SerializedName("data")
        @Expose
        private Data data;
        @SerializedName("message")
        @Expose
        private String message;

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }



    public class Listing {

        @SerializedName("l_id")
        @Expose
        private String lId;
        @SerializedName("m_id")
        @Expose
        private String mId;
        @SerializedName("ur_id")
        @Expose
        private String urId;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("is_read")
        @Expose
        private String isRead;
        @SerializedName("company_slug")
        @Expose
        private String companySlug;
        @SerializedName("comp_name")
        @Expose
        private String compName;
        @SerializedName("desc")
        @Expose
        private String desc;

        public String getIspaid() {
            return ispaid;
        }

        public void setIspaid(String ispaid) {
            this.ispaid = ispaid;
        }

        @SerializedName("ispaid")
        @Expose
        private String ispaid;
        @SerializedName("business_type")
        @Expose
        private String businessType;
        @SerializedName("category")
        @Expose
        private String category;
        @SerializedName("sub_cat_name")
        @Expose
        private String subCatName;
        @SerializedName("custom_sub_cat")
        @Expose
        private String customSubCat;
        @SerializedName("sub_categories")
        @Expose
        private String subCategories;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("local_area")
        @Expose
        private String localArea;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("listing_img")
        @Expose
        private String listingImg;
        @SerializedName("cover_img")
        @Expose
        private String coverImg;
        @SerializedName("video_url")
        @Expose
        private String videoUrl;
        @SerializedName("monday_opening")
        @Expose
        private String mondayOpening;
        @SerializedName("monday_closing")
        @Expose
        private String mondayClosing;
        @SerializedName("tuesday_opening")
        @Expose
        private String tuesdayOpening;
        @SerializedName("tuesday_closing")
        @Expose
        private String tuesdayClosing;
        @SerializedName("wednesday_opening")
        @Expose
        private String wednesdayOpening;
        @SerializedName("wednesday_closing")
        @Expose
        private String wednesdayClosing;
        @SerializedName("thursday_opening")
        @Expose
        private String thursdayOpening;
        @SerializedName("thursday_closing")
        @Expose
        private String thursdayClosing;
        @SerializedName("friday_opening")
        @Expose
        private String fridayOpening;
        @SerializedName("friday_closing")
        @Expose
        private String fridayClosing;
        @SerializedName("saturday_opening")
        @Expose
        private String saturdayOpening;
        @SerializedName("saturday_closing")
        @Expose
        private String saturdayClosing;
        @SerializedName("sunday_opening")
        @Expose
        private String sundayOpening;
        @SerializedName("sunday_closing")
        @Expose
        private String sundayClosing;
        @SerializedName("website")
        @Expose
        private String website;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("contact")
        @Expose
        private String contact;
        @SerializedName("whatsapp")
        @Expose
        private String whatsapp;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("pin_code")
        @Expose
        private String pinCode;
        @SerializedName("facebook")
        @Expose
        private String facebook;
        @SerializedName("twitter")
        @Expose
        private String twitter;
        @SerializedName("linkedin")
        @Expose
        private String linkedin;
        @SerializedName("instagram")
        @Expose
        private String instagram;
        @SerializedName("wordpress")
        @Expose
        private String wordpress;
        @SerializedName("pint")
        @Expose
        private String pint;
        @SerializedName("youtube")
        @Expose
        private String youtube;
        @SerializedName("tumblr")
        @Expose
        private String tumblr;
        @SerializedName("shopupdates")
        @Expose
        private String shopupdates;
        @SerializedName("reason")
        @Expose
        private String reason;
        @SerializedName("marketing_code")
        @Expose
        private Object marketingCode;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("rating")
        @Expose
        private Double rating;

        public String getlId() {
            return lId;
        }

        public void setlId(String lId) {
            this.lId = lId;
        }

        public String getmId() {
            return mId;
        }

        public void setmId(String mId) {
            this.mId = mId;
        }

        public String getUrId() {
            return urId;
        }

        public void setUrId(String urId) {
            this.urId = urId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getIsRead() {
            return isRead;
        }

        public void setIsRead(String isRead) {
            this.isRead = isRead;
        }

        public String getCompanySlug() {
            return companySlug;
        }

        public void setCompanySlug(String companySlug) {
            this.companySlug = companySlug;
        }

        public String getCompName() {
            return compName;
        }

        public void setCompName(String compName) {
            this.compName = compName;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getBusinessType() {
            return businessType;
        }

        public void setBusinessType(String businessType) {
            this.businessType = businessType;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getSubCatName() {
            return subCatName;
        }

        public void setSubCatName(String subCatName) {
            this.subCatName = subCatName;
        }

        public String getCustomSubCat() {
            return customSubCat;
        }

        public void setCustomSubCat(String customSubCat) {
            this.customSubCat = customSubCat;
        }

        public String getSubCategories() {
            return subCategories;
        }

        public void setSubCategories(String subCategories) {
            this.subCategories = subCategories;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getLocalArea() {
            return localArea;
        }

        public void setLocalArea(String localArea) {
            this.localArea = localArea;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getListingImg() {
            return listingImg;
        }

        public void setListingImg(String listingImg) {
            this.listingImg = listingImg;
        }

        public String getCoverImg() {
            return coverImg;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getMondayOpening() {
            return mondayOpening;
        }

        public void setMondayOpening(String mondayOpening) {
            this.mondayOpening = mondayOpening;
        }

        public String getMondayClosing() {
            return mondayClosing;
        }

        public void setMondayClosing(String mondayClosing) {
            this.mondayClosing = mondayClosing;
        }

        public String getTuesdayOpening() {
            return tuesdayOpening;
        }

        public void setTuesdayOpening(String tuesdayOpening) {
            this.tuesdayOpening = tuesdayOpening;
        }

        public String getTuesdayClosing() {
            return tuesdayClosing;
        }

        public void setTuesdayClosing(String tuesdayClosing) {
            this.tuesdayClosing = tuesdayClosing;
        }

        public String getWednesdayOpening() {
            return wednesdayOpening;
        }

        public void setWednesdayOpening(String wednesdayOpening) {
            this.wednesdayOpening = wednesdayOpening;
        }

        public String getWednesdayClosing() {
            return wednesdayClosing;
        }

        public void setWednesdayClosing(String wednesdayClosing) {
            this.wednesdayClosing = wednesdayClosing;
        }

        public String getThursdayOpening() {
            return thursdayOpening;
        }

        public void setThursdayOpening(String thursdayOpening) {
            this.thursdayOpening = thursdayOpening;
        }

        public String getThursdayClosing() {
            return thursdayClosing;
        }

        public void setThursdayClosing(String thursdayClosing) {
            this.thursdayClosing = thursdayClosing;
        }

        public String getFridayOpening() {
            return fridayOpening;
        }

        public void setFridayOpening(String fridayOpening) {
            this.fridayOpening = fridayOpening;
        }

        public String getFridayClosing() {
            return fridayClosing;
        }

        public void setFridayClosing(String fridayClosing) {
            this.fridayClosing = fridayClosing;
        }

        public String getSaturdayOpening() {
            return saturdayOpening;
        }

        public void setSaturdayOpening(String saturdayOpening) {
            this.saturdayOpening = saturdayOpening;
        }

        public String getSaturdayClosing() {
            return saturdayClosing;
        }

        public void setSaturdayClosing(String saturdayClosing) {
            this.saturdayClosing = saturdayClosing;
        }

        public String getSundayOpening() {
            return sundayOpening;
        }

        public void setSundayOpening(String sundayOpening) {
            this.sundayOpening = sundayOpening;
        }

        public String getSundayClosing() {
            return sundayClosing;
        }

        public void setSundayClosing(String sundayClosing) {
            this.sundayClosing = sundayClosing;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getWhatsapp() {
            return whatsapp;
        }

        public void setWhatsapp(String whatsapp) {
            this.whatsapp = whatsapp;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPinCode() {
            return pinCode;
        }

        public void setPinCode(String pinCode) {
            this.pinCode = pinCode;
        }

        public String getFacebook() {
            return facebook;
        }

        public void setFacebook(String facebook) {
            this.facebook = facebook;
        }

        public String getTwitter() {
            return twitter;
        }

        public void setTwitter(String twitter) {
            this.twitter = twitter;
        }

        public String getLinkedin() {
            return linkedin;
        }

        public void setLinkedin(String linkedin) {
            this.linkedin = linkedin;
        }

        public String getInstagram() {
            return instagram;
        }

        public void setInstagram(String instagram) {
            this.instagram = instagram;
        }

        public String getWordpress() {
            return wordpress;
        }

        public void setWordpress(String wordpress) {
            this.wordpress = wordpress;
        }

        public String getPint() {
            return pint;
        }

        public void setPint(String pint) {
            this.pint = pint;
        }

        public String getYoutube() {
            return youtube;
        }

        public void setYoutube(String youtube) {
            this.youtube = youtube;
        }

        public String getTumblr() {
            return tumblr;
        }

        public void setTumblr(String tumblr) {
            this.tumblr = tumblr;
        }

        public String getShopupdates() {
            return shopupdates;
        }

        public void setShopupdates(String shopupdates) {
            this.shopupdates = shopupdates;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public Object getMarketingCode() {
            return marketingCode;
        }

        public void setMarketingCode(Object marketingCode) {
            this.marketingCode = marketingCode;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Double getRating() {
            return rating;
        }

        public void setRating(Double rating) {
            this.rating = rating;
        }

    }
}