package com.areaonline.channelpartner.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class View_MemberDetail_Response {

    public class Category {

        @SerializedName("cat_id")
        @Expose
        private String catId;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("cat_name")
        @Expose
        private String catName;
        @SerializedName("slug_cat")
        @Expose
        private String slugCat;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("sb_cat")
        @Expose
        private String sbCat;
        @SerializedName("sub_cat_name")
        @Expose
        private String subCatName;
        @SerializedName("cat_logo")
        @Expose
        private String catLogo;
        @SerializedName("cat_bg_img")
        @Expose
        private String catBgImg;

        public String getCatId() {
            return catId;
        }

        public void setCatId(String catId) {
            this.catId = catId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCatName() {
            return catName;
        }

        public void setCatName(String catName) {
            this.catName = catName;
        }

        public String getSlugCat() {
            return slugCat;
        }

        public void setSlugCat(String slugCat) {
            this.slugCat = slugCat;
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

        public String getSbCat() {
            return sbCat;
        }

        public void setSbCat(String sbCat) {
            this.sbCat = sbCat;
        }

        public String getSubCatName() {
            return subCatName;
        }

        public void setSubCatName(String subCatName) {
            this.subCatName = subCatName;
        }

        public String getCatLogo() {
            return catLogo;
        }

        public void setCatLogo(String catLogo) {
            this.catLogo = catLogo;
        }

        public String getCatBgImg() {
            return catBgImg;
        }

        public void setCatBgImg(String catBgImg) {
            this.catBgImg = catBgImg;
        }

    }

    public class Data {

        @SerializedName("listing")
        @Expose
        private List<Listing> listing = null;
        @SerializedName("view_listing")
        @Expose
        private ViewListing viewListing;
        @SerializedName("member_listing")
        @Expose
        private Object memberListing;
        @SerializedName("vendor")
        @Expose
        private Vendor vendor;
        @SerializedName("product")
        @Expose
        private List<Product> product = null;
        @SerializedName("category")
        @Expose
        private List<Category> category = null;
        @SerializedName("payment")
        @Expose
        private List<Payment> payment = null;

        public List<Listing> getListing() {
            return listing;
        }

        public void setListing(List<Listing> listing) {
            this.listing = listing;
        }

        public ViewListing getViewListing() {
            return viewListing;
        }

        public void setViewListing(ViewListing viewListing) {
            this.viewListing = viewListing;
        }

        public Object getMemberListing() {
            return memberListing;
        }

        public void setMemberListing(Object memberListing) {
            this.memberListing = memberListing;
        }

        public Vendor getVendor() {
            return vendor;
        }

        public void setVendor(Vendor vendor) {
            this.vendor = vendor;
        }

        public List<Product> getProduct() {
            return product;
        }

        public void setProduct(List<Product> product) {
            this.product = product;
        }

        public List<Category> getCategory() {
            return category;
        }

        public void setCategory(List<Category> category) {
            this.category = category;
        }

        public List<Payment> getPayment() {
            return payment;
        }

        public void setPayment(List<Payment> payment) {
            this.payment = payment;
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
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

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

    public class Payment {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("m_id")
        @Expose
        private String mId;
        @SerializedName("product_id")
        @Expose
        private String productId;
        @SerializedName("payment_id")
        @Expose
        private String paymentId;
        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("package_validity")
        @Expose
        private String packageValidity;
        @SerializedName("plan_name")
        @Expose
        private String planName;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("pay_date")
        @Expose
        private String payDate;
        @SerializedName("pay_update_date")
        @Expose
        private String payUpdateDate;
        @SerializedName("is_email_verified")
        @Expose
        private String isEmailVerified;
        @SerializedName("otp")
        @Expose
        private String otp;
        @SerializedName("otp_time")
        @Expose
        private String otpTime;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getmId() {
            return mId;
        }

        public void setmId(String mId) {
            this.mId = mId;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getPaymentId() {
            return paymentId;
        }

        public void setPaymentId(String paymentId) {
            this.paymentId = paymentId;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getPackageValidity() {
            return packageValidity;
        }

        public void setPackageValidity(String packageValidity) {
            this.packageValidity = packageValidity;
        }

        public String getPlanName() {
            return planName;
        }

        public void setPlanName(String planName) {
            this.planName = planName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPayDate() {
            return payDate;
        }

        public void setPayDate(String payDate) {
            this.payDate = payDate;
        }

        public String getPayUpdateDate() {
            return payUpdateDate;
        }

        public void setPayUpdateDate(String payUpdateDate) {
            this.payUpdateDate = payUpdateDate;
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

    }

    public class Vendor {

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

    }

    public class ViewListing {

        @SerializedName("l_id")
        @Expose
        private String lId;
        @SerializedName("m_id")
        @Expose
        private String mId;
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
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

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
}
