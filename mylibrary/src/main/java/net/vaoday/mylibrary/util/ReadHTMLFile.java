package net.vaoday.mylibrary.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class ReadHTMLFile {
    public static String readHtml(Context context, String s) {

        InputStream input;
        String text = null;
        try {
            AssetManager assetManager = context.getAssets();
            input = assetManager.open(s);
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            text = new String(buffer);
            text = text.replaceAll("src=\"|src= \"",
                    "src= \"file:///android_asset/");
            Log.v("", "Copy Html Content");
            return text;
        } catch (IOException ex) {
            Log.e("Lỗi khi đọc file html", ex.getMessage());
            return null;
        }

    }

}
