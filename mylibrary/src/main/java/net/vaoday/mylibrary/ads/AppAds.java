package net.vaoday.mylibrary.ads;

        import java.util.List;

/**
 * Created by quang on 26/09/2015.
 */
public class AppAds {


    private List<AppsBean> apps;
    private List<FocusAdsBean> focusAds;

    public List<AppsBean> getApps() {
        return apps;
    }

    public void setApps(List<AppsBean> apps) {
        this.apps = apps;
    }

    public List<FocusAdsBean> getFocusAds() {
        return focusAds;
    }

    public void setFocusAds(List<FocusAdsBean> focusAds) {
        this.focusAds = focusAds;
    }



    public static class AppsBean {
        /**
         * appName : Truyện Cười
         * packageName : com.aloapp.truyencuoi
         * publisher : qvsoft
         * decription : Cười té ghế với ứng dụng Truyện Cười nhé!
         * appAds : https://i.imgur.com/dhPFVHe.jpg
         * appIcon : https://lh3.googleusercontent.com/-putnFi_SN10/VlbaBiy1raI/AAAAAAAAAD4/qSYBUK4ZvFc/s144-Ic42/icon512.png
         */

        private String appName;
        private String packageName;
        private String publisher;
        private String decription;
        private String appAds;
        private String appIcon;

        public AppsBean(String appName, String packageName, String publisher, String decription, String appAds, String appIcon) {
            this.appName = appName;
            this.packageName = packageName;
            this.publisher = publisher;
            this.decription = decription;
            this.appAds = appAds;
            this.appIcon = appIcon;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public String getDecription() {
            return decription;
        }

        public void setDecription(String decription) {
            this.decription = decription;
        }

        public String getAppAds() {
            return appAds;
        }

        public void setAppAds(String appAds) {
            this.appAds = appAds;
        }

        public String getAppIcon() {
            return appIcon;
        }

        public void setAppIcon(String appIcon) {
            this.appIcon = appIcon;
        }
    }

    public static class FocusAdsBean {
        /**
         * adsName : Nghệ thuật giao tiếp
         * asdType : app
         * packageName : com.aloapp.nghethuatgiaotiep
         * adsLink : Tinh tế, khéo léo trong từng cách ứng xử....
         * adsBanner : https://i.imgur.com/dlbZoRM.jpg
         */

        private String adsName;
        private String asdType;
        private String packageName;
        private String adsLink;
        private String adsBanner;

        public FocusAdsBean(String adsName, String asdType, String packageName, String adsLink, String adsBanner) {
            this.adsName = adsName;
            this.asdType = asdType;
            this.packageName = packageName;
            this.adsLink = adsLink;
            this.adsBanner = adsBanner;
        }

        public String getAdsName() {
            return adsName;
        }

        public void setAdsName(String adsName) {
            this.adsName = adsName;
        }

        public String getAsdType() {
            return asdType;
        }

        public void setAsdType(String asdType) {
            this.asdType = asdType;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getAdsLink() {
            return adsLink;
        }

        public void setAdsLink(String adsLink) {
            this.adsLink = adsLink;
        }

        public String getAdsBanner() {
            return adsBanner;
        }

        public void setAdsBanner(String adsBanner) {
            this.adsBanner = adsBanner;
        }
    }
}
