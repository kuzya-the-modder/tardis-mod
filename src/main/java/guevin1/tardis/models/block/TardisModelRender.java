package guevin1.tardis.models.block;

import guevin1.tardis.TardisMod;
import guevin1.tardis.client.TardisModelLayers;
import guevin1.tardis.entity.block.TardisBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import org.joml.Vector3f;

import static net.minecraft.state.property.Properties.HORIZONTAL_FACING;
import static net.minecraft.state.property.Properties.ROTATION;


// Made with Blockbench 4.9.0
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class TardisModelRender<T extends BlockEntity> implements BlockEntityRenderer<TardisBlockEntity> {
	private final ModelPart all;
	private final ModelPart door2;
	private final ModelPart door1;
	private final ModelPart lamp;


	public TardisModelRender(BlockEntityRendererFactory.Context ctx) {
		ModelPart root = ctx.getLayerModelPart(TardisModelLayers.TARDIS);
		this.all = root.getChild("all");
		this.door1 = all.getChild("stenka").getChild("door").getChild("bone");
		this.door2 = all.getChild("stenka").getChild("door").getChild("bone2");
		this.lamp = all.getChild("header").getChild("lamp").getChild("lamp2");
	}




	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData all = modelPartData.addChild("all", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData header = all.addChild("header", ModelPartBuilder.create().uv(66, 30).cuboid(-10.0F, -36.0F, -10.0F, 20.0F, 4.0F, 20.0F, new Dilation(0.0F))
				.uv(66, 52).cuboid(-9.0F, -37.0F, -9.0F, 18.0F, 1.0F, 18.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, 0.0F));

		ModelPartData lamp = header.addChild("lamp", ModelPartBuilder.create().uv(0, 11).cuboid(-3.0F, -38.0F, -3.0F, 6.0F, 1.0F, 6.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-2.0F, -43.0F, -2.0F, 4.0F, 5.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData lamp2 = lamp.addChild("lamp2", ModelPartBuilder.create().uv(0, 50).cuboid(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -39.5F, 0.0F));

		ModelPartData tables = header.addChild("tables", ModelPartBuilder.create().uv(24, 75).cuboid(9.5F, -35.5F, -8.0F, 1.0F, 3.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r1 = tables.addChild("cube_r1", ModelPartBuilder.create().uv(24, 75).cuboid(-0.5F, -1.5F, -8.0F, 1.0F, 3.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -34.0F, -10.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData cube_r2 = tables.addChild("cube_r2", ModelPartBuilder.create().uv(24, 75).cuboid(9.5F, -35.5F, -8.0F, 1.0F, 3.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		ModelPartData cube_r3 = tables.addChild("cube_r3", ModelPartBuilder.create().uv(24, 75).cuboid(-0.5F, -1.5F, -8.0F, 1.0F, 3.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(-10.0F, -34.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		ModelPartData floor = all.addChild("floor", ModelPartBuilder.create().uv(0, 50).cuboid(-11.0F, -1.0F, -11.0F, 22.0F, 1.0F, 22.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData stenka = all.addChild("stenka", ModelPartBuilder.create().uv(16, 73).cuboid(8.0F, -33.0F, -10.0F, 2.0F, 32.0F, 2.0F, new Dilation(0.0F))
				.uv(8, 73).cuboid(-10.0F, -33.0F, 8.0F, 2.0F, 32.0F, 2.0F, new Dilation(0.0F))
				.uv(0, 73).cuboid(8.0F, -33.0F, 8.0F, 2.0F, 32.0F, 2.0F, new Dilation(0.0F))
				.uv(72, 0).cuboid(-10.0F, -33.0F, -10.0F, 2.0F, 32.0F, 2.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-9.0F, -33.0F, -9.0F, 18.0F, 32.0F, 18.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData door = stenka.addChild("door", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData bone = door.addChild("bone", ModelPartBuilder.create().uv(62, 17).cuboid(-8.5F, -33.0F, -0.6F, 8.0F, 32.0F, 1.0F, new Dilation(0.0F))
				.uv(24, 75).cuboid(-8.25F, -21.0F, -1.5F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(8.5F, 0.0F, -8.5F));

		ModelPartData bone2 = door.addChild("bone2", ModelPartBuilder.create().uv(54, 17).cuboid(0.0F, -32.0F, -0.1F, 8.0F, 32.0F, 1.0F, new Dilation(0.0F))
				.uv(24, 75).cuboid(6.75F, -20.0F, -1.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-8.0F, -1.0F, -9.0F));
		return TexturedModelData.of(modelData, 256, 256);
	}
	public void reload(TardisBlockEntity entity, Direction direction){

	}
	@Override
	public void render(TardisBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutout(new Identifier(TardisMod.MOD_ID,"textures/entity/block/tardis.png")));
		all.setPivot(8,0,8);
		all.setAngles(0,0,0);
		Direction dir = entity.getCachedState().get(HORIZONTAL_FACING);
		double[] Rotation = new double[]{Math.PI,Math.PI/2,0,Math.PI*1.5};
		all.yaw = (float) Rotation[dir.getHorizontal()];
		all.yScale = -1;
		all.xScale = -1;
		all.render(matrices, vertexConsumer, light, overlay);
		lamp.rotate(new Vector3f(0,0.04F,0));

	}
}