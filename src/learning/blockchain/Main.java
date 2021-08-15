package learning.blockchain;

public class Main {

    public static void main(String[] args) {
	BlockChain fredChain = new BlockChain();
        fredChain.chain.add(Block.createBlock(fredChain.chain.get(0),"12"));
        System.out.println( fredChain);
    }
}
