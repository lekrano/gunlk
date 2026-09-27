package me.lekrano.gunlk.ModEntities;

import me.lekrano.gunlk.ModBlocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;

import java.util.Random;

public class LootDropEntity extends Entity {

    public LootDropEntity(EntityType<? extends LootDropEntity> entityType, Level level) {
        super(entityType, level);
    }
    private ItemStack ammunition = new ItemStack(
            BuiltInRegistries.ITEM.get(ResourceLocation.tryParse("tacz:ammo"))
    );

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    public void tick() {
        super.tick();

        if (!this.onGround()) {
            this.setDeltaMovement(0, -0.5, 0);
        } else {
            this.setDeltaMovement(0, 0, 0);
            BlockPos position = this.blockPosition();
            this.level().setBlock(
                    position,
                    ModBlocks.LOOT_DROP_BLOCK.get().defaultBlockState(),
                    3
            );

            this.setRemoved(RemovalReason.KILLED);
        }

        this.move(MoverType.SELF, this.getDeltaMovement());
    }
}
