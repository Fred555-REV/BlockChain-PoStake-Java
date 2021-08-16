package learning.blockchain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Date;

public class Block {
    private final Date timeStamp;
    private final String previousHash;
    private final String hash;
    private final String data;
    private String validator; // the address of the person that made this block
    private String signature; //the encrypted hash of the block, signed by the validator

    public Block(String data, String previousHash, String hash) {
        timeStamp = Date.from(Instant.now());
        this.data = data;
        this.previousHash = previousHash;
        this.hash = hash;
//        this.validator = validator;
//        this.signature = signature;
    }

    public String blockHash() {
        return calculateHash(timeStamp, previousHash, data);
    }

//    static blockHash(block){
//        //destructuring
//        const { timestamp, lastHash, data } = block;
//        return Block.hash(timestamp,lastHash,data);
//    }

    public static String calculateHash(Date timeStamp, String previousHash, String data) {
        try {
            return SHA3_256(timeStamp + previousHash + data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Block genesis() {
        return new Block("", "", "");
    }

    public static Block createBlock(Block previousBlock, String data) {
        return new Block(data, previousBlock.previousHash, calculateHash(Date.from(Instant.now()), previousBlock.hash, data));
    }

    private static String SHA3_256(final String originalString) throws NoSuchAlgorithmException {
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

    public String getPreviousHash() {
        return previousHash;
    }

    public String getHash() {
        return hash;
    }

    @Override
    public String toString() {
        return "Block{" +
                "\ntimeStamp=" + timeStamp +
                "\n, previousHash='" + previousHash + '\'' +
                "\n, hash='" + hash + '\'' +
                "\n, data='" + data + '\'' +
                "\n, validator='" + validator + '\'' +
                "\n, signature='" + signature + '\'' +
                '}';
    }
}
