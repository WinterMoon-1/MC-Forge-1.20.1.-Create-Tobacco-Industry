package net.moonangel.tobacco_industry.block.entity;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.moonangel.tobacco_industry.TobaccoIndustry;
import net.moonangel.tobacco_industry.block.TIBlocks;

public class TIBlockEntities {

    public static final BlockEntityEntry<RollingMachineBlockEntity> ROLLING_MACHINE = TobaccoIndustry.REGISTRATE
            .blockEntity("rolling_machine", RollingMachineBlockEntity::new)
            .validBlocks(TIBlocks.ROLLING_MACHINE)
            .renderer(() -> RollingMachineBlockEntityRenderer::new)
            .register();

    public static void register(IEventBus modEventBus) {}
}
