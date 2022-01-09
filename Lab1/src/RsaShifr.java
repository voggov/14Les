import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;

public class RsaShifr {
    private KeyPairGenerator pairGenerator;
    private KeyPair pair;
    private Key publicKey;
    private Key privateKey;

    public RsaShifr() throws NoSuchAlgorithmException {
        this.pairGenerator = KeyPairGenerator.getInstance("RSA");
        this.pair = pairGenerator.generateKeyPair();
        this.publicKey = pair.getPublic();
        this.privateKey = pair.getPrivate();
    }

    public byte[] encryptDescriptMethod(byte[] inputMes, int mode) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            if (mode == Cipher.ENCRYPT_MODE) {
                cipher.init(mode, this.publicKey);
                var temp = cipher.doFinal(inputMes);
                return temp;
            } else {
                cipher.init(mode, this.privateKey);
                var temp = cipher.doFinal(inputMes);
                return temp;
            }
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }


}
