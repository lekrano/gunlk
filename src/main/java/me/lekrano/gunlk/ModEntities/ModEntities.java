package me.lekrano.gunlk.ModEntities;

import me.lekrano.gunlk.gunlk;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, gunlk.MODID);

    public static final RegistryObject<EntityType<LootDropEntity>> LOOT_DROP = ENTITY_TYPES.register(
            "loot_drop",
            () -> EntityType.Builder
                    .of(LootDropEntity::new, MobCategory.MISC)
                    .sized(1.0f, 1.0f)
                    .build("loot_drop")
    );
}
