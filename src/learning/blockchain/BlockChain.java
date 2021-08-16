package learning.blockchain;

import java.util.ArrayList;
import java.util.List;

public class BlockChain {

    public List<Block> chain;

    public BlockChain() {
        chain = new ArrayList<>();
        chain.add(Block.genesis());
    }

    public Block addBlock(String data) {
        Block block = Block.createBlock(this.chain.get(this.chain.size() - 1), data);
        this.chain.add(block);
        return block;
    }

    public boolean isChainValid() {
        if (!chain.get(0).toString().equals(Block.genesis().toString())) {
            return false;
        }
        for (int i = 1; i < chain.size(); i++) {
            Block current = chain.get(i);
            Block previous = chain.get(i - 1);
            if (!current.getPreviousHash().equals(previous.getHash()) || !current.getHash().equals(current.blockHash())) {
                return false;
            }
        }
        return true;
    }

    public void replaceChainWith(BlockChain newChain){
        if(newChain.chain.size()<= this.chain.size()){
            System.out.println("Recieved chain is not longer than current chain");

        }else if(!newChain.isChainValid()){
            System.out.println("Recieved chain is invalid");

        }

        System.out.println("Replacing the current chain with new chain");
        this.chain = newChain.chain;
    }

    @Override
    public String toString() {
        return "BlockChain{" +
                "chain=\n" + chain +
                "\n}";
    }
}
