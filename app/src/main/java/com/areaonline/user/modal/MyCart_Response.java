package com.areaonline.user.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyCart_Response {

    public class Datum {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("member_id")
        @Expose
        private String memberId;
        @SerializedName("shop_id")
        @Expose
        private String shopId;
        @SerializedName("product_id")
        @Expose
        private String productId;
        @SerializedName("qty")
        @Expose
        private String qty;
        @SerializedName("created_at")
        @Expose
        private Object createdAt;
        @SerializedName("services_name")
        @Expose
        private String servicesName;
        @SerializedName("wgtunit")
        @Expose
        private String wgtunit;
        @SerializedName("weight")
        @Expose
        private String weight;
        @SerializedName("length")
        @Expose
        private String length;
        @SerializedName("breadth")
        @Expose
        private String breadth;
        @SerializedName("height")
        @Expose
        private String height;
        @SerializedName("product_img")
        @Expose
        private String productImg;
        @SerializedName("price")
        @Expose
        private String price;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public Object getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Object createdAt) {
            this.createdAt = createdAt;
        }

        public String getServicesName() {
            return servicesName;
        }

        public void setServicesName(String servicesName) {
            this.servicesName = servicesName;
        }

        public String getWgtunit() {
            return wgtunit;
        }

        public void setWgtunit(String wgtunit) {
            this.wgtunit = wgtunit;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getLength() {
            return length;
        }

        public void setLength(String length) {
            this.length = length;
        }

        public String getBreadth() {
            return breadth;
        }

        public void setBreadth(String breadth) {
            this.breadth = breadth;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getProductImg() {
            return productImg;
        }

        public void setProductImg(String productImg) {
            this.productImg = productImg;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

    }


        @SerializedName("success")
        @Expose
        private Boolean success;
        @SerializedName("data")
        @Expose
        private List<Datum> data = null;
        @SerializedName("message")
        @Expose
        private String message;

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public List<Datum> getData() {
            return data;
        }

        public void setData(List<Datum> data) {
            this.data = data;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }
