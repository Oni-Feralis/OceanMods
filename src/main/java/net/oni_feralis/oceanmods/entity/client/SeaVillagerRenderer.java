package net.oni_feralis.oceanmods.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.oni_feralis.oceanmods.entity.AbstractSeaVillagerEntity;
import net.oni_feralis.oceanmods.entity.SeaVillager;

import static net.oni_feralis.oceanmods.core.ModEntities.SEAVILLAGER;

public class SeaVillagerRenderer extends MobRenderer<AbstractSeaVillagerEntity, SeaVillagerModel>
{
    public SeaVillagerRenderer(EntityRendererProvider.Context context)
    {
        super(context, new SeaVillagerModel(context.bakeLayer(SeaVillagerModelLayers.SEAVILLAGER)), 0.5f);
        this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
    }

    @Override
    public ResourceLocation getTextureLocation(AbstractSeaVillagerEntity entity)
    {
        return entity.getTexture();
    }

    public void render(AbstractSeaVillagerEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource source, int light)
    {
        poseStack.pushPose();
        super.render(entity, 0F, partialTicks, poseStack, source, light);
        poseStack.popPose();
    }
}