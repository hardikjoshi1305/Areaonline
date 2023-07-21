package com.areaonline.user.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VerifyVendor_OTP {


    public class Data {

        @SerializedName("v_id")
        @Expose
        private String vId;
        @SerializedName("partner_code")
        @Expose
        private String partnerCode;
        @SerializedName("f_name")
        @Expose
        private String fName;
        @SerializedName("l_name")
        @Expose
        private String lName;
        @SerializedName("v_email_id")
        @Expose
        private String vEmailId;
        @SerializedName("login_type")
        @Expose
        private String loginType;

        public String getvId() {
            return vId;
        }

        public void setvId(String vId) {
            this.vId = vId;
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

        public String getvEmailId() {
            return vEmailId;
        }

        public void setvEmailId(String vEmailId) {
            this.vEmailId = vEmailId;
        }

        public String getLoginType() {
            return loginType;
        }

        public void setLoginType(String loginType) {
            this.loginType = loginType;
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

    }