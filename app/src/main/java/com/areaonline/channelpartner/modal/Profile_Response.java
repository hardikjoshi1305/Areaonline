package com.areaonline.channelpartner.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile_Response {

    public class Data {

        @SerializedName("profile")
        @Expose
        private Profile profile;

        public Profile getProfile() {
            return profile;
        }

        public void setProfile(Profile profile) {
            this.profile = profile;
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



    public class Profile {

        @SerializedName("v_id")
        @Expose
        private String vId;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("is_read")
        @Expose
        private String isRead;
        @SerializedName("otp")
        @Expose
        private String otp;
        @SerializedName("otp_time")
        @Expose
        private String otpTime;
        @SerializedName("partner_code")
        @Expose
        private String partnerCode;
        @SerializedName("f_name")
        @Expose
        private String fName;
        @SerializedName("l_name")
        @Expose
        private String lName;
        @SerializedName("mobile_no")
        @Expose
        private String mobileNo;
        @SerializedName("mobile_2")
        @Expose
        private String mobile2;
        @SerializedName("v_email_id")
        @Expose
        private String vEmailId;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("v_comp_name")
        @Expose
        private String vCompName;
        @SerializedName("v_comp_details")
        @Expose
        private String vCompDetails;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("certificate_no")
        @Expose
        private String certificateNo;
        @SerializedName("document_type")
        @Expose
        private String documentType;
        @SerializedName("certificate")
        @Expose
        private String certificate;
        @SerializedName("v_create_at")
        @Expose
        private String vCreateAt;
        @SerializedName("v_updated_at")
        @Expose
        private String vUpdatedAt;

        public String getvId() {
            return vId;
        }

        public void setvId(String vId) {
            this.vId = vId;
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

        public String getPartnerCode() {
            return partnerCode;
        }

        public void setPartnerCode(String partnerCode) {
            this.partnerCode = partnerCode;
        }

        public String getfName() {
            return fName;
        }

        public void setfName(String fName) {
            this.fName = fName;
        }

        public String getlName() {
            return lName;
        }

        public void setlName(String lName) {
            this.lName = lName;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }

        public String getMobile2() {
            return mobile2;
        }

        public void setMobile2(String mobile2) {
            this.mobile2 = mobile2;
        }

        public String getvEmailId() {
            return vEmailId;
        }

        public void setvEmailId(String vEmailId) {
            this.vEmailId = vEmailId;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getvCompName() {
            return vCompName;
        }

        public void setvCompName(String vCompName) {
            this.vCompName = vCompName;
        }

        public String getvCompDetails() {
            return vCompDetails;
        }

        public void setvCompDetails(String vCompDetails) {
            this.vCompDetails = vCompDetails;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public String getCertificate() {
            return certificate;
        }

        public void setCertificate(String certificate) {
            this.certificate = certificate;
        }

        public String getvCreateAt() {
            return vCreateAt;
        }

        public void setvCreateAt(String vCreateAt) {
            this.vCreateAt = vCreateAt;
        }

        public String getvUpdatedAt() {
            return vUpdatedAt;
        }

        public void setvUpdatedAt(String vUpdatedAt) {
            this.vUpdatedAt = vUpdatedAt;
        }

    }
}