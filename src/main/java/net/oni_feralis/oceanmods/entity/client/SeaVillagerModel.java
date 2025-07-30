package net.oni_feralis.oceanmods.entity.client;// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.animation.*;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.oni_feralis.oceanmods.OceanMods;

public class SeaVillagerModel extends EntityModel<SeaVillagerRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

	public static final ModelLayerLocation MY_LAYER =
			new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(OceanMods.MOD_ID, "seavillager"), "main");

	private final ModelPart crab;
	private final ModelPart upperBody;
	private final ModelPart torso;
	private final ModelPart rightEye;
	private final ModelPart leftEye;
	private final ModelPart shell;
	private final ModelPart rightArm;
	private final ModelPart rightClaw;
	private final ModelPart rightSmallClaw;
	private final ModelPart rightBigClaw;
	private final ModelPart leftArm;
	private final ModelPart leftClaw;
	private final ModelPart leftSmallClaw;
	private final ModelPart leftBigClaw;
	private final ModelPart backLeftLeg;
	private final ModelPart BackLeftLeggy;
	private final ModelPart backLeftFoot;
	private final ModelPart frontLeftLeg;
	private final ModelPart frontLeftLeggy;
	private final ModelPart frontLeftFoot;
	private final ModelPart backRightLeg;
	private final ModelPart backRightLeggy;
	private final ModelPart backRightFoot;
	private final ModelPart frontRightLeg;
	private final ModelPart frontRightLeggy;
	private final ModelPart frontRightFoot;

	public SeaVillagerModel(ModelPart root) {
		this.crab = root.getChild("crab");
		this.upperBody = this.crab.getChild("upperBody");
		this.torso = this.upperBody.getChild("torso");
		this.rightEye = this.torso.getChild("rightEye");
		this.leftEye = this.torso.getChild("leftEye");
		this.shell = this.torso.getChild("shell");
		this.rightArm = this.upperBody.getChild("rightArm");
		this.rightClaw = this.rightArm.getChild("rightClaw");
		this.rightSmallClaw = this.rightClaw.getChild("rightSmallClaw");
		this.rightBigClaw = this.rightClaw.getChild("rightBigClaw");
		this.leftArm = this.upperBody.getChild("leftArm");
		this.leftClaw = this.leftArm.getChild("leftClaw");
		this.leftSmallClaw = this.leftClaw.getChild("leftSmallClaw");
		this.leftBigClaw = this.leftClaw.getChild("leftBigClaw");
		this.backLeftLeg = this.crab.getChild("backLeftLeg");
		this.BackLeftLeggy = this.backLeftLeg.getChild("BackLeftLeggy");
		this.backLeftFoot = this.backLeftLeg.getChild("backLeftFoot");
		this.frontLeftLeg = this.crab.getChild("frontLeftLeg");
		this.frontLeftLeggy = this.frontLeftLeg.getChild("frontLeftLeggy");
		this.frontLeftFoot = this.frontLeftLeg.getChild("frontLeftFoot");
		this.backRightLeg = this.crab.getChild("backRightLeg");
		this.backRightLeggy = this.backRightLeg.getChild("backRightLeggy");
		this.backRightFoot = this.backRightLeg.getChild("backRightFoot");
		this.frontRightLeg = this.crab.getChild("frontRightLeg");
		this.frontRightLeggy = this.frontRightLeg.getChild("frontRightLeggy");
		this.frontRightFoot = this.frontRightLeg.getChild("frontRightFoot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition crab = partdefinition.addOrReplaceChild("crab", CubeListBuilder.create(), PartPose.offset(1.0F, 19.0F, -2.0F));

		PartDefinition upperBody = crab.addOrReplaceChild("upperBody", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition torso = upperBody.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -4.0F, -1.0F, 10.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightEye = torso.addOrReplaceChild("rightEye", CubeListBuilder.create(), PartPose.offset(-4.0F, -4.0F, 1.0F));

		PartDefinition cube_r1 = rightEye.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(22, 21).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.75F, -1.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r2 = rightEye.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(22, 31).addBox(0.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition leftEye = torso.addOrReplaceChild("leftEye", CubeListBuilder.create(), PartPose.offset(1.0F, -4.0F, 1.0F));

		PartDefinition cube_r3 = leftEye.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(10, 25).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.75F, -1.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r4 = leftEye.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(18, 31).addBox(0.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition shell = torso.addOrReplaceChild("shell", CubeListBuilder.create().texOffs(22, 17).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(2.0F))
		.texOffs(0, 0).addBox(-5.0F, -3.5F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.0F, -5.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.25F, -5.0F, 0.25F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-5.0F, -1.0F, 0.25F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, -3.0F, 6.0F));

		PartDefinition rightArm = upperBody.addOrReplaceChild("rightArm", CubeListBuilder.create(), PartPose.offset(-5.25F, -0.75F, 2.0F));

		PartDefinition cube_r5 = rightArm.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(22, 11).addBox(-1.0F, -3.0F, -1.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition rightClaw = rightArm.addOrReplaceChild("rightClaw", CubeListBuilder.create().texOffs(12, 18).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.25F, -3.25F, 0.15F));

		PartDefinition rightSmallClaw = rightClaw.addOrReplaceChild("rightSmallClaw", CubeListBuilder.create(), PartPose.offset(1.0F, -2.75F, -0.1F));

		PartDefinition cube_r6 = rightSmallClaw.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 21).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

		PartDefinition rightBigClaw = rightClaw.addOrReplaceChild("rightBigClaw", CubeListBuilder.create(), PartPose.offset(-1.25F, -3.25F, -0.5F));

		PartDefinition cube_r7 = rightBigClaw.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 11).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0436F));

		PartDefinition leftArm = upperBody.addOrReplaceChild("leftArm", CubeListBuilder.create(), PartPose.offset(3.25F, -0.75F, 2.0F));

		PartDefinition cube_r8 = leftArm.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(30, 21).addBox(0.0F, -3.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition leftClaw = leftArm.addOrReplaceChild("leftClaw", CubeListBuilder.create().texOffs(18, 26).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.25F, -3.25F, 0.15F));

		PartDefinition leftSmallClaw = leftClaw.addOrReplaceChild("leftSmallClaw", CubeListBuilder.create().texOffs(26, 26).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -2.25F, -0.1F));

		PartDefinition leftBigClaw = leftClaw.addOrReplaceChild("leftBigClaw", CubeListBuilder.create(), PartPose.offset(1.25F, -2.25F, -0.5F));

		PartDefinition cube_r9 = leftBigClaw.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(12, 11).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1309F));

		PartDefinition backLeftLeg = crab.addOrReplaceChild("backLeftLeg", CubeListBuilder.create(), PartPose.offset(4.25F, 2.0F, 3.5F));

		PartDefinition BackLeftLeggy = backLeftLeg.addOrReplaceChild("BackLeftLeggy", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r10 = BackLeftLeggy.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 28).addBox(0.0F, -3.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition backLeftFoot = backLeftLeg.addOrReplaceChild("backLeftFoot", CubeListBuilder.create().texOffs(26, 31).addBox(0.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.2F, 2.5F, 0.5F));

		PartDefinition frontLeftLeg = crab.addOrReplaceChild("frontLeftLeg", CubeListBuilder.create(), PartPose.offset(4.25F, 2.0F, 0.5F));

		PartDefinition frontLeftLeggy = frontLeftLeg.addOrReplaceChild("frontLeftLeggy", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r11 = frontLeftLeggy.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(6, 30).addBox(0.0F, -3.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition frontLeftFoot = frontLeftLeg.addOrReplaceChild("frontLeftFoot", CubeListBuilder.create().texOffs(30, 31).addBox(0.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.2F, 2.5F, 0.5F));

		PartDefinition backRightLeg = crab.addOrReplaceChild("backRightLeg", CubeListBuilder.create(), PartPose.offset(-6.25F, 2.0F, 3.5F));

		PartDefinition backRightLeggy = backRightLeg.addOrReplaceChild("backRightLeggy", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r12 = backRightLeggy.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(12, 30).addBox(-1.0F, -3.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition backRightFoot = backRightLeg.addOrReplaceChild("backRightFoot", CubeListBuilder.create().texOffs(32, 0).addBox(0.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.85F, 2.5F, 0.5F));

		PartDefinition frontRightLeg = crab.addOrReplaceChild("frontRightLeg", CubeListBuilder.create(), PartPose.offset(-6.25F, 2.0F, 0.5F));

		PartDefinition frontRightLeggy = frontRightLeg.addOrReplaceChild("frontRightLeggy", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r13 = frontRightLeggy.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(30, 11).addBox(-1.0F, -3.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition frontRightFoot = frontRightLeg.addOrReplaceChild("frontRightFoot", CubeListBuilder.create().texOffs(32, 4).addBox(0.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.85F, 2.5F, 0.5F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(SeaVillagerRenderState state) {
		super.setupAnim(state);
		crab.visible = state.myBoolean();
		crab.xRot = state.myXRotation();
		crab.yRot = state.myYRotation();
		crab.zRot = state.myZRotation();
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		crab.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}