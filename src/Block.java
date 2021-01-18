
import java.util.Date;

public class Block {
    public String hash;
    public String previousHash;
    private String data; //or Transactions
    private long timestamp;
    private int nonce;

    public Block(String previousHash, String data) {
        this.previousHash = previousHash;
        this.data = data;
        this.timestamp = new Date().getTime();
        this.hash = calculateHash();
        this.nonce=0;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String calculateHash(){
        String calculatedHash=Hashing.applySHAHashing(previousHash+Long.toString(timestamp)+data+nonce);
        return calculatedHash;
    }

    public void mineBlock(int difficulty){
        String target=new String(new char[difficulty]).replace('\0','0');
        while(!hash.substring(0,difficulty).equals(target)){
            nonce++;
            hash=calculateHash();
        }
        System.out.println("Block Mined"+hash);
    }


}
