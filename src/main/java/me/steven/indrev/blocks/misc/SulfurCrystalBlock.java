package me.steven.indrev.blocks.misc;

import me.steven.indrev.registry.IRItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class SulfurCrystalBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    private final VoxelShape UP_SHAPE = Stream.of(
            box(2.0, 5.0, 4.0, 3.0, 6.0, 5.0),
            box(2.0, 4.0, 4.0, 4.0, 5.0, 6.0),
            box(1.0, 0.0, 3.0, 4.0, 4.0, 6.0),
            box(3.0, 0.0, 2.0, 5.0, 1.0, 4.0),
            box(0.0, 0.0, 5.0, 2.0, 2.0, 7.0),
            box(10.0, 0.0, 1.0, 12.0, 1.0, 3.0),
            box(11.0, 0.0, 2.0, 14.0, 4.0, 5.0),
            box(11.0, 4.0, 2.0, 13.0, 5.0, 4.0),
            box(12.0, 5.0, 3.0, 13.0, 6.0, 4.0),
            box(13.0, 0.0, 4.0, 15.0, 2.0, 6.0),
            box(6.0, 0.0, 7.0, 8.0, 2.0, 10.0),
            box(7.0, 0.0, 8.0, 12.0, 5.0, 13.0),
            box(8.0, 5.0, 9.0, 11.0, 8.0, 12.0),
            box(9.0, 8.0, 10.0, 10.0, 10.0, 11.0),
            box(11.0, 0.0, 12.0, 14.0, 3.0, 15.0)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).orElse(null);

    private final VoxelShape DOWN_SHAPE = Stream.of(
            box(13.0, 11.0, 4.0, 14.0, 12.0, 5.0),
            box(12.0, 12.0, 4.0, 14.0, 13.0, 6.0),
            box(12.0, 13.0, 3.0, 15.0, 17.0, 6.0),
            box(11.0, 16.0, 2.0, 13.0, 17.0, 4.0),
            box(14.0, 15.0, 5.0, 16.0, 17.0, 7.0),
            box(4.0, 16.0, 1.0, 6.0, 17.0, 3.0),
            box(2.0, 13.0, 2.0, 5.0, 17.0, 5.0),
            box(3.0, 12.0, 2.0, 5.0, 13.0, 4.0),
            box(3.0, 11.0, 3.0, 4.0, 12.0, 4.0),
            box(1.0, 15.0, 4.0, 3.0, 17.0, 6.0),
            box(8.0, 15.0, 7.0, 10.0, 17.0, 10.0),
            box(4.0, 12.0, 8.0, 9.0, 17.0, 13.0),
            box(5.0, 9.0, 9.0, 8.0, 12.0, 12.0),
            box(6.0, 7.0, 10.0, 7.0, 9.0, 11.0),
            box(2.0, 14.0, 12.0, 5.0, 17.0, 15.0)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).orElse(null);

    private final VoxelShape EAST_SHAPE = Stream.of(
            box(5.0, 11.0, 13.0, 6.0, 12.0, 14.0),
            box(4.0, 10.0, 12.0, 5.0, 12.0, 14.0),
            box(0.0, 10.0, 12.0, 4.0, 13.0, 15.0),
            box(0.0, 12.0, 11.0, 1.0, 14.0, 13.0),
            box(0.0, 9.0, 14.0, 2.0, 11.0, 16.0),
            box(0.0, 13.0, 4.0, 1.0, 15.0, 6.0),
            box(0.0, 11.0, 2.0, 4.0, 14.0, 5.0),
            box(4.0, 12.0, 3.0, 5.0, 14.0, 5.0),
            box(5.0, 12.0, 3.0, 6.0, 13.0, 4.0),
            box(0.0, 10.0, 1.0, 2.0, 12.0, 3.0),
            box(0.0, 6.0, 8.0, 2.0, 9.0, 10.0),
            box(0.0, 3.0, 4.0, 5.0, 8.0, 9.0),
            box(5.0, 4.0, 5.0, 8.0, 7.0, 8.0),
            box(8.0, 5.0, 6.0, 10.0, 6.0, 7.0),
            box(0.0, 1.0, 2.0, 3.0, 4.0, 5.0)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).orElse(null);

    private final VoxelShape SOUTH_SHAPE = Stream.of(
            box(1.0, 4.0, 0.0, 3.0, 6.0, 2.0),
            box(3.0, 3.0, 5.0, 4.0, 4.0, 6.0),
            box(3.0, 2.0, 4.0, 5.0, 4.0, 5.0),
            box(2.0, 2.0, 0.0, 5.0, 5.0, 4.0),
            box(4.0, 1.0, 0.0, 6.0, 3.0, 1.0),
            box(11.0, 2.0, 0.0, 13.0, 4.0, 1.0),
            box(12.0, 3.0, 0.0, 15.0, 6.0, 4.0),
            box(12.0, 4.0, 4.0, 14.0, 6.0, 5.0),
            box(13.0, 4.0, 5.0, 14.0, 5.0, 6.0),
            box(14.0, 5.0, 0.0, 16.0, 7.0, 2.0),
            box(8.0, 7.0, 0.0, 10.0, 10.0, 2.0),
            box(4.0, 8.0, 0.0, 9.0, 13.0, 5.0),
            box(5.0, 9.0, 5.0, 8.0, 12.0, 8.0),
            box(6.0, 10.0, 8.0, 7.0, 11.0, 10.0),
            box(2.0, 12.0, 0.0, 5.0, 15.0, 3.0)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).orElse(null);

    private final VoxelShape NORTH_SHAPE = Stream.of(
            box(13.0, 4.0, 14.0, 15.0, 6.0, 16.0),
            box(12.0, 3.0, 10.0, 13.0, 4.0, 11.0),
            box(11.0, 2.0, 11.0, 13.0, 4.0, 12.0),
            box(11.0, 2.0, 12.0, 14.0, 5.0, 16.0),
            box(10.0, 1.0, 15.0, 12.0, 3.0, 16.0),
            box(3.0, 2.0, 15.0, 5.0, 4.0, 16.0),
            box(1.0, 3.0, 12.0, 4.0, 6.0, 16.0),
            box(2.0, 4.0, 11.0, 4.0, 6.0, 12.0),
            box(2.0, 4.0, 10.0, 3.0, 5.0, 11.0),
            box(0.0, 5.0, 14.0, 2.0, 7.0, 16.0),
            box(6.0, 7.0, 14.0, 8.0, 10.0, 16.0),
            box(7.0, 8.0, 11.0, 12.0, 13.0, 16.0),
            box(8.0, 9.0, 8.0, 11.0, 12.0, 11.0),
            box(9.0, 10.0, 6.0, 10.0, 11.0, 8.0),
            box(11.0, 12.0, 13.0, 14.0, 15.0, 16.0)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).orElse(null);

    private final VoxelShape WEST_SHAPE = Stream.of(
            box(10.0, 11.0, 2.0, 11.0, 12.0, 3.0),
            box(11.0, 10.0, 2.0, 12.0, 12.0, 4.0),
            box(12.0, 10.0, 1.0, 16.0, 13.0, 4.0),
            box(15.0, 12.0, 3.0, 16.0, 14.0, 5.0),
            box(14.0, 9.0, 0.0, 16.0, 11.0, 2.0),
            box(15.0, 13.0, 10.0, 16.0, 15.0, 12.0),
            box(12.0, 11.0, 11.0, 16.0, 14.0, 14.0),
            box(11.0, 12.0, 11.0, 12.0, 14.0, 13.0),
            box(10.0, 12.0, 12.0, 11.0, 13.0, 13.0),
            box(14.0, 10.0, 13.0, 16.0, 12.0, 15.0),
            box(14.0, 6.0, 6.0, 16.0, 9.0, 8.0),
            box(11.0, 3.0, 7.0, 16.0, 8.0, 12.0),
            box(8.0, 4.0, 8.0, 11.0, 7.0, 11.0),
            box(6.0, 5.0, 9.0, 8.0, 6.0, 10.0),
            box(13.0, 1.0, 11.0, 16.0, 4.0, 14.0)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).orElse(null);

    public SulfurCrystalBlock(Properties properties) {
        super(properties);
        this.stateDefinition.any().setValue(FACING, Direction.UP);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case DOWN -> DOWN_SHAPE;
            case UP -> UP_SHAPE;
            case NORTH -> NORTH_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case WEST -> WEST_SHAPE;
            case EAST -> EAST_SHAPE;
        };
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        BlockPos vec = pos.subtract(neighborPos);
        Direction dir = Direction.fromDelta(vec.getX(), vec.getY(), vec.getZ());
        if (state.getValue(FACING) == dir) {
            level.destroyBlock(pos, true);
        }
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockPos blockPos = pos.relative(direction.getOpposite());
        BlockState blockState = level.getBlockState(blockPos);
        return blockState.isFaceSturdy(level, blockPos, direction);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getNearestLookingDirection().getOpposite());
    }

    @Override
    public Item asItem() {
        return IRItemRegistry.SULFUR_CRYSTAL_ITEM.get();
    }
}
