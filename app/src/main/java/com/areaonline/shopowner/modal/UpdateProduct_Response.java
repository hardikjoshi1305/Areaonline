package com.areaonline.shopowner.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateProduct_Response {

    public class Data {

        @SerializedName("services_name")
        @Expose
        private String servicesName;
        @SerializedName("services_slug")
        @Expose
        private String servicesSlug;
        @SerializedName("l_id")
        @Expose
        private String lId;
        @SerializedName("cat_name")
        @Expose
        private String catName;
        @SerializedName("services_desc")
        @Expose
        private String servicesDesc;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("product_img")
        @Expose
        private Object productImg;
        @SerializedName("product_photo")
        @Expose
        private String productPhoto;

        public String getServicesName() {
            return servicesName;
        }

        public void setServicesName(String servicesName) {
            this.servicesName = servicesName;
        }

        public String getServicesSlug() {
            return servicesSlug;
        }

        public void setServicesSlug(String servicesSlug) {
            this.servicesSlug = servicesSlug;
        }

        public String getlId() {
            return lId;
        }

        public void setlId(String lId) {
            this.lId = lId;
        }

        public String getCatName() {
            return catName;
        }

        public void setCatName(String catName) {
            this.catName = catName;
        }

        public String getServicesDesc() {
            return servicesDesc;
        }

        public void setServicesDesc(String servicesDesc) {
            this.servicesDesc = servicesDesc;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Object getProductImg() {
            return productImg;
        }

        public void setProductImg(Object productImg) {
            this.productImg = productImg;
        }

        public String getProductPhoto() {
            return productPhoto;
        }

        public void setProductPhoto(String productPhoto) {
            this.productPhoto = productPhoto;
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
