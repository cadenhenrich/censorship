package net.cadenhenrich.censorship;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CensorshipMod implements ModInitializer {
    public static final String MOD_ID = "censorship";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
            LOGGER.info("Hello *CENSORED* world!");
    }
}
