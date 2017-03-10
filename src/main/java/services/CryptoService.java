package services;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.spec.KeySpec;
import java.util.Base64;

public class CryptoService {

    private byte[] iv;

    private Cipher cipherEncrypt;
    private Cipher cipherDecrypt;

    private SecretKeySpec secret;


    public CryptoService(){
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec("isenIsC00l".toCharArray(), "2017".getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            secret = new SecretKeySpec(tmp.getEncoded(), "AES");
            cipherEncrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipherDecrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipherEncrypt.init(Cipher.ENCRYPT_MODE, secret);
            AlgorithmParameters params = cipherEncrypt.getParameters();
            iv = params.getParameterSpec(IvParameterSpec.class).getIV();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String encrypt(byte[] clearInfo){
        try {

            byte[] ciphertext = cipherEncrypt.doFinal(clearInfo);
            return Base64.getEncoder().encodeToString(ciphertext);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public byte[] decrypt(String cryptedInfo, String iv){
        try {

            cipherDecrypt.init(Cipher.DECRYPT_MODE,secret,new IvParameterSpec(Base64.getDecoder().decode(iv)));
            return cipherDecrypt.doFinal(Base64.getDecoder().decode(cryptedInfo));
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public byte[] getIv() {
        return iv;
    }
}
