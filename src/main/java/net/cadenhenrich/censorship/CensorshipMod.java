package net.cadenhenrich.censorship;

import net.cadenhenrich.censorship.block.ModBlocks;
import net.cadenhenrich.censorship.item.ModItems;
import net.fabricmc.api.ModInitializer;

import java.util.ArrayList;
// import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CensorshipMod implements ModInitializer {
    public static final String MOD_ID = "censorship";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static ArrayList<String> censorList = new ArrayList<String>();

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

    @Override
    public void onInitialize() {
        LOGGER.info("Hello *CENSORED* world!");
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();

        initializeCensorList();
    }
}
