package com.jeven.sample.utils;

import android.content.Context;

/**
 * 创建人: Jeven
 * 邮箱:   liaowenjie@sto.cn
 * 功能:
 */
public class Test {


    /*static {
        BluetoothAdapter blueToothHelper = BluetoothAdapter.getDefaultAdapter();
        if (blueToothHelper == null)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                blueToothHelper = new BluetoothManager().getAdapter();
            }
        }
    }*/

    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

}
