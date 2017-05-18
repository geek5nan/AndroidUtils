package com.devwu.utils.code;

import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;


public class RSA {


    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
    private static final String KEY_ALGORITHM = "RSA";
    private static final String ANDROID_PRO = "RSA/None/PKCS1Padding";

    /**
     * 签名
     * @param content       待签名内容
     * @param keyString     签名所用的密钥
     * @return              签名
     */
    public static String sign(String content, String keyString) {
        try {
            PrivateKey key = (PrivateKey) getKey(keyString, EncodeKeyType.ENCODE_KEY_PKCS8);
            // 用私钥对信息生成数字签名
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(key);
            signature.update(content.getBytes());
            return CodeUtil.encodeBase64(signature.sign());
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 验证签名
     * @param content      待签名内容
     * @param sign         签名
     * @param keyString    验证签名所需的公钥
     * @return             验证结果
     */
    public static boolean verify(String content, String sign, String keyString){
        try {
            PublicKey key = (PublicKey) getKey(keyString, EncodeKeyType.ENCODE_KEY_X509);
            // 用公钥验证签名信息
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(key);
            signature.update(content.getBytes());
            return signature.verify(CodeUtil.decodeBase64(sign));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 加密<br>
     * 用公钥加密
     *
     * @param originStr 将要加密的内容
     * @param keyString Base64编码的密钥
     * @return RSA加密后经Base64转码的字符串
     */
    public static String encrypt(String originStr, String keyString) {
        byte[] data = originStr.getBytes();
        try {
            Key key = getKey(keyString, EncodeKeyType.ENCODE_KEY_X509);
            return CodeUtil.encodeBase64(cipher(key, Cipher.ENCRYPT_MODE, data));
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 解密<br>
     * 用私钥解密
     *
     * @param encodedBase64Str RSA加密后经Base64转码后的字符串
     * @param keyStr           Base64编码的密钥
     * @return 加密前的原文
     * @throws Exception
     */
    public static String decrypt(String encodedBase64Str, String keyStr) {
        byte[] data = CodeUtil.decodeBase64(encodedBase64Str);
        try {
            Key privateKey = getKey(keyStr, EncodeKeyType.ENCODE_KEY_PKCS8);
            return new String(cipher(privateKey, Cipher.DECRYPT_MODE, data)).trim();
        } catch (Exception e) {
            return "";
        }
    }


    private static byte[] cipher(Key key, int mode, byte[] data) throws Exception {
        Cipher cipher = Cipher.getInstance(ANDROID_PRO);
        cipher.init(mode, key);
        return cipher.doFinal(data);

    }

    /**
     * @param keyString
     * @param keyType
     * @return 返回对应的Key
     * @throws Exception
     */
    private static Key getKey(String keyString, EncodeKeyType keyType) throws Exception {
        // 将密钥字符串还原为二进制数据
        byte[] keyBytes = CodeUtil.decodeBase64(keyString);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key key = null;
        //转为私钥
        if (keyType == EncodeKeyType.ENCODE_KEY_PKCS8) {
            key = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
        }
        //转为公钥
        if (keyType == EncodeKeyType.ENCODE_KEY_X509) {
            key = keyFactory.generatePublic(new X509EncodedKeySpec(keyBytes));
        }
        return key;
    }

    enum EncodeKeyType {
        ENCODE_KEY_X509,
        ENCODE_KEY_PKCS8,
    }


}
