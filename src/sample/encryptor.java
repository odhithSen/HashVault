package sample;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Base64;

public class encryptor {

    private static String PATH2 = "Program Data.bin";
    private static ArrayList<Password> plainPasswordList = new ArrayList<>();
    private static SealedObject encryptedData;

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, IOException, InvalidKeyException, BadPaddingException, ClassNotFoundException {

//        addData();
        SecretKey key = getKeyFromPassword("odhith","1234");


        encryptedData = encryptObject("AES/CBC/PKCS5Padding",plainPasswordList,key,generateIv());
        saveData();

        loadData();

        plainPasswordList =(ArrayList<Password>) decryptObject("AES/CBC/PKCS5Padding",encryptedData,key,generateIv());
        System.out.println(plainPasswordList);


    }

    public static SecretKey getKeyFromPassword(String password, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
        return secret;
    }

    public static IvParameterSpec generateIv() {
        byte[] iv = {1,2,3,4,5,6,7,8,8,7,5,4,3,2,2,2};
        return new IvParameterSpec(iv);
    }





    // encrypt data
    public static SealedObject encryptObject(String algorithm, Serializable object,
                                             SecretKey key, IvParameterSpec iv) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, IOException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        SealedObject sealedObject = new SealedObject(object, cipher);
        return sealedObject;
    }

    //decrypt data
    public static Serializable decryptObject(String algorithm, SealedObject sealedObject,
                                             SecretKey key, IvParameterSpec iv) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
            ClassNotFoundException, BadPaddingException, IllegalBlockSizeException,
            IOException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        Serializable unsealObject = (Serializable) sealedObject.getObject(cipher);
        return unsealObject;
    }





    public static void saveData (){

        try {
            FileOutputStream fileOut = new FileOutputStream(PATH2);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(encryptedData);
            out.close();
            fileOut.close();
            System.out.println("\nSerialization Successful... Checkout your specified output file..\n");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadData (){

        try {
            FileInputStream fileIn = new FileInputStream(PATH2);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            System.out.println("Deserialized Data" );
            encryptedData = (SealedObject) in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




    public static void addData(){
        plainPasswordList.add(new Password("username 1","password 1","description 1"));
        plainPasswordList.add(new Password("username 2","password 2","description 2"));
        plainPasswordList.add(new Password("username 3","password 3","description 3"));
        plainPasswordList.add(new Password("username 4","password 4","description 4"));

    }

    public static byte[] hashPassword(String password) throws NoSuchAlgorithmException {

        // adding salt
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(password);
        //salt
        sBuilder.append("Cryptography is the study of secure communications techniques that allow only" +
                " the sender and intended recipient of a message to view its contents");

        final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
        final String SaltedPassword = sBuilder.toString();
        final byte[] hashBytes = digest.digest(SaltedPassword.getBytes(StandardCharsets.UTF_8));
        return hashBytes;
    }



}
