package me.steven.indrev.world.features;

import com.mojang.serialization.Codec;
import me.steven.indrev.blocks.misc.SulfurCrystalBlock;
import me.steven.indrev.registry.IRBlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SulfurCrystalFeature extends Feature<NoneFeatureConfiguration> {
    private static final List<Direction> DIRECTIONS_LIST = Direction.stream().toList();

    public SulfurCrystalFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos blockPos = context.origin();
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        AABB coveredArea = new AABB(blockPos).inflate(8.0, 8.0, 8.0);

        boolean isNearLava = false;

        for (int x = (int) Math.floor(coveredArea.minX); x <= (int) Math.floor(coveredArea.maxX); x++) {
            for (int y = (int) Math.floor(coveredArea.minY); y <= (int) Math.floor(coveredArea.maxY); y++) {
                if (level.isOutsideBuildHeight(y)) return false;
                for (int z = (int) Math.floor(coveredArea.minZ); z <= (int) Math.floor(coveredArea.maxZ); z++) {
                    mutablePos.set(x, y, z);
                    if (level.getBlockState(mutablePos).is(Blocks.LAVA)) {
                        isNearLava = true;
                        break;
                    }
                }
                if (isNearLava) break;
            }
            if (isNearLava) break;
        }

        if (!isNearLava) return false;

        for (int x = (int) Math.floor(coveredArea.minX); x <= (int) Math.floor(coveredArea.maxX); x++) {
            for (int y = (int) Math.floor(coveredArea.minY); y <= (int) Math.floor(coveredArea.maxY); y++) {
                if (level.isOutsideBuildHeight(y)) return false;
                for (int z = (int) Math.floor(coveredArea.minZ); z <= (int) Math.floor(coveredArea.maxZ); z++) {
                    mutablePos.set(x, y, z);
                    List<Direction> shuffled = new ArrayList<>(DIRECTIONS_LIST);
                    Collections.shuffle(shuffled);

                    for (Direction dir : shuffled) {
                        BlockState blockState = level.getBlockState(mutablePos);
                        BlockPos pos = mutablePos.relative(dir);
                        BlockState airState = level.getBlockState(pos);
                        BlockState state = IRBlockRegistry.SULFUR_CRYSTAL_CLUSTER.get().defaultBlockState().setValue(SulfurCrystalBlock.FACING, dir);

                        if (blockState.is(BlockTags.BASE_STONE_OVERWORLD) && airState.isAir() && state.canSurvive(level, pos)) {
                            level.setBlock(pos, state, 2);
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}
