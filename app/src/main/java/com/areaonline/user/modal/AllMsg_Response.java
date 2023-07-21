package com.areaonline.user.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllMsg_Response {

    public static class Datum {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("from_id")
        @Expose
        private String fromId;
        @SerializedName("to_id")
        @Expose
        private String toId;
        @SerializedName("rel_id")
        @Expose
        private String relId;
        @SerializedName("from_name")
        @Expose
        private String fromName;
        @SerializedName("to_name")
        @Expose
        private String toName;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("msg_type")
        @Expose
        private String msgType;
        @SerializedName("attachment")
        @Expose
        private String attachment;
        @SerializedName("is_seen")
        @Expose
        private String isSeen;
        @SerializedName("cur_time")
        @Expose
        private String curTime;

        public String getFrom_image() {
            return from_image;
        }

        public void setFrom_image(String from_image) {
            this.from_image = from_image;
        }

        @SerializedName("from_image")
        @Expose
        private String from_image;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFromId() {
            return fromId;
        }

        public void setFromId(String fromId) {
            this.fromId = fromId;
        }

        public String getToId() {
            return toId;
        }

        public void setToId(String toId) {
            this.toId = toId;
        }

        public String getRelId() {
            return relId;
        }

        public void setRelId(String relId) {
            this.relId = relId;
        }

        public String getFromName() {
            return fromName;
        }

        public void setFromName(String fromName) {
            this.fromName = fromName;
        }

        public String getToName() {
            return toName;
        }

        public void setToName(String toName) {
            this.toName = toName;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getMsgType() {
            return msgType;
        }

        public void setMsgType(String msgType) {
            this.msgType = msgType;
        }

        public String getAttachment() {
            return attachment;
        }

        public void setAttachment(String attachment) {
            this.attachment = attachment;
        }

        public String getIsSeen() {
            return isSeen;
        }

        public void setIsSeen(String isSeen) {
            this.isSeen = isSeen;
        }

        public String getCurTime() {
            return curTime;
        }

        public void setCurTime(String curTime) {
            this.curTime = curTime;
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
