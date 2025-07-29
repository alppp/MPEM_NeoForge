// EntityTypeMixin.java - 无变化
package net.shuyanmc.mpem.mixin;

import net.minecraft.world.entity.EntityType;
import net.shuyanmc.mpem.api.IOptimizableEntity;
import net.shuyanmc.mpem.util.EntityTypeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityType.class)
public abstract class EntityTypeMixin implements IOptimizableEntity {
    @Unique private boolean alwaysTick;
    @Unique private boolean tickInRaid;
    @Unique private boolean mpem$registered = false;

    @Override
    public boolean shouldAlwaysTick() {
        mpem$ensureRegistered();
        return this.alwaysTick;
    }

    @Override
    public void setAlwaysTick(boolean value) {
        mpem$ensureRegistered();
        this.alwaysTick = value;
    }

    @Override
    public boolean shouldTickInRaid() {
        mpem$ensureRegistered();
        return this.tickInRaid;
    }

    @Override
    public void setTickInRaid(boolean value) {
        mpem$ensureRegistered();
        this.tickInRaid = value;
    }
    
    @Unique
    private void mpem$ensureRegistered() {
        if (!mpem$registered) {
            EntityTypeRegistry.registerMixedEntityType((EntityType<?>) (Object) this);
            mpem$registered = true;
        }
    }
}