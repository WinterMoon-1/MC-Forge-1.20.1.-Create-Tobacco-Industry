package net.moonangel.tobacco_industry.datagen.loot;

import net.moonangel.tobacco_industry.block.ModBlocks;
import net.moonangel.tobacco_industry.block.custom.TobaccoCropBlock;
import net.moonangel.tobacco_industry.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        LootItemCondition.Builder age11 = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.TOBACCO_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(TobaccoCropBlock.AGE, 11));

        LootItemCondition.Builder age18 = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.TOBACCO_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(TobaccoCropBlock.AGE, 18));

        LootItemCondition.Builder anyMature = AnyOfCondition.anyOf(age11, age18);

        this.add(ModBlocks.TOBACCO_CROP.get(), createTobaccoCropDrops(ModItems.TOBACCO_LEAF.get(),
                ModItems.TOBACCO_SEEDS.get(), anyMature));

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    protected LootTable.Builder createTobaccoCropDrops(Item cropItem, Item seedItem, LootItemCondition.Builder ageCondition) {
        return LootTable.lootTable()
                .apply(ApplyExplosionDecay.explosionDecay())
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.TOBACCO_LEAF.get())
                                .when(ageCondition)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.TOBACCO_SEEDS.get())
                                .when(ageCondition)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.TOBACCO_SEEDS.get())
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))));
    }
}
