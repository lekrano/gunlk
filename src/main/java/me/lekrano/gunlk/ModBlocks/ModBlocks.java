package me.lekrano.gunlk.ModBlocks;

import me.lekrano.gunlk.gunlk;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, gunlk.MODID);

    public static final RegistryObject<Block> LOOT_DROP_BLOCK = BLOCKS.register(
            "loot_drop_block",
            () -> new Block(
                    BlockBehaviour.Properties.copy(Blocks.BARREL)
            )
    );

    public static final RegistryObject<Block> LOOT_DROP_BLOCK_OPENED = BLOCKS.register(
            "loot_drop_block_opened",
            () -> new Block(
                    BlockBehaviour.Properties.copy(Blocks.BARREL)
            )
    );
}
