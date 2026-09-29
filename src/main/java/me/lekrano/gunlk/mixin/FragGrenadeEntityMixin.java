package me.lekrano.gunlk.mixin;

import com.victoriomods.taczballisticbreaching.FragGrenadeEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FragGrenadeEntity.class)
public class FragGrenadeEntityMixin {

    @Inject(
            method = "getEffectiveDamage",
            at = @At("RETURN"),
            cancellable = true,
            remap = false
    )
    private void gunlk$test(CallbackInfoReturnable<Double> cir) {
        System.out.println("Original damage: " + cir.getReturnValue());
        System.out.println("=============================================== IT WORKS ============================================================");

        cir.setReturnValue(200.0D);
    }

}
