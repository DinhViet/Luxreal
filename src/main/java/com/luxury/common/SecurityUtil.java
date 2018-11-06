package com.luxury.common;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class SecurityUtil {
  public static String KEY = "14249605-99c5-4df7-af25-e5a2cfbd0298"; // Key
  // for gen account & password

  public static String KEY_SERIAL = "3190a60e-7315-464d-a185-60db1786184f"; // for

  // ma
  // hoa
  // card

  public static String encrypt(String key, String data) throws Exception {
    if (StringUtils.isBlank(key))
      key = KEY;
    Cipher cipher = Cipher.getInstance("TripleDES");
    MessageDigest md5 = MessageDigest.getInstance("MD5");
    md5.update(key.getBytes(), 0, key.length());
    String keymd5 = new BigInteger(1, md5.digest()).toString(16).substring(0, 24);
    SecretKeySpec keyspec = new SecretKeySpec(keymd5.getBytes(), "TripleDES");
    cipher.init(Cipher.ENCRYPT_MODE, keyspec);
    byte[] stringBytes = data.getBytes();
    byte[] raw = cipher.doFinal(stringBytes);
    BASE64Encoder encoder = new BASE64Encoder();
    String base64 = encoder.encode(raw);
    return base64;
  }

  public static String decrypt(String key, String data) throws Exception {
    if (StringUtils.isBlank(key))
      key = KEY;
    Cipher cipher = Cipher.getInstance("TripleDES");
    MessageDigest md5 = MessageDigest.getInstance("MD5");
    md5.update(key.getBytes(), 0, key.length());
    String keymd5 = new BigInteger(1, md5.digest()).toString(16).substring(0, 24);
    SecretKeySpec keyspec = new SecretKeySpec(keymd5.getBytes(), "TripleDES");
    cipher.init(Cipher.DECRYPT_MODE, keyspec);
    BASE64Decoder decoder = new BASE64Decoder();
    byte[] raw = decoder.decodeBuffer(data);
    byte[] stringBytes = cipher.doFinal(raw);
    String result = new String(stringBytes);
    return result;
  }

  public static String createSign(String data, String filePath) {
    try {
      final File privKeyFile = new File(filePath);
      final byte[] privKeyBytes = readFile(privKeyFile);
      final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      final PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec(privKeyBytes);
      final PrivateKey pk = (PrivateKey) keyFactory.generatePrivate(privSpec);

      final Signature sg = Signature.getInstance("SHA1withRSA");

      sg.initSign(pk);
      sg.update(data.getBytes());
      final byte[] bDS = sg.sign();
      return new String(org.apache.commons.codec.binary.Base64.encodeBase64(bDS));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return "";
  }

  public static boolean checkSign(String sign, String data, String publicKeyFile) {
    try {
      File pubKeyFile = new File(publicKeyFile);
      byte[] pubKeyBytes = readFile(pubKeyFile);
      X509EncodedKeySpec pubSpec = new X509EncodedKeySpec(pubKeyBytes);

      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      PublicKey k = (RSAPublicKey) keyFactory.generatePublic(pubSpec);

      Signature signature = Signature.getInstance("SHA1withRSA");
      signature.initVerify(k);
      signature.update(data.getBytes());

      return signature.verify(org.apache.commons.codec.binary.Base64.decodeBase64(sign.getBytes()));

    } catch (Exception ex) {
      ex.printStackTrace();
      System.out.println(ex.getMessage());
    }

    return false;
  }

  public static boolean checkSignXml(String sign, String data, String publicKey) {
    try {
      byte[] expBytes = Base64.decodeBase64(getXmlValue(publicKey, "Exponent"));
      byte[] modBytes = Base64.decodeBase64(getXmlValue(publicKey, "Modulus"));

      BigInteger modules = new BigInteger(1, modBytes);
      BigInteger exponent = new BigInteger(1, expBytes);
      RSAPublicKeySpec pubSpec = new RSAPublicKeySpec(modules, exponent);

      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      PublicKey k = (RSAPublicKey) keyFactory.generatePublic(pubSpec);

      Signature signature = Signature.getInstance("SHA1withRSA");
      signature.initVerify(k);
      signature.update(data.getBytes());

      return signature.verify(org.apache.commons.codec.binary.Base64.decodeBase64(sign.getBytes()));

    } catch (Exception ex) {
      ex.printStackTrace();
      System.out.println(ex.getMessage());
    }

    return false;
  }

  public static String getXmlValue(String xml, String tagName) {
    String openTag = "<" + tagName + ">";
    String closeTag = "</" + tagName + ">";
    int f = xml.indexOf(openTag) + openTag.length();
    int l = xml.indexOf(closeTag);
    return (f > l) ? "" : xml.substring(f, l);
  }

  public static byte[] readFile(final File file) throws FileNotFoundException, IOException {
    DataInputStream dis = null;
    try {
      dis = new DataInputStream(new FileInputStream(file));
      final byte[] data = new byte[(int) file.length()];
      dis.readFully(data);
      return data;
    } finally {
      if (dis != null) {
        dis.close();
      }
    }
  }

  public static void main(String[] a) throws Exception {
    // String data = "94auuydpxid2c2yplofjzuqk7x32glq1";
    // System.out.println(encrypt(null, data));
    // String hashed =
    // "H5G12IF4wfpLdikdlLUCRnYYsk/ukiZrHE0D/tz7uPMgQ9FZaV2L2g==";
    // System.out.println(decrypt("94auuydpxid2c2yplofjzuqk7x32glq1",
    // "uz7Net/NuHaP62lBiwKWGLD0woOhZflaji4diOVix5kM5X0uV3bcHxjZLqsqKIUtH0j1nyeSE4xn\nUOf6ln54B3gg4us7ylwgoGxp+M+rkEAtUKfLcymlerAc+4vOaOisObPVQD3xDG8="));
    // System.out.println(UUID.randomUUID());

    /*
     * String urlprivatekey = "/home/hp/key/merchant/tofu/private.der";
     * 
     * String publicKey =
     * "<RSAKeyValue><Modulus>8q5FVypq+VTbafwOZuUbNgkj6Zq/HYHpVwts27c1tB5bR9c76SK7EQBU7tmuoQbeU0Le00XU+TjcXktKnadXbhIwJWxqBdqUrZD2KNYxgHLlKR1CQKzA9cV0G65oPAZbqFy420VnxCKWDFfoXAvsVXEX0END9yekuy+CZAubWcU=</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>"
     * ; String text = "jackwin8x.nl1@gmail.com|974729|9xjio1gxkj34m3lkp1g1|1PAY0051"; String
     * dataSign2 = createSign(text, urlprivatekey); System.out.println(dataSign2);
     */

    // System.out.println(decrypt("94auuydpxid2c2yplofjzuqk7x32glq1",
    // "uz7Net/NuHZf3j6G0y/lT7lf62YhMrCL7xQmlxJceSLcKuvxIZlFjhjZLqsqKIUtgMN4JyztL54="));

    // String sign =
    // "XmC+KpKhz7fBDDvlJZZW9EXFBBQuZNriK5MJrRJIE0/PFzuzsCgS6BdV1hNQyrgTO7pFarb7rKR4bBib98VphxaOk2pVTg59YNkpx5fvxkpvfpCBuUy54fbgD0/BMQGUY3UcdkpwWy2n38J+kdp9KepEzi/hR2yOF5YYI7A709w=";
    // System.out.println(checkSign(sign,
    // "0|97efe1981bf945fb8cf96e772b349cbe",
    // "/home/hp/key/merchant/merchant@1pay.vn/public.der"));

    // System.out.println(decrypt(null,
    // "LvbdEeITcFm+3jbj+NbunY88+wVhvt0wi/gzUI5ouHsgQ9FZaV2L2g=="));
    // System.out.println(UUID.randomUUID().toString());

    /*
     * String data = "ghnOh3N0v+g5uKjuhcyYv3sjJkb/mJUuYAkcYkxZ8DOducFASguJVkQWGOm+cdOK0eeX1nJpIE8="
     * ; data = "hm1rG2maeeaLWlaMDV4T8AnCeCalcQ1lCr4Qq1R79hqrF1RAEPANUYCsn/IeORsIDIwEvWJtOMUb" +
     * "4PoRkks115KuQBVjIWCj4XPbRwU3fLCnCyKWVHoi3Ie3zY8UypKslEN1vrLKbjqe010ThvXB64dp" +
     * "xU6c73nT8TJbXt3DMbadoGaCbIt6bUkI+DNm6Qupm96GIG/q4m4/OTjLQihrVgOkAbM2/C9w+Tre" +
     * "J8zLG8NDGxUPqqFGVeeaP3vwrQ5fvvx9uKqi4ErdKFl8B82eHHxebeIH07quJKZI2p9VAMGDTbXn" +
     * "LZAlgvsT63m87OsCZmNNV4LnD/ibQ8xaODD2g+pE0QeoLM/v68JCcTlJSKaFOtqpW96sb+ti+OK0" +
     * "0b+0yodEPMzQYh3DRH1ToSFHl9O4dMpF3zz3Gk/ZBO+xxGzJQYK+8gp0cAjWughDFiMZSXV/kggt" +
     * "Kva22b7FEb+U6aW0Snh0lzAGONum9Ax0mZsTv8JY/ZqEApZoG75arSwwHFtLV3RIRNKGbWsbaZp5" +
     * "5otaVowNXhPwsT8wAj3SQm5ZVSpycA7P/+jkv+0F4d3lgKyf8h45GwgMjAS9Ym04xRvg+hGSSzXX" +
     * "P9XG15xFOZU9dtCtPgO0S3tq8DCcUXYwh7fNjxTKkqyUQ3W+sspuOp7TXROG9cHry4p4sLZFwrTy"
     * +"UO0ZKLqpjygO30jSmKWWSQj4M2bpC6kyuqfpUVoS3Q=="; data = decrypt(KEY_SERIAL, data);
     * System.out.println(data);
     */

    System.out.println("------------------------------");
    System.out.println(decrypt(null, "5orwiOX8cHqHHL74Xa7uqAcgASUG7qx3iantqHVXEj4gQ9FZaV2L2g=="));
    System.out.println(decrypt(null, "qMmM7gHzJkiayJUp/bOAE2hZIc+F1WC0"));

    /*
     * String data = "58PsuyXKdUEtI9AZFmOEQf64NICw6ygnn74M7JRR7LqdOefmbQSdRV8tP3A/1cUZufPlZQcGrO8="
     * ; System.out.println(decrypt(KEY_SERIAL, data));
     */

  }
}
