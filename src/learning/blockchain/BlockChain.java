package learning.blockchain;

import java.util.ArrayList;
import java.util.List;

public class BlockChain {

    public List<Block> chain;

    public BlockChain() {
        chain = new ArrayList<>();
        chain.add(Block.genesis());
    }

    public Block addBlock(String data){
        Block block = Block.createBlock(this.chain.get(this.chain.size()-1),data);
        this.chain.add(block);
        return block;
    }

    @Override
    public String toString() {
        return "BlockChain{" +
                "chain=\n" + chain +
                "\n}";
    }
}
