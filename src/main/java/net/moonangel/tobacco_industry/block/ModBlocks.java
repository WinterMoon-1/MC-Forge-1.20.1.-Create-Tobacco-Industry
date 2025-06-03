package net.moonangel.tobacco_industry.block;

import com.simibubi.create.AllTags;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
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

import java.util.function.Supplier;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TobaccoIndustry.MOD_ID);

    public static final RegistryObject<Block> TOBACCO_CROP = BLOCKS.register("tobacco_crop",
            () -> new TobaccoCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noCollission().noOcclusion()));

    public static final BlockEntry<RollingMachineBlock> ROLLING_MACHINE = TobaccoIndustry.REGISTRATE.block("rolling_machine", RollingMachineBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .tag(AllTags.AllBlockTags.SAFE_NBT.tag)
            .transform(BlockStressDefaults.setImpact(4.0))
            .item()
            .tag(AllTags.AllItemTags.CONTRAPTION_CONTROLLED.tag)
            .transform(customItemModel())
            .register();


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
            RegistryObject<T> toReturn = BLOCKS.register(name, block);
            registerBlockItem(name, toReturn);
            return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
