package dev.xaerovulkan.compat.mixin;

import dev.xaerovulkan.compat.XaeroVulkanCompat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "xaero.map.region.texture.LeafRegionTexture", remap = false)
public class MixinXaeroWorldMapLeafRegionTexture {

    @Inject(method = "uploadNonCache", at = @At("HEAD"), cancellable = true, remap = false)
    private void xvcompat$onUploadNonCache(CallbackInfoReturnable<Boolean> cir) {
        if (XaeroVulkanCompat.VULKAN_ACTIVE) {
            cir.setReturnValue(false);
        }
    }
}
