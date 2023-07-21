package com.areaonline.shopowner.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EditProduct_Response {

    public class Data {

        @SerializedName("product")
        @Expose
        private Product product;
        @SerializedName("services")
        @Expose
        private List<Object> services = null;

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public List<Object> getServices() {
            return services;
        }

        public void setServices(List<Object> services) {
            this.services = services;
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
}