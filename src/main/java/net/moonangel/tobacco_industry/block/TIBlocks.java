package net.moonangel.tobacco_industry.block;

import com.simibubi.create.AllTags;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.RenderType;
import net.moonangel.tobacco_industry.TobaccoIndustry;
import net.moonangel.tobacco_industry.block.custom.rollingmachine.RollingMachineBlock;
import net.moonangel.tobacco_industry.block.custom.TobaccoCropBlock;
import net.moonangel.tobacco_industry.item.TIItems;
import net.minecraft.world.level.block.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.moonangel.tobacco_industry.util.TILootUtils;
import net.moonangel.tobacco_industry.util.TIModelUtils;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;

public class TIBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TobaccoIndustry.MOD_ID);

    public static final BlockEntry<RollingMachineBlock> ROLLING_MACHINE = TobaccoIndustry.REGISTRATE.block("rolling_machine", RollingMachineBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .tag(AllTags.AllBlockTags.SAFE_NBT.tag)
            .transform(BlockStressDefaults.setImpact(4.0))
            .blockstate((ctx, prov) -> prov.horizontalBlock(ctx.get(), prov.models()
                    .getExistingFile(prov.modLoc("block/rolling_machine"))))
            .item()
            .tag(AllTags.AllItemTags.CONTRAPTION_CONTROLLED.tag)
            .transform(customItemModel())
            .register();

    public static final BlockEntry<TobaccoCropBlock> TOBACCO_CROP = TobaccoIndustry.REGISTRATE.block("tobacco_crop", TobaccoCropBlock::new)
            .initialProperties(() -> Blocks.WHEAT)
            .blockstate((ctx, prov) -> TIModelUtils.makeTobaccoCrop(ctx.get(), prov, "tobacco_stage_"))
            .loot((lootTables, block) -> { lootTables.add(block, TILootUtils.createCropDrops(TIItems.TOBACCO_LEAF.get(), TIItems.TOBACCO_SEEDS.get()));})
            .register();

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
