package com.devwu.utils.code;


public abstract class CodeUtil {
    public static void setPrivateKey(String privateKey) {
        CodeUtil.privateKey = privateKey;
    }

    public static void setPublicKey(String publicKey) {
        CodeUtil.publicKey = publicKey;
    }

    private static String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANFSfxQa1eD5KwG8M7xL8HnxSurOdYmnRA7/ur2qJBuIJeRVfjrQyOJCfhjrLIw7+m4SOz5fXmJXlEUbEhV56T+EpuJsQmY+HZgionbnbBxfAjkTrEgDM7wBC1SPizHkGtTgyUKBB67f4zRe9dCmFrZ50fg0uo2LO+CM8hRkWUbTAgMBAAECgYEAzEqxkBOozksguC7QZ2POLZVbwtMBT/mZqPHZdaaKWvyDztfJvOlsRbcTjopoDe2zup28iGFXoq9LHC8RdThodTum2dJo+APOmqVvz5/Mj0KI2pxf8iwG1l6oivz4LzEYbCix0HTjDJP525QhrsVMmpcTeJCUTuEggn1M+f32BeECQQDtgisXVoYTlNRKg5nVR+HhdkEQ+Z36s8BhUSVCR35mP8ZiKgzC//+x+KZmoSy/QHMPoGUHYztTwp+nPs8s0JUrAkEA4Z6K5noFuKoF4BFEae3Mx+kTU2plJPgDOPNbK3jBiOt4JyNZCE7faXIiAo4/MKKiFI79538CyazGDJDDxLCQ+QJAGlJFskVtgHgK+uMQxlk8CD+RwG7ZejI3jNFwXqMLuAszUie02FuEm9Who1gTXHKpDb9J0wLFTWgRR3infyDylwJAY0A5XlZDeg7hcsZl0CuaMD8qD0H4qKCp2j3D14XRrYfYXW9BVVRNF7frmjA1QBHrNxwIOfQ3p7xni1OLq1T5EQJBALIsR8hos//TW60eh6keps+oMNeJsj+MyDxVcgHF/+vioMsWHLm+yaMdKzOBorhpbiEXNyqd7cruTdTsPYbPCKI=";
    private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDRUn8UGtXg+SsBvDO8S/B58UrqznWJp0QO/7q9qiQbiCXkVX460MjiQn4Y6yyMO/puEjs+X15iV5RFGxIVeek/hKbibEJmPh2YIqJ252wcXwI5E6xIAzO8AQtUj4sx5BrU4MlCgQeu3+M0XvXQpha2edH4NLqNizvgjPIUZFlG0wIDAQAB";

    public static String signRSA(String content) {
        return RSA.sign(content, privateKey);
    }


    public static boolean verifyRSA(String content, String sign) {
        return RSA.verify(content, sign, publicKey);
    }

    public static String encodeRSA(String originStr) {//加密
        return RSA.encrypt(originStr, publicKey);
    }

    public static String decodeRSA(String encodedBase64Str) {//解密
        return RSA.decrypt(encodedBase64Str, privateKey);
    }

    public static String encodeBase64(byte[] key) {
        return Base64.encode(key);
    }

    public static byte[] decodeBase64(String key) {
        return Base64.decode(key);
    }

    public static String encodeMD5(String sourceStr) {
        return MD5.encodeMD5(sourceStr);
    }
}
