package fr.faustvx.dices.block;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.faustvx.dices.Dices;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class Dice extends Block
{
    private Dice()
    {
        super(Block.Properties.of(Material.DECORATION));
        LOGGER.info("created block: {}", DICE_BLOCK.getId());
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Dices.MOD_ID);

    public static final RegistryObject<Block> DICE_BLOCK = BLOCKS.register("dice", Dice::new);

    public static final IntegerProperty VALUE_PROPERTY = IntegerProperty.create("value", 1, 6);

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(VALUE_PROPERTY);
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if (!worldIn.isClientSide)
        {
            state = RandomDice.DICE_BLOCK.get().defaultBlockState();
            worldIn.setBlock(pos, state, 0);
            LOGGER.info("Changed dice to randomDice");
            return ActionResultType.CONSUME;
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos)
    {
        return state.getValue(VALUE_PROPERTY) * 2;
    }

    @Override
    public Block getBlock()
    {
        return RandomDice.DICE_BLOCK.get();
    }
}
