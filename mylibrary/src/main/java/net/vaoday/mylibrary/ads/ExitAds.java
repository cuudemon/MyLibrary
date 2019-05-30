package net.vaoday.mylibrary.ads;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import net.vaoday.mylibrary.R;
import net.vaoday.mylibrary.util.Encoder;
import net.vaoday.mylibrary.util.ListRandom;
import net.vaoday.mylibrary.util.Tools;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by quang on 26/09/2015.
 */
public class ExitAds {
    Context mContext;
    public static boolean isLoaded = false;
    private static Dialog dialogAds;
    private String url = "";
    private Gson gson;
    private AsyncHttpClient client;
    private AppAds getAdsObj;
    public static ArrayList<AppAds.AppsBean> ads2 = new ArrayList<AppAds.AppsBean>();
    public static ArrayList<AppAds.FocusAdsBean> mFocusAds = new ArrayList<AppAds.FocusAdsBean>();
    private Dialog dialogAdsFb;


    public ExitAds(final Context mContext) {
        this.mContext = mContext;
//        nativeAd = new NativeAd(mContext, mContext.getResources().getString(R.string.placementID));
        dialogAdsFb = new Dialog(mContext);
        dialogAdsFb.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogAdsFb.setContentView(R.layout.dialog_exit_ok);

        if (Tools.isConnectingToInternet(mContext)) {

            if (getVersionName(mContext).equals("0")) {
                try {
                    url = Encoder
                            .decrypt("1V9eUE1cLIVyQzAS0OgJcCKzysEHdzn3O6M7R/8B1P0TYPrh4yT1x/D5Mmucy8An");
                    getAds(url);
                } catch (Exception e) {
                    Log.v("Lỗi", "Lỗi khi giải mã url vn");
                    e.printStackTrace();
                }
            } else if (getVersionName(mContext).equals("3")) {
                if (getCountryName(mContext).equals("None")) {
                    getCountryFromIP();
                } else if (getCountryName(mContext).equals("Vietnam")) {
                    try {
                        url = Encoder
                                .decrypt("1V9eUE1cLIVyQzAS0OgJcCKzysEHdzn3O6M7R/8B1P0TYPrh4yT1x/D5Mmucy8An");
                        getAds(url);
                    } catch (Exception e) {
                        Log.v("Lỗi", "Lỗi khi giải mã url vn");
                    }
                } else {
                    try {
                        url = Encoder
                                .decrypt("1V9eUE1cLIVyQzAS0OgJcCKzysEHdzn3O6M7R/8B1P2XMG0A3iPqj2YVJeW084KE");
                        getAds(url);
                    } catch (Exception e) {
                        Log.v("Lỗi", "Lỗi khi giải mã url en");
                        e.printStackTrace();
                    }
                }
            } else {
                try {
                    url = Encoder
                            .decrypt("1V9eUE1cLIVyQzAS0OgJcCKzysEHdzn3O6M7R/8B1P2XMG0A3iPqj2YVJeW084KE");
                    getAds(url);
                } catch (Exception e) {
                    Log.v("Lỗi", "Lỗi khi giải mã url en");
                    e.printStackTrace();
                }
            }

        }

        LinearLayout btnYes = (LinearLayout) dialogAdsFb.findViewById(R.id.btnYes);
        LinearLayout btnNo = (LinearLayout) dialogAdsFb.findViewById(R.id.btnNo);


        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitApp(mContext);
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdsFb.dismiss();
            }
        });


//        nativeAd.setAdListener(new NativeAdListener() {
//            @Override
//            public void onMediaDownloaded(Ad ad) {
//
//            }
//
//            @Override
//            public void onError(Ad ad, AdError adError) {
//
//            }
//
//            @Override
//            public void onAdLoaded(Ad ad) {
//                try {
//                    nativeAd.unregisterView();
//                    View adView = NativeAdView.render(mContext, nativeAd, NativeAdView.Type.HEIGHT_300);
//                    LinearLayout nativeAdContainer = (LinearLayout) dialogAdsFb.findViewById(R.id.native_ad_container);
//                    // Add the Native Ad View to your ad container
//                    nativeAdContainer.addView(adView);
//                } catch (Exception e) {
//                    Log.e("catch adView onAdLoaded", e.getMessage());
//                }
//            }
//
//            @Override
//            public void onAdClicked(Ad ad) {
//
//            }
//
//            @Override
//            public void onLoggingImpression(Ad ad) {
//
//            }
//        });

//        nativeAd.setAdListener(new AdListener() {
//            @Override
//            public void onError(Ad ad, AdError adError) {
//
//            }
//
//            @Override
//            public void onAdLoaded(Ad ad) {
//                try {
//                    nativeAd.unregisterView();
//                    View adView = NativeAdView.render(mContext, nativeAd, NativeAdView.Type.HEIGHT_120);
//                    LinearLayout nativeAdContainer = (LinearLayout) dialogAdsFb.findViewById(R.id.native_ad_container);
//                    // Add the Native Ad View to your ad container
//                    nativeAdContainer.addView(adView);
//                } catch (Exception e) {
//                    Log.e("catch adView onAdLoaded", e.getMessage());
//                }
//            }
//
//            @Override
//            public void onAdClicked(Ad ad) {
//
//            }
//
//            @Override
//            public void onLoggingImpression(Ad ad) {
//
//            }
//        });
//        nativeAd.loadAd();
    }

    public static String getVersionName(Context mContext) {
        PackageInfo pInfo = null;
        try {
            pInfo = mContext.getPackageManager().getPackageInfo(
                    mContext.getPackageName(), 0);
            String version = pInfo.versionName;
            Log.v("Version Name",
                    version.substring(version.length() - 1, version.length()));
            return version.substring(version.length() - 1, version.length());
        } catch (PackageManager.NameNotFoundException e) {
            Log.v("Lỗi", "Lỗi khi get versionname");
            e.printStackTrace();
            return null;
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
    }

    private void exitApp(Context mContext) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
        System.exit(0);
    }

//    public void showExitAds(final Context mContext) {
//        if (isLoaded) {
//            try {
//                dialogAds = new Dialog(mContext);
//                dialogAds.requestWindowFeature(1);
//                dialogAds.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialogAds.setContentView(R.layout.dialog_exit_ads);
//                ArrayList<AppAds.AppsEntity> temp = new ArrayList<AppAds.AppsEntity>();
//                temp.addAll(getAdsObj.getApps());
//                int size = temp.size();
////                final ArrayList<AppAds.AppsEntity> ads2 = new ArrayList<AppAds.AppsEntity>();
//                ads2.clear();
//                int k[] = new ListRandom().randomArr(size);
////                int total;
////                if (temp.size() >= 5) {
////                    total = 5;
////                } else
////                    total = temp.size();
//                for (int i = 0; i < temp.size(); i++) {
//                    if (isPackageInstalled(temp.get(k[i]).getPackageName(), mContext)) {
//                        Log.v("Bo qua: ", temp.get(k[i]).getPackageName());
//                    } else {
//                        ads2.add(new AppAds.AppsEntity(temp.get(k[i]).getAppName(), temp.get(k[i]).getPackageName(),
//                                temp.get(k[i]).getPublisher(), temp.get(k[i]).getDecription(), temp.get(k[i]).getAppIcon()));
//                    }
//                }
//                if (ads2.size() != 0) {
//                    ListView lvexitAds = (ListView) dialogAds.findViewById(R.id.lvexitAds);
//                    AdapterAds adapter = new AdapterAds(mContext, ads2);
//                    lvexitAds.setAdapter(adapter);
//                    lvexitAds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                            openLink(mContext, ads2.get(i).getPackageName());
//                        }
//                    });
//                    TextView tvAds = (TextView) dialogAds.findViewById(R.id.tvAds);
//                    Tools.setFontsTextView((Activity) mContext, tvAds, "roboto.ttf");
//                    LinearLayout llClose = (LinearLayout) dialogAds.findViewById(R.id.llcloseAds);
//                    llClose.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            exitApp(mContext);
//                        }
//                    });
//                    dialogAds.show();
//                } else {
//                    exitApp(mContext);
//                }
//            } catch (Exception ex) {
//                Log.e("Lỗi khi show exit ads: ", ex.getMessage());
//                exitApp(mContext);
//            }
//        } else {
//            exitApp(mContext);
//        }
//    }

    public void showExitDialog() {
        dialogAdsFb.show();
    }


    protected void openApps(Context mContext, String packageName) {
        Log.d("onClick Item", "click Quang Cao");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=" + packageName));
        Log.v("namePakage", packageName);
        mContext.startActivity(intent);
    }


    protected void openLinks(Context mContext, String link) {
        Log.d("onClick Item", "click Quang Cao");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(link));
        Log.v("Link open", link);
        mContext.startActivity(intent);
    }

    public static String prefname = "MOBILE ADS LIBRARY";
    public static String TAG_IS_FIRST_TIMES = "country_tag";

    // SharePreferences
    public static void saveCountryName(Context mContext, String value) {
        SharedPreferences pre = mContext.getSharedPreferences(prefname, mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        editor.putString(TAG_IS_FIRST_TIMES, value);
        editor.commit();
        Log.e("save country_tag", value);
    }

    public static String getCountryName(Context mContext) {
        SharedPreferences pre = mContext.getSharedPreferences(prefname, mContext.MODE_PRIVATE);
        String value;
        value = pre.getString(TAG_IS_FIRST_TIMES, "None");
        Log.d("get country_tag", value + "");
        return value;
    }

    private void getCountryFromIP() {
        AsyncHttpClient client;
        final String url1 = "http://www.ip-api.com/json";
        client = new AsyncHttpClient();
        client.get(url1, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONObject obj = new JSONObject(new String(responseBody));
                    String countryName = obj.getString("country");
                    Log.e("countryName", countryName);

                    if (countryName.equals("Vietnam")) {
                        saveCountryName(mContext, "Vietnam");
                        try {
                            url = Encoder
                                    .decrypt("1V9eUE1cLIVyQzAS0OgJcCKzysEHdzn3O6M7R/8B1P0TYPrh4yT1x/D5Mmucy8An");
                            getAds(url);

                        } catch (Exception e) {
                            Log.v("Lỗi", "Lỗi khi giải mã url vn");
                            e.printStackTrace();
                        }

                    } else {
                        try {
                            url = Encoder
                                    .decrypt("1V9eUE1cLIVyQzAS0OgJcCKzysEHdzn3O6M7R/8B1P2XMG0A3iPqj2YVJeW084KE");
                            getAds(url);
                        } catch (Exception e) {
                            Log.v("Lỗi", "Lỗi khi giải mã url en");
                            e.printStackTrace();
                        }
                    }

                } catch (Exception e) {
                    Log.e("Catch getCountryFromIP", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }


    private void getAds(String url) {
        try {
            client = new AsyncHttpClient();
            client.get(mContext, url, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    String str = new String(responseBody);
                    gson = new Gson();
                    getAdsObj = gson.fromJson(str, AppAds.class);

                    isLoaded = true;
                    //new
                    ArrayList<AppAds.AppsBean> temp = new ArrayList<AppAds.AppsBean>();
                    ArrayList<AppAds.FocusAdsBean> tempFocusAds = new ArrayList<AppAds.FocusAdsBean>();

                    tempFocusAds.addAll(getAdsObj.getFocusAds());
                    temp.addAll(getAdsObj.getApps());

                    Log.e("Tong so quang cao", getAdsObj.getApps().size() + "");
                    Log.e("Tong so focus Ads", getAdsObj.getFocusAds().size() + "");

                    int size = temp.size();
                    int sizeFocusAds = tempFocusAds.size();
                    mFocusAds.clear();
                    ads2.clear();
                    if (sizeFocusAds > 0) {
                        int kFocus[] = new ListRandom().randomArr(sizeFocusAds);
                        for (int j = 0; j < sizeFocusAds; j++) {
                            if (tempFocusAds.get(kFocus[j]).getAsdType().equals("app")) {
                                if (isPackageInstalled(tempFocusAds.get(kFocus[j]).getPackageName(), mContext)) {
                                    Log.v("Bo qua: ", tempFocusAds.get(kFocus[j]).getPackageName());
                                } else {
                                    mFocusAds.add(new AppAds.FocusAdsBean(tempFocusAds.get(kFocus[j]).getAdsName(),
                                            tempFocusAds.get(kFocus[j]).getAsdType(),
                                            tempFocusAds.get(kFocus[j]).getPackageName(),
                                            tempFocusAds.get(kFocus[j]).getAdsLink(),
                                            tempFocusAds.get(kFocus[j]).getAdsBanner()));
                                }
                            } else {
                                mFocusAds.add(new AppAds.FocusAdsBean(tempFocusAds.get(kFocus[j]).getAdsName(),
                                        tempFocusAds.get(kFocus[j]).getAsdType(),
                                        tempFocusAds.get(kFocus[j]).getPackageName(),
                                        tempFocusAds.get(kFocus[j]).getAdsLink(),
                                        tempFocusAds.get(kFocus[j]).getAdsBanner()));
                            }
                        }

                        if (mFocusAds.size() > 0) {
                            LinearLayout focus_ads = dialogAdsFb.findViewById(R.id.focus_ads);
                            ImageView imFocus_Ads = dialogAdsFb.findViewById(R.id.imFocus_Ads);
                            focus_ads.setVisibility(View.VISIBLE);
                            Glide.with(mContext).load(mFocusAds.get(0).getAdsBanner())
                                    .transition(new DrawableTransitionOptions().crossFade())
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .into(imFocus_Ads);

                            focus_ads.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    try {
                                        if (mFocusAds.get(0).getAsdType().equals("ads"))
                                            openLinks(mContext, mFocusAds.get(0).getAdsLink());
                                        else
                                            openApps(mContext, mFocusAds.get(0).getPackageName());
                                    } catch (Exception e) {
                                        Log.e("TAG", "catch in open FocusAds");
                                        exitApp(mContext);
                                    }

                                }
                            });


                        }

                    }

                    int k[] = new ListRandom().randomArr(size);
                    for (int i = 0; i < temp.size(); i++) {
                        if (isPackageInstalled(temp.get(k[i]).getPackageName(), mContext)) {
                            Log.v("Bo qua: ", temp.get(k[i]).getPackageName());
                        } else {
                            ads2.add(new AppAds.AppsBean(temp.get(k[i]).getAppName(), temp.get(k[i]).getPackageName(),
                                    temp.get(k[i]).getPublisher(), temp.get(k[i]).getDecription(), temp.get(k[i]).getAppAds(), temp.get(k[i]).getAppIcon()));
                        }
                    }

                    if (ExitAds.isLoaded && ExitAds.ads2.size() > 0) {
                        try {
                            TextView tvAds = (TextView) dialogAdsFb.findViewById(R.id.tvAds);
                            tvAds.setText(mContext.getResources().getString(R.string.exitTile_on));
                            LinearLayout list = (LinearLayout) dialogAdsFb.findViewById(R.id.listApps);
                            list.setVisibility(View.VISIBLE);
                            RecyclerView listApps = (RecyclerView) dialogAdsFb.findViewById(R.id.recycler);
                            AdapterAdsFb adapterAdsFb = new AdapterAdsFb(mContext, ads2);
                            listApps.setHasFixedSize(false);
                            LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
                            mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                            listApps.setAdapter(adapterAdsFb);
                            listApps.setLayoutManager(mLayoutManager);
                            listApps.addOnItemTouchListener(new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    openApps(mContext, ads2.get(position).getPackageName());
                                }
                            }));

                        } catch (Exception ex) {
                            Log.e("catch Adapter recycler", ex.getMessage());
                        }

                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    isLoaded = false;
                }
            });

        } catch (Exception e) {
            Log.e("Lỗi khi get JSON", e.getMessage());
        }
    }


}
