package net.cadenhenrich.censorship.block;

import net.cadenhenrich.censorship.CensorshipMod;
import net.cadenhenrich.censorship.block.entity.ChatObserverBlockEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ChatObserverBlock extends BlockWithEntity {
    public ChatObserverBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ChatObserverBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, CensorshipMod.CHAT_OBSERVER_BLOCK_ENTITY, (world1, pos, state1, be) -> ChatObserverBlockEntity.tick(world1, pos, state1, be));
    }
}
