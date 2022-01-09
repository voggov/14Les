import java.security.*;

public class Sign {
    private Signature signature;
    private KeyPair keyPair;

    public Sign(){
        try {
            this.signature = Signature.getInstance("SHA256WithDSA");
            SecureRandom secureRandom = new SecureRandom();
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
            this.keyPair = keyPairGenerator.generateKeyPair();
            this.signature.initSign(this.keyPair.getPrivate(),secureRandom);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public byte[] signatureMethod(byte[] inputMessage){
        try {
            this.signature.update(inputMessage);
            byte[] temp = this.signature.sign();
            return temp;

        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean verifyMethod(byte[] inputSign, byte[] message){
        try {
            Signature sign = Signature.getInstance("SHA256WithDSA");
            sign.initVerify(this.keyPair.getPublic());
            sign.update(message);
            var verified = sign.verify(inputSign);
            return verified;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return false;

    }
}
