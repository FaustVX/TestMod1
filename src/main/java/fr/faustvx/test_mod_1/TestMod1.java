package fr.faustvx.test_mod_1;

import fr.faustvx.test_mod_1.block.block1;
import fr.faustvx.test_mod_1.item.item_block1;
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
        block1.BLOCKS.register(modEventBus);
        item_block1.ITEMS.register(modEventBus);
    }
}
