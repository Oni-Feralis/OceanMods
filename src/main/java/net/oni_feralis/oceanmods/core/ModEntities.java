package net.oni_feralis.oceanmods.core;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.oni_feralis.oceanmods.OceanMods;
import net.oni_feralis.oceanmods.entity.SeaVillager;

import java.util.function.Supplier;

public class ModEntities
{
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, OceanMods.MOD_ID);

    public static ResourceKey<EntityType<?>> SEAVILLAGER_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("seavillager"));

    public static final Supplier<EntityType<SeaVillager>> SEAVILLAGER =
            ENTITY_TYPES.register("seavillager", () -> EntityType.Builder.of(SeaVillager::new, MobCategory.WATER_CREATURE)
                    .sized(0.75f,0.35f).build(SEAVILLAGER_KEY));

    public static void register(IEventBus eventBus)
    {
        ENTITY_TYPES.register(eventBus);
    }
}
