package learning.blockchain;

import java.util.ArrayList;
import java.util.List;

public class BlockChain {

    public List<Block> chain;

    public BlockChain() {
        chain = new ArrayList<>();
        chain.add(Block.genesis());
    }

    @Override
    public String toString() {
        return "BlockChain{" +
                "chain=\n" + chain +
                "\n}";
    }
}
