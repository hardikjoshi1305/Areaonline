package com.areaonline.shopowner.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InitializeSub_Response {


        @SerializedName("subscription_id")
        @Expose
        private String subscriptionId;
        @SerializedName("plan_id")
        @Expose
        private String planId;

        public String getSubscriptionId() {
            return subscriptionId;
        }

        public void setSubscriptionId(String subscriptionId) {
            this.subscriptionId = subscriptionId;
        }

        public String getPlanId() {
            return planId;
        }

        public void setPlanId(String planId) {
            this.planId = planId;
        }

    }
