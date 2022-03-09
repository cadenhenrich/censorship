package net.cadenhenrich.censorship.block;

import net.cadenhenrich.censorship.block.entity.ChatObserverBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class ChatObserverBlock extends Block implements BlockEntityProvider {
    public ChatObserverBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ChatObserverBlockEntity(pos, state);
    }
}
