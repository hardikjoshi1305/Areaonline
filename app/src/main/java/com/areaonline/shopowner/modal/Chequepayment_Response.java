package com.areaonline.shopowner.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Chequepayment_Response {

    public class Data {

        @SerializedName("payment")
        @Expose
        private List<Payment> payment = null;

        public List<Payment> getPayment() {
            return payment;
        }

        public void setPayment(List<Payment> payment) {
            this.payment = payment;
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


    public class Payment {

        @SerializedName("ch_id")
        @Expose
        private String chId;
        @SerializedName("m_id")
        @Expose
        private String mId;
        @SerializedName("product_id")
        @Expose
        private String productId;
        @SerializedName("check_status")
        @Expose
        private String checkStatus;
        @SerializedName("check_no")
        @Expose
        private String checkNo;
        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("check_date")
        @Expose
        private String checkDate;
        @SerializedName("bank_name")
        @Expose
        private String bankName;
        @SerializedName("package_validity")
        @Expose
        private String packageValidity;
        @SerializedName("plan_name")
        @Expose
        private String planName;
        @SerializedName("check_pay_date")
        @Expose
        private String checkPayDate;

        public String getChId() {
            return chId;
        }

        public void setChId(String chId) {
            this.chId = chId;
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

        public String getCheckStatus() {
            return checkStatus;
        }

        public void setCheckStatus(String checkStatus) {
            this.checkStatus = checkStatus;
        }

        public String getCheckNo() {
            return checkNo;
        }

        public void setCheckNo(String checkNo) {
            this.checkNo = checkNo;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getCheckDate() {
            return checkDate;
        }

        public void setCheckDate(String checkDate) {
            this.checkDate = checkDate;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
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

        public String getCheckPayDate() {
            return checkPayDate;
        }

        public void setCheckPayDate(String checkPayDate) {
            this.checkPayDate = checkPayDate;
        }

    }
}