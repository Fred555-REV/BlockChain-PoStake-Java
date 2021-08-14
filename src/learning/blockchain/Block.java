package learning.blockchain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Date;

public class Block {
    private final Date timeStamp;
    private String previousHash;
    private String hash;
    private String data;
    private String validator; // the address of the person that made this block
    private String signature; //the encrypted hash of the block, signed by the validator

    public Block(String data, String validator, String signature){
        timeStamp = Date.from(Instant.now());
        this.data = data;
        this.validator = validator;
        this.signature = signature;
    }

    public String calculateHash() {
        try {
            return SHA3_256((previousHash + timeStamp + data));// add rest of variables for calc hash
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Block genesis(){
        return new Block("","","");
    }

    private String SHA3_256(final String originalString) throws NoSuchAlgorithmException {
        final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
        final byte[] hashbytes = digest.digest(
                originalString.getBytes(StandardCharsets.UTF_8));
        String sha3Hex = bytesToHex(hashbytes);
        return sha3Hex;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            result.append(String.format("%02x", aByte));
            // upper case
            // result.append(String.format("%02X", aByte));
        }
        return result.toString();
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return "Block{" +
                "timeStamp=" + timeStamp +
                ", previousHash='" + previousHash + '\'' +
                ", hash='" + hash + '\'' +
                ", data='" + data + '\'' +
                ", validator='" + validator + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}
