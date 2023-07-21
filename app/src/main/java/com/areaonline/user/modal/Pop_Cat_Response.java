package com.areaonline.user.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pop_Cat_Response {

    public static class Cat {

        @SerializedName("category_img")
        @Expose
        private String categoryImg;
        @SerializedName("cat_name")
        @Expose
        private String catName;
        @SerializedName("countcat")
        @Expose
        private String countcat;

        public String getCategoryImg() {
            return categoryImg;
        }

        public void setCategoryImg(String categoryImg) {
            this.categoryImg = categoryImg;
        }

        public String getCatName() {
            return catName;
        }

        public void setCatName(String catName) {
            this.catName = catName;
        }

        public String getCountcat() {
            return countcat;
        }

        public void setCountcat(String countcat) {
            this.countcat = countcat;
        }

    }

    public class Data {

        @SerializedName("cat")
        @Expose
        private List<Cat> cat = null;

        public List<Cat> getCat() {
            return cat;
        }

        public void setCat(List<Cat> cat) {
            this.cat = cat;
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