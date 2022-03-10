package net.cadenhenrich.censorship;

import net.cadenhenrich.censorship.block.ChatObserverBlock;
import net.cadenhenrich.censorship.block.entity.ChatObserverBlockEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
// import java.util.Arrays;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CensorshipMod implements ModInitializer {
    public static final String MOD_ID = "censorship";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final ChatObserverBlock CHAT_OBSERVER = new ChatObserverBlock(
            FabricBlockSettings.of(Material.STONE).strength(6f)
                .requiresTool());
    public static BlockEntityType<ChatObserverBlockEntity> CHAT_OBSERVER_BLOCK_ENTITY;

    private static ArrayList<String> censorList = new ArrayList<String>();
    private static ArrayList<UUID> censoredPlayers = new ArrayList<UUID>();
    private static ArrayList<UUID> uncensorQueue = new ArrayList<UUID>();

    public static void initializeCensorList() {
        censorList.add("deez");
        censorList.add("nuts");
    }

    public static boolean shouldCensor(String message) {
        for (String s : censorList) {
            LOGGER.info("Checking " + s + "...");
            if (message.contains(s)) {
                return true;
            }
        }
        return false;
    }

    public static void addCensoredPlayer(UUID uuid) {
        if (!censoredPlayers.contains(uuid))
            censoredPlayers.add(uuid);

        uncensorQueue.remove(uuid);
    }

    public static boolean isCensored(UUID uuid) {
        return censoredPlayers.contains(uuid);
    }

    public static void uncensor(UUID uuid) {
        uncensorQueue.add(uuid);
    }

    public static void removeQueued() {
        for (UUID uuid : uncensorQueue) {
            censoredPlayers.remove(uuid);
        }

        uncensorQueue.clear();
    }

    public static void clearCensoredPlayers() {
        censoredPlayers.clear();
    }

    @Override
    public void onInitialize() {
        LOGGER.info("Hello *CENSORED* world!");

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "chat_observer"),
                CHAT_OBSERVER);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "chat_observer"),
                new BlockItem(CHAT_OBSERVER, new Item.Settings().group(ItemGroup.REDSTONE)));

        CHAT_OBSERVER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(MOD_ID, "chat_observer_block_entity"),
                FabricBlockEntityTypeBuilder.create(ChatObserverBlockEntity::new,
                    CHAT_OBSERVER).build(null));

        initializeCensorList();
    }
}
