package me.lekrano.gunlk;


import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;

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
        Entity entity = context.getSource().getEntity();
        if (entity == null) return 0;
        context.getSource().getLevel().setBlock(
                entity.blockPosition(),
                Blocks.BARREL.defaultBlockState(),
                3
        );
        return 1;
    }

}
