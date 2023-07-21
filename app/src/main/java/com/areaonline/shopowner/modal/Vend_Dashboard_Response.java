package com.areaonline.shopowner.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vend_Dashboard_Response {

    public class Data {

        @SerializedName("total_member")
        @Expose
        private Integer totalMember;
        @SerializedName("online_payment")
        @Expose
        private String onlinePayment;
        @SerializedName("check_payment")
        @Expose
        private Integer checkPayment;
        @SerializedName("totalpayment")
        @Expose
        private Integer totalpayment;

        public Integer getTotalMember() {
            return totalMember;
        }

        public void setTotalMember(Integer totalMember) {
            this.totalMember = totalMember;
        }

        public String getOnlinePayment() {
            return onlinePayment;
        }

        public void setOnlinePayment(String onlinePayment) {
            this.onlinePayment = onlinePayment;
        }

        public Integer getCheckPayment() {
            return checkPayment;
        }

        public void setCheckPayment(Integer checkPayment) {
            this.checkPayment = checkPayment;
        }

        public Integer getTotalpayment() {
            return totalpayment;
        }

        public void setTotalpayment(Integer totalpayment) {
            this.totalpayment = totalpayment;
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
