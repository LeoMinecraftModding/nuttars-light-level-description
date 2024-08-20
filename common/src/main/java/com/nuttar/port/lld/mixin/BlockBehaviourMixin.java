package com.nuttar.port.lld.mixin;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.class)
public abstract class BlockBehaviourMixin {
    @Inject(method = "useWithoutItem", at = @At(value = "HEAD"))
    private void show(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult, CallbackInfoReturnable<InteractionResult> cir) {
        var i = ((BlockBehaviourAccessor) level.getBlockState(blockPos).getBlock().properties()).lightEmission().applyAsInt(blockState);
        if (i > 0) {
            player.displayClientMessage(Component.translatable("item.nslld.message").withStyle(ChatFormatting.GOLD).append(":" + i).withStyle(ChatFormatting.WHITE), true);
        }
    }
}
