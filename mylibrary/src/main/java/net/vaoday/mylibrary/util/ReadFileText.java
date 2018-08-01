package net.vaoday.mylibrary.util;

import android.app.Activity;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class ReadFileText {
	public static String loadData(Activity mContext, String fileName) {
		String strContent = "";
		try {
			InputStream stream = mContext.getAssets().open(fileName + ".txt");
			int size = stream.available();
			byte[] buffer = new byte[size];
			stream.read(buffer);
			stream.close();
			strContent = new String(buffer);
		} catch (IOException e) {
			//Toast.makeText(mContext, "Lỗi khi đọc file", Toast.LENGTH_SHORT).show();
			Log.e("Lỗi khi đọc file" + fileName, e.getMessage());
		}
		return strContent;
	}

}
