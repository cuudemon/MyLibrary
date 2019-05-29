package net.vaoday.mylibrary.util;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;

import net.vaoday.mylibrary.R;

public class BannerFacebookAdsUtils {
    private static BannerFacebookAdsUtils sharedInstance;

    public static BannerFacebookAdsUtils getSharedInstance() {
        if (sharedInstance == null) {
            sharedInstance = new BannerFacebookAdsUtils();
        }
        return sharedInstance;
    }

    public void showFBBannerAds(final Context mContext, final int bannerLayout) {
        try {
            AudienceNetworkAds.initialize(mContext);
            final AdView adView = new AdView(mContext, mContext.getResources().getString(R.string.FB_BANNER_ID), AdSize.BANNER_HEIGHT_50);
            final LinearLayout adContainer = (LinearLayout) ((Activity) mContext).findViewById(bannerLayout);
            adView.loadAd();

            adView.setAdListener(new AdListener() {
                @Override
                public void onError(Ad ad, AdError adError) {
                    // Ad error callback
                    Log.e("Error: ", adError.getErrorMessage());
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    adContainer.removeView(adView);
                    adContainer.addView(adView);
                }

                @Override
                public void onAdClicked(Ad ad) {
                    // Ad clicked callback
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                    // Ad impression logged callback
                }
            });

        } catch (Exception e) {
            Log.e("Catch in show FBbanner", e.getMessage() + "A");
        }
    }
}
