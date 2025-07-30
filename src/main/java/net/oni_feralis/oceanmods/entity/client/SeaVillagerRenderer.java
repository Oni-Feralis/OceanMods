package net.oni_feralis.oceanmods.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.oni_feralis.oceanmods.entity.AbstractSeaVillagerEntity;
import net.oni_feralis.oceanmods.entity.SeaVillager;

import static net.oni_feralis.oceanmods.core.ModEntities.SEAVILLAGER;

public class SeaVillagerRenderer extends LivingEntityRenderer<SeaVillager,SeaVillagerRenderState,SeaVillagerModel> {
    public SeaVillagerRenderer(EntityRendererProvider.Context context) {
        super(context, new SeaVillagerModel(context.bakeLayer(SEAVILLAGER)), 0.5f);
        this.addLayer(new SeaVillagerRenderLayer(this, context.getModelSet()));
    }

    @Override
    public SeaVillagerRenderState createRenderState() {
        return new SeaVillagerRenderState();
    }

    @Override
    public void extractRenderState(SeaVillager entity, SeaVillagerRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
    }

    public void render(SeaVillagerRenderState state, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(state, poseStack, bufferSource, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(SeaVillager state) {
        return ResourceLocation.parse("oceanmods:textures/entity/seavillager_texture.png");

        //return ResourceLocation.fromNamespaceAndPath("examplemod", "example_entity");
    }

    private static final class AnimatedModel extends SeaVillagerModel<SeaVillager> {
        private final ModelPart root;
        private final HierarchicalModel animator = new HierarchicalModel<SeaVillager>() {
            @Override
            public ModelPart root() {
                return root;
            }

            @Override
            public void setupAnim(SeaVillager entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
                this.root().getAllParts().forEach(ModelPart::resetPose);
                this.animate(entity.animationState0 , SeaVillagerAnimations.idle, ageInTicks, 1f);
                this.animateWalk(SeaVillagerAnimations.walk, limbSwing, limbSwingAmount, 1f, 1f);
            }
        };

        public AnimatedModel(ModelPart root) {
            super(root);
            this.root = root;
        }

        @Override
        public void setupAnim(SeaVillager entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            animator.setupAnim(entity , limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        }
    }
}