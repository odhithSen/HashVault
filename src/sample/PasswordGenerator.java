package sample;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordGenerator {

    private String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String integers = "1234567890";
    private String characters = "!@#$%^&*!@#$%^&*";

    public PasswordGenerator() {
    }
    public String getGeneratedPassword (){
        boolean resume = true;

        StringBuffer sBuffer = new StringBuffer();

        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(letters);
        sBuilder.append(integers);
        sBuilder.append(characters);

        SecureRandom sRnd = new SecureRandom();

        while (resume) {
            for (int i = 0; i < 15; i++) {
                sBuffer.append(sBuilder.charAt(sRnd.nextInt(78)));
            }
            if (checkStrength(sBuffer.toString()))
            {
                resume = false;
            }
            else
            {
                resume = true;
                sBuffer.delete(0, sBuffer.length());
            }
        }
            return sBuffer.toString();
    }

    public boolean checkStrength (String password){

        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);

        if (m.matches()){
            return true;
        }
        else {
            return false;
        }
    }
}
