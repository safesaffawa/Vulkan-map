package dev.xaerovulkan.compat.mixin;

import dev.xaerovulkan.compat.XaeroVulkanCompat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "xaero.map.MapLimiter", remap = false)
public class MixinXaeroWorldMapMapLimiter {

    @Inject(method = "determineDriverType", at = @At("HEAD"), cancellable = true, remap = false)
    private void xvcompat$onDetermineDriverType(CallbackInfo ci) {
        if (XaeroVulkanCompat.VULKAN_ACTIVE) {
            // 直接返回，跳过实际执行
            ci.cancel();
        }
    }

    @Inject(method = "updateAvailableVRAM", at = @At("HEAD"), cancellable = true, remap = false)
    private void xvcompat$onUpdateAvailableVRAM(CallbackInfo ci) {
        if (XaeroVulkanCompat.VULKAN_ACTIVE) {
            ci.cancel();
        }
    }
}
