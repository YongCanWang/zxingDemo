package com.google.zxing.client.android.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.text.TextUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.EnumMap;
import java.util.Map;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

/**
 * @author TomCan
 * @description:
 * @date :2019/11/13 11:08
 */
public class QRCodeBuild {


    private Bitmap logoBitmap;
    private float  logoPercent;
    private int    width;
    private int    height;
    private String content;

    public QRCodeBuild(String content) {
        this.content = content;
    }


    public QRCodeBuild(String content, int width, int height) {
        this.content = content;
        this.width = width;
        this.height = height;
    }


    public QRCodeBuild(String content, Bitmap logoBitmap, float logoPercent) {
        this.content = content;
        this.logoBitmap = logoBitmap;
        this.logoPercent = logoPercent;
    }


    public QRCodeBuild(String content, int width, int height, Bitmap logoBitmap, float logoPercent) {
        this.content = content;
        this.width = width;
        this.height = height;
        this.logoBitmap = logoBitmap;
        this.logoPercent = logoPercent;
    }


    public Bitmap buildQRcodeBitmap() {

        // 字符串内容判空
        if (TextUtils.isEmpty(content)) {
            return null;
        }

        if (width < 0 || width == 0) {
            width = 600;
        }

        if (height < 0 || height == 0) {
            height = 600;
        }

        Bitmap QRCodeBitmap = null;
        try {
            Map<EncodeHintType, Object> hints = null;
//            String encoding = getEncoding(str);//获取字符编码
            String encoding = "utf-8";//获取字符编码

            if (encoding != null) {
                hints = new EnumMap<>(EncodeHintType.class);
                hints.put(EncodeHintType.CHARACTER_SET, encoding);
            }
            BitMatrix result = null; // 通过字符串创建二维矩阵
            result = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            int width = result.getWidth();
            int height = result.getHeight();
            int[] pixels = new int[width * height];

            for (int y = 0; y < height; y++) {
                int offset = y * width;
                for (int x = 0; x < width; x++) {
                    pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;//根据二维矩阵数据创建数组
                }
            }
            QRCodeBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);//创建位图
            QRCodeBitmap.setPixels(pixels, 0, width, 0, 0, width, height);//将数组加载到位图中
            if (logoBitmap != null)
                QRCodeBitmap = attachLogo(QRCodeBitmap, logoBitmap, logoPercent);

        } catch (WriterException e) {
            e.printStackTrace();
        }
        return QRCodeBitmap;
    }



    private Bitmap attachLogo(Bitmap QRCodeBitmap, Bitmap logoBitmap, float logoPercent) {

        if (QRCodeBitmap == null) {
            return null;
        }
        if (logoBitmap == null) {
            return QRCodeBitmap;
        }

        //传值不合法时使用0.2F
        if (logoPercent < 0F || logoPercent > 1F) {
            logoPercent = 0.2F;
        }


        /** 1. 获取原图片和Logo图片各自的宽、高值 */
        int QRCodeWidth = QRCodeBitmap.getWidth();
        int QRCodeHeight = QRCodeBitmap.getHeight();
        int logoWidth = logoBitmap.getWidth();
        int logoHeight = logoBitmap.getHeight();

        /** 2. 计算画布缩放的宽高比 */
        float scaleWidth = QRCodeWidth * logoPercent / logoWidth;
        float scaleHeight = QRCodeHeight * logoPercent / logoHeight;


        /** 3. 使用Canvas绘制,合成图片 */
        Bitmap bitmap = Bitmap.createBitmap(QRCodeWidth, QRCodeHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(QRCodeBitmap, 0, 0, null);
        canvas.scale(scaleWidth, scaleHeight, QRCodeWidth / 2, QRCodeHeight / 2);
        canvas.drawBitmap(logoBitmap, QRCodeWidth / 2 - logoWidth / 2, QRCodeHeight / 2 - logoHeight / 2, null);

        return bitmap;
    }

}
