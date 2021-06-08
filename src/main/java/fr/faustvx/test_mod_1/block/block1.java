package fr.faustvx.test_mod_1.block;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.faustvx.test_mod_1.TestMod1;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class block1
{
    private static final Logger LOGGER = LogManager.getLogger();

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TestMod1.MOD_ID);

    public static final RegistryObject<Block> BLOCK1 = BLOCKS.register("block1", block1::create_block);

    private static Block create_block()
    {
        LOGGER.info("created block: {}", BLOCK1.getId());
        return new Block(Block.Properties.of(Material.METAL)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            );
    }
}
