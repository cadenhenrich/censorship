package net.cadenhenrich.censorship.mixin;

import net.cadenhenrich.censorship.CensorshipDamageSource;
import net.cadenhenrich.censorship.CensorshipMod;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
    @Inject(at = @At("HEAD"), method = "onChatMessage(Lnet/minecraft/network/packet/c2s/play/ChatMessageC2SPacket;)V", cancellable = true)
    public void onChatMessage(ChatMessageC2SPacket packet, CallbackInfo info) {
        CensorshipMod.LOGGER.info("Chat message sent: " + packet.getChatMessage());
        if (CensorshipMod.shouldCensor(packet.getChatMessage()) && CensorshipMod.isCensored(((ServerPlayNetworkHandler)(Object)this).player.getUuid())) {
            CensorshipMod.LOGGER.info("Censoring message...");
            DamageSource damageSource = CensorshipDamageSource.sources[(int)Math.floor(Math.random()*(CensorshipDamageSource.sources.length-0+1)+0)];
            ((ServerPlayNetworkHandler)(Object)this).player.damage(damageSource, Float.MAX_VALUE);
            info.cancel();
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
