package com.areaonline.shopowner.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Tracking_Response {

    public class CourierAgentDetails {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("delivery_time")
        @Expose
        private String deliveryTime;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(String deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

    }

    public class Data {

        @SerializedName("tracking_data")
        @Expose
        private TrackingData trackingData;

        public TrackingData getTrackingData() {
            return trackingData;
        }

        public void setTrackingData(TrackingData trackingData) {
            this.trackingData = trackingData;
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



    public class QcResponse {

        @SerializedName("qc_image")
        @Expose
        private String qcImage;
        @SerializedName("qc_failed_reason")
        @Expose
        private String qcFailedReason;

        public String getQcImage() {
            return qcImage;
        }

        public void setQcImage(String qcImage) {
            this.qcImage = qcImage;
        }

        public String getQcFailedReason() {
            return qcFailedReason;
        }

        public void setQcFailedReason(String qcFailedReason) {
            this.qcFailedReason = qcFailedReason;
        }

    }

    public class ShipmentTrack {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("awb_code")
        @Expose
        private String awbCode;
        @SerializedName("courier_company_id")
        @Expose
        private Integer courierCompanyId;
        @SerializedName("shipment_id")
        @Expose
        private Integer shipmentId;
        @SerializedName("order_id")
        @Expose
        private Integer orderId;
        @SerializedName("pickup_date")
        @Expose
        private Object pickupDate;
        @SerializedName("delivered_date")
        @Expose
        private Object deliveredDate;
        @SerializedName("weight")
        @Expose
        private String weight;
        @SerializedName("packages")
        @Expose
        private Integer packages;
        @SerializedName("current_status")
        @Expose
        private String currentStatus;
        @SerializedName("delivered_to")
        @Expose
        private String deliveredTo;
        @SerializedName("destination")
        @Expose
        private String destination;
        @SerializedName("consignee_name")
        @Expose
        private String consigneeName;
        @SerializedName("origin")
        @Expose
        private String origin;
        @SerializedName("courier_agent_details")
        @Expose
        private CourierAgentDetails courierAgentDetails;
        @SerializedName("edd")
        @Expose
        private String edd;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getAwbCode() {
            return awbCode;
        }

        public void setAwbCode(String awbCode) {
            this.awbCode = awbCode;
        }

        public Integer getCourierCompanyId() {
            return courierCompanyId;
        }

        public void setCourierCompanyId(Integer courierCompanyId) {
            this.courierCompanyId = courierCompanyId;
        }

        public Integer getShipmentId() {
            return shipmentId;
        }

        public void setShipmentId(Integer shipmentId) {
            this.shipmentId = shipmentId;
        }

        public Integer getOrderId() {
            return orderId;
        }

        public void setOrderId(Integer orderId) {
            this.orderId = orderId;
        }

        public Object getPickupDate() {
            return pickupDate;
        }

        public void setPickupDate(Object pickupDate) {
            this.pickupDate = pickupDate;
        }

        public Object getDeliveredDate() {
            return deliveredDate;
        }

        public void setDeliveredDate(Object deliveredDate) {
            this.deliveredDate = deliveredDate;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public Integer getPackages() {
            return packages;
        }

        public void setPackages(Integer packages) {
            this.packages = packages;
        }

        public String getCurrentStatus() {
            return currentStatus;
        }

        public void setCurrentStatus(String currentStatus) {
            this.currentStatus = currentStatus;
        }

        public String getDeliveredTo() {
            return deliveredTo;
        }

        public void setDeliveredTo(String deliveredTo) {
            this.deliveredTo = deliveredTo;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public String getConsigneeName() {
            return consigneeName;
        }

        public void setConsigneeName(String consigneeName) {
            this.consigneeName = consigneeName;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public CourierAgentDetails getCourierAgentDetails() {
            return courierAgentDetails;
        }

        public void setCourierAgentDetails(CourierAgentDetails courierAgentDetails) {
            this.courierAgentDetails = courierAgentDetails;
        }

        public String getEdd() {
            return edd;
        }

        public void setEdd(String edd) {
            this.edd = edd;
        }

    }

    public class ShipmentTrackActivity {

        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("activity")
        @Expose
        private Boolean activity;
        @SerializedName("location")
        @Expose
        private String location;
        @SerializedName("delivery_time")
        @Expose
        private String deliveryTime;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Boolean getActivity() {
            return activity;
        }

        public void setActivity(Boolean activity) {
            this.activity = activity;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(String deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

    }



    public class TrackingData {

        @SerializedName("track_status")
        @Expose
        private Integer trackStatus;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        @SerializedName("error")
        @Expose
        private String error;
        @SerializedName("shipment_status")
        @Expose
        private Integer shipmentStatus;
        @SerializedName("shipment_track")
        @Expose
        private List<ShipmentTrack> shipmentTrack = null;
        @SerializedName("shipment_track_activities")
        @Expose
        private List<ShipmentTrackActivity> shipmentTrackActivities = null;
        @SerializedName("track_url")
        @Expose
        private String trackUrl;
        @SerializedName("etd")
        @Expose
        private String etd;
        @SerializedName("qc_response")
        @Expose
        private QcResponse qcResponse;

        public Integer getTrackStatus() {
            return trackStatus;
        }

        public void setTrackStatus(Integer trackStatus) {
            this.trackStatus = trackStatus;
        }

        public Integer getShipmentStatus() {
            return shipmentStatus;
        }

        public void setShipmentStatus(Integer shipmentStatus) {
            this.shipmentStatus = shipmentStatus;
        }

        public List<ShipmentTrack> getShipmentTrack() {
            return shipmentTrack;
        }

        public void setShipmentTrack(List<ShipmentTrack> shipmentTrack) {
            this.shipmentTrack = shipmentTrack;
        }

        public List<ShipmentTrackActivity> getShipmentTrackActivities() {
            return shipmentTrackActivities;
        }

        public void setShipmentTrackActivities(List<ShipmentTrackActivity> shipmentTrackActivities) {
            this.shipmentTrackActivities = shipmentTrackActivities;
        }

        public String getTrackUrl() {
            return trackUrl;
        }

        public void setTrackUrl(String trackUrl) {
            this.trackUrl = trackUrl;
        }

        public String getEtd() {
            return etd;
        }

        public void setEtd(String etd) {
            this.etd = etd;
        }

        public QcResponse getQcResponse() {
            return qcResponse;
        }

        public void setQcResponse(QcResponse qcResponse) {
            this.qcResponse = qcResponse;
        }

    }
}