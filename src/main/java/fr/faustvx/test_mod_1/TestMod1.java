package fr.faustvx.test_mod_1;

import fr.faustvx.test_mod_1.block.Dice;
import fr.faustvx.test_mod_1.block.RandomDice;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TestMod1.MOD_ID)
public final class TestMod1
{
    public static final String MOD_ID = "test_mod_1";

    public TestMod1()
    {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        Dice.BLOCKS.register(modEventBus);
        RandomDice.BLOCKS.register(modEventBus);
        RandomDice.ITEMS.register(modEventBus);
    }
}
