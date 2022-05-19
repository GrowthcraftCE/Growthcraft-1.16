package growthcraft.milk.client.ter;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import growthcraft.milk.common.tileentity.PancheonTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;

import java.awt.*;

public class PancheonTileEntityRender extends TileEntityRenderer<PancheonTileEntity> {

    public PancheonTileEntityRender(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(PancheonTileEntity tileEntity, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if (tileEntity.isFluidEmpty()) {
            return;
        }

        float baseOffset = 1 / 16F;
        float maxFluidHeight = 3.5F / 16F;

        FluidStack inputFluidStack = tileEntity.getFluidTank(0).getFluid();
        FluidStack outputFluidStackPrimary = tileEntity.getFluidTank(1).getFluid();
        FluidStack outputFluidStackSecondary = tileEntity.getFluidTank(2).getFluid();

        if (!inputFluidStack.isEmpty()) {
            float inputFluidStackHeight = baseOffset + (maxFluidHeight - baseOffset) * inputFluidStack.getAmount() / (float) tileEntity.getFluidTank(0).getCapacity();

            renderFluid(inputFluidStack, inputFluidStackHeight, bufferIn, matrixStackIn, combinedLightIn, combinedOverlayIn);
        }

        if (!outputFluidStackPrimary.isEmpty()) {
            float outputFluidStackPrimaryHeight = baseOffset + (maxFluidHeight - baseOffset) * outputFluidStackPrimary.getAmount() / (float) tileEntity.getFluidTank(1).getCapacity() - (maxFluidHeight / 2);

            renderFluid(outputFluidStackPrimary, outputFluidStackPrimaryHeight, bufferIn, matrixStackIn, combinedLightIn, combinedOverlayIn);
        }

        if (!outputFluidStackSecondary.isEmpty()) {
            float outputFluidStackSecondaryHeight = baseOffset + (maxFluidHeight - baseOffset) * outputFluidStackSecondary.getAmount() / (float) tileEntity.getFluidTank(2).getCapacity();

            renderFluid(outputFluidStackSecondary, outputFluidStackSecondaryHeight, bufferIn, matrixStackIn, combinedLightIn, combinedOverlayIn);
        }
    }

    public void renderIcon(MatrixStack matrixStack, IVertexBuilder builder, TextureAtlasSprite sprite, Color color, float alpha, int overlay, int light) {
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        Matrix4f mat = matrixStack.getLast().getMatrix();

        builder.pos(mat, 0, 17, 0).color(red, green, blue, (int) (alpha * 255F)).tex(sprite.getMinU(),
                sprite.getMaxV()).overlay(overlay).lightmap(light).normal(0, 0, 1).endVertex();
        builder.pos(mat, 17, 17, 0).color(red, green, blue, (int) (alpha * 255F)).tex(sprite.getMaxU(),
                sprite.getMaxV()).overlay(overlay).lightmap(light).normal(0, 0, 1).endVertex();
        builder.pos(mat, 17, 0, 0).color(red, green, blue, (int) (alpha * 255F)).tex(sprite.getMaxU(),
                sprite.getMinV()).overlay(overlay).lightmap(light).normal(0, 0, 1).endVertex();
        builder.pos(mat, 0, 0, 0).color(red, green, blue, (int) (alpha * 255F)).tex(sprite.getMinU(),
                sprite.getMinV()).overlay(overlay).lightmap(light).normal(0, 0, 1).endVertex();

    }

    public void renderFluid(FluidStack fluidStack, float height, IRenderTypeBuffer buffer, MatrixStack matrixStack, int lightLevel, int overlay) {
        matrixStack.push();
        matrixStack.translate(0.5F, height, 0.5F);

        float s = 14 / 256F;
        float v = 1.55F / 8F;
        float w = -v * 2.5F;

        float alpha = 2F;

        matrixStack.translate(w, 0.0F, w);
        matrixStack.rotate(Vector3f.XP.rotationDegrees(90));
        matrixStack.scale(s, s, s);

        FluidAttributes fluidAttributes = fluidStack.getFluid().getAttributes();
        Color color = new Color(fluidAttributes.getColor(fluidStack));

        TextureAtlasSprite sprite = Minecraft.getInstance().getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE)
                .apply(fluidAttributes.getStillTexture(fluidStack));

        IVertexBuilder builder = buffer.getBuffer(Atlases.getTranslucentCullBlockType());

        renderIcon(matrixStack, builder, sprite, color, alpha, overlay, lightLevel);
        matrixStack.pop();
    }
}
