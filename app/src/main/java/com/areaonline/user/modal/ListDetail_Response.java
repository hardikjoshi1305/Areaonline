package com.areaonline.user.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListDetail_Response {


        @SerializedName("product")
        @Expose
        private java.util.List<Product> product = null;
        @SerializedName("list")
        @Expose
        private List list;

        public java.util.List<Product> getProduct() {
            return product;
        }

        public void setProduct(java.util.List<Product> product) {
            this.product = product;
        }

        public List getList() {
            return list;
        }

        public void setList(List list) {
            this.list = list;
        }

    

    public class List {

        @SerializedName("m_id")
        @Expose
        private String mId;
        @SerializedName("is_email_verified")
        @Expose
        private String isEmailVerified;
        @SerializedName("otp")
        @Expose
        private String otp;
        @SerializedName("otp_time")
        @Expose
        private String otpTime;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("is_read")
        @Expose
        private String isRead;
        @SerializedName("v_id")
        @Expose
        private String vId;
        @SerializedName("source")
        @Expose
        private String source;
        @SerializedName("partner_code")
        @Expose
        private String partnerCode;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("contactno")
        @Expose
        private String contactno;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("gstno")
        @Expose
        private String gstno;
        @SerializedName("comp_name")
        @Expose
        private String compName;
        @SerializedName("certificate_no")
        @Expose
        private String certificateNo;
        @SerializedName("document_type")
        @Expose
        private String documentType;
        @SerializedName("desc")
        @Expose
        private String desc;
        @SerializedName("certificate")
        @Expose
        private String certificate;
        @SerializedName("agree")
        @Expose
        private String agree;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("l_id")
        @Expose
        private String lId;
        @SerializedName("company_slug")
        @Expose
        private String companySlug;
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
        @SerializedName("contact")
        @Expose
        private String contact;
        @SerializedName("whatsapp")
        @Expose
        private String whatsapp;
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

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        @SerializedName("domain")
        @Expose
        private String domain;
        @SerializedName("rating")
        @Expose
        private Integer rating;
        @SerializedName("review")
        @Expose
        private java.util.List<Review> review = null;
        @SerializedName("avg_rating")
        @Expose
        private Double avgRating;

        public String getmId() {
            return mId;
        }

        public void setmId(String mId) {
            this.mId = mId;
        }

        public String getIsEmailVerified() {
            return isEmailVerified;
        }

        public void setIsEmailVerified(String isEmailVerified) {
            this.isEmailVerified = isEmailVerified;
        }

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }

        public String getOtpTime() {
            return otpTime;
        }

        public void setOtpTime(String otpTime) {
            this.otpTime = otpTime;
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

        public String getvId() {
            return vId;
        }

        public void setvId(String vId) {
            this.vId = vId;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getPartnerCode() {
            return partnerCode;
        }

        public void setPartnerCode(String partnerCode) {
            this.partnerCode = partnerCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getContactno() {
            return contactno;
        }

        public void setContactno(String contactno) {
            this.contactno = contactno;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getGstno() {
            return gstno;
        }

        public void setGstno(String gstno) {
            this.gstno = gstno;
        }

        public String getCompName() {
            return compName;
        }

        public void setCompName(String compName) {
            this.compName = compName;
        }

        public String getCertificateNo() {
            return certificateNo;
        }

        public void setCertificateNo(String certificateNo) {
            this.certificateNo = certificateNo;
        }

        public String getDocumentType() {
            return documentType;
        }

        public void setDocumentType(String documentType) {
            this.documentType = documentType;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getCertificate() {
            return certificate;
        }

        public void setCertificate(String certificate) {
            this.certificate = certificate;
        }

        public String getAgree() {
            return agree;
        }

        public void setAgree(String agree) {
            this.agree = agree;
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

        public String getlId() {
            return lId;
        }

        public void setlId(String lId) {
            this.lId = lId;
        }

        public String getCompanySlug() {
            return companySlug;
        }

        public void setCompanySlug(String companySlug) {
            this.companySlug = companySlug;
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

        public Integer getRating() {
            return rating;
        }

        public void setRating(Integer rating) {
            this.rating = rating;
        }

        public java.util.List<Review> getReview() {
            return review;
        }

        public void setReview(java.util.List<Review> review) {
            this.review = review;
        }

        public Double getAvgRating() {
            return avgRating;
        }

        public void setAvgRating(Double avgRating) {
            this.avgRating = avgRating;
        }

    }

    public class Product {

        @SerializedName("ps_id")
        @Expose
        private String psId;
        @SerializedName("m_id")
        @Expose
        private String mId;
        @SerializedName("l_id")
        @Expose
        private String lId;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("services_name")
        @Expose
        private String servicesName;
        @SerializedName("price")
        @Expose
        private String price;

        public String getOrg_price() {
            return org_price;
        }

        public void setOrg_price(String org_price) {
            this.org_price = org_price;
        }

        @SerializedName("org_price")
        @Expose
        private String org_price;
        @SerializedName("cat_name")
        @Expose
        private String catName;
        @SerializedName("services_slug")
        @Expose
        private String servicesSlug;
        @SerializedName("services_desc")
        @Expose
        private String servicesDesc;
        @SerializedName("product_img")
        @Expose
        private String productImg;
        @SerializedName("product_photo")
        @Expose
        private String productPhoto;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        public String getPsId() {
            return psId;
        }

        public void setPsId(String psId) {
            this.psId = psId;
        }

        public String getmId() {
            return mId;
        }

        public void setmId(String mId) {
            this.mId = mId;
        }

        public String getlId() {
            return lId;
        }

        public void setlId(String lId) {
            this.lId = lId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getServicesName() {
            return servicesName;
        }

        public void setServicesName(String servicesName) {
            this.servicesName = servicesName;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCatName() {
            return catName;
        }

        public void setCatName(String catName) {
            this.catName = catName;
        }

        public String getServicesSlug() {
            return servicesSlug;
        }

        public void setServicesSlug(String servicesSlug) {
            this.servicesSlug = servicesSlug;
        }

        public String getServicesDesc() {
            return servicesDesc;
        }

        public void setServicesDesc(String servicesDesc) {
            this.servicesDesc = servicesDesc;
        }

        public String getProductImg() {
            return productImg;
        }

        public void setProductImg(String productImg) {
            this.productImg = productImg;
        }

        public String getProductPhoto() {
            return productPhoto;
        }

        public void setProductPhoto(String productPhoto) {
            this.productPhoto = productPhoto;
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

    }

    public class Review {

        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("is_read")
        @Expose
        private String isRead;
        @SerializedName("otp")
        @Expose
        private String otp;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("mobile_no")
        @Expose
        private String mobileNo;
        @SerializedName("email_id")
        @Expose
        private String emailId;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("rating_id")
        @Expose
        private String ratingId;
        @SerializedName("v_id")
        @Expose
        private String vId;
        @SerializedName("l_id")
        @Expose
        private String lId;
        @SerializedName("m_id")
        @Expose
        private String mId;
        @SerializedName("rating")
        @Expose
        private String rating;
        @SerializedName("comment")
        @Expose
        private String comment;
        @SerializedName("rating_at")
        @Expose
        private String ratingAt;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getIsRead() {
            return isRead;
        }

        public void setIsRead(String isRead) {
            this.isRead = isRead;
        }

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }

        public String getEmailId() {
            return emailId;
        }

        public void setEmailId(String emailId) {
            this.emailId = emailId;
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

        public String getRatingId() {
            return ratingId;
        }

        public void setRatingId(String ratingId) {
            this.ratingId = ratingId;
        }

        public String getvId() {
            return vId;
        }

        public void setvId(String vId) {
            this.vId = vId;
        }

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

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getRatingAt() {
            return ratingAt;
        }

        public void setRatingAt(String ratingAt) {
            this.ratingAt = ratingAt;
        }

    }
}