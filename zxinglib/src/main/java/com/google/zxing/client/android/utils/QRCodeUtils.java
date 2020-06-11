package com.google.zxing.client.android.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;

import com.google.zxing.client.android.CaptureActivity;

/**
 * @author TomCan
 * @description:
 * @date :2019/11/13 10:22
 */
public class QRCodeUtils {


    public static void startEnCode(Activity activity) {
        activity.startActivityForResult(new Intent(activity, CaptureActivity.class), 1001);
    }


    public static Bitmap buildQRcodeBitmap(String content) {
        QRCodeBuild qrCodeBuild = new QRCodeBuild(content);
        Bitmap bitmap = qrCodeBuild.buildQRcodeBitmap();
        return bitmap;
    }


    public static Bitmap buildQRcodeBitmap(String content, int width, int height) {
        QRCodeBuild qrCodeBuild = new QRCodeBuild(content, width, height);
        Bitmap bitmap = qrCodeBuild.buildQRcodeBitmap();
        return bitmap;
    }


    public static Bitmap buildQRcodeBitmap(String content, Bitmap logoBitmap, float logoPercent) {
        QRCodeBuild qrCodeBuild = new QRCodeBuild(content, logoBitmap, logoPercent);
        Bitmap bitmap = qrCodeBuild.buildQRcodeBitmap();
        return bitmap;
    }


    public static Bitmap buildQRcodeBitmap(String content, int width, int height, Bitmap logoBitmap, float logoPercent) {
        QRCodeBuild qrCodeBuild = new QRCodeBuild(content, width, height, logoBitmap, logoPercent);
        Bitmap bitmap = qrCodeBuild.buildQRcodeBitmap();
        return bitmap;
    }


}
