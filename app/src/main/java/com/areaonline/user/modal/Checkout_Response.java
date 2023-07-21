package com.areaonline.user.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Checkout_Response {

    public class AvailableCourierCompany {

        @SerializedName("base_courier_id")
        @Expose
        private Object baseCourierId;
        @SerializedName("blocked")
        @Expose
        private Integer blocked;
        @SerializedName("call_before_delivery")
        @Expose
        private String callBeforeDelivery;
        @SerializedName("cod")
        @Expose
        private Integer cod;
        @SerializedName("cod_charges")
        @Expose
        private Integer codCharges;
        @SerializedName("cod_multiplier")
        @Expose
        private Integer codMultiplier;
        @SerializedName("courier_company_id")
        @Expose
        private Integer courierCompanyId;
        @SerializedName("courier_name")
        @Expose
        private String courierName;
        @SerializedName("courier_type")
        @Expose
        private String courierType;
        @SerializedName("coverage_charges")
        @Expose
        private Integer coverageCharges;
        @SerializedName("delivery_boy_contact")
        @Expose
        private String deliveryBoyContact;
        @SerializedName("delivery_performance")
        @Expose
        private Double deliveryPerformance;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("entry_tax")
        @Expose
        private Integer entryTax;
        @SerializedName("estimated_delivery_days")
        @Expose
        private String estimatedDeliveryDays;
        @SerializedName("etd")
        @Expose
        private String etd;
        @SerializedName("etd_hours")
        @Expose
        private Integer etdHours;
        @SerializedName("freight_charge")
        @Expose
        private Double freightCharge;
        @SerializedName("is_hyperlocal")
        @Expose
        private Boolean isHyperlocal;
        @SerializedName("is_international")
        @Expose
        private Integer isInternational;
        @SerializedName("is_rto_address_available")
        @Expose
        private Boolean isRtoAddressAvailable;
        @SerializedName("is_surface")
        @Expose
        private Boolean isSurface;
        @SerializedName("min_weight")
        @Expose
        private Double minWeight;
        @SerializedName("mode")
        @Expose
        private Integer mode;
        @SerializedName("note")
        @Expose
        private String note;
        @SerializedName("pickup_availability")
        @Expose
        private Integer pickupAvailability;
        @SerializedName("pickup_performance")
        @Expose
        private Double pickupPerformance;
        @SerializedName("pod_available")
        @Expose
        private String podAvailable;
        @SerializedName("rate")
        @Expose
        private Double rate;
        @SerializedName("rating")
        @Expose
        private Double rating;
        @SerializedName("realtime_tracking")
        @Expose
        private String realtimeTracking;
        @SerializedName("rto_charges")
        @Expose
        private String rtoCharges;
        @SerializedName("rto_performance")
        @Expose
        private Double rtoPerformance;
        @SerializedName("seconds_left_for_pickup")
        @Expose
        private Integer secondsLeftForPickup;
        @SerializedName("tracking_performance")
        @Expose
        private Integer trackingPerformance;
        @SerializedName("weight_cases")
        @Expose
        private Integer weightCases;

        public Object getBaseCourierId() {
            return baseCourierId;
        }

        public void setBaseCourierId(Object baseCourierId) {
            this.baseCourierId = baseCourierId;
        }

        public Integer getBlocked() {
            return blocked;
        }

        public void setBlocked(Integer blocked) {
            this.blocked = blocked;
        }

        public String getCallBeforeDelivery() {
            return callBeforeDelivery;
        }

        public void setCallBeforeDelivery(String callBeforeDelivery) {
            this.callBeforeDelivery = callBeforeDelivery;
        }

        public Integer getCod() {
            return cod;
        }

        public void setCod(Integer cod) {
            this.cod = cod;
        }

        public Integer getCodCharges() {
            return codCharges;
        }

        public void setCodCharges(Integer codCharges) {
            this.codCharges = codCharges;
        }

        public Integer getCodMultiplier() {
            return codMultiplier;
        }

        public void setCodMultiplier(Integer codMultiplier) {
            this.codMultiplier = codMultiplier;
        }

        public Integer getCourierCompanyId() {
            return courierCompanyId;
        }

        public void setCourierCompanyId(Integer courierCompanyId) {
            this.courierCompanyId = courierCompanyId;
        }

        public String getCourierName() {
            return courierName;
        }

        public void setCourierName(String courierName) {
            this.courierName = courierName;
        }

        public String getCourierType() {
            return courierType;
        }

        public void setCourierType(String courierType) {
            this.courierType = courierType;
        }

        public Integer getCoverageCharges() {
            return coverageCharges;
        }

        public void setCoverageCharges(Integer coverageCharges) {
            this.coverageCharges = coverageCharges;
        }

        public String getDeliveryBoyContact() {
            return deliveryBoyContact;
        }

        public void setDeliveryBoyContact(String deliveryBoyContact) {
            this.deliveryBoyContact = deliveryBoyContact;
        }

        public Double getDeliveryPerformance() {
            return deliveryPerformance;
        }

        public void setDeliveryPerformance(Double deliveryPerformance) {
            this.deliveryPerformance = deliveryPerformance;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getEntryTax() {
            return entryTax;
        }

        public void setEntryTax(Integer entryTax) {
            this.entryTax = entryTax;
        }

        public String getEstimatedDeliveryDays() {
            return estimatedDeliveryDays;
        }

        public void setEstimatedDeliveryDays(String estimatedDeliveryDays) {
            this.estimatedDeliveryDays = estimatedDeliveryDays;
        }

        public String getEtd() {
            return etd;
        }

        public void setEtd(String etd) {
            this.etd = etd;
        }

        public Integer getEtdHours() {
            return etdHours;
        }

        public void setEtdHours(Integer etdHours) {
            this.etdHours = etdHours;
        }

        public Double getFreightCharge() {
            return freightCharge;
        }

        public void setFreightCharge(Double freightCharge) {
            this.freightCharge = freightCharge;
        }

        public Boolean getIsHyperlocal() {
            return isHyperlocal;
        }

        public void setIsHyperlocal(Boolean isHyperlocal) {
            this.isHyperlocal = isHyperlocal;
        }

        public Integer getIsInternational() {
            return isInternational;
        }

        public void setIsInternational(Integer isInternational) {
            this.isInternational = isInternational;
        }

        public Boolean getIsRtoAddressAvailable() {
            return isRtoAddressAvailable;
        }

        public void setIsRtoAddressAvailable(Boolean isRtoAddressAvailable) {
            this.isRtoAddressAvailable = isRtoAddressAvailable;
        }

        public Boolean getIsSurface() {
            return isSurface;
        }

        public void setIsSurface(Boolean isSurface) {
            this.isSurface = isSurface;
        }

        public Double getMinWeight() {
            return minWeight;
        }

        public void setMinWeight(Double minWeight) {
            this.minWeight = minWeight;
        }

        public Integer getMode() {
            return mode;
        }

        public void setMode(Integer mode) {
            this.mode = mode;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public Integer getPickupAvailability() {
            return pickupAvailability;
        }

        public void setPickupAvailability(Integer pickupAvailability) {
            this.pickupAvailability = pickupAvailability;
        }

        public Double getPickupPerformance() {
            return pickupPerformance;
        }

        public void setPickupPerformance(Double pickupPerformance) {
            this.pickupPerformance = pickupPerformance;
        }

        public String getPodAvailable() {
            return podAvailable;
        }

        public void setPodAvailable(String podAvailable) {
            this.podAvailable = podAvailable;
        }

        public Double getRate() {
            return rate;
        }

        public void setRate(Double rate) {
            this.rate = rate;
        }

        public Double getRating() {
            return rating;
        }

        public void setRating(Double rating) {
            this.rating = rating;
        }

        public String getRealtimeTracking() {
            return realtimeTracking;
        }

        public void setRealtimeTracking(String realtimeTracking) {
            this.realtimeTracking = realtimeTracking;
        }

        public String getRtoCharges() {
            return rtoCharges;
        }

        public void setRtoCharges(String rtoCharges) {
            this.rtoCharges = rtoCharges;
        }

        public Double getRtoPerformance() {
            return rtoPerformance;
        }

        public void setRtoPerformance(Double rtoPerformance) {
            this.rtoPerformance = rtoPerformance;
        }

        public Integer getSecondsLeftForPickup() {
            return secondsLeftForPickup;
        }

        public void setSecondsLeftForPickup(Integer secondsLeftForPickup) {
            this.secondsLeftForPickup = secondsLeftForPickup;
        }

        public Integer getTrackingPerformance() {
            return trackingPerformance;
        }

        public void setTrackingPerformance(Integer trackingPerformance) {
            this.trackingPerformance = trackingPerformance;
        }

        public Integer getWeightCases() {
            return weightCases;
        }

        public void setWeightCases(Integer weightCases) {
            this.weightCases = weightCases;
        }

    }

    public class CovidZones {

        @SerializedName("delivery_zone")
        @Expose
        private Object deliveryZone;
        @SerializedName("pickup_zone")
        @Expose
        private Object pickupZone;

        public Object getDeliveryZone() {
            return deliveryZone;
        }

        public void setDeliveryZone(Object deliveryZone) {
            this.deliveryZone = deliveryZone;
        }

        public Object getPickupZone() {
            return pickupZone;
        }

        public void setPickupZone(Object pickupZone) {
            this.pickupZone = pickupZone;
        }

    }

    public class Data {

        @SerializedName("order_id")
        @Expose
        private Integer orderId;
        @SerializedName("shipment_id")
        @Expose
        private Integer shipmentId;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("status_code")
        @Expose
        private Integer statusCode;
        @SerializedName("onboarding_completed_now")
        @Expose
        private Integer onboardingCompletedNow;
        @SerializedName("awb_code")
        @Expose
        private String awbCode;
        @SerializedName("courier_company_id")
        @Expose
        private String courierCompanyId;
        @SerializedName("courier_name")
        @Expose
        private String courierName;
        @SerializedName("services")
        @Expose
        private Services services;

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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Integer getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(Integer statusCode) {
            this.statusCode = statusCode;
        }

        public Integer getOnboardingCompletedNow() {
            return onboardingCompletedNow;
        }

        public void setOnboardingCompletedNow(Integer onboardingCompletedNow) {
            this.onboardingCompletedNow = onboardingCompletedNow;
        }

        public String getAwbCode() {
            return awbCode;
        }

        public void setAwbCode(String awbCode) {
            this.awbCode = awbCode;
        }

        public String getCourierCompanyId() {
            return courierCompanyId;
        }

        public void setCourierCompanyId(String courierCompanyId) {
            this.courierCompanyId = courierCompanyId;
        }

        public String getCourierName() {
            return courierName;
        }

        public void setCourierName(String courierName) {
            this.courierName = courierName;
        }

        public Services getServices() {
            return services;
        }

        public void setServices(Services services) {
            this.services = services;
        }

    }

    public class Data__1 {

        @SerializedName("available_courier_companies")
        @Expose
        private List<AvailableCourierCompany> availableCourierCompanies = null;
        @SerializedName("child_courier_id")
        @Expose
        private Object childCourierId;
        @SerializedName("is_recommendation_enabled")
        @Expose
        private Integer isRecommendationEnabled;
        @SerializedName("recommended_by")
        @Expose
        private RecommendedBy recommendedBy;
        @SerializedName("recommended_courier_company_id")
        @Expose
        private Integer recommendedCourierCompanyId;
        @SerializedName("shiprocket_recommended_courier_id")
        @Expose
        private Integer shiprocketRecommendedCourierId;

        public List<AvailableCourierCompany> getAvailableCourierCompanies() {
            return availableCourierCompanies;
        }

        public void setAvailableCourierCompanies(List<AvailableCourierCompany> availableCourierCompanies) {
            this.availableCourierCompanies = availableCourierCompanies;
        }

        public Object getChildCourierId() {
            return childCourierId;
        }

        public void setChildCourierId(Object childCourierId) {
            this.childCourierId = childCourierId;
        }

        public Integer getIsRecommendationEnabled() {
            return isRecommendationEnabled;
        }

        public void setIsRecommendationEnabled(Integer isRecommendationEnabled) {
            this.isRecommendationEnabled = isRecommendationEnabled;
        }

        public RecommendedBy getRecommendedBy() {
            return recommendedBy;
        }

        public void setRecommendedBy(RecommendedBy recommendedBy) {
            this.recommendedBy = recommendedBy;
        }

        public Integer getRecommendedCourierCompanyId() {
            return recommendedCourierCompanyId;
        }

        public void setRecommendedCourierCompanyId(Integer recommendedCourierCompanyId) {
            this.recommendedCourierCompanyId = recommendedCourierCompanyId;
        }

        public Integer getShiprocketRecommendedCourierId() {
            return shiprocketRecommendedCourierId;
        }

        public void setShiprocketRecommendedCourierId(Integer shiprocketRecommendedCourierId) {
            this.shiprocketRecommendedCourierId = shiprocketRecommendedCourierId;
        }

    }


    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private Data data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
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


    public class RecommendedBy {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("title")
        @Expose
        private String title;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

    }

    public class SellerAddress {

        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("address_2")
        @Expose
        private String address2;
        @SerializedName("address_type")
        @Expose
        private Object addressType;
        @SerializedName("alternate_phone")
        @Expose
        private Object alternatePhone;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("company_id")
        @Expose
        private Integer companyId;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("delhivery_clientware_id")
        @Expose
        private String delhiveryClientwareId;
        @SerializedName("delhivery_documents_250gm_clientware_id")
        @Expose
        private String delhiveryDocuments250gmClientwareId;
        @SerializedName("delhivery_documents_clientware_id")
        @Expose
        private String delhiveryDocumentsClientwareId;
        @SerializedName("delhivery_essential_5kg_clientware_id")
        @Expose
        private String delhiveryEssential5kgClientwareId;
        @SerializedName("delhivery_flash_air_clientware_id")
        @Expose
        private String delhiveryFlashAirClientwareId;
        @SerializedName("delhivery_surface_10kg_clientware_id")
        @Expose
        private String delhiverySurface10kgClientwareId;
        @SerializedName("delhivery_surface_20kg_clientware_id")
        @Expose
        private String delhiverySurface20kgClientwareId;
        @SerializedName("delhivery_surface_clientware_id")
        @Expose
        private String delhiverySurfaceClientwareId;
        @SerializedName("delhivery_surface_lite_clientware_id")
        @Expose
        private String delhiverySurfaceLiteClientwareId;
        @SerializedName("delhivery_surface_standard_clientware_id")
        @Expose
        private String delhiverySurfaceStandardClientwareId;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("extra_info")
        @Expose
        private String extraInfo;
        @SerializedName("gati_sg_customer_vendor_code")
        @Expose
        private Object gatiSgCustomerVendorCode;
        @SerializedName("gati_surface_10kg_customer_vendor_code")
        @Expose
        private Object gatiSurface10kgCustomerVendorCode;
        @SerializedName("gati_surface_customer_vendor_code")
        @Expose
        private Object gatiSurfaceCustomerVendorCode;
        @SerializedName("gstin")
        @Expose
        private Object gstin;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("invoice_prefix")
        @Expose
        private Object invoicePrefix;
        @SerializedName("invoice_serial")
        @Expose
        private Object invoiceSerial;
        @SerializedName("lat")
        @Expose
        private String lat;
        @SerializedName("long")
        @Expose
        private String _long;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("phone_verified")
        @Expose
        private Integer phoneVerified;
        @SerializedName("pickup_code")
        @Expose
        private String pickupCode;
        @SerializedName("pin_code")
        @Expose
        private String pinCode;
        @SerializedName("rto_address_id")
        @Expose
        private Object rtoAddressId;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("sx1_delhivery_surface_clientware_id")
        @Expose
        private Object sx1DelhiverySurfaceClientwareId;
        @SerializedName("sx2_delhivery_surface_clientware_id")
        @Expose
        private Object sx2DelhiverySurfaceClientwareId;
        @SerializedName("sx3_delhivery_surface_clientware_id")
        @Expose
        private Object sx3DelhiverySurfaceClientwareId;
        @SerializedName("sx4_delhivery_surface_clientware_id")
        @Expose
        private Object sx4DelhiverySurfaceClientwareId;
        @SerializedName("sx_delhivery_clientware_id")
        @Expose
        private Object sxDelhiveryClientwareId;
        @SerializedName("sx_delhivery_surface_clientware_id")
        @Expose
        private Object sxDelhiverySurfaceClientwareId;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("updated_on")
        @Expose
        private String updatedOn;
        @SerializedName("warehouse_code")
        @Expose
        private Object warehouseCode;

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

        public Object getAddressType() {
            return addressType;
        }

        public void setAddressType(Object addressType) {
            this.addressType = addressType;
        }

        public Object getAlternatePhone() {
            return alternatePhone;
        }

        public void setAlternatePhone(Object alternatePhone) {
            this.alternatePhone = alternatePhone;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Integer getCompanyId() {
            return companyId;
        }

        public void setCompanyId(Integer companyId) {
            this.companyId = companyId;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDelhiveryClientwareId() {
            return delhiveryClientwareId;
        }

        public void setDelhiveryClientwareId(String delhiveryClientwareId) {
            this.delhiveryClientwareId = delhiveryClientwareId;
        }

        public String getDelhiveryDocuments250gmClientwareId() {
            return delhiveryDocuments250gmClientwareId;
        }

        public void setDelhiveryDocuments250gmClientwareId(String delhiveryDocuments250gmClientwareId) {
            this.delhiveryDocuments250gmClientwareId = delhiveryDocuments250gmClientwareId;
        }

        public String getDelhiveryDocumentsClientwareId() {
            return delhiveryDocumentsClientwareId;
        }

        public void setDelhiveryDocumentsClientwareId(String delhiveryDocumentsClientwareId) {
            this.delhiveryDocumentsClientwareId = delhiveryDocumentsClientwareId;
        }

        public String getDelhiveryEssential5kgClientwareId() {
            return delhiveryEssential5kgClientwareId;
        }

        public void setDelhiveryEssential5kgClientwareId(String delhiveryEssential5kgClientwareId) {
            this.delhiveryEssential5kgClientwareId = delhiveryEssential5kgClientwareId;
        }

        public String getDelhiveryFlashAirClientwareId() {
            return delhiveryFlashAirClientwareId;
        }

        public void setDelhiveryFlashAirClientwareId(String delhiveryFlashAirClientwareId) {
            this.delhiveryFlashAirClientwareId = delhiveryFlashAirClientwareId;
        }

        public String getDelhiverySurface10kgClientwareId() {
            return delhiverySurface10kgClientwareId;
        }

        public void setDelhiverySurface10kgClientwareId(String delhiverySurface10kgClientwareId) {
            this.delhiverySurface10kgClientwareId = delhiverySurface10kgClientwareId;
        }

        public String getDelhiverySurface20kgClientwareId() {
            return delhiverySurface20kgClientwareId;
        }

        public void setDelhiverySurface20kgClientwareId(String delhiverySurface20kgClientwareId) {
            this.delhiverySurface20kgClientwareId = delhiverySurface20kgClientwareId;
        }

        public String getDelhiverySurfaceClientwareId() {
            return delhiverySurfaceClientwareId;
        }

        public void setDelhiverySurfaceClientwareId(String delhiverySurfaceClientwareId) {
            this.delhiverySurfaceClientwareId = delhiverySurfaceClientwareId;
        }

        public String getDelhiverySurfaceLiteClientwareId() {
            return delhiverySurfaceLiteClientwareId;
        }

        public void setDelhiverySurfaceLiteClientwareId(String delhiverySurfaceLiteClientwareId) {
            this.delhiverySurfaceLiteClientwareId = delhiverySurfaceLiteClientwareId;
        }

        public String getDelhiverySurfaceStandardClientwareId() {
            return delhiverySurfaceStandardClientwareId;
        }

        public void setDelhiverySurfaceStandardClientwareId(String delhiverySurfaceStandardClientwareId) {
            this.delhiverySurfaceStandardClientwareId = delhiverySurfaceStandardClientwareId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getExtraInfo() {
            return extraInfo;
        }

        public void setExtraInfo(String extraInfo) {
            this.extraInfo = extraInfo;
        }

        public Object getGatiSgCustomerVendorCode() {
            return gatiSgCustomerVendorCode;
        }

        public void setGatiSgCustomerVendorCode(Object gatiSgCustomerVendorCode) {
            this.gatiSgCustomerVendorCode = gatiSgCustomerVendorCode;
        }

        public Object getGatiSurface10kgCustomerVendorCode() {
            return gatiSurface10kgCustomerVendorCode;
        }

        public void setGatiSurface10kgCustomerVendorCode(Object gatiSurface10kgCustomerVendorCode) {
            this.gatiSurface10kgCustomerVendorCode = gatiSurface10kgCustomerVendorCode;
        }

        public Object getGatiSurfaceCustomerVendorCode() {
            return gatiSurfaceCustomerVendorCode;
        }

        public void setGatiSurfaceCustomerVendorCode(Object gatiSurfaceCustomerVendorCode) {
            this.gatiSurfaceCustomerVendorCode = gatiSurfaceCustomerVendorCode;
        }

        public Object getGstin() {
            return gstin;
        }

        public void setGstin(Object gstin) {
            this.gstin = gstin;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Object getInvoicePrefix() {
            return invoicePrefix;
        }

        public void setInvoicePrefix(Object invoicePrefix) {
            this.invoicePrefix = invoicePrefix;
        }

        public Object getInvoiceSerial() {
            return invoiceSerial;
        }

        public void setInvoiceSerial(Object invoiceSerial) {
            this.invoiceSerial = invoiceSerial;
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

        public Integer getPhoneVerified() {
            return phoneVerified;
        }

        public void setPhoneVerified(Integer phoneVerified) {
            this.phoneVerified = phoneVerified;
        }

        public String getPickupCode() {
            return pickupCode;
        }

        public void setPickupCode(String pickupCode) {
            this.pickupCode = pickupCode;
        }

        public String getPinCode() {
            return pinCode;
        }

        public void setPinCode(String pinCode) {
            this.pinCode = pinCode;
        }

        public Object getRtoAddressId() {
            return rtoAddressId;
        }

        public void setRtoAddressId(Object rtoAddressId) {
            this.rtoAddressId = rtoAddressId;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Object getSx1DelhiverySurfaceClientwareId() {
            return sx1DelhiverySurfaceClientwareId;
        }

        public void setSx1DelhiverySurfaceClientwareId(Object sx1DelhiverySurfaceClientwareId) {
            this.sx1DelhiverySurfaceClientwareId = sx1DelhiverySurfaceClientwareId;
        }

        public Object getSx2DelhiverySurfaceClientwareId() {
            return sx2DelhiverySurfaceClientwareId;
        }

        public void setSx2DelhiverySurfaceClientwareId(Object sx2DelhiverySurfaceClientwareId) {
            this.sx2DelhiverySurfaceClientwareId = sx2DelhiverySurfaceClientwareId;
        }

        public Object getSx3DelhiverySurfaceClientwareId() {
            return sx3DelhiverySurfaceClientwareId;
        }

        public void setSx3DelhiverySurfaceClientwareId(Object sx3DelhiverySurfaceClientwareId) {
            this.sx3DelhiverySurfaceClientwareId = sx3DelhiverySurfaceClientwareId;
        }

        public Object getSx4DelhiverySurfaceClientwareId() {
            return sx4DelhiverySurfaceClientwareId;
        }

        public void setSx4DelhiverySurfaceClientwareId(Object sx4DelhiverySurfaceClientwareId) {
            this.sx4DelhiverySurfaceClientwareId = sx4DelhiverySurfaceClientwareId;
        }

        public Object getSxDelhiveryClientwareId() {
            return sxDelhiveryClientwareId;
        }

        public void setSxDelhiveryClientwareId(Object sxDelhiveryClientwareId) {
            this.sxDelhiveryClientwareId = sxDelhiveryClientwareId;
        }

        public Object getSxDelhiverySurfaceClientwareId() {
            return sxDelhiverySurfaceClientwareId;
        }

        public void setSxDelhiverySurfaceClientwareId(Object sxDelhiverySurfaceClientwareId) {
            this.sxDelhiverySurfaceClientwareId = sxDelhiverySurfaceClientwareId;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getUpdatedOn() {
            return updatedOn;
        }

        public void setUpdatedOn(String updatedOn) {
            this.updatedOn = updatedOn;
        }

        public Object getWarehouseCode() {
            return warehouseCode;
        }

        public void setWarehouseCode(Object warehouseCode) {
            this.warehouseCode = warehouseCode;
        }

    }

    public class Services {

        @SerializedName("company_auto_shipment_insurance_setting")
        @Expose
        private Boolean companyAutoShipmentInsuranceSetting;
        @SerializedName("covid_zones")
        @Expose
        private CovidZones covidZones;
        @SerializedName("currency")
        @Expose
        private String currency;
        @SerializedName("data")
        @Expose
        private Data__1 data;
        @SerializedName("dg_courier")
        @Expose
        private Integer dgCourier;
        @SerializedName("eligible_for_insurance")
        @Expose
        private Integer eligibleForInsurance;
        @SerializedName("insurace_opted_at_order_creation")
        @Expose
        private Boolean insuraceOptedAtOrderCreation;
        @SerializedName("is_allow_templatized_pricing")
        @Expose
        private Boolean isAllowTemplatizedPricing;
        @SerializedName("is_latlong")
        @Expose
        private Integer isLatlong;
        @SerializedName("label_generate_type")
        @Expose
        private Integer labelGenerateType;
        @SerializedName("seller_address")
        @Expose
        private SellerAddress sellerAddress;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("user_insurance_manadatory")
        @Expose
        private Boolean userInsuranceManadatory;

        public Boolean getCompanyAutoShipmentInsuranceSetting() {
            return companyAutoShipmentInsuranceSetting;
        }

        public void setCompanyAutoShipmentInsuranceSetting(Boolean companyAutoShipmentInsuranceSetting) {
            this.companyAutoShipmentInsuranceSetting = companyAutoShipmentInsuranceSetting;
        }

        public CovidZones getCovidZones() {
            return covidZones;
        }

        public void setCovidZones(CovidZones covidZones) {
            this.covidZones = covidZones;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public Data__1 getData() {
            return data;
        }

        public void setData(Data__1 data) {
            this.data = data;
        }

        public Integer getDgCourier() {
            return dgCourier;
        }

        public void setDgCourier(Integer dgCourier) {
            this.dgCourier = dgCourier;
        }

        public Integer getEligibleForInsurance() {
            return eligibleForInsurance;
        }

        public void setEligibleForInsurance(Integer eligibleForInsurance) {
            this.eligibleForInsurance = eligibleForInsurance;
        }

        public Boolean getInsuraceOptedAtOrderCreation() {
            return insuraceOptedAtOrderCreation;
        }

        public void setInsuraceOptedAtOrderCreation(Boolean insuraceOptedAtOrderCreation) {
            this.insuraceOptedAtOrderCreation = insuraceOptedAtOrderCreation;
        }

        public Boolean getIsAllowTemplatizedPricing() {
            return isAllowTemplatizedPricing;
        }

        public void setIsAllowTemplatizedPricing(Boolean isAllowTemplatizedPricing) {
            this.isAllowTemplatizedPricing = isAllowTemplatizedPricing;
        }

        public Integer getIsLatlong() {
            return isLatlong;
        }

        public void setIsLatlong(Integer isLatlong) {
            this.isLatlong = isLatlong;
        }

        public Integer getLabelGenerateType() {
            return labelGenerateType;
        }

        public void setLabelGenerateType(Integer labelGenerateType) {
            this.labelGenerateType = labelGenerateType;
        }

        public SellerAddress getSellerAddress() {
            return sellerAddress;
        }

        public void setSellerAddress(SellerAddress sellerAddress) {
            this.sellerAddress = sellerAddress;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Boolean getUserInsuranceManadatory() {
            return userInsuranceManadatory;
        }

        public void setUserInsuranceManadatory(Boolean userInsuranceManadatory) {
            this.userInsuranceManadatory = userInsuranceManadatory;
        }

    }
}