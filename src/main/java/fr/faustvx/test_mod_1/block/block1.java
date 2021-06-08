package fr.faustvx.test_mod_1.block;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.faustvx.test_mod_1.TestMod1;
import fr.faustvx.test_mod_1.Utilities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class block1 extends Block
{
    private block1()
    {
        super(Block.Properties.of(Material.METAL)
        .harvestTool(ToolType.PICKAXE)
        .harvestLevel(2)
        );
        registerDefaultState(defaultBlockState().setValue(LIT_PROPERTY, Boolean.valueOf(false)));
        LOGGER.info("created block: {}", BLOCK1.getId());
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TestMod1.MOD_ID);

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TestMod1.MOD_ID);

    public static final RegistryObject<Block> BLOCK1 = BLOCKS.register("block1", block1::new);

    public static final RegistryObject<BlockItem> ITEM1 = ITEMS.register(BLOCK1.getId().getPath(), block1::create_item);

    public static final BooleanProperty LIT_PROPERTY = BlockStateProperties.LIT;

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
    {
      builder.add(LIT_PROPERTY);
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos)
    {
        return state.getValue(LIT_PROPERTY) ? 15 : 0;
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if (!worldIn.isClientSide)
        {
            Utilities.changeState(state, worldIn, pos, LIT_PROPERTY, lit -> !lit);
            return ActionResultType.CONSUME;
        }
        return ActionResultType.SUCCESS;
    }

    private static BlockItem create_item()
    {
        LOGGER.info("created block_item: {}", BLOCK1.getId());
        return new BlockItem(block1.BLOCK1.get(),
            new Item.Properties()
                .tab(ItemGroup.TAB_BUILDING_BLOCKS)
            );
    }
}
