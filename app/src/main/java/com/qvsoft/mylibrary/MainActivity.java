package com.qvsoft.mylibrary;

import net.vaoday.mylibrary.activity.AloAppActivity;
import net.vaoday.mylibrary.util.Tools;

public class MainActivity extends AloAppActivity {

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void settingView() {
//        if (Tools.isConnectingToInternet(this))


    }

    @Override
    public void onBackPressed() {
        if (Tools.isConnectingToInternet(this))
            exitDialog();
        else
            exitAppNoInternet();
    }
}
