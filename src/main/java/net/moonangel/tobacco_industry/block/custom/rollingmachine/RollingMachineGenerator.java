package net.moonangel.tobacco_industry.block.custom.rollingmachine;

import com.simibubi.create.foundation.data.SpecialBlockStateGen;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.ModelFile;

public class RollingMachineGenerator extends SpecialBlockStateGen {
    @Override
    protected int getXRotation(BlockState blockState) {
        return 0;
    }

    @Override
    protected int getYRotation(BlockState blockState) {
        return horizontalAngle(blockState.getValue(RollingMachineBlock.HORIZONTAL_FACING));
    }

    @Override
    public <T extends Block> ModelFile getModel(DataGenContext<Block, T> dataGenContext, RegistrateBlockstateProvider registrateBlockstateProvider, BlockState blockState) {
        return registrateBlockstateProvider.models().getExistingFile(registrateBlockstateProvider.modLoc("block/" + registrateBlockstateProvider.getName() + "/base"));
    }
}
