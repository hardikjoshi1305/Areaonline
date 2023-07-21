package com.areaonline.shopowner.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Detail_Order_Response {

    public class Companyinfo {

        @SerializedName("pickup_location")
        @Expose
        private String pickupLocation;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("address_2")
        @Expose
        private String address2;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("pin_code")
        @Expose
        private String pinCode;
        @SerializedName("image")
        @Expose
        private String image;

        public String getPickupLocation() {
            return pickupLocation;
        }

        public void setPickupLocation(String pickupLocation) {
            this.pickupLocation = pickupLocation;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAddress2() {
            return address2;
        }

        public void setAddress2(String address2) {
            this.address2 = address2;
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

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getPinCode() {
            return pinCode;
        }

        public void setPinCode(String pinCode) {
            this.pinCode = pinCode;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    }

    public class Data {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("m_id")
        @Expose
        private String mId;
        @SerializedName("ps_id")
        @Expose
        private String psId;
        @SerializedName("seller_id")
        @Expose
        private String sellerId;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("qty")
        @Expose
        private String qty;
        @SerializedName("total")
        @Expose
        private String total;
        @SerializedName("payment_id")
        @Expose
        private String paymentId;
        @SerializedName("payment_method")
        @Expose
        private String paymentMethod;
        @SerializedName("ship_company")
        @Expose
        private String shipCompany;
        @SerializedName("ship_company_name")
        @Expose
        private String shipCompanyName;
        @SerializedName("ship_estd")
        @Expose
        private String shipEstd;
        @SerializedName("ship_rate")
        @Expose
        private String shipRate;
        @SerializedName("shiprocket")
        @Expose
        private Shiprocket shiprocket;
        @SerializedName("shiprocket_order")
        @Expose
        private String shiprocketOrder;
        @SerializedName("shiprocket_response")
        @Expose
        private String shiprocketResponse;
        @SerializedName("companyinfo")
        @Expose
        private Companyinfo companyinfo;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("awb_response")
        @Expose
        private String awbResponse;
        @SerializedName("trackdata")
        @Expose
        private String trackdata;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("comp_name")
        @Expose
        private String compName;
        @SerializedName("services_name")
        @Expose
        private String servicesName;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("contactno")
        @Expose
        private String contactno;
        @SerializedName("awb_response_new")
        @Expose
        private AwbResponseNew awbResponseNew;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getmId() {
            return mId;
        }

        public void setmId(String mId) {
            this.mId = mId;
        }

        public String getPsId() {
            return psId;
        }

        public void setPsId(String psId) {
            this.psId = psId;
        }

        public String getSellerId() {
            return sellerId;
        }

        public void setSellerId(String sellerId) {
            this.sellerId = sellerId;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getPaymentId() {
            return paymentId;
        }

        public void setPaymentId(String paymentId) {
            this.paymentId = paymentId;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public String getShipCompany() {
            return shipCompany;
        }

        public void setShipCompany(String shipCompany) {
            this.shipCompany = shipCompany;
        }

        public String getShipCompanyName() {
            return shipCompanyName;
        }

        public void setShipCompanyName(String shipCompanyName) {
            this.shipCompanyName = shipCompanyName;
        }

        public String getShipEstd() {
            return shipEstd;
        }

        public void setShipEstd(String shipEstd) {
            this.shipEstd = shipEstd;
        }

        public String getShipRate() {
            return shipRate;
        }

        public void setShipRate(String shipRate) {
            this.shipRate = shipRate;
        }

        public Shiprocket getShiprocket() {
            return shiprocket;
        }

        public void setShiprocket(Shiprocket shiprocket) {
            this.shiprocket = shiprocket;
        }

        public String getShiprocketOrder() {
            return shiprocketOrder;
        }

        public void setShiprocketOrder(String shiprocketOrder) {
            this.shiprocketOrder = shiprocketOrder;
        }

        public String getShiprocketResponse() {
            return shiprocketResponse;
        }

        public void setShiprocketResponse(String shiprocketResponse) {
            this.shiprocketResponse = shiprocketResponse;
        }

        public Companyinfo getCompanyinfo() {
            return companyinfo;
        }

        public void setCompanyinfo(Companyinfo companyinfo) {
            this.companyinfo = companyinfo;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAwbResponse() {
            return awbResponse;
        }

        public void setAwbResponse(String awbResponse) {
            this.awbResponse = awbResponse;
        }

        public String getTrackdata() {
            return trackdata;
        }

        public void setTrackdata(String trackdata) {
            this.trackdata = trackdata;
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

        public String getCompName() {
            return compName;
        }

        public void setCompName(String compName) {
            this.compName = compName;
        }

        public String getServicesName() {
            return servicesName;
        }

        public void setServicesName(String servicesName) {
            this.servicesName = servicesName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getContactno() {
            return contactno;
        }

        public void setContactno(String contactno) {
            this.contactno = contactno;
        }
        public AwbResponseNew getAwbResponseNew() {
            return awbResponseNew;
        }

        public void setAwbResponseNew(AwbResponseNew awbResponseNew) {
            this.awbResponseNew = awbResponseNew;
        }
    }

    public class AssignedDateTime {
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("timezone_type")
        @Expose
        private Integer timezoneType;
        @SerializedName("timezone")
        @Expose
        private String timezone;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Integer getTimezoneType() {
            return timezoneType;
        }

        public void setTimezoneType(Integer timezoneType) {
            this.timezoneType = timezoneType;
        }

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

    }

    public class ShippedBy {
        @SerializedName("shipper_company_name")
        @Expose
        private String shipperCompanyName;
        @SerializedName("shipper_address_1")
        @Expose
        private String shipperAddress1;
        @SerializedName("shipper_address_2")
        @Expose
        private String shipperAddress2;
        @SerializedName("shipper_city")
        @Expose
        private String shipperCity;
        @SerializedName("shipper_state")
        @Expose
        private String shipperState;
        @SerializedName("shipper_country")
        @Expose
        private String shipperCountry;
        @SerializedName("shipper_postcode")
        @Expose
        private String shipperPostcode;
        @SerializedName("shipper_first_mile_activated")
        @Expose
        private Integer shipperFirstMileActivated;
        @SerializedName("shipper_phone")
        @Expose
        private String shipperPhone;
        @SerializedName("lat")
        @Expose
        private String lat;
        @SerializedName("long")
        @Expose
        private String _long;
        @SerializedName("shipper_email")
        @Expose
        private String shipperEmail;
        @SerializedName("rto_company_name")
        @Expose
        private String rtoCompanyName;
        @SerializedName("rto_address_1")
        @Expose
        private String rtoAddress1;
        @SerializedName("rto_address_2")
        @Expose
        private String rtoAddress2;
        @SerializedName("rto_city")
        @Expose
        private String rtoCity;
        @SerializedName("rto_state")
        @Expose
        private String rtoState;
        @SerializedName("rto_country")
        @Expose
        private String rtoCountry;
        @SerializedName("rto_postcode")
        @Expose
        private String rtoPostcode;
        @SerializedName("rto_phone")
        @Expose
        private String rtoPhone;
        @SerializedName("rto_email")
        @Expose
        private String rtoEmail;

        public String getShipperCompanyName() {
            return shipperCompanyName;
        }

        public void setShipperCompanyName(String shipperCompanyName) {
            this.shipperCompanyName = shipperCompanyName;
        }

        public String getShipperAddress1() {
            return shipperAddress1;
        }

        public void setShipperAddress1(String shipperAddress1) {
            this.shipperAddress1 = shipperAddress1;
        }

        public String getShipperAddress2() {
            return shipperAddress2;
        }

        public void setShipperAddress2(String shipperAddress2) {
            this.shipperAddress2 = shipperAddress2;
        }

        public String getShipperCity() {
            return shipperCity;
        }

        public void setShipperCity(String shipperCity) {
            this.shipperCity = shipperCity;
        }

        public String getShipperState() {
            return shipperState;
        }

        public void setShipperState(String shipperState) {
            this.shipperState = shipperState;
        }

        public String getShipperCountry() {
            return shipperCountry;
        }

        public void setShipperCountry(String shipperCountry) {
            this.shipperCountry = shipperCountry;
        }

        public String getShipperPostcode() {
            return shipperPostcode;
        }

        public void setShipperPostcode(String shipperPostcode) {
            this.shipperPostcode = shipperPostcode;
        }

        public Integer getShipperFirstMileActivated() {
            return shipperFirstMileActivated;
        }

        public void setShipperFirstMileActivated(Integer shipperFirstMileActivated) {
            this.shipperFirstMileActivated = shipperFirstMileActivated;
        }

        public String getShipperPhone() {
            return shipperPhone;
        }

        public void setShipperPhone(String shipperPhone) {
            this.shipperPhone = shipperPhone;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLong() {
            return _long;
        }

        public void setLong(String _long) {
            this._long = _long;
        }

        public String getShipperEmail() {
            return shipperEmail;
        }

        public void setShipperEmail(String shipperEmail) {
            this.shipperEmail = shipperEmail;
        }

        public String getRtoCompanyName() {
            return rtoCompanyName;
        }

        public void setRtoCompanyName(String rtoCompanyName) {
            this.rtoCompanyName = rtoCompanyName;
        }

        public String getRtoAddress1() {
            return rtoAddress1;
        }

        public void setRtoAddress1(String rtoAddress1) {
            this.rtoAddress1 = rtoAddress1;
        }

        public String getRtoAddress2() {
            return rtoAddress2;
        }

        public void setRtoAddress2(String rtoAddress2) {
            this.rtoAddress2 = rtoAddress2;
        }

        public String getRtoCity() {
            return rtoCity;
        }

        public void setRtoCity(String rtoCity) {
            this.rtoCity = rtoCity;
        }

        public String getRtoState() {
            return rtoState;
        }

        public void setRtoState(String rtoState) {
            this.rtoState = rtoState;
        }

        public String getRtoCountry() {
            return rtoCountry;
        }

        public void setRtoCountry(String rtoCountry) {
            this.rtoCountry = rtoCountry;
        }

        public String getRtoPostcode() {
            return rtoPostcode;
        }

        public void setRtoPostcode(String rtoPostcode) {
            this.rtoPostcode = rtoPostcode;
        }

        public String getRtoPhone() {
            return rtoPhone;
        }

        public void setRtoPhone(String rtoPhone) {
            this.rtoPhone = rtoPhone;
        }

        public String getRtoEmail() {
            return rtoEmail;
        }

        public void setRtoEmail(String rtoEmail) {
            this.rtoEmail = rtoEmail;
        }

    }

    public class AwbResponseNew {
        @SerializedName("awb_code")
        @Expose
        private String awbCode;
        @SerializedName("cod")
        @Expose
        private Integer cod;
        @SerializedName("order_id")
        @Expose
        private Integer orderId;
        @SerializedName("shipment_id")
        @Expose
        private Integer shipmentId;
        @SerializedName("awb_code_status")
        @Expose
        private Integer awbCodeStatus;
        @SerializedName("assigned_date_time")
        @Expose
        private AssignedDateTime assignedDateTime;
        @SerializedName("applied_weight")
        @Expose
        private Double appliedWeight;
        @SerializedName("company_id")
        @Expose
        private Integer companyId;
        @SerializedName("flat_charges")
        @Expose
        private Integer flatCharges;
        @SerializedName("courier_company_id")
        @Expose
        private Integer courierCompanyId;
        @SerializedName("freight_percentage")
        @Expose
        private Integer freightPercentage;
        @SerializedName("device_id")
        @Expose
        private String deviceId;
        @SerializedName("routing_code")
        @Expose
        private String routingCode;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("is_hyperlocal")
        @Expose
        private Boolean isHyperlocal;
        @SerializedName("courier_name")
        @Expose
        private String courierName;
        @SerializedName("channel_order_id")
        @Expose
        private String channelOrderId;
        @SerializedName("rto_routing_code")
        @Expose
        private String rtoRoutingCode;
        @SerializedName("invoice_no")
        @Expose
        private String invoiceNo;
        @SerializedName("transporter_id")
        @Expose
        private String transporterId;
        @SerializedName("transporter_name")
        @Expose
        private String transporterName;
        @SerializedName("shipped_by")
        @Expose
        private ShippedBy shippedBy;

        public String getAwbCode() {
            return awbCode;
        }

        public void setAwbCode(String awbCode) {
            this.awbCode = awbCode;
        }

        public Integer getCod() {
            return cod;
        }

        public void setCod(Integer cod) {
            this.cod = cod;
        }

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

        public Integer getAwbCodeStatus() {
            return awbCodeStatus;
        }

        public void setAwbCodeStatus(Integer awbCodeStatus) {
            this.awbCodeStatus = awbCodeStatus;
        }

        public AssignedDateTime getAssignedDateTime() {
            return assignedDateTime;
        }

        public void setAssignedDateTime(AssignedDateTime assignedDateTime) {
            this.assignedDateTime = assignedDateTime;
        }

        public Double getAppliedWeight() {
            return appliedWeight;
        }

        public void setAppliedWeight(Double appliedWeight) {
            this.appliedWeight = appliedWeight;
        }

        public Integer getCompanyId() {
            return companyId;
        }

        public void setCompanyId(Integer companyId) {
            this.companyId = companyId;
        }

        public Integer getFlatCharges() {
            return flatCharges;
        }

        public void setFlatCharges(Integer flatCharges) {
            this.flatCharges = flatCharges;
        }

        public Integer getCourierCompanyId() {
            return courierCompanyId;
        }

        public void setCourierCompanyId(Integer courierCompanyId) {
            this.courierCompanyId = courierCompanyId;
        }

        public Integer getFreightPercentage() {
            return freightPercentage;
        }

        public void setFreightPercentage(Integer freightPercentage) {
            this.freightPercentage = freightPercentage;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getRoutingCode() {
            return routingCode;
        }

        public void setRoutingCode(String routingCode) {
            this.routingCode = routingCode;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Boolean getIsHyperlocal() {
            return isHyperlocal;
        }

        public void setIsHyperlocal(Boolean isHyperlocal) {
            this.isHyperlocal = isHyperlocal;
        }

        public String getCourierName() {
            return courierName;
        }

        public void setCourierName(String courierName) {
            this.courierName = courierName;
        }

        public String getChannelOrderId() {
            return channelOrderId;
        }

        public void setChannelOrderId(String channelOrderId) {
            this.channelOrderId = channelOrderId;
        }

        public String getRtoRoutingCode() {
            return rtoRoutingCode;
        }

        public void setRtoRoutingCode(String rtoRoutingCode) {
            this.rtoRoutingCode = rtoRoutingCode;
        }

        public String getInvoiceNo() {
            return invoiceNo;
        }

        public void setInvoiceNo(String invoiceNo) {
            this.invoiceNo = invoiceNo;
        }

        public String getTransporterId() {
            return transporterId;
        }

        public void setTransporterId(String transporterId) {
            this.transporterId = transporterId;
        }

        public String getTransporterName() {
            return transporterName;
        }

        public void setTransporterName(String transporterName) {
            this.transporterName = transporterName;
        }

        public ShippedBy getShippedBy() {
            return shippedBy;
        }

        public void setShippedBy(ShippedBy shippedBy) {
            this.shippedBy = shippedBy;
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

    public class OrderItem {
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("sku")
        @Expose
        private String sku;
        @SerializedName("units")
        @Expose
        private String units;
        @SerializedName("selling_price")
        @Expose
        private String sellingPrice;
        @SerializedName("discount")
        @Expose
        private String discount;
        @SerializedName("tax")
        @Expose
        private String tax;
        @SerializedName("hsn")
        @Expose
        private String hsn;

        public String getProduct_img() {
            return product_img;
        }

        public void setProduct_img(String product_img) {
            this.product_img = product_img;
        }

        @SerializedName("product_img")
        @Expose
        private String product_img;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public String getUnits() {
            return units;
        }

        public void setUnits(String units) {
            this.units = units;
        }

        public String getSellingPrice() {
            return sellingPrice;
        }

        public void setSellingPrice(String sellingPrice) {
            this.sellingPrice = sellingPrice;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getTax() {
            return tax;
        }

        public void setTax(String tax) {
            this.tax = tax;
        }

        public String getHsn() {
            return hsn;
        }

        public void setHsn(String hsn) {
            this.hsn = hsn;
        }

    }

    public class Shiprocket {
        @SerializedName("order_id")
        @Expose
        private String orderId;
        @SerializedName("order_date")
        @Expose
        private String orderDate;
        @SerializedName("pickup_location")
        @Expose
        private String pickupLocation;
        @SerializedName("channel_id")
        @Expose
        private Integer channelId;
        @SerializedName("billing_customer_name")
        @Expose
        private String billingCustomerName;
        @SerializedName("billing_last_name")
        @Expose
        private String billingLastName;
        @SerializedName("billing_address")
        @Expose
        private String billingAddress;
        @SerializedName("billing_address_2")
        @Expose
        private String billingAddress2;
        @SerializedName("billing_city")
        @Expose
        private String billingCity;
        @SerializedName("billing_pincode")
        @Expose
        private String billingPincode;
        @SerializedName("billing_state")
        @Expose
        private String billingState;
        @SerializedName("billing_country")
        @Expose
        private String billingCountry;
        @SerializedName("billing_email")
        @Expose
        private String billingEmail;
        @SerializedName("billing_phone")
        @Expose
        private String billingPhone;
        @SerializedName("shipping_is_billing")
        @Expose
        private Boolean shippingIsBilling;
        @SerializedName("shipping_customer_name")
        @Expose
        private String shippingCustomerName;
        @SerializedName("shipping_last_name")
        @Expose
        private String shippingLastName;
        @SerializedName("shipping_address")
        @Expose
        private String shippingAddress;
        @SerializedName("shipping_address_2")
        @Expose
        private String shippingAddress2;
        @SerializedName("shipping_city")
        @Expose
        private String shippingCity;
        @SerializedName("shipping_pincode")
        @Expose
        private String shippingPincode;
        @SerializedName("shipping_country")
        @Expose
        private String shippingCountry;
        @SerializedName("shipping_state")
        @Expose
        private String shippingState;
        @SerializedName("shipping_email")
        @Expose
        private String shippingEmail;
        @SerializedName("shipping_phone")
        @Expose
        private String shippingPhone;
        @SerializedName("latitute")
        @Expose
        private String latitute;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("order_items")
        @Expose
        private List<OrderItem> orderItems = null;
        @SerializedName("payment_method")
        @Expose
        private String paymentMethod;
        @SerializedName("giftwrap_charges")
        @Expose
        private Integer giftwrapCharges;
        @SerializedName("transaction_charges")
        @Expose
        private Integer transactionCharges;
        @SerializedName("total_discount")
        @Expose
        private Integer totalDiscount;
        @SerializedName("sub_total")
        @Expose
        private Integer subTotal;
        @SerializedName("weight")
        @Expose
        private Double weight;
        @SerializedName("length")
        @Expose
        private Double length;
        @SerializedName("breadth")
        @Expose
        private Double breadth;
        @SerializedName("height")
        @Expose
        private Double height;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(String orderDate) {
            this.orderDate = orderDate;
        }

        public String getPickupLocation() {
            return pickupLocation;
        }

        public void setPickupLocation(String pickupLocation) {
            this.pickupLocation = pickupLocation;
        }

        public Integer getChannelId() {
            return channelId;
        }

        public void setChannelId(Integer channelId) {
            this.channelId = channelId;
        }

        public String getBillingCustomerName() {
            return billingCustomerName;
        }

        public void setBillingCustomerName(String billingCustomerName) {
            this.billingCustomerName = billingCustomerName;
        }

        public String getBillingLastName() {
            return billingLastName;
        }

        public void setBillingLastName(String billingLastName) {
            this.billingLastName = billingLastName;
        }

        public String getBillingAddress() {
            return billingAddress;
        }

        public void setBillingAddress(String billingAddress) {
            this.billingAddress = billingAddress;
        }

        public String getBillingAddress2() {
            return billingAddress2;
        }

        public void setBillingAddress2(String billingAddress2) {
            this.billingAddress2 = billingAddress2;
        }

        public String getBillingCity() {
            return billingCity;
        }

        public void setBillingCity(String billingCity) {
            this.billingCity = billingCity;
        }

        public String getBillingPincode() {
            return billingPincode;
        }

        public void setBillingPincode(String billingPincode) {
            this.billingPincode = billingPincode;
        }

        public String getBillingState() {
            return billingState;
        }

        public void setBillingState(String billingState) {
            this.billingState = billingState;
        }

        public String getBillingCountry() {
            return billingCountry;
        }

        public void setBillingCountry(String billingCountry) {
            this.billingCountry = billingCountry;
        }

        public String getBillingEmail() {
            return billingEmail;
        }

        public void setBillingEmail(String billingEmail) {
            this.billingEmail = billingEmail;
        }

        public String getBillingPhone() {
            return billingPhone;
        }

        public void setBillingPhone(String billingPhone) {
            this.billingPhone = billingPhone;
        }

        public Boolean getShippingIsBilling() {
            return shippingIsBilling;
        }

        public void setShippingIsBilling(Boolean shippingIsBilling) {
            this.shippingIsBilling = shippingIsBilling;
        }

        public String getShippingCustomerName() {
            return shippingCustomerName;
        }

        public void setShippingCustomerName(String shippingCustomerName) {
            this.shippingCustomerName = shippingCustomerName;
        }

        public String getShippingLastName() {
            return shippingLastName;
        }

        public void setShippingLastName(String shippingLastName) {
            this.shippingLastName = shippingLastName;
        }

        public String getShippingAddress() {
            return shippingAddress;
        }

        public void setShippingAddress(String shippingAddress) {
            this.shippingAddress = shippingAddress;
        }

        public String getShippingAddress2() {
            return shippingAddress2;
        }

        public void setShippingAddress2(String shippingAddress2) {
            this.shippingAddress2 = shippingAddress2;
        }

        public String getShippingCity() {
            return shippingCity;
        }

        public void setShippingCity(String shippingCity) {
            this.shippingCity = shippingCity;
        }

        public String getShippingPincode() {
            return shippingPincode;
        }

        public void setShippingPincode(String shippingPincode) {
            this.shippingPincode = shippingPincode;
        }

        public String getShippingCountry() {
            return shippingCountry;
        }

        public void setShippingCountry(String shippingCountry) {
            this.shippingCountry = shippingCountry;
        }

        public String getShippingState() {
            return shippingState;
        }

        public void setShippingState(String shippingState) {
            this.shippingState = shippingState;
        }

        public String getShippingEmail() {
            return shippingEmail;
        }

        public void setShippingEmail(String shippingEmail) {
            this.shippingEmail = shippingEmail;
        }

        public String getShippingPhone() {
            return shippingPhone;
        }

        public void setShippingPhone(String shippingPhone) {
            this.shippingPhone = shippingPhone;
        }

        public String getLatitute() {
            return latitute;
        }

        public void setLatitute(String latitute) {
            this.latitute = latitute;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public List<OrderItem> getOrderItems() {
            return orderItems;
        }

        public void setOrderItems(List<OrderItem> orderItems) {
            this.orderItems = orderItems;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public Integer getGiftwrapCharges() {
            return giftwrapCharges;
        }

        public void setGiftwrapCharges(Integer giftwrapCharges) {
            this.giftwrapCharges = giftwrapCharges;
        }

        public Integer getTransactionCharges() {
            return transactionCharges;
        }

        public void setTransactionCharges(Integer transactionCharges) {
            this.transactionCharges = transactionCharges;
        }

        public Integer getTotalDiscount() {
            return totalDiscount;
        }

        public void setTotalDiscount(Integer totalDiscount) {
            this.totalDiscount = totalDiscount;
        }

        public Integer getSubTotal() {
            return subTotal;
        }

        public void setSubTotal(Integer subTotal) {
            this.subTotal = subTotal;
        }

        public Double getWeight() {
            return weight;
        }

        public void setWeight(Double weight) {
            this.weight = weight;
        }

        public Double getLength() {
            return length;
        }

        public void setLength(Double length) {
            this.length = length;
        }

        public Double getBreadth() {
            return breadth;
        }

        public void setBreadth(Double breadth) {
            this.breadth = breadth;
        }

        public Double getHeight() {
            return height;
        }

        public void setHeight(Double height) {
            this.height = height;
        }

    }

}