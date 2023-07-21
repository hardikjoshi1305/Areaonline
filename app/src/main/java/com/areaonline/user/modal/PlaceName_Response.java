package com.areaonline.user.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaceName_Response {

    public class Data {

        @SerializedName("houseNumber")
        @Expose
        private String houseNumber;
        @SerializedName("houseName")
        @Expose
        private String houseName;
        @SerializedName("poi")
        @Expose
        private String poi;
        @SerializedName("poi_dist")
        @Expose
        private String poiDist;
        @SerializedName("street")
        @Expose
        private String street;
        @SerializedName("street_dist")
        @Expose
        private String streetDist;
        @SerializedName("subSubLocality")
        @Expose
        private String subSubLocality;
        @SerializedName("subLocality")
        @Expose
        private String subLocality;
        @SerializedName("locality")
        @Expose
        private String locality;
        @SerializedName("village")
        @Expose
        private String village;
        @SerializedName("district")
        @Expose
        private String district;
        @SerializedName("subDistrict")
        @Expose
        private String subDistrict;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("pincode")
        @Expose
        private String pincode;
        @SerializedName("lat")
        @Expose
        private String lat;
        @SerializedName("lng")
        @Expose
        private String lng;
        @SerializedName("area")
        @Expose
        private String area;
        @SerializedName("formatted_address")
        @Expose
        private String formattedAddress;

        public String getHouseNumber() {
            return houseNumber;
        }

        public void setHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
        }

        public String getHouseName() {
            return houseName;
        }

        public void setHouseName(String houseName) {
            this.houseName = houseName;
        }

        public String getPoi() {
            return poi;
        }

        public void setPoi(String poi) {
            this.poi = poi;
        }

        public String getPoiDist() {
            return poiDist;
        }

        public void setPoiDist(String poiDist) {
            this.poiDist = poiDist;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getStreetDist() {
            return streetDist;
        }

        public void setStreetDist(String streetDist) {
            this.streetDist = streetDist;
        }

        public String getSubSubLocality() {
            return subSubLocality;
        }

        public void setSubSubLocality(String subSubLocality) {
            this.subSubLocality = subSubLocality;
        }

        public String getSubLocality() {
            return subLocality;
        }

        public void setSubLocality(String subLocality) {
            this.subLocality = subLocality;
        }

        public String getLocality() {
            return locality;
        }

        public void setLocality(String locality) {
            this.locality = locality;
        }

        public String getVillage() {
            return village;
        }

        public void setVillage(String village) {
            this.village = village;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getSubDistrict() {
            return subDistrict;
        }

        public void setSubDistrict(String subDistrict) {
            this.subDistrict = subDistrict;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getPincode() {
            return pincode;
        }

        public void setPincode(String pincode) {
            this.pincode = pincode;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getFormattedAddress() {
            return formattedAddress;
        }

        public void setFormattedAddress(String formattedAddress) {
            this.formattedAddress = formattedAddress;
        }

    }
        @SerializedName("status")
        @Expose
        private Boolean status;
        @SerializedName("msg")
        @Expose
        private String msg;
        @SerializedName("data")
        @Expose
        private Data data;

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

    }
