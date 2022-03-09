package net.cadenhenrich.censorship;

import net.minecraft.entity.damage.DamageSource;

public class CensorshipDamageSource {
    public static final DamageSource CENSOR = new UnblockableDamageSource("censor");
    public static final DamageSource EXECUTED = new UnblockableDamageSource("executed");
    public static final DamageSource SILENCED = new UnblockableDamageSource("silenced");
    public static final DamageSource FORBIDDEN = new UnblockableDamageSource("forbidden");
    public static final DamageSource DISRUPTION = new UnblockableDamageSource("disruption");
    public static final DamageSource CHAOS = new UnblockableDamageSource("chaos");
    public static final DamageSource IMPOSTER = new UnblockableDamageSource("imposter");
    public static final DamageSource PROBLEM = new UnblockableDamageSource("problem");
    public static final DamageSource BEHAVE = new UnblockableDamageSource("behave");
    public static final DamageSource RULES = new UnblockableDamageSource("rules");
    public static final DamageSource SOCIAL = new UnblockableDamageSource("social");

    public static final DamageSource[] sources = {CENSOR, EXECUTED, SILENCED, FORBIDDEN,
        DISRUPTION, CHAOS, IMPOSTER, PROBLEM, BEHAVE, RULES, SOCIAL};

    private static class UnblockableDamageSource extends DamageSource {
        protected UnblockableDamageSource(String name) {
            super(name);
            setBypassesArmor();
            setUnblockable();
            setOutOfWorld();
        }
    }
}
