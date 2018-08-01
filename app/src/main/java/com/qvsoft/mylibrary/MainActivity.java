package com.qvsoft.mylibrary;

import net.vaoday.mylibrary.activity.AloAppActivity;

public class MainActivity extends AloAppActivity {

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void settingView() {

    }

    @Override
    public void onBackPressed() {
        exitDialog();
    }
}
