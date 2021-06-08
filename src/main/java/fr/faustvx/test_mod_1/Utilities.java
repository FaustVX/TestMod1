package fr.faustvx.test_mod_1;

import java.util.function.Function;

import net.minecraft.block.BlockState;
import net.minecraft.state.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Utilities
{
    public static <T extends Comparable<T>> void changeState(BlockState state, World worldIn, BlockPos pos, Property<T> property, Function<T, T> newStateFunc)
    {
        T currentState = state.getValue(property);
        BlockState newState = state.setValue(property, newStateFunc.apply(currentState));
        worldIn.setBlock(pos, newState, 0);
    }
}
