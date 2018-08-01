package net.vaoday.mylibrary.util;

import android.annotation.SuppressLint;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

/**
 * Created by quang on 25/09/2015.
 */
public class Encoder {
    private static String algorithm = "AES";
//	private static byte[] keyValue = new byte[] { 'A', 'S', 'e', 'c', 'u', 'r',
//			'e', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };

    private static byte[] keyValue = new byte[] { 'A', 'S', 'e', 'y', 'u', 'r',
            'e', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'z' };

    // Performs Encryption
    @SuppressLint("TrulyRandom")
    public static String encrypt(String plainText) throws Exception {
        Key key = generateKey();
        Cipher chiper = Cipher.getInstance(algorithm);
        chiper.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = chiper.doFinal(plainText.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    // Performs decryption
    public static String decrypt(String encryptedText) throws Exception {
        // generate key
        Key key = generateKey();
        Cipher chiper = Cipher.getInstance(algorithm);
        chiper.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedText);
        byte[] decValue = chiper.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    // generateKey() is used to generate a secret key for AES algorithm
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, algorithm);
        return key;
    }

}
