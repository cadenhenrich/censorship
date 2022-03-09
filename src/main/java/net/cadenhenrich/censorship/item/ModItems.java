package net.cadenhenrich.censorship.item;

import net.cadenhenrich.censorship.CensorshipMod;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM,
                new Identifier(CensorshipMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        CensorshipMod.LOGGER.info("Registering ModItems for " +
                CensorshipMod.MOD_ID);
    }
}
