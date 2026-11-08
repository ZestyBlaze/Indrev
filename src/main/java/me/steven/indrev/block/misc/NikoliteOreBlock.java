package me.steven.indrev.block.misc;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.DropExperienceBlock;

public class NikoliteOreBlock extends DropExperienceBlock {
    public NikoliteOreBlock(Properties properties) {
        super(UniformInt.of(1, 6), properties);
    }
}
