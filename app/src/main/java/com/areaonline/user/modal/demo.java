package com.areaonline.user.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class demo {

    public class AvailableCourierCompany {

        @SerializedName("base_courier_id")
        @Expose
        private Object baseCourierId;
        @SerializedName("courier_type")
        @Expose
        private String courierType;
        @SerializedName("courier_company_id")
        @Expose
        private Integer courierCompanyId;
        @SerializedName("courier_name")
        @Expose
        private String courierName;
        @SerializedName("is_rto_address_available")
        @Expose
        private Boolean isRtoAddressAvailable;
        @SerializedName("rate")
        @Expose
        private Double rate;
        @SerializedName("is_custom_rate")
        @Expose
        private Integer isCustomRate;
        @SerializedName("cod_multiplier")
        @Expose
        private Integer codMultiplier;
        @SerializedName("cod_charges")
        @Expose
        private Integer codCharges;
        @SerializedName("freight_charge")
        @Expose
        private Double freightCharge;
        @SerializedName("rto_charges")
        @Expose
        private Double rtoCharges;
        @SerializedName("coverage_charges")
        @Expose
        private Integer coverageCharges;
        @SerializedName("is_surface")
        @Expose
        private Boolean isSurface;
        @SerializedName("rating")
        @Expose
        private Double rating;
        @SerializedName("rto_performance")
        @Expose
        private Double rtoPerformance;
        @SerializedName("pickup_performance")
        @Expose
        private Double pickupPerformance;
        @SerializedName("delivery_performance")
        @Expose
        private Double deliveryPerformance;
        @SerializedName("cod")
        @Expose
        private Integer cod;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("mode")
        @Expose
        private Integer mode;
        @SerializedName("blocked")
        @Expose
        private Integer blocked;
        @SerializedName("suppression_dates")
        @Expose
        private List<Object> suppressionDates = null;
        @SerializedName("min_weight")
        @Expose
        private Integer minWeight;
        @SerializedName("is_international")
        @Expose
        private Integer isInternational;
        @SerializedName("is_hyperlocal")
        @Expose
        private Boolean isHyperlocal;
        @SerializedName("entry_tax")
        @Expose
        private Integer entryTax;
        @SerializedName("cutoff_time")
        @Expose
        private String cutoffTime;
        @SerializedName("pickup_availability")
        @Expose
        private Integer pickupAvailability;
        @SerializedName("seconds_left_for_pickup")
        @Expose
        private Integer secondsLeftForPickup;
        @SerializedName("suppress_text")
        @Expose
        private String suppressText;
        @SerializedName("pickup_supress_hours")
        @Expose
        private Integer pickupSupressHours;
        @SerializedName("suppress_date")
        @Expose
        private String suppressDate;
        @SerializedName("supress_hours")
        @Expose
        private Integer supressHours;
        @SerializedName("etd_hours")
        @Expose
        private Integer etdHours;
        @SerializedName("etd")
        @Expose
        private String etd;
        @SerializedName("estimated_delivery_days")
        @Expose
        private String estimatedDeliveryDays;
        @SerializedName("tracking_performance")
        @Expose
        private Double trackingPerformance;
        @SerializedName("weight_cases")
        @Expose
        private Double weightCases;
        @SerializedName("realtime_tracking")
        @Expose
        private String realtimeTracking;
        @SerializedName("delivery_boy_contact")
        @Expose
        private String deliveryBoyContact;
        @SerializedName("pod_available")
        @Expose
        private String podAvailable;
        @SerializedName("call_before_delivery")
        @Expose
        private String callBeforeDelivery;
        @SerializedName("rank")
        @Expose
        private String rank;
        @SerializedName("cost")
        @Expose
        private String cost;
        @SerializedName("edd")
        @Expose
        private String edd;
        @SerializedName("base_weight")
        @Expose
        private String baseWeight;
        @SerializedName("pickup_priority")
        @Expose
        private String pickupPriority;
        @SerializedName("qc_courier")
        @Expose
        private Integer qcCourier;
        @SerializedName("odablock")
        @Expose
        private Boolean odablock;

        public Object getBaseCourierId() {
            return baseCourierId;
        }

        public void setBaseCourierId(Object baseCourierId) {
            this.baseCourierId = baseCourierId;
        }

        public String getCourierType() {
            return courierType;
        }

        public void setCourierType(String courierType) {
            this.courierType = courierType;
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

        public Boolean getIsRtoAddressAvailable() {
            return isRtoAddressAvailable;
        }

        public void setIsRtoAddressAvailable(Boolean isRtoAddressAvailable) {
            this.isRtoAddressAvailable = isRtoAddressAvailable;
        }

        public Double getRate() {
            return rate;
        }

        public void setRate(Double rate) {
            this.rate = rate;
        }

        public Integer getIsCustomRate() {
            return isCustomRate;
        }

        public void setIsCustomRate(Integer isCustomRate) {
            this.isCustomRate = isCustomRate;
        }

        public Integer getCodMultiplier() {
            return codMultiplier;
        }

        public void setCodMultiplier(Integer codMultiplier) {
            this.codMultiplier = codMultiplier;
        }

        public Integer getCodCharges() {
            return codCharges;
        }

        public void setCodCharges(Integer codCharges) {
            this.codCharges = codCharges;
        }

        public Double getFreightCharge() {
            return freightCharge;
        }

        public void setFreightCharge(Double freightCharge) {
            this.freightCharge = freightCharge;
        }

        public Double getRtoCharges() {
            return rtoCharges;
        }

        public void setRtoCharges(Double rtoCharges) {
            this.rtoCharges = rtoCharges;
        }

        public Integer getCoverageCharges() {
            return coverageCharges;
        }

        public void setCoverageCharges(Integer coverageCharges) {
            this.coverageCharges = coverageCharges;
        }

        public Boolean getIsSurface() {
            return isSurface;
        }

        public void setIsSurface(Boolean isSurface) {
            this.isSurface = isSurface;
        }

        public Double getRating() {
            return rating;
        }

        public void setRating(Double rating) {
            this.rating = rating;
        }

        public Double getRtoPerformance() {
            return rtoPerformance;
        }

        public void setRtoPerformance(Double rtoPerformance) {
            this.rtoPerformance = rtoPerformance;
        }

        public Double getPickupPerformance() {
            return pickupPerformance;
        }

        public void setPickupPerformance(Double pickupPerformance) {
            this.pickupPerformance = pickupPerformance;
        }

        public Double getDeliveryPerformance() {
            return deliveryPerformance;
        }

        public void setDeliveryPerformance(Double deliveryPerformance) {
            this.deliveryPerformance = deliveryPerformance;
        }

        public Integer getCod() {
            return cod;
        }

        public void setCod(Integer cod) {
            this.cod = cod;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getMode() {
            return mode;
        }

        public void setMode(Integer mode) {
            this.mode = mode;
        }

        public Integer getBlocked() {
            return blocked;
        }

        public void setBlocked(Integer blocked) {
            this.blocked = blocked;
        }

        public List<Object> getSuppressionDates() {
            return suppressionDates;
        }

        public void setSuppressionDates(List<Object> suppressionDates) {
            this.suppressionDates = suppressionDates;
        }

        public Integer getMinWeight() {
            return minWeight;
        }

        public void setMinWeight(Integer minWeight) {
            this.minWeight = minWeight;
        }

        public Integer getIsInternational() {
            return isInternational;
        }

        public void setIsInternational(Integer isInternational) {
            this.isInternational = isInternational;
        }

        public Boolean getIsHyperlocal() {
            return isHyperlocal;
        }

        public void setIsHyperlocal(Boolean isHyperlocal) {
            this.isHyperlocal = isHyperlocal;
        }

        public Integer getEntryTax() {
            return entryTax;
        }

        public void setEntryTax(Integer entryTax) {
            this.entryTax = entryTax;
        }

        public String getCutoffTime() {
            return cutoffTime;
        }

        public void setCutoffTime(String cutoffTime) {
            this.cutoffTime = cutoffTime;
        }

        public Integer getPickupAvailability() {
            return pickupAvailability;
        }

        public void setPickupAvailability(Integer pickupAvailability) {
            this.pickupAvailability = pickupAvailability;
        }

        public Integer getSecondsLeftForPickup() {
            return secondsLeftForPickup;
        }

        public void setSecondsLeftForPickup(Integer secondsLeftForPickup) {
            this.secondsLeftForPickup = secondsLeftForPickup;
        }

        public String getSuppressText() {
            return suppressText;
        }

        public void setSuppressText(String suppressText) {
            this.suppressText = suppressText;
        }

        public Integer getPickupSupressHours() {
            return pickupSupressHours;
        }

        public void setPickupSupressHours(Integer pickupSupressHours) {
            this.pickupSupressHours = pickupSupressHours;
        }

        public String getSuppressDate() {
            return suppressDate;
        }

        public void setSuppressDate(String suppressDate) {
            this.suppressDate = suppressDate;
        }

        public Integer getSupressHours() {
            return supressHours;
        }

        public void setSupressHours(Integer supressHours) {
            this.supressHours = supressHours;
        }

        public Integer getEtdHours() {
            return etdHours;
        }

        public void setEtdHours(Integer etdHours) {
            this.etdHours = etdHours;
        }

        public String getEtd() {
            return etd;
        }

        public void setEtd(String etd) {
            this.etd = etd;
        }

        public String getEstimatedDeliveryDays() {
            return estimatedDeliveryDays;
        }

        public void setEstimatedDeliveryDays(String estimatedDeliveryDays) {
            this.estimatedDeliveryDays = estimatedDeliveryDays;
        }

        public Double getTrackingPerformance() {
            return trackingPerformance;
        }

        public void setTrackingPerformance(Double trackingPerformance) {
            this.trackingPerformance = trackingPerformance;
        }

        public Double getWeightCases() {
            return weightCases;
        }

        public void setWeightCases(Double weightCases) {
            this.weightCases = weightCases;
        }

        public String getRealtimeTracking() {
            return realtimeTracking;
        }

        public void setRealtimeTracking(String realtimeTracking) {
            this.realtimeTracking = realtimeTracking;
        }

        public String getDeliveryBoyContact() {
            return deliveryBoyContact;
        }

        public void setDeliveryBoyContact(String deliveryBoyContact) {
            this.deliveryBoyContact = deliveryBoyContact;
        }

        public String getPodAvailable() {
            return podAvailable;
        }

        public void setPodAvailable(String podAvailable) {
            this.podAvailable = podAvailable;
        }

        public String getCallBeforeDelivery() {
            return callBeforeDelivery;
        }

        public void setCallBeforeDelivery(String callBeforeDelivery) {
            this.callBeforeDelivery = callBeforeDelivery;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }

        public String getEdd() {
            return edd;
        }

        public void setEdd(String edd) {
            this.edd = edd;
        }

        public String getBaseWeight() {
            return baseWeight;
        }

        public void setBaseWeight(String baseWeight) {
            this.baseWeight = baseWeight;
        }

        public String getPickupPriority() {
            return pickupPriority;
        }

        public void setPickupPriority(String pickupPriority) {
            this.pickupPriority = pickupPriority;
        }

        public Integer getQcCourier() {
            return qcCourier;
        }

        public void setQcCourier(Integer qcCourier) {
            this.qcCourier = qcCourier;
        }

        public Boolean getOdablock() {
            return odablock;
        }

        public void setOdablock(Boolean odablock) {
            this.odablock = odablock;
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

        @SerializedName("is_recommendation_enabled")
        @Expose
        private Integer isRecommendationEnabled;
        @SerializedName("recommended_by")
        @Expose
        private RecommendedBy recommendedBy;
        @SerializedName("child_courier_id")
        @Expose
        private Object childCourierId;
        @SerializedName("recommended_courier_company_id")
        @Expose
        private Integer recommendedCourierCompanyId;
        @SerializedName("shiprocket_recommended_courier_id")
        @Expose
        private Integer shiprocketRecommendedCourierId;
        @SerializedName("available_courier_companies")
        @Expose
        private List<AvailableCourierCompany> availableCourierCompanies = null;
        @SerializedName("recommendation_advance_rule")
        @Expose
        private Object recommendationAdvanceRule;
        @SerializedName("customer_selected_courier_id")
        @Expose
        private Object customerSelectedCourierId;

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

        public Object getChildCourierId() {
            return childCourierId;
        }

        public void setChildCourierId(Object childCourierId) {
            this.childCourierId = childCourierId;
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

        public List<AvailableCourierCompany> getAvailableCourierCompanies() {
            return availableCourierCompanies;
        }

        public void setAvailableCourierCompanies(List<AvailableCourierCompany> availableCourierCompanies) {
            this.availableCourierCompanies = availableCourierCompanies;
        }

        public Object getRecommendationAdvanceRule() {
            return recommendationAdvanceRule;
        }

        public void setRecommendationAdvanceRule(Object recommendationAdvanceRule) {
            this.recommendationAdvanceRule = recommendationAdvanceRule;
        }

        public Object getCustomerSelectedCourierId() {
            return customerSelectedCourierId;
        }

        public void setCustomerSelectedCourierId(Object customerSelectedCourierId) {
            this.customerSelectedCourierId = customerSelectedCourierId;
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

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("pickup_code")
        @Expose
        private String pickupCode;
        @SerializedName("gstin")
        @Expose
        private Object gstin;
        @SerializedName("invoice_prefix")
        @Expose
        private Object invoicePrefix;
        @SerializedName("invoice_serial")
        @Expose
        private Object invoiceSerial;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("address_2")
        @Expose
        private String address2;
        @SerializedName("address_type")
        @Expose
        private Object addressType;
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
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("phone_verified")
        @Expose
        private Integer phoneVerified;
        @SerializedName("alternate_phone")
        @Expose
        private Object alternatePhone;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("company_id")
        @Expose
        private Integer companyId;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("rto_address_id")
        @Expose
        private Object rtoAddressId;
        @SerializedName("delhivery_clientware_id")
        @Expose
        private String delhiveryClientwareId;
        @SerializedName("delhivery_surface_clientware_id")
        @Expose
        private String delhiverySurfaceClientwareId;
        @SerializedName("delhivery_surface_standard_clientware_id")
        @Expose
        private String delhiverySurfaceStandardClientwareId;
        @SerializedName("delhivery_surface_lite_clientware_id")
        @Expose
        private String delhiverySurfaceLiteClientwareId;
        @SerializedName("delhivery_surface_10kg_clientware_id")
        @Expose
        private String delhiverySurface10kgClientwareId;
        @SerializedName("delhivery_surface_20kg_clientware_id")
        @Expose
        private String delhiverySurface20kgClientwareId;
        @SerializedName("sx_delhivery_clientware_id")
        @Expose
        private Object sxDelhiveryClientwareId;
        @SerializedName("sx_delhivery_surface_clientware_id")
        @Expose
        private Object sxDelhiverySurfaceClientwareId;
        @SerializedName("sx4_delhivery_surface_clientware_id")
        @Expose
        private Object sx4DelhiverySurfaceClientwareId;
        @SerializedName("sx3_delhivery_surface_clientware_id")
        @Expose
        private Object sx3DelhiverySurfaceClientwareId;
        @SerializedName("sx2_delhivery_surface_clientware_id")
        @Expose
        private Object sx2DelhiverySurfaceClientwareId;
        @SerializedName("sx1_delhivery_surface_clientware_id")
        @Expose
        private Object sx1DelhiverySurfaceClientwareId;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("gati_surface_customer_vendor_code")
        @Expose
        private Object gatiSurfaceCustomerVendorCode;
        @SerializedName("gati_sg_customer_vendor_code")
        @Expose
        private Object gatiSgCustomerVendorCode;
        @SerializedName("delhivery_flash_air_clientware_id")
        @Expose
        private String delhiveryFlashAirClientwareId;
        @SerializedName("updated_on")
        @Expose
        private String updatedOn;
        @SerializedName("lat")
        @Expose
        private String lat;
        @SerializedName("long")
        @Expose
        private String _long;
        @SerializedName("delhivery_essential_5kg_clientware_id")
        @Expose
        private String delhiveryEssential5kgClientwareId;
        @SerializedName("warehouse_code")
        @Expose
        private Object warehouseCode;
        @SerializedName("gati_surface_10kg_customer_vendor_code")
        @Expose
        private Object gatiSurface10kgCustomerVendorCode;
        @SerializedName("extra_info")
        @Expose
        private String extraInfo;
        @SerializedName("delhivery_documents_clientware_id")
        @Expose
        private String delhiveryDocumentsClientwareId;
        @SerializedName("delhivery_documents_250gm_clientware_id")
        @Expose
        private String delhiveryDocuments250gmClientwareId;
        @SerializedName("rto_pin_code")
        @Expose
        private Object rtoPinCode;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getPickupCode() {
            return pickupCode;
        }

        public void setPickupCode(String pickupCode) {
            this.pickupCode = pickupCode;
        }

        public Object getGstin() {
            return gstin;
        }

        public void setGstin(Object gstin) {
            this.gstin = gstin;
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

        public Integer getPhoneVerified() {
            return phoneVerified;
        }

        public void setPhoneVerified(Integer phoneVerified) {
            this.phoneVerified = phoneVerified;
        }

        public Object getAlternatePhone() {
            return alternatePhone;
        }

        public void setAlternatePhone(Object alternatePhone) {
            this.alternatePhone = alternatePhone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getCompanyId() {
            return companyId;
        }

        public void setCompanyId(Integer companyId) {
            this.companyId = companyId;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Object getRtoAddressId() {
            return rtoAddressId;
        }

        public void setRtoAddressId(Object rtoAddressId) {
            this.rtoAddressId = rtoAddressId;
        }

        public String getDelhiveryClientwareId() {
            return delhiveryClientwareId;
        }

        public void setDelhiveryClientwareId(String delhiveryClientwareId) {
            this.delhiveryClientwareId = delhiveryClientwareId;
        }

        public String getDelhiverySurfaceClientwareId() {
            return delhiverySurfaceClientwareId;
        }

        public void setDelhiverySurfaceClientwareId(String delhiverySurfaceClientwareId) {
            this.delhiverySurfaceClientwareId = delhiverySurfaceClientwareId;
        }

        public String getDelhiverySurfaceStandardClientwareId() {
            return delhiverySurfaceStandardClientwareId;
        }

        public void setDelhiverySurfaceStandardClientwareId(String delhiverySurfaceStandardClientwareId) {
            this.delhiverySurfaceStandardClientwareId = delhiverySurfaceStandardClientwareId;
        }

        public String getDelhiverySurfaceLiteClientwareId() {
            return delhiverySurfaceLiteClientwareId;
        }

        public void setDelhiverySurfaceLiteClientwareId(String delhiverySurfaceLiteClientwareId) {
            this.delhiverySurfaceLiteClientwareId = delhiverySurfaceLiteClientwareId;
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

        public Object getSx4DelhiverySurfaceClientwareId() {
            return sx4DelhiverySurfaceClientwareId;
        }

        public void setSx4DelhiverySurfaceClientwareId(Object sx4DelhiverySurfaceClientwareId) {
            this.sx4DelhiverySurfaceClientwareId = sx4DelhiverySurfaceClientwareId;
        }

        public Object getSx3DelhiverySurfaceClientwareId() {
            return sx3DelhiverySurfaceClientwareId;
        }

        public void setSx3DelhiverySurfaceClientwareId(Object sx3DelhiverySurfaceClientwareId) {
            this.sx3DelhiverySurfaceClientwareId = sx3DelhiverySurfaceClientwareId;
        }

        public Object getSx2DelhiverySurfaceClientwareId() {
            return sx2DelhiverySurfaceClientwareId;
        }

        public void setSx2DelhiverySurfaceClientwareId(Object sx2DelhiverySurfaceClientwareId) {
            this.sx2DelhiverySurfaceClientwareId = sx2DelhiverySurfaceClientwareId;
        }

        public Object getSx1DelhiverySurfaceClientwareId() {
            return sx1DelhiverySurfaceClientwareId;
        }

        public void setSx1DelhiverySurfaceClientwareId(Object sx1DelhiverySurfaceClientwareId) {
            this.sx1DelhiverySurfaceClientwareId = sx1DelhiverySurfaceClientwareId;
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

        public Object getGatiSurfaceCustomerVendorCode() {
            return gatiSurfaceCustomerVendorCode;
        }

        public void setGatiSurfaceCustomerVendorCode(Object gatiSurfaceCustomerVendorCode) {
            this.gatiSurfaceCustomerVendorCode = gatiSurfaceCustomerVendorCode;
        }

        public Object getGatiSgCustomerVendorCode() {
            return gatiSgCustomerVendorCode;
        }

        public void setGatiSgCustomerVendorCode(Object gatiSgCustomerVendorCode) {
            this.gatiSgCustomerVendorCode = gatiSgCustomerVendorCode;
        }

        public String getDelhiveryFlashAirClientwareId() {
            return delhiveryFlashAirClientwareId;
        }

        public void setDelhiveryFlashAirClientwareId(String delhiveryFlashAirClientwareId) {
            this.delhiveryFlashAirClientwareId = delhiveryFlashAirClientwareId;
        }

        public String getUpdatedOn() {
            return updatedOn;
        }

        public void setUpdatedOn(String updatedOn) {
            this.updatedOn = updatedOn;
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

        public String getDelhiveryEssential5kgClientwareId() {
            return delhiveryEssential5kgClientwareId;
        }

        public void setDelhiveryEssential5kgClientwareId(String delhiveryEssential5kgClientwareId) {
            this.delhiveryEssential5kgClientwareId = delhiveryEssential5kgClientwareId;
        }

        public Object getWarehouseCode() {
            return warehouseCode;
        }

        public void setWarehouseCode(Object warehouseCode) {
            this.warehouseCode = warehouseCode;
        }

        public Object getGatiSurface10kgCustomerVendorCode() {
            return gatiSurface10kgCustomerVendorCode;
        }

        public void setGatiSurface10kgCustomerVendorCode(Object gatiSurface10kgCustomerVendorCode) {
            this.gatiSurface10kgCustomerVendorCode = gatiSurface10kgCustomerVendorCode;
        }

        public String getExtraInfo() {
            return extraInfo;
        }

        public void setExtraInfo(String extraInfo) {
            this.extraInfo = extraInfo;
        }

        public String getDelhiveryDocumentsClientwareId() {
            return delhiveryDocumentsClientwareId;
        }

        public void setDelhiveryDocumentsClientwareId(String delhiveryDocumentsClientwareId) {
            this.delhiveryDocumentsClientwareId = delhiveryDocumentsClientwareId;
        }

        public String getDelhiveryDocuments250gmClientwareId() {
            return delhiveryDocuments250gmClientwareId;
        }

        public void setDelhiveryDocuments250gmClientwareId(String delhiveryDocuments250gmClientwareId) {
            this.delhiveryDocuments250gmClientwareId = delhiveryDocuments250gmClientwareId;
        }

        public Object getRtoPinCode() {
            return rtoPinCode;
        }

        public void setRtoPinCode(Object rtoPinCode) {
            this.rtoPinCode = rtoPinCode;
        }

    }

    public class Services {

        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("data")
        @Expose
        private Data__1 data;
        @SerializedName("covid_zones")
        @Expose
        private List<Object> covidZones = null;
        @SerializedName("is_latlong")
        @Expose
        private Integer isLatlong;
        @SerializedName("seller_address")
        @Expose
        private SellerAddress sellerAddress;
        @SerializedName("currency")
        @Expose
        private String currency;
        @SerializedName("dg_courier")
        @Expose
        private Integer dgCourier;
        @SerializedName("eligible_for_insurance")
        @Expose
        private Integer eligibleForInsurance;

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Data__1 getData() {
            return data;
        }

        public void setData(Data__1 data) {
            this.data = data;
        }

        public List<Object> getCovidZones() {
            return covidZones;
        }

        public void setCovidZones(List<Object> covidZones) {
            this.covidZones = covidZones;
        }

        public Integer getIsLatlong() {
            return isLatlong;
        }

        public void setIsLatlong(Integer isLatlong) {
            this.isLatlong = isLatlong;
        }

        public SellerAddress getSellerAddress() {
            return sellerAddress;
        }

        public void setSellerAddress(SellerAddress sellerAddress) {
            this.sellerAddress = sellerAddress;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
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

    }
}