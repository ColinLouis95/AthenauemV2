package com.athenauem;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/* Class made for encrypting data before storing in MySQL db/decrypting data after retrieving data stored.
   Essentially when user will input what is wanted to store into local db (MySQL db), first we will encrypt the
   text, and then we will store it into the db. Once the user wants to see the contents, we will retrieve the stored
   encrypted text and show the decrypted text to the user.

   Only worried about encrypting the password.

 */

public class cryptography {
    protected static Cipher cipher;
    protected static SecretKeySpec key;
    protected static IvParameterSpec spec;
    private byte[] keyBytes = "000010010090909".getBytes(StandardCharsets.UTF_8);
    private byte[] ivBytes = "10000000".getBytes(StandardCharsets.UTF_8);

    /* Main method used for testing cryptography class
    public static void main(String[] args) throws InvalidAlgorithmParameterException, IllegalBlockSizeException,
            BadPaddingException, IOException, InvalidKeyException, ClassNotFoundException {
        cryptography c = new cryptography();
        String word = "asjkdngs";

        byte[] ennn = c.encrypt(word);
        System.out.println(word);
        System.out.println(ennn);
        System.out.println(c.decrypt(ennn));
    }*/

    public cryptography() {
        spec = new IvParameterSpec(ivBytes);

        try{
            DESKeySpec dkey = new DESKeySpec(keyBytes);
            key = new SecretKeySpec(dkey.getKey(),"DES");
            cipher = cipher.getInstance("DES/CBC/PKCS5Padding");

        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public byte[] encrypt(Object obj) throws InvalidAlgorithmParameterException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, IOException {
        byte[] input = convertToByteArray(obj);
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);

        return cipher.doFinal(input);
    }

    public static Object decrypt(byte[] data) throws InvalidAlgorithmParameterException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, IOException, ClassNotFoundException {
        cipher.init(Cipher.DECRYPT_MODE,key,spec);

        return convertFromByteArray(cipher.doFinal(data));
    }

    protected static byte[] convertToByteArray(Object obj) throws IOException{
        ByteArrayOutputStream boutStream;
        ObjectOutputStream out;

        boutStream = new ByteArrayOutputStream();
        out = new ObjectOutputStream(boutStream);
        out.writeObject(obj);

        return boutStream.toByteArray();
    }

    private static Object convertFromByteArray(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream binStream;
        ObjectInputStream in;

        binStream = new ByteArrayInputStream(data);
        in = new ObjectInputStream(binStream);
        Object read = in.readObject();
        in.close();

        return read;
    }

}
