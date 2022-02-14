package com.epicoders.metier;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

  public  class  PasswordEncoder {
    private static final String ALGO = "AES";
    private static final byte[] keyValue = new byte [16];

    public static String encrypt(String password) {

        String encoderPassword = "";
        try {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = c.doFinal(password.getBytes());
            encoderPassword = Base64.getEncoder().encodeToString(encVal);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return encoderPassword;
    }
        public static String decrypt(String encryptedData){

            String decoderPassword = "";
            try {
                Key key = generateKey();
                Cipher c = Cipher.getInstance(ALGO);
                c.init(Cipher.DECRYPT_MODE,key);
                byte [] decordedValue = Base64.getDecoder().decode(encryptedData);
                byte [] decValue = c.doFinal(decordedValue);
                decoderPassword = new String(decValue);

            }catch (Exception e){
                e.printStackTrace();

            }
            return decoderPassword;


    }

    private static Key generateKey() {
        SecretKeySpec key = new SecretKeySpec(keyValue,ALGO);
        return key;
    }


}
