package aurocosh.divinefavor.common.config.entries.spell_talismans;

import aurocosh.divinefavor.common.util.UtilTick;
import net.minecraftforge.common.config.Config;

public class NightEye {
    @Config.Name("Favor cost")
    public int favorCost = 10;
    @Config.Name("Tolerable light level")
    public int tolerableLightLevel = 4;
    @Config.Name("Blindness duration")
    public int blindnessDuration = UtilTick.INSTANCE.secondsToTicks(2.5f);
    @Config.Name("Night vision duration")
    public int nightVisionDuration = UtilTick.INSTANCE.secondsToTicks(20);
}
