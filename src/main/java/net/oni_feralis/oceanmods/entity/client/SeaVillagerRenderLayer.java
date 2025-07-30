package net.oni_feralis.oceanmods.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

public class SeaVillagerRenderLayer extends RenderLayer<SeaVillagerRenderState, SeaVillagerModel> {
    private final SeaVillagerModel model;

    public SeaVillagerRenderLayer(SeaVillagerRenderer renderer, EntityModelSet entityModelSet) {
        super(renderer);
        this.model = new SeaVillagerModel(entityModelSet.bakeLayer(SeaVillagerModel.MY_LAYER));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, SeaVillagerRenderState renderState, float yRot, float xRot) {
        // ...
    }
}
