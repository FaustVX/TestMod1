package fr.faustvx.test_mod_1.item;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.faustvx.test_mod_1.TestMod1;
import fr.faustvx.test_mod_1.block.block1;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class item_block1
{
    private static final Logger LOGGER = LogManager.getLogger();

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TestMod1.MOD_ID);

    public static final RegistryObject<BlockItem> BLOCK1 = ITEMS.register(block1.BLOCK1.getId().getPath(), item_block1::create_item);

    private static BlockItem create_item()
    {
        LOGGER.info("created block_item: {}", BLOCK1.getId());
        return new BlockItem(block1.BLOCK1.get(),
            new Item.Properties()
                .tab(ItemGroup.TAB_BUILDING_BLOCKS)
            );
    }
}
