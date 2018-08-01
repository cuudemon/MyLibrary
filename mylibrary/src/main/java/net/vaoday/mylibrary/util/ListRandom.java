package net.vaoday.mylibrary.util;

import java.util.Random;

public class ListRandom {
	private int mang[];

	/**
	 * Hàm này dùng sắp xếp ngẫu nhiên của mảng từ 0 tới total
	 * 
	 * @param total
	 * @return trả v�? 1 mảng được random từ o tới total
	 */
	public int[] randomArr(int total) {
		mang = new int[total];
		for (int q = 0; q < mang.length; q++)
			mang[q] = -1;
		int i = 0;
		while (i < mang.length) {
			int k = new Random().nextInt(total);
			if (!kt(k)) {
				mang[i] = k;
				i++;
			}
		}
		return mang;
	}

	private boolean kt(int l) {
		for (int i = 0; i < mang.length; i++)
			if (mang[i] == l)
				return true;
		return false;
	}
}
