package com.areaonline.user.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClassObj {


    @SerializedName("stdClass")
    @Expose
    private StdClass stdClass;

    public StdClass getStdClass() {
        return stdClass;
    }

    public void setStdClass(StdClass stdClass) {
        this.stdClass = stdClass;
    }


    public class StdClass {

        @SerializedName("order_id")
        @Expose
        private Integer orderId;
        @SerializedName("shipment_id")
        @Expose
        private Integer shipmentId;

        public Integer getOrderId() {
            return orderId;
        }

        public void setOrderId(Integer orderId) {
            this.orderId = orderId;
        }

        public Integer getShipmentId() {
            return shipmentId;
        }

        public void setShipmentId(Integer shipmentId) {
            this.shipmentId = shipmentId;
        }

    }
}
