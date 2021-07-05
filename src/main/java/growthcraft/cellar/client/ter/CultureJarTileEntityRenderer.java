package growthcraft.cellar.client.ter;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import growthcraft.cellar.common.tileentity.CultureJarTileEntity;
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

public class CultureJarTileEntityRenderer extends TileEntityRenderer<CultureJarTileEntity> {

    private final Minecraft minecraft = Minecraft.getInstance();

    public CultureJarTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(CultureJarTileEntity tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int lightLevel, int overlay) {
        if (tileEntity.getInputFluidTank(0).isEmpty()) {
            return;
        }

        FluidStack inputFluidStack = tileEntity.getInputFluidTank(0).getFluid();

        float baseOffset = 0 / 16F;
        float maxFluidHeight = 4 / 16F;

        float inputFluidHeight = baseOffset + (maxFluidHeight - baseOffset) * inputFluidStack.getAmount() / (float) tileEntity.getInputFluidTank(0).getCapacity();

        renderFluid(inputFluidStack, 0.525F, 0.525F, inputFluidHeight, 0, buffer, matrixStack, lightLevel, overlay, 90);
        renderFluid(inputFluidStack, 0.525F, 0.525F, 0, inputFluidHeight, buffer, matrixStack, lightLevel, overlay, 0);

    }

    public void renderIcon(MatrixStack matrixStack, IVertexBuilder builder, TextureAtlasSprite sprite, Color color, float alpha, int overlay, int light) {
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        Matrix4f mat = matrixStack.getLast().getMatrix();

        builder.pos(mat, 0, 16, 0).color(red, green, blue, (int) (alpha * 255F)).tex(sprite.getMinU(),
                sprite.getMaxV()).overlay(overlay).lightmap(light).normal(0, 0, 1).endVertex();
        builder.pos(mat, 16, 16, 0).color(red, green, blue, (int) (alpha * 255F)).tex(sprite.getMaxU(),
                sprite.getMaxV()).overlay(overlay).lightmap(light).normal(0, 0, 1).endVertex();
        builder.pos(mat, 16, 0, 0).color(red, green, blue, (int) (alpha * 255F)).tex(sprite.getMaxU(),
                sprite.getMinV()).overlay(overlay).lightmap(light).normal(0, 0, 1).endVertex();
        builder.pos(mat, 0, 0, 0).color(red, green, blue, (int) (alpha * 255F)).tex(sprite.getMinU(),
                sprite.getMinV()).overlay(overlay).lightmap(light).normal(0, 0, 1).endVertex();

    }

    private void renderFluid(FluidStack fluidStack, float x, float z, float y, float height, IRenderTypeBuffer buffer, MatrixStack matrixStack, int lightLevel, int overlay, int rotation) {
        matrixStack.push();
        // set the base point
        matrixStack.translate(x, y, z);

        // scale
        float s = 3.75F / 256F;
        //
        float v = 1.55F / 8F;
        //
        float w = -v * 0.5F;

        float alpha = 2F;

        // set the stretch to point
        matrixStack.translate(w, height, w);
        // rotate on the x-axis
        matrixStack.rotate(Vector3f.XP.rotationDegrees(rotation));
        //
        matrixStack.scale(s, s, s);

        FluidAttributes fluidAttributes = fluidStack.getFluid().getAttributes();
        Color color = new Color(fluidAttributes.getColor(fluidStack));

        TextureAtlasSprite fluidStillTexture = minecraft.getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE)
                .apply(fluidAttributes.getStillTexture(fluidStack));

        IVertexBuilder builder = buffer.getBuffer(Atlases.getTranslucentCullBlockType());

        renderIcon(matrixStack, builder, fluidStillTexture, color, alpha, overlay, lightLevel);

        matrixStack.pop();
    }

}
