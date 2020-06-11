//package com.google.zxing.client.android.utils;
//
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.text.TextUtils;
//
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.EncodeHintType;
//import com.google.zxing.MultiFormatWriter;
//import com.google.zxing.WriterException;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.qrcode.QRCodeWriter;
//
//import java.util.EnumMap;
//import java.util.Hashtable;
//import java.util.Map;
//
//import static android.graphics.Color.BLACK;
//import static android.graphics.Color.WHITE;
//
///**
// * 暂不允许使用
// *
// * @author TomCan
// * @description:
// * @date :2019/11/13 10:22
// */
//class QRCodeTools {
//
//    private QRCodeTools() {
//    }
//
//    private Bitmap buildQRcodeBitmap(String content, Bitmap logoBitmap, float logoPercent) {
//
//        Bitmap QRCodeBitmap = null;
//        try {
//            Map<EncodeHintType, Object> hints = null;
////            String encoding = getEncoding(str);//获取字符编码
//            String encoding = "utf-8";//获取字符编码
//
//            if (encoding != null) {
//                hints = new EnumMap<>(EncodeHintType.class);
//                hints.put(EncodeHintType.CHARACTER_SET, encoding);
//            }
//            BitMatrix result = null; // 通过字符串创建二维矩阵
//            result = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 600, 600, hints);
//            int width = result.getWidth();
//            int height = result.getHeight();
//            int[] pixels = new int[width * height];
//
//            for (int y = 0; y < height; y++) {
//                int offset = y * width;
//                for (int x = 0; x < width; x++) {
//                    pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;//根据二维矩阵数据创建数组
//                }
//            }
//            QRCodeBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);//创建位图
//            QRCodeBitmap.setPixels(pixels, 0, width, 0, 0, width, height);//将数组加载到位图中
//            if (logoBitmap != null)
//                QRCodeBitmap = attachLogo(QRCodeBitmap, logoBitmap, logoPercent);
//
//        } catch (WriterException e) {
//            e.printStackTrace();
//        }
//        return QRCodeBitmap;
//    }
//
//
//
//    /**
//     * 生成简单二维码
//     *
//     * @param content                字符串内容
//     * @param width                  二维码宽度
//     * @param height                 二维码高度
//     * @param character_set          编码方式（一般使用UTF-8）
//     * @param error_correction_level 容错率 L：7% M：15% Q：25% H：35%
//     * @param margin                 空白边距（二维码与边框的空白区域）
//     * @param color_black            黑色色块
//     * @param color_white            白色色块
//     * @return BitMap
//     */
//    private Bitmap createQRCodeBitmap(String content, int width, int height,
//                                      String character_set, String error_correction_level,
//                                      String margin, int color_black, int color_white) {
//        // 字符串内容判空
//        if (TextUtils.isEmpty(content)) {
//            return null;
//        }
//        // 宽和高>=0
//        if (width < 0 || height < 0) {
//            return null;
//        }
//        try {
//            /** 1.设置二维码相关配置 */
//            Hashtable<EncodeHintType, String> hints = new Hashtable<>();
//            // 字符转码格式设置
//            if (!TextUtils.isEmpty(character_set)) {
//                hints.put(EncodeHintType.CHARACTER_SET, character_set);
//            }
//            // 容错率设置
//            if (!TextUtils.isEmpty(error_correction_level)) {
//                hints.put(EncodeHintType.ERROR_CORRECTION, error_correction_level);
//            }
//            // 空白边距设置
//            if (!TextUtils.isEmpty(margin)) {
//                hints.put(EncodeHintType.MARGIN, margin);
//            }
//            /** 2.将配置参数传入到QRCodeWriter的encode方法生成BitMatrix(位矩阵)对象 */
//            BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
//
//            /** 3.创建像素数组,并根据BitMatrix(位矩阵)对象为数组元素赋颜色值 */
//            int[] pixels = new int[width * height];
//            for (int y = 0; y < height; y++) {
//                for (int x = 0; x < width; x++) {
//                    //bitMatrix.get(x,y)方法返回true是黑色色块，false是白色色块
//                    if (bitMatrix.get(x, y)) {
//                        pixels[y * width + x] = color_black;//黑色色块像素设置
//                    } else {
//                        pixels[y * width + x] = color_white;// 白色色块像素设置
//                    }
//                }
//            }
//            /** 4.创建Bitmap对象,根据像素数组设置Bitmap每个像素点的颜色值,并返回Bitmap对象 */
//            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
//            return bitmap;
//        } catch (WriterException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//
//    /**
//     * 生成logo 二维码
//     *
//     * @param content                字符串内容
//     * @param width                  二维码宽度
//     * @param height                 二维码高度
//     * @param character_set          编码方式（一般使用UTF-8）
//     * @param error_correction_level 容错率 L：7% M：15% Q：25% H：35%
//     * @param margin                 空白边距（二维码与边框的空白区域）
//     * @param color_black            黑色色块
//     * @param color_white            白色色块
//     * @param logoBitmap             logo图片
//     * @param logoPercent            logo所占百分比
//     * @return
//     */
//    private Bitmap createQRCodeBitmap(String content, int width, int height, String character_set,
//                                      String error_correction_level, String margin, int color_black,
//                                      int color_white, Bitmap logoBitmap, float logoPercent) {
//        // 字符串内容判空
//        if (TextUtils.isEmpty(content)) {
//            return null;
//        }
//        // 宽和高>=0
//        if (width < 0 || height < 0) {
//            return null;
//        }
//        try {
//            /** 1.设置二维码相关配置,生成BitMatrix(位矩阵)对象 */
//            Hashtable<EncodeHintType, String> hints = new Hashtable<>();
//            // 字符转码格式设置
//            if (!TextUtils.isEmpty(character_set)) {
//                hints.put(EncodeHintType.CHARACTER_SET, character_set);
//            }
//            // 容错率设置
//            if (!TextUtils.isEmpty(error_correction_level)) {
//                hints.put(EncodeHintType.ERROR_CORRECTION, error_correction_level);
//            }
//            // 空白边距设置
//            if (!TextUtils.isEmpty(margin)) {
//                hints.put(EncodeHintType.MARGIN, margin);
//            }
//            /** 2.将配置参数传入到QRCodeWriter的encode方法生成BitMatrix(位矩阵)对象 */
//            BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
//
//            /** 3.创建像素数组,并根据BitMatrix(位矩阵)对象为数组元素赋颜色值 */
//            int[] pixels = new int[width * height];
//            for (int y = 0; y < height; y++) {
//                for (int x = 0; x < width; x++) {
//                    //bitMatrix.get(x,y)方法返回true是黑色色块，false是白色色块
//                    if (bitMatrix.get(x, y)) {
//                        pixels[y * width + x] = color_black;//黑色色块像素设置
//                    } else {
//                        pixels[y * width + x] = color_white;// 白色色块像素设置
//                    }
//                }
//            }
//
//            /** 4.创建Bitmap对象,根据像素数组设置Bitmap每个像素点的颜色值,并返回Bitmap对象 */
//            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
//
//            /** 5.为二维码添加logo图标 */
//            if (logoBitmap != null) {
//                return attachLogo(bitmap, logoBitmap, logoPercent);
//            }
//            return bitmap;
//        } catch (WriterException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//
//
//
//    /**
//     * 向二维码中间添加logo图片(图片合成)
//     *
//     * @param QRCodeBitmap 原图片（生成的简单二维码图片）
//     * @param logoBitmap   logo图片
//     * @param logoPercent  百分比 (用于调整logo图片在原图片中的显示大小, 取值范围[0,1] )
//     * @return
//     */
//    private Bitmap attachLogo(Bitmap QRCodeBitmap, Bitmap logoBitmap, float logoPercent) {
//
//
//        if (QRCodeBitmap == null) {
//            return null;
//        }
//        if (logoBitmap == null) {
//            return QRCodeBitmap;
//        }
//
//        //传值不合法时使用0.2F
//        if (logoPercent < 0F || logoPercent > 1F) {
//            logoPercent = 0.2F;
//        }
//
//
//        /** 1. 获取原图片和Logo图片各自的宽、高值 */
//        int QRCodeWidth = QRCodeBitmap.getWidth();
//        int QRCodeHeight = QRCodeBitmap.getHeight();
//        int logoWidth = logoBitmap.getWidth();
//        int logoHeight = logoBitmap.getHeight();
//
//        /** 2. 计算画布缩放的宽高比 */
//        float scaleWidth = QRCodeWidth * logoPercent / logoWidth;
//        float scaleHeight = QRCodeHeight * logoPercent / logoHeight;
//
//
//        /** 3. 使用Canvas绘制,合成图片 */
//        Bitmap bitmap = Bitmap.createBitmap(QRCodeWidth, QRCodeHeight, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        canvas.drawBitmap(QRCodeBitmap, 0, 0, null);
//        canvas.scale(scaleWidth, scaleHeight, QRCodeWidth / 2, QRCodeHeight / 2);
//        canvas.drawBitmap(logoBitmap, QRCodeWidth / 2 - logoWidth / 2, QRCodeHeight / 2 - logoHeight / 2, null);
//
//        return bitmap;
//    }
//
//
//}
