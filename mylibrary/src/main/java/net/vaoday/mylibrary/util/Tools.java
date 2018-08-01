package net.vaoday.mylibrary.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by quang on 25/09/2015.
 */
public class Tools {
    private static InterstitialAd interstitialAd;

    public static void admobBannerLayout(final Context mContext,
                                         final int idLayout, String idAdmobBanner) {
        String id = "";
        try {
            id = Encoder.decrypt(idAdmobBanner);
            final AdView mAdView = new AdView((Activity) mContext);
            mAdView.setAdSize(AdSize.SMART_BANNER);
            mAdView.setAdUnitId(id);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    ((LinearLayout) ((Activity) mContext).findViewById(idLayout))
                            .removeView(mAdView);
                    ((LinearLayout) ((Activity) mContext).findViewById(idLayout))
                            .addView(mAdView);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    public static void admobBannerLayout(final Context mContext,
//                                         final int idLayout, String idAdmobBanner) {
//        String id = "";
//        try {
//            id = Encoder.decrypt(idAdmobBanner);
//            final AdView mAdView = new AdView((Activity) mContext);
////            final com.facebook.ads.AdView fbAdsview = new com.facebook.ads.AdView((Activity) mContext, idFbanner, com.facebook.ads.AdSize.BANNER_HEIGHT_50);
//            mAdView.setAdSize(AdSize.SMART_BANNER);
//            mAdView.setAdUnitId(id);
//            final AdRequest adRequest = new AdRequest.Builder().build();
//            mAdView.loadAd(adRequest);
////            mAdView.setAdListener(new AdListener() {
////                @Override
////                public void onAdLoaded() {
//////                    ((LinearLayout) ((Activity) mContext).findViewById(idLayout))
//////                            .removeView(fbAdsview);
////                    ((LinearLayout) ((Activity) mContext).findViewById(idLayout))
////                            .removeView(mAdView);
////                    ((LinearLayout) ((Activity) mContext).findViewById(idLayout))
////                            .addView(mAdView);
////                }
////
////
////                @Override
////                public void onAdFailedToLoad(int i) {
////                    Log.e("faile to load banner", "load");
//////                    try {
////////                        ((LinearLayout) ((Activity) mContext).findViewById(idLayout))
////////                                .removeView(mAdView);
////////                        ((LinearLayout) ((Activity) mContext).findViewById(idLayout))
////////                                .removeView(fbAdsview);
////////                        ((LinearLayout) ((Activity) mContext).findViewById(idLayout))
////////                                .addView(fbAdsview);
//////
////////                        fbAdsview.setAdListener(new com.facebook.ads.AdListener() {
////////                            @Override
////////                            public void onError(Ad ad, AdError adError) {
////////                                ((LinearLayout) ((Activity) mContext).findViewById(idLayout))
////////                                        .removeView(fbAdsview);
////////                                ((LinearLayout) ((Activity) mContext).findViewById(idLayout))
////////                                        .removeView(mAdView);
////////                                ((LinearLayout) ((Activity) mContext).findViewById(idLayout))
////////                                        .addView(mAdView);
////////                            }
////////
////////                            @Override
////////                            public void onAdLoaded(Ad ad) {
////////
////////                            }
////////
////////                            @Override
////////                            public void onAdClicked(Ad ad) {
////////
////////                            }
////////
////////                            @Override
////////                            public void onLoggingImpression(Ad ad) {
////////
////////                            }
////////                        });
////////
////////                        fbAdsview.loadAd();
//////                    } catch (Exception ex) {
//////                        Log.e("catch in load fb banner", "error");
//////                    }
////
////                }
////            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
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

    public static void adsSubnetBanner(Context mContext, String idAdmobBanner,
                                       Boolean isBottom, boolean isLeft) {
        String id = "";
        try {
            id = Encoder.decrypt(idAdmobBanner);
            AdView mAdView = new AdView(mContext);
            mAdView.setAdUnitId(id);
            mAdView.setAdSize(AdSize.BANNER);
            mAdView.loadAd(new AdRequest.Builder().build());
            createLayout(mAdView, (Activity) mContext, isBottom, isLeft);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void admobInterstitialAd(Context mContext, String AD_UNIT_ID) {
        String id = "";
        try {
            id = Encoder.decrypt(AD_UNIT_ID);

        } catch (Exception e) {
            e.printStackTrace();
        }
        interstitialAd = new InterstitialAd(mContext);
        interstitialAd.setAdUnitId(id);
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);

    }

    public static void showInterstitialAd() {
        try {
            if (interstitialAd.isLoaded())
                interstitialAd.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Set font
    public static void setFontsTextView(Activity mContext, TextView mTextView,
                                        String fontName) {
        try {
            Typeface type = Typeface.createFromAsset(mContext.getAssets(), "fonts/"
                    + fontName);
            mTextView.setTypeface(type);
        } catch (Exception e) {
            Log.e("Lỗi setFontsTextView", e.getMessage());
        }

    }

    public static void setFontsButton(Activity mContext, Button mButton,
                                      String fontName) {
        try {
            Typeface type = Typeface.createFromAsset(mContext.getAssets(), "fonts"
                    + fontName);
            mButton.setTypeface(type);
        } catch (Exception e) {
            Log.e("Lỗi setFontsButton", e.getMessage());
        }

    }

//    @SuppressLint("NewApi")
//    @SuppressWarnings("deprecation")
//    public static void onClickCopy(Context mContext, String str, String toast) {
//        // User-defined onClick Listener
//        try {
//            int sdk_Version = android.os.Build.VERSION.SDK_INT;
//            if (sdk_Version < android.os.Build.VERSION_CODES.HONEYCOMB) {
//                android.text.ClipboardManager clipboard = (android.text.ClipboardManager) mContext
//                        .getSystemService(Context.CLIPBOARD_SERVICE);
//                clipboard.setText(str); // Assuming that you are copying the text
//                // from a TextView
//                Toast.makeText(mContext, toast, Toast.LENGTH_LONG).show();
//            } else {
//                android.content.ClipboardManager clipboard = (android.content.ClipboardManager) mContext
//                        .getSystemService(Context.CLIPBOARD_SERVICE);
//                android.content.ClipData clip = android.content.ClipData
//                        .newPlainText("aloapp", str);
//                clipboard.setPrimaryClip(clip);
//                Toast.makeText(mContext, toast, Toast.LENGTH_LONG).show();
//            }
//            Log.v("", "Copy nội dung.");
//        } catch (Exception e) {
//            Log.e("Lỗi onClickCopy", e.getMessage());
//        }
//
//
//    }

   /* public static void shareFacebookContent(Context mContext, String contentURL, String contentTitle, String imageURL, String contentDescription) {
        try {
            ShareLinkContent shareContent = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse(contentURL))
                    .setContentDescription(contentDescription)
                    .setContentTitle(contentTitle)
                    .setImageUrl(Uri.parse(imageURL))
                    .build();
            ShareDialog shareDialog = new ShareDialog((Activity) mContext);
            shareDialog.show(shareContent);
            //Cong hoa xa hoi chu nghia viet nam


        } catch (Exception e) {
            Log.e("Lỗi khi share content: ", e.getMessage());
        }

    }

    public static boolean isPackageInstalled(String packagename, Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
            return true;

        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }*/


}
