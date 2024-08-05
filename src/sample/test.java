package sample;

public class test {
    public static void main(String[] args) {

        PasswordGenerator pg = new PasswordGenerator();
        for (int i = 0; i < 1000; i++) {
            System.out.println(pg.getGeneratedPassword());
        }
    }
}
