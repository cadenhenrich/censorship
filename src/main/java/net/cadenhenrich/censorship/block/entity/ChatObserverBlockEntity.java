package net.cadenhenrich.censorship.block.entity;

import java.util.List;

import net.cadenhenrich.censorship.CensorshipMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class ChatObserverBlockEntity extends BlockEntity {

    public ChatObserverBlockEntity(BlockPos pos, BlockState state) {
        super(CensorshipMod.CHAT_OBSERVER_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, ChatObserverBlockEntity blockEntity) {
        applyCensorship(world, pos);
    }

    public static void applyCensorship(World world, BlockPos pos) {
        if (world.isClient) return;

        Box box = new Box(pos).expand(50).stretch(0.0, world.getHeight(), 0.0);
        List<PlayerEntity> playersInRange = world.getNonSpectatingEntities(PlayerEntity.class, box);

        for (PlayerEntity p : playersInRange) {
            CensorshipMod.addCensoredPlayer(p.getUuid());
        }
    }
}
