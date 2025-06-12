package net.moonangel.tobacco_industry.block;

import com.simibubi.create.AllTags;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.moonangel.tobacco_industry.TobaccoIndustry;
import net.moonangel.tobacco_industry.block.custom.rollingmachine.RollingMachineBlock;
import net.moonangel.tobacco_industry.block.custom.TobaccoCropBlock;
import net.moonangel.tobacco_industry.block.custom.rollingmachine.RollingMachineGenerator;
import net.moonangel.tobacco_industry.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.moonangel.tobacco_industry.util.ModLootUtils;
import net.moonangel.tobacco_industry.util.ModModelUtils;

import java.util.function.Supplier;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TobaccoIndustry.MOD_ID);

    public static final BlockEntry<RollingMachineBlock> ROLLING_MACHINE = TobaccoIndustry.REGISTRATE.block("rolling_machine", RollingMachineBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .tag(AllTags.AllBlockTags.SAFE_NBT.tag)
            .transform(BlockStressDefaults.setImpact(4.0))
            .item()
            .tag(AllTags.AllItemTags.CONTRAPTION_CONTROLLED.tag)
            .transform(customItemModel())
            .register();

    public static final BlockEntry<TobaccoCropBlock> TOBACCO_CROP = TobaccoIndustry.REGISTRATE.block("tobacco_crop", TobaccoCropBlock::new)
            .initialProperties(() -> Blocks.WHEAT)
            .blockstate((ctx, prov) -> ModModelUtils.makeTobaccoCrop(ctx.get(), prov, "tobacco_stage_"))
            .loot((lootTables, block) -> { lootTables.add(block, ModLootUtils.createCropDrops(ModItems.TOBACCO_LEAF.get(), ModItems.TOBACCO_SEEDS.get()));})
            .register();

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
