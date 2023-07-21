package com.areaonline;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Notification_Response {


        @SerializedName("notifications")
        @Expose
        private List<Notification> notifications = null;

        public List<Notification> getNotifications() {
            return notifications;
        }

        public void setNotifications(List<Notification> notifications) {
            this.notifications = notifications;
        }



    public class Notification {

        @SerializedName("n_id")
        @Expose
        private String nId;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("desc")
        @Expose
        private String desc;
        @SerializedName("img_link")
        @Expose
        private String imgLink;
        @SerializedName("website_link")
        @Expose
        private String websiteLink;
        @SerializedName("created_at")
        @Expose
        private String createdAt;

        public String getnId() {
            return nId;
        }

        public void setnId(String nId) {
            this.nId = nId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getImgLink() {
            return imgLink;
        }

        public void setImgLink(String imgLink) {
            this.imgLink = imgLink;
        }

        public String getWebsiteLink() {
            return websiteLink;
        }

        public void setWebsiteLink(String websiteLink) {
            this.websiteLink = websiteLink;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
    }

    }
