package com.areaonline.user.modal;

public class Messages {

    private String message,type;
    private long time;
    private boolean seen;
    private String from;

    public String getShop_m_id() {
        return shop_m_id;
    }

    public void setShop_m_id(String shop_m_id) {
        this.shop_m_id = shop_m_id;
    }

    private String shop_m_id;

    public String getCmp() {
        return cmp;
    }

    public void setCmp(String cmp) {
        this.cmp = cmp;
    }

    private String cmp;

    public Messages(){

    }
    public Messages(String message, String type, long time, boolean seen,String from,String cmp, String shop_m_id) {
        this.message = message;
        this.type = type;
        this.time = time;
        this.seen = seen;
        this.from = from;
        this.cmp = cmp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}

