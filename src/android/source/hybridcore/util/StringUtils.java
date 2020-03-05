package com.centerm.hybridcore.util;

/**
 * @author chenyijiang
 * @data   2018/5/31
 */
public class StringUtils {
    /**
     * 转化byte数组为16进制String
     * @param bArray 传进来的byte数组
     * @return 转化后的字符串
     */
    public static  String bytesToHexString(byte[] bArray) {
        StringBuilder sb = new StringBuilder(bArray.length);
        String sTemp;
         for (int i:bArray) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }
}
