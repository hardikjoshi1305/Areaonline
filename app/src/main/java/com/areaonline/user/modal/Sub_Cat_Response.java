package com.areaonline.user.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Sub_Cat_Response {

    public static class Cat {

        @SerializedName("cat_logo")
        @Expose
        private String catLogo;
        @SerializedName("sub_cat_name")
        @Expose
        private String subCatName;
        @SerializedName("countsubcat")
        @Expose
        private String countsubcat;

        public String getCatLogo() {
            return catLogo;
        }

        public void setCatLogo(String catLogo) {
            this.catLogo = catLogo;
        }

        public String getSubCatName() {
            return subCatName;
        }

        public void setSubCatName(String subCatName) {
            this.subCatName = subCatName;
        }

        public String getCountsubcat() {
            return countsubcat;
        }

        public void setCountsubcat(String countsubcat) {
            this.countsubcat = countsubcat;
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