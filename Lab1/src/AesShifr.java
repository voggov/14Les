import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AesShifr {
    private int firstMes;
    private SecretKey secretKey;
    private byte[] salt = new byte[8];

    public AesShifr() {

        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            this.secretKey = keyGenerator.generateKey();
            SecureRandom random = new SecureRandom();
            random.nextBytes(salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    public byte[] shifrAES(byte[] inputMes, int mode) {
        if (mode == Cipher.ENCRYPT_MODE) {
            this.firstMes = inputMes.length;
            byte[] temp = new byte[inputMes.length + this.salt.length];
            System.arraycopy(inputMes, 0, temp, 0, inputMes.length);
            System.arraycopy(salt, 0, temp, inputMes.length, salt.length);
            byte[] out = shifr(temp, mode);
            return out;
        }else {
            return shifr(inputMes,mode);
        }
    }

    private byte[] shifr(byte[] inputMes, int mode) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(mode, this.secretKey);
            byte[] outMes = cipher.doFinal(inputMes);
            if (mode == Cipher.DECRYPT_MODE) {
                byte[] out = new byte[this.firstMes];
                System.arraycopy(outMes, 0, out, 0, this.firstMes);
                return out;
            }
            return outMes;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
