package net.vaoday.mylibrary.util;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class InterstitialUtils {
    private static InterstitialUtils sharedInstance;
    private InterstitialAd interstitialAd;
    private AdCloseListener adCloseListener;
    private boolean isReloaded = false;

    public static InterstitialUtils getSharedInstance() {
        if (sharedInstance == null) {
            sharedInstance = new InterstitialUtils();
        }
        return sharedInstance;
    }

    public void init(Context mContext, String APP_ID, String FULL_ID) {
        try {
            MobileAds.initialize(mContext, Encoder.decrypt(APP_ID));
            interstitialAd = new InterstitialAd(mContext);
            interstitialAd.setAdUnitId(Encoder.decrypt(FULL_ID));
            interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    if (adCloseListener != null) {
                        adCloseListener.onAdClosed();
                    }
                    loadInterstitialAd();

                }

                @Override
                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    if (!isReloaded) {
                        isReloaded = true;
                        loadInterstitialAd();
                    }
                }
            });
        }catch (Exception e){
            Log.e("TAG", "Catch in initInterstitial");
        }

    }

    private void loadInterstitialAd() {
        if (interstitialAd != null && !interstitialAd.isLoading() && !interstitialAd.isLoaded()) {
            AdRequest adRequest = new AdRequest.Builder().build();
            interstitialAd.loadAd(adRequest);
        }
    }

    public void showInterstitialAd(AdCloseListener adCloseListener) {
        if (canShowInterstitialAd()) {
            isReloaded = false;
            this.adCloseListener = adCloseListener;
            interstitialAd.show();
        } else {
            loadInterstitialAd();
            adCloseListener.onAdClosed();
        }

    }

    private boolean canShowInterstitialAd() {
        return interstitialAd != null && interstitialAd.isLoaded();
    }


    public interface AdCloseListener {
        public void onAdClosed();
    }

}