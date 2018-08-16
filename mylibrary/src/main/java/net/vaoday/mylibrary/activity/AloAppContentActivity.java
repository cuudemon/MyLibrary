package net.vaoday.mylibrary.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public abstract class AloAppContentActivity extends AppCompatActivity {

    protected abstract int getLayoutID();

    //protected abstract int getStatusBarID();

    protected abstract void settingView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        settingView();

    }

    protected int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height",
                "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    protected void setStatusBarColor(View statusBar, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            int statusBarHeight = getStatusBarHeight();
            statusBar.getLayoutParams().height = statusBarHeight;
            statusBar.setBackgroundColor(color);
        }
    }


    protected void makeToast(String strToast) {
        Toast.makeText(this, strToast, Toast.LENGTH_SHORT).show();
    }

    // Exit App
    protected void exitApp(Context mContext) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
        System.exit(0);
    }


    /**
     * @param text        Mô tả cho link
     * @param packageName packageName của ứng dụng muốn chia sẻ
     */
    protected void shareLink(String text, String packageName) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
        emailIntent.putExtra(Intent.EXTRA_TEXT, text + "\n\n"
                + "https://play.google.com/store/apps/details?id=" + packageName);
        startActivity(emailIntent);
        Log.v("Link", packageName + "");
    }

    /**
     * @param packageName packageName của ứng dụng muốn người dùng đánh giá
     */
    protected void rateApp(String packageName) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=" + packageName));
        Log.v("packageName", packageName);
        startActivity(intent);
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

}