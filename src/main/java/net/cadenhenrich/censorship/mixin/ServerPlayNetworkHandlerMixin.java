package net.cadenhenrich.censorship.mixin;

import net.cadenhenrich.censorship.CensorshipMod;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.cadenhenrich.censorship.mixin.ServerPlayNetworkHandlerAccessor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
    @Inject(at = @At("HEAD"), method = "onChatMessage(Lnet/minecraft/network/packet/c2s/play/ChatMessageC2SPacket;)V")
    public void onChatMessage(ChatMessageC2SPacket packet, CallbackInfo info) {
        CensorshipMod.LOGGER.info("Chat message sent: " + packet.getChatMessage());
        if (CensorshipMod.shouldCensor(packet.getChatMessage()) && CensorshipMod.isCensored(((ServerPlayNetworkHandler)(Object)this).player.getUuid())) {
            CensorshipMod.LOGGER.info("Censoring message...");
            ((ServerPlayNetworkHandler)(Object)this).player.damage(DamageSource.GENERIC, Float.MAX_VALUE);
        }
    }

    @Inject(at = @At("TAIL"), method = "tick()V")
    public void tick(CallbackInfo info) {
        CensorshipMod.removeQueued();
        if (((ServerPlayNetworkHandlerAccessor)(ServerPlayNetworkHandler)(Object)this).getTicks() % 80 == 0) {
            CensorshipMod.uncensor(((ServerPlayNetworkHandler)(Object)this).player.getUuid());
        }
    }
}
