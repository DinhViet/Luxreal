package com.luxury.common;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by binhminh on 10/01/2017.
 */
public class TripleDESMerchant {
  private static final String ALGORITHM = "DESede";

  private static Key generateKey(byte[] keyValue) {
    Key key = new SecretKeySpec(keyValue, ALGORITHM);
    return key;
  }

  public static byte[] encryptUseECBModeWithPKCS5Padding(byte[] keyValue, byte[] data)
      throws Exception {
    Key key = generateKey(keyValue);
    Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE, key);
    byte[] encVal = cipher.doFinal(data);
    return encVal;
  }

  public static byte[] decryptUseECBModeWithPKCS5Padding(byte[] keyValue, byte[] encryptedData)
      throws Exception {
    Key key = generateKey(keyValue);
    Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
    cipher.init(Cipher.DECRYPT_MODE, key);
    byte[] decValue = cipher.doFinal(encryptedData);
    return decValue;
  }
}
