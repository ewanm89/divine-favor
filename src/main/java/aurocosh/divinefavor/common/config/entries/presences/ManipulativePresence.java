package aurocosh.divinefavor.common.config.entries.presences;

import aurocosh.divinefavor.common.util.UtilTick;
import net.minecraftforge.common.config.Config;

public class ManipulativePresence {
    @Config.Name("Duration")
    public int duration = UtilTick.INSTANCE.minutesToTicks(2);
    @Config.Name("Starting chance")
    public float startingChance = 0.1f;
    @Config.Name("Chance increase")
    public float chanceIncrease = 0.5f;
}
