package com.devwu.utils.code;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by WuNan-QF on 16/7/15.
 */
public class MD5 {
    public static String encodeMD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte b[] = md.digest(sourceStr.getBytes());
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                int i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }
}
