import javax.crypto.Cipher;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        AesShifr aes = new AesShifr();
        RsaShifr rsa = new RsaShifr();
        InputStream inputStream = System.in;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        System.out.print("Введите текст сообщения:");
        String message = reader.readLine();
        System.out.println("Передаваемое сообщение: " + message);
        byte[] aesMessage = aes.shifrAES(message.getBytes(StandardCharsets.UTF_8), Cipher.ENCRYPT_MODE);
        System.out.println(aesMessage);
        byte[] decMessage = aes.shifrAES(aesMessage,Cipher.DECRYPT_MODE);
        System.out.println(new String(decMessage));
        System.out.println("///////////////////////////////////////////////");
        byte[] rsaMessage = rsa.encryptDescriptMethod(message.getBytes(StandardCharsets.UTF_8),Cipher.ENCRYPT_MODE);
        System.out.println(new String(rsaMessage));
        var decRsaMessage = rsa.encryptDescriptMethod(rsaMessage,Cipher.DECRYPT_MODE);
        System.out.println(new String(decRsaMessage));


    }
}
