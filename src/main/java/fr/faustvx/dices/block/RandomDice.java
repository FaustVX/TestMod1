package fr.faustvx.dices.block;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.faustvx.dices.Dices;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class RandomDice extends Block
{
    private RandomDice()
    {
        super(Block.Properties.of(Material.DECORATION));
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Dices.MOD_ID);

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Dices.MOD_ID);

    public static final RegistryObject<Block> DICE_BLOCK = BLOCKS.register("random_dice", RandomDice::new);

    public static final RegistryObject<BlockItem> DICE_ITEM = ITEMS.register(DICE_BLOCK.getId().getPath(), RandomDice::create_item);

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if (!worldIn.isClientSide)
        {
            state = Dice.DICE_BLOCK.get().defaultBlockState().setValue(Dice.VALUE_PROPERTY, worldIn.random.nextInt(6) + 1);
            worldIn.setBlock(pos, state, 0);
            LOGGER.info("Changed dice to: {}", state.getValue(Dice.VALUE_PROPERTY));
            return ActionResultType.CONSUME;
        }
        return ActionResultType.SUCCESS;
    }

    private static BlockItem create_item()
    {
        LOGGER.info("created block_item: {}", DICE_BLOCK.getId());
        return new BlockItem(RandomDice.DICE_BLOCK.get(),
            new Item.Properties()
                .tab(ItemGroup.TAB_DECORATIONS)
            );
    }
}
