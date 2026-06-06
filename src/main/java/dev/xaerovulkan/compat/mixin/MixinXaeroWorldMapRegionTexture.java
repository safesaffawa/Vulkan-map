package dev.xaerovulkan.compat.mixin;

import dev.xaerovulkan.compat.XaeroVulkanCompat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "xaero.map.region.texture.RegionTexture", remap = false)
public class MixinXaeroWorldMapRegionTexture {

    @Inject(method = "ensureUnpackPBO", at = @At("HEAD"), cancellable = true, remap = false)
    private void xvcompat$onEnsureUnpackPBO(CallbackInfo ci) {
        if (XaeroVulkanCompat.VULKAN_ACTIVE) {
            // 禁用 PBO 创建，避免 VulkanMod 的 bug
            ci.cancel();
        }
    }

    @Inject(method = "writeToUnpackPBO", at = @At("HEAD"), cancellable = true, remap = false)
    private void xvcompat$onWriteToUnpackPBO(CallbackInfo ci) {
        if (XaeroVulkanCompat.VULKAN_ACTIVE) {
            ci.cancel();
        }
    }

    @Inject(method = "uploadBuffer", at = @At("HEAD"), cancellable = true, remap = false)
    private void xvcompat$onUploadBuffer(CallbackInfo ci) {
        if (XaeroVulkanCompat.VULKAN_ACTIVE) {
            // 跳过缓冲区上传，使用备选路径
            ci.cancel();
        }
    }
}
