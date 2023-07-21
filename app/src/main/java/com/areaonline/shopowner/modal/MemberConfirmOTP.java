package com.areaonline.shopowner.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberConfirmOTP {

    public class Data {

        @SerializedName("contactno")
        @Expose
        private String contactno;

        public String getContactno() {
            return contactno;
        }

        public void setContactno(String contactno) {
            this.contactno = contactno;
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
