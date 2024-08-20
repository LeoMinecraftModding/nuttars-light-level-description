package com.nuttar.port.lld.mixin;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Inject(method = "appendHoverText", at = @At(value = "HEAD"))
    private void injectAHT(ItemStack itemStack, Item.TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag, CallbackInfo ci) {
        if (itemStack.getItem() instanceof BlockItem item) {
            var level = ((BlockBehaviourAccessor) item.getBlock().properties()).lightEmission().applyAsInt(item.getBlock().defaultBlockState());
            if (level > 0) {
                list.add(Component.translatable("item.nslld.message").withStyle(ChatFormatting.GOLD).append(":" + level).withStyle(ChatFormatting.WHITE));
            }
        }
    }
}
