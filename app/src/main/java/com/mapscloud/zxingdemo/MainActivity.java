package com.mapscloud.zxingdemo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;

import com.google.zxing.client.android.utils.QRCodeUtils;

import androidx.appcompat.app.AppCompatActivity;
import pub.devrel.easypermissions.EasyPermissions;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private             String      TAG                       = this.getClass().getSimpleName();
    private             ImageButton bt_qr_code;
    private             WebView     wv_web;
    private static      String[]    mWritePerms               = new String[]{Manifest.permission.CAMERA};
    public static final int         REQUEST_CODE_ASK_WRITE_SD = 1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermissions(this);
        findID();
        setOnClickListener();
    }


    private void setOnClickListener() {
        bt_qr_code.setOnClickListener(this);

        wv_web.setOnClickListener(this);
    }

    private void findID() {
        bt_qr_code = findViewById(R.id.bt_qr_code);
        wv_web = findViewById(R.id.wv_web);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == Activity.RESULT_OK) {
            String result = data.getStringExtra("encode");
            Log.i(TAG, "encode：" + result);
            wv_web.loadUrl(result);
            bt_qr_code.setVisibility(View.GONE);
            wv_web.setVisibility(View.VISIBLE);
        } else {
            bt_qr_code.setVisibility(View.VISIBLE);
            wv_web.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_qr_code:

//                QRCodeUtils.startEnCode(this);

                Bitmap logoBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.launcher_icon);
                Bitmap bitmap = QRCodeUtils.buildQRcodeBitmap("www.baidu.com", 1000, 1000, logoBitmap, 0.2f);
                bt_qr_code.setImageBitmap(bitmap);
                break;
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_ASK_WRITE_SD) {

        }
    }


    /**
     * 检查权限
     */
    public static boolean checkPermissions(Context context) {
        //检查读写权限
        if (EasyPermissions.hasPermissions(context, mWritePerms)) {
            return true;
        } else {
            // 没有权限，动态的申请权限
            EasyPermissions.requestPermissions(context, "需要您打开读写权限",
                    REQUEST_CODE_ASK_WRITE_SD, mWritePerms);
            return false;
        }
    }


}
