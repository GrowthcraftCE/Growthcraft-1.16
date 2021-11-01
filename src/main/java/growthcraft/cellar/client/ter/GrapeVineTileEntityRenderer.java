package growthcraft.cellar.client.ter;

import com.mojang.blaze3d.matrix.MatrixStack;
import growthcraft.cellar.common.tileentity.GrapeVineTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class GrapeVineTileEntityRenderer extends TileEntityRenderer<GrapeVineTileEntity> {

    private final Minecraft minecraft = Minecraft.getInstance();

    public GrapeVineTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(GrapeVineTileEntity grapeVineTileEntity, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if (grapeVineTileEntity.getInventory().getStackInSlot(1).isEmpty()) return;

        ClientPlayerEntity player = minecraft.player;
        int lightLevel = getLightLevel(grapeVineTileEntity.getWorld(), grapeVineTileEntity.getPos().up());

        double[] placement = new double[]{0.5D, 0.5D, 0.5D};
        float itemScale = 1.75F;

        renderItem(grapeVineTileEntity.getInventory().getStackInSlot(1), placement, Vector3f.YP.rotationDegrees(180F - player.rotationYaw), matrixStackIn, bufferIn, partialTicks, combinedOverlayIn, lightLevel, itemScale);

    }

    private void renderItem(ItemStack stack, double[] translation, Quaternion rotation, MatrixStack matrixStack,
                            IRenderTypeBuffer buffer, float partialTicks, int combinedOverlay, int lightLevel, float scale) {
        matrixStack.push();
        matrixStack.translate(translation[0], translation[1], translation[2]);
        matrixStack.rotate(rotation);
        matrixStack.scale(scale, scale, scale);

        IBakedModel model = minecraft.getItemRenderer().getItemModelWithOverrides(stack, null, null);
        minecraft.getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.GROUND, true, matrixStack, buffer, lightLevel, combinedOverlay, model);
        matrixStack.pop();
    }

    private int getLightLevel(World worldIn, BlockPos pos) {
        int blockLightLevel = worldIn.getLightFor(LightType.BLOCK, pos);
        int skyLightLevel = worldIn.getLightFor(LightType.SKY, pos);
        return LightTexture.packLight(blockLightLevel, skyLightLevel);
    }
}
