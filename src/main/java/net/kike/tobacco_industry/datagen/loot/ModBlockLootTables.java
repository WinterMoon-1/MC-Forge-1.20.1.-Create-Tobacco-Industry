package net.kike.tobacco_industry.datagen.loot;

import net.kike.tobacco_industry.block.ModBlocks;
import net.kike.tobacco_industry.block.custom.TobaccoCropBlock;
import net.kike.tobacco_industry.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
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
        LootItemCondition.Builder tobaccoLeavesCondition = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.TOBACCO_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(TobaccoCropBlock.AGE, 18));

        this.add(ModBlocks.TOBACCO_CROP.get(), createTobaccoCropDrops(ModItems.TOBACCO_LEAF.get(),
                ModItems.TOBACCO_SEEDS.get(), tobaccoLeavesCondition));

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    protected LootTable.Builder createTobaccoCropDrops(Item cropItem, Item seedItem, LootItemCondition.Builder condition) {
        return LootTable.lootTable()
                .apply(ApplyExplosionDecay.explosionDecay()) // Handles explosion-related loot drops
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(AlternativesEntry.alternatives( // Groups conditional items under alternatives
                                LootItem.lootTableItem(cropItem)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5))) // Leaves drop 2-5
                                        .when(condition),
                                LootItem.lootTableItem(seedItem)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 3))) // Seeds drop 1-3
                                        .when(condition)
                        )))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(seedItem) // Guaranteed 1 seed
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))));
    }
}
