package net.shuyanmc.mpem.util;

import net.minecraft.world.entity.EntityType;
import net.shuyanmc.mpem.api.IOptimizableEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Safe registry for tracking EntityTypes that have been successfully mixed with IOptimizableEntity
 */
public final class EntityTypeRegistry {
    private static final Logger LOGGER = LoggerFactory.getLogger(EntityTypeRegistry.class);
    
    // Thread-safe map to track successfully mixed EntityTypes
    private static final Map<EntityType<?>, Boolean> MIXED_ENTITY_TYPES = new ConcurrentHashMap<>();
    private static final Map<EntityType<?>, EntityOptimizationData> OPTIMIZATION_DATA = new ConcurrentHashMap<>();
    
    private EntityTypeRegistry() {
        // Utility class
    }
    
    /**
     * Register an EntityType as successfully mixed
     */
    public static void registerMixedEntityType(EntityType<?> entityType) {
        MIXED_ENTITY_TYPES.put(entityType, true);
        OPTIMIZATION_DATA.put(entityType, new EntityOptimizationData());
        LOGGER.debug("Registered mixed EntityType: {}", entityType);
    }
    
    /**
     * Check if an EntityType has been successfully mixed
     */
    public static boolean isMixed(EntityType<?> entityType) {
        return MIXED_ENTITY_TYPES.getOrDefault(entityType, false);
    }
    
    /**
     * Safely get IOptimizableEntity from EntityType with null checking
     */
    public static IOptimizableEntity getOptimizableEntity(EntityType<?> entityType) {
        if (!isMixed(entityType)) {
            LOGGER.warn("Attempted to cast unmixed EntityType to IOptimizableEntity: {}", entityType);
            return null;
        }
        
        try {
            return (IOptimizableEntity) entityType;
        } catch (ClassCastException e) {
            LOGGER.error("Failed to cast EntityType to IOptimizableEntity despite being registered as mixed: {}", entityType, e);
            // Remove from registry to prevent future attempts
            MIXED_ENTITY_TYPES.remove(entityType);
            OPTIMIZATION_DATA.remove(entityType);
            return null;
        }
    }
    
    /**
     * Safely check if an entity should always tick
     */
    public static boolean shouldAlwaysTick(EntityType<?> entityType) {
        IOptimizableEntity optimizable = getOptimizableEntity(entityType);
        if (optimizable != null) {
            return optimizable.shouldAlwaysTick();
        }
        
        // Fallback behavior - use cached data or safe defaults
        EntityOptimizationData data = OPTIMIZATION_DATA.get(entityType);
        if (data != null) {
            return data.alwaysTick;
        }
        
        // Safe default - don't optimize unknown entities
        return true;
    }
    
    /**
     * Safely check if an entity should tick in raids
     */
    public static boolean shouldTickInRaid(EntityType<?> entityType) {
        IOptimizableEntity optimizable = getOptimizableEntity(entityType);
        if (optimizable != null) {
            return optimizable.shouldTickInRaid();
        }
        
        // Fallback behavior - use cached data or safe defaults
        EntityOptimizationData data = OPTIMIZATION_DATA.get(entityType);
        if (data != null) {
            return data.tickInRaid;
        }
        
        // Safe default - don't optimize unknown entities
        return true;
    }
    
    /**
     * Set optimization properties with fallback data caching
     */
    public static void setAlwaysTick(EntityType<?> entityType, boolean value) {
        IOptimizableEntity optimizable = getOptimizableEntity(entityType);
        if (optimizable != null) {
            optimizable.setAlwaysTick(value);
        }
        
        // Always cache the data as fallback
        OPTIMIZATION_DATA.computeIfAbsent(entityType, k -> new EntityOptimizationData()).alwaysTick = value;
    }
    
    /**
     * Set raid tick properties with fallback data caching
     */
    public static void setTickInRaid(EntityType<?> entityType, boolean value) {
        IOptimizableEntity optimizable = getOptimizableEntity(entityType);
        if (optimizable != null) {
            optimizable.setTickInRaid(value);
        }
        
        // Always cache the data as fallback
        OPTIMIZATION_DATA.computeIfAbsent(entityType, k -> new EntityOptimizationData()).tickInRaid = value;
    }
    
    /**
     * Get statistics about mixed entity types
     */
    public static int getMixedEntityCount() {
        return MIXED_ENTITY_TYPES.size();
    }
    
    /**
     * Internal data class for caching optimization settings
     */
    private static class EntityOptimizationData {
        boolean alwaysTick = false;
        boolean tickInRaid = false;
    }
}