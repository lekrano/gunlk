package me.lekrano.gunlk;


import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import me.lekrano.gunlk.ModEntities.LootDropEntity;
import me.lekrano.gunlk.ModEntities.ModEntities;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class lkCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("debug")
                        .then(Commands.literal("test")
                                .executes(lkCommands::test))
                        .then(Commands.literal("crate")
                                .executes(lkCommands::crate))
        );
    }

    private static int test(CommandContext<CommandSourceStack> context) {
        context.getSource().sendSuccess(
                () -> Component.literal("command is working"),
                true
        );
        return 1;
    }

    private static int crate(CommandContext<CommandSourceStack> context) {
        context.getSource().sendSuccess(
                () -> Component.literal("crates"),
                true
        );
        Entity player = context.getSource().getPlayer();
        if (player == null) return 0;

        ServerLevel level = context.getSource().getLevel();
        Vec3 position = player.position();
        LootDropEntity drop = new LootDropEntity(
                ModEntities.LOOT_DROP.get(),
                level
        );
        drop.setPos(
                position.x,
                position.y + 10,
                position.z
        );
        level.addFreshEntity(drop);
        return 1;
    }

}
