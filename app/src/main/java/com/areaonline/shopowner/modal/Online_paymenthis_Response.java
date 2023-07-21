package com.areaonline.shopowner.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Online_paymenthis_Response {

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

        public String getInv_id() {
            return inv_id;
        }

        public void setInv_id(String inv_id) {
            this.inv_id = inv_id;
        }

        @SerializedName("inv_id")
        @Expose
        private String inv_id;


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

    }  }