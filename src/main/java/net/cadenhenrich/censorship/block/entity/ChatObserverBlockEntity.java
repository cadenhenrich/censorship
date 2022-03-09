package net.cadenhenrich.censorship.block.entity;

import net.cadenhenrich.censorship.CensorshipMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class ChatObserverBlockEntity extends BlockEntity {

    public ChatObserverBlockEntity(BlockPos pos, BlockState state) {
        super(CensorshipMod.CHAT_OBSERVER_BLOCK_ENTITY, pos, state);
    }

}
