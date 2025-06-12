package net.moonangel.tobacco_industry.util;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.world.item.Item;
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
import net.moonangel.tobacco_industry.block.TIBlocks;
import net.moonangel.tobacco_industry.block.custom.TobaccoCropBlock;

public class TILootUtils {
    public static LootTable.Builder createCropDrops(Item cropItem, Item seedItem) {
        LootItemCondition.Builder age11 = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(TIBlocks.TOBACCO_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(TobaccoCropBlock.AGE, 11));

        LootItemCondition.Builder age18 = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(TIBlocks.TOBACCO_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(TobaccoCropBlock.AGE, 18));

        LootItemCondition.Builder anyMature = AnyOfCondition.anyOf(age11, age18);

        return LootTable.lootTable()
                .apply(ApplyExplosionDecay.explosionDecay())
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(cropItem)
                                .when(anyMature)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(seedItem)
                                .when(anyMature)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(seedItem)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))));
    }
}
