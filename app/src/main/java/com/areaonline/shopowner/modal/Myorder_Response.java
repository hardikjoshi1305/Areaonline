package com.areaonline.shopowner.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Myorder_Response {

    public class Datum {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("m_id")
        @Expose
        private String mId;
        @SerializedName("ps_id")
        @Expose
        private String psId;
        @SerializedName("seller_id")
        @Expose
        private String sellerId;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("qty")
        @Expose
        private String qty;
        @SerializedName("total")
        @Expose
        private String total;
        @SerializedName("payment_id")
        @Expose
        private String paymentId;
        @SerializedName("payment_method")
        @Expose
        private String paymentMethod;
        @SerializedName("ship_company")
        @Expose
        private String shipCompany;
        @SerializedName("ship_company_name")
        @Expose
        private String shipCompanyName;
        @SerializedName("ship_estd")
        @Expose
        private String shipEstd;
        @SerializedName("ship_rate")
        @Expose
        private String shipRate;
        @SerializedName("shiprocket")
        @Expose
        private String shiprocket;
        @SerializedName("shiprocket_order")
        @Expose
        private String shiprocketOrder;
        @SerializedName("shiprocket_response")
        @Expose
        private String shiprocketResponse;
        @SerializedName("companyinfo")
        @Expose
        private String companyinfo;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("awb_response")
        @Expose
        private String awbResponse;
        @SerializedName("trackdata")
        @Expose
        private Object trackdata;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("comp_name")
        @Expose
        private String compName;
        @SerializedName("services_name")
        @Expose
        private String servicesName;


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

        public String getPsId() {
            return psId;
        }

        public void setPsId(String psId) {
            this.psId = psId;
        }

        public String getSellerId() {
            return sellerId;
        }

        public void setSellerId(String sellerId) {
            this.sellerId = sellerId;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getPaymentId() {
            return paymentId;
        }

        public void setPaymentId(String paymentId) {
            this.paymentId = paymentId;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public String getShipCompany() {
            return shipCompany;
        }

        public void setShipCompany(String shipCompany) {
            this.shipCompany = shipCompany;
        }

        public String getShipCompanyName() {
            return shipCompanyName;
        }

        public void setShipCompanyName(String shipCompanyName) {
            this.shipCompanyName = shipCompanyName;
        }

        public String getShipEstd() {
            return shipEstd;
        }

        public void setShipEstd(String shipEstd) {
            this.shipEstd = shipEstd;
        }

        public String getShipRate() {
            return shipRate;
        }

        public void setShipRate(String shipRate) {
            this.shipRate = shipRate;
        }

        public String getShiprocket() {
            return shiprocket;
        }

        public void setShiprocket(String shiprocket) {
            this.shiprocket = shiprocket;
        }

        public String getShiprocketOrder() {
            return shiprocketOrder;
        }

        public void setShiprocketOrder(String shiprocketOrder) {
            this.shiprocketOrder = shiprocketOrder;
        }

        public String getShiprocketResponse() {
            return shiprocketResponse;
        }

        public void setShiprocketResponse(String shiprocketResponse) {
            this.shiprocketResponse = shiprocketResponse;
        }

        public String getCompanyinfo() {
            return companyinfo;
        }

        public void setCompanyinfo(String companyinfo) {
            this.companyinfo = companyinfo;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAwbResponse() {
            return awbResponse;
        }

        public void setAwbResponse(String awbResponse) {
            this.awbResponse = awbResponse;
        }

        public Object getTrackdata() {
            return trackdata;
        }

        public void setTrackdata(Object trackdata) {
            this.trackdata = trackdata;
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

        public String getCompName() {
            return compName;
        }

        public void setCompName(String compName) {
            this.compName = compName;
        }

        public String getServicesName() {
            return servicesName;
        }

        public void setServicesName(String servicesName) {
            this.servicesName = servicesName;
        }

    }


    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<Receivedorder_Response.Datum> data = null;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Receivedorder_Response.Datum> getData() {
        return data;
    }

    public void setData(List<Receivedorder_Response.Datum> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
