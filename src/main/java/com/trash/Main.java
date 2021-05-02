package com.trash;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

//блядская функция
public class Main {

    public static void main(String[] args) throws Exception {
        String key = "000102030405060708090a0b0c0d0e0f";
        String s = "20276d22a19f07eaba8110010006aabbccddeeff010203040105060708090a0b0c0d0e0f111c";
        String initialVector = "000102030405060708090a0b0c0d0e0f";

        System.out.println(encryptBSh(s, key, initialVector));
    }

    public static String encryptBSh(String s, String key, String initialVector) {
        return byteArrayToHexString(encrypt(transformHexStringToByteArray(s),
                transformHexStringToByteArray(key),
                transformHexStringToByteArray(initialVector)));
    }

    public static byte[] encrypt(byte[] s, byte[] key, byte[] initialVector) {

        IvParameterSpec ivParameterSpec = new IvParameterSpec(initialVector);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

        // Encrypt.
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            return cipher.doFinal(s);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public static String byteArrayToHexString(byte[] array) {
        byte[] res = new byte[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            res[2 * i] = getAsciByteForDigit((byte) ((array[i] & 0xf0) >> 4));
            res[2 * i + 1] = getAsciByteForDigit((byte) (array[i] & 0xf));
        }

        return new String(res, StandardCharsets.US_ASCII);
    }

    private static byte getAsciByteForDigit(byte d) {
        return (byte) (d > 9 ? d - 10 + 'a' : d + '0');
    }

    public static byte[] transformHexStringToByteArray(String s) {
        if (s.isEmpty()) {
            return new byte[0];
        }

        byte[] sb = s.getBytes(StandardCharsets.US_ASCII);
        byte[] res = new byte[sb.length / 2 + (sb.length & 1)];

        for (int ri = sb.length - 1; ri >= 0; ri--) {
            int i = sb.length - 1 - ri;

            byte digit;
            if (sb[ri] - '0' >= 0 && sb[ri] - '0' < 10) {
                digit = (byte) (sb[ri] - '0');
            } else if (sb[ri] - 'a' >= 0 && sb[ri] - 'a' < 6) {
                digit = (byte) (sb[ri] - 'a' + 10);
            } else {
                throw new IllegalArgumentException();
            }

            res[res.length - 1 - i / 2] += (i & 1) == 0 ? digit : digit * 16;
        }

        return res;
    }
}