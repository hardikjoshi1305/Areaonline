package com.areaonline.user.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShopSubCategory_Response {

    public class Data {

        @SerializedName("sub_category")
        @Expose
        private List<SubCategory> subCategory = null;

        public List<SubCategory> getSubCategory() {
            return subCategory;
        }

        public void setSubCategory(List<SubCategory> subCategory) {
            this.subCategory = subCategory;
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


    public class SubCategory {

        @SerializedName("sb_cat")
        @Expose
        private String sbCat;
        @SerializedName("cat_id")
        @Expose
        private String catId;
        @SerializedName("sub_cat_name")
        @Expose
        private String subCatName;
        @SerializedName("cat_logo")
        @Expose
        private String catLogo;
        @SerializedName("cat_bg_img")
        @Expose
        private String catBgImg;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        private boolean isSelected = false;


        public void setSelected(boolean selected) {
            isSelected = selected;
        }


        public boolean isSelected() {
            return isSelected;
        }
        public String getSbCat() {
            return sbCat;
        }

        public void setSbCat(String sbCat) {
            this.sbCat = sbCat;
        }

        public String getCatId() {
            return catId;
        }

        public void setCatId(String catId) {
            this.catId = catId;
        }

        public String getSubCatName() {
            return subCatName;
        }

        public void setSubCatName(String subCatName) {
            this.subCatName = subCatName;
        }

        public String getCatLogo() {
            return catLogo;
        }

        public void setCatLogo(String catLogo) {
            this.catLogo = catLogo;
        }

        public String getCatBgImg() {
            return catBgImg;
        }

        public void setCatBgImg(String catBgImg) {
            this.catBgImg = catBgImg;
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