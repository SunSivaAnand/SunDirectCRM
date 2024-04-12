package com.sundirect.crm.bean;

import java.util.List;

public class PlanBundleMapping {
    private List<Bundle> planBundleMapping;
    
    public List<Bundle> getPlanBundleMapping() {
        return planBundleMapping;
    }

    public void setPlanBundleMapping(List<Bundle> planBundleMapping) {
        this.planBundleMapping = planBundleMapping;
    }
    
   public static class Bundle {
        private String bundleName;
        private String imageUrl;

        public String getBundleName() {
            return bundleName;
        }

        public void setBundleName(String bundleName) {
            this.bundleName = bundleName;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }

}
