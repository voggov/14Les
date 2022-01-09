import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        Sign sign = new Sign();

        InputStream inputStream = System.in;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        System.out.print("Введите сообщение: ");
        String message = reader.readLine();

        byte[] digitSign = sign.signatureMethod(message.getBytes(StandardCharsets.UTF_8));
        System.out.println("Цифровая подпись: " + new String(digitSign));

        System.out.println("Проверка подписи: " + sign.verifyMethod(digitSign,message.getBytes(StandardCharsets.UTF_8)));



    }
}
