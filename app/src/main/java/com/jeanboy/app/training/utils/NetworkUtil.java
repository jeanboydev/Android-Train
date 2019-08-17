package com.jeanboy.app.training.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

public class NetworkUtil {

    public static final int NETWORK_NONE = 0;
    public static final int NETWORK_WIFI = 1;
    public static final int NETWORK_MOBILE = 10;
    public static final int NETWORK_2G = 12;
    public static final int NETWORK_3G = 13;
    public static final int NETWORK_4G = 14;

    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context == null) return false;
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) return false;
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo == null) return false;
        return networkInfo.isAvailable() && networkInfo.isConnected();
    }

    /**
     * 获取当前的网络状态
     *
     * @param context
     * @return 没有网络:0; WIFI:1; 手机网络:10; 2G:12; 3G:13; 4G:14;
     */
    public static int getNetworkType(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) return NETWORK_NONE;

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isAvailable()) return NETWORK_NONE;

        int type = networkInfo.getType();
        if (type == ConnectivityManager.TYPE_WIFI) {
            return NETWORK_WIFI; // WiFi
        }

        if (type == ConnectivityManager.TYPE_MOBILE) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (telephonyManager == null) return NETWORK_NONE;

            int networkType = telephonyManager.getNetworkType();
            /**
             * 1xRTT : 2G CDMA2000 1xRTT (RTT - 无线电传输技术) 144kbps 2G的过渡
             * CDMA : 2G 电信 Code Division Multiple Access 码分多址
             * IDEN : 2G Integrated Dispatch Enhanced Networks 集成数字增强型网络 （属于2G，来自维基百科）
             * GPRS : 2G(2.5) General Packet Radia Service 114kbps
             * EDGE : 2G(2.75G) Enhanced Data Rate for GSM Evolution 384kbps
             *
             * UMTS : 3G WCDMA 联通3G Universal Mobile Telecommunication System 完整的3G移动通信技术标准
             * EHRPD : 3G CDMA2000向LTE 4G的中间产物 Evolved High Rate Packet Data HRPD的升级
             * EVDO_0 : 3G (EVDO 全程 CDMA2000 1xEV-DO) Evolution - Data Only (Data Optimized) 153.6kps - 2.4mbps 属于3G
             * EVDO_A : 3G 1.8mbps - 3.1mbps 属于3G过渡，3.5G
             * EVDO_B : 3G EV-DO Rev.B 14.7Mbps 下行 3.5G
             * HSPAP : 3G HSPAP 比 HSDPA 快些
             * HSPA : 3G (分HSDPA,HSUPA) High Speed Packet Access
             * HSDPA : 3.5G 高速下行分组接入 3.5G WCDMA High Speed Downlink Packet Access 14.4mbps
             * HSUPA : 3.5G High Speed Uplink Packet Access 高速上行链路分组接入 1.4 - 5.8 mbps
             *
             * LTE : 4G Long Term Evolution FDD-LTE 和 TDD-LTE , 3G过渡，升级版 LTE Advanced 才是4G
             */
            switch (networkType) {
                // 2G
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN:
                    return NETWORK_2G;
                // 3G
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                case TelephonyManager.NETWORK_TYPE_EHRPD:
                case TelephonyManager.NETWORK_TYPE_HSPAP:
                    return NETWORK_3G;
                // 4G
                case TelephonyManager.NETWORK_TYPE_LTE:
                    return NETWORK_4G;
                default:
                    return NETWORK_MOBILE;
            }
        }

        return NETWORK_NONE;
    }
}
