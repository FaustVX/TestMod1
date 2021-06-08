package fr.faustvx.dices;

import fr.faustvx.dices.block.Dice;
import fr.faustvx.dices.block.RandomDice;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Dices.MOD_ID)
public final class Dices
{
    public static final String MOD_ID = "dices";

    public Dices()
    {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        Dice.BLOCKS.register(modEventBus);
        RandomDice.BLOCKS.register(modEventBus);
        RandomDice.ITEMS.register(modEventBus);
    }
}
