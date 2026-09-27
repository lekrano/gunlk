package me.lekrano.gunlk;

import com.mojang.logging.LogUtils;
import me.lekrano.gunlk.Loot.AmmunitionManager;
import me.lekrano.gunlk.ModBlocks.ModBlocks;
import me.lekrano.gunlk.ModEntities.LootDropRenderer;
import me.lekrano.gunlk.ModEntities.ModEntities;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(gunlk.MODID)
public class gunlk
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "gunlk";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public gunlk()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public void onRegisterCommand(RegisterCommandsEvent e) {
        lkCommands.register(e.getDispatcher());
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        ServerLevel level = event.getServer().overworld();

        level.setBlock(
                new BlockPos(100, 100, 100),
                Blocks.NETHERITE_BLOCK.defaultBlockState(),
                3
        );
    }

    @SubscribeEvent
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getLevel().getBlockState(event.getPos()).is(ModBlocks.LOOT_DROP_BLOCK.get())) {

            event.getLevel().setBlock(
                    event.getPos(),
                    ModBlocks.LOOT_DROP_BLOCK_OPENED.get().defaultBlockState(),
                    3
            );

            AmmunitionManager.giveAmmunition(event.getEntity());

            event.setCanceled(true);
        }
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(
                    ModEntities.LOOT_DROP.get(),
                    LootDropRenderer::new
            );
        }
    }
}
