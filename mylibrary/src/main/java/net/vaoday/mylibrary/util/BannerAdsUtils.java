package net.vaoday.mylibrary.util;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import net.vaoday.mylibrary.R;

public class BannerAdsUtils {

    private static BannerAdsUtils sharedInstance;

    public static BannerAdsUtils getSharedInstance() {
        if (sharedInstance == null) {
            sharedInstance = new BannerAdsUtils();
        }
        return sharedInstance;
    }

    public void showBannerAdsLayout(final Context mContext, final int ID_LAYOUT) {
        try {


            MobileAds.initialize(mContext, Encoder.decrypt(mContext.getResources().getString(R.string.APP_ID)));
            final AdView mAdView = new AdView((Activity) mContext);
            mAdView.setAdSize(AdSize.SMART_BANNER);
            mAdView.setAdUnitId(mContext.getResources().getString(R.string.BANNER_ID));
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    ((LinearLayout) ((Activity) mContext).findViewById(ID_LAYOUT))
                            .removeView(mAdView);
                    ((LinearLayout) ((Activity) mContext).findViewById(ID_LAYOUT))
                            .addView(mAdView);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void showBannerAdsNoLayout(Context mContext,
                                      Boolean isBottom, boolean isLeft) {
        try {
            AdView mAdView;
            MobileAds.initialize(mContext, Encoder.decrypt(mContext.getResources().getString(R.string.APP_ID)));
            mAdView = new AdView(mContext);
            mAdView.setAdUnitId(Encoder.decrypt(mContext.getResources().getString(R.string.BANNER_ID)));
            mAdView.setAdSize(AdSize.BANNER);
            mAdView.loadAd(new AdRequest.Builder().build());
            createLayout(mAdView, (Activity) mContext, isBottom, isLeft);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static RelativeLayout createLayout(View obj, Activity myActivity,
                                               boolean isbottom, boolean isLeft) {
        // Dung cho quang cao khong co layout
        RelativeLayout lnAd = new RelativeLayout(myActivity);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        if (isbottom && isLeft) {
            lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        } else if (isbottom && !isLeft) {
            lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        } else {
            lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        }
        lnAd.addView(obj, lp);// adView=quang cao cua minh'
        myActivity.addContentView(lnAd, new ViewGroup.LayoutParams(-1, -1));
        return lnAd;

    }


}
