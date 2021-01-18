import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class BlockChain {
    public static ArrayList<Block> blockchain=new ArrayList<>();
    public static int difficult=5;

    public static boolean isChainValid(){
        Block currentBlock;
        Block previouBlock;

        for(int i=1;i< blockchain.size();i++){
            currentBlock=blockchain.get(i);
            previouBlock=blockchain.get(i-1);
            String hashTarget = new String(new char[difficult]).replace('\0', '0');
            //get previous hash from current Block
            String previoushashFromCurentBlock=currentBlock.getPreviousHash();

            //get hash from Previous Block
            String currentHashFromPreviousBlock=previouBlock.getHash();

            if(currentHashFromPreviousBlock.equals(previoushashFromCurentBlock)){
                if(currentBlock.hash.substring(0,difficult).equals(hashTarget)) {
                    System.out.println("Consistent: Hash are equal");
                    return true;
                }
                else {
                    return false;
                }
            }
            else{
                System.out.println("Incosistency: Hash not equal");
                return false;
            }

        }
        return false;

    }



    public static void main(String [] args){
        blockchain.add(new Block("0","This is the genesis block"));
        System.out.println("Trying to Mine block 0 ....");
        blockchain.get(0).mineBlock(difficult);
        blockchain.add(new Block(blockchain.get(blockchain.size()-1).hash,"This is the second block"));
        System.out.println("Trying to Mine block 1 ....");
        blockchain.get(1).mineBlock(difficult);
        blockchain.add(new Block(blockchain.get(blockchain.size()-1).hash,"This is the third block"));
        System.out.println("Trying to Mine block 2 ....");
        blockchain.get(2).mineBlock(difficult);
        isChainValid();
        System.out.println("\n Blockchain is Valid"+isChainValid());

        String blockchainJson=new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\n The block chain: ");
        System.out.println(blockchainJson);
    }
}
