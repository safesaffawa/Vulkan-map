package dev.xaerovulkan.compat.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "xaero.map.MapLimiter", remap = false)
public class MixinXaeroWorldMapMapLimiter {

    @Inject(method = "determineDriverType", at = @At("HEAD"), cancellable = true, remap = false)
    private void xvcompat$onDetermineDriverType(CallbackInfoReturnable<Integer> cir) {
        // 在 Vulkan 环境下，直接返回 Unknown driver，避免调用 OpenGL
        if (dev.xaerovulkan.compat.XaeroVulkanCompat.VULKAN_ACTIVE) {
            cir.setReturnValue(0); // 0 = Unknown driver
        }
    }

    @Inject(method = "updateAvailableVRAM", at = @At("HEAD"), cancellable = true, remap = false)
    private void xvcompat$onUpdateAvailableVRAM(CallbackInfo ci) {
        // 在 Vulkan 环境下，跳过 VRAM 检测
        if (dev.xaerovulkan.compat.XaeroVulkanCompat.VULKAN_ACTIVE) {
            ci.cancel();
        }
    }
}
