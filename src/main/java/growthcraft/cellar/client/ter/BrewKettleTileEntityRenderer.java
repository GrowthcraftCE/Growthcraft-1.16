package growthcraft.cellar.client.ter;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import growthcraft.cellar.common.tileentity.BrewKettleTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;

public class BrewKettleTileEntityRenderer extends TileEntityRenderer<BrewKettleTileEntity> {

    private final Minecraft minecraft = Minecraft.getInstance();

    public BrewKettleTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(BrewKettleTileEntity tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int lightLevel, int overlay) {
        if (tileEntity.getFluidTank(0).isEmpty() && tileEntity.getFluidTank(1).isEmpty()) {
            return;
        }

        FluidStack inputFluidStack = tileEntity.getFluidTank(0).getFluid();
        FluidStack outputFluidStack = tileEntity.getFluidTank(1).getFluid();

        float baseOffset = 4 / 16F;
        float maxFluidHeight = 20 / 16F;

        float inputFluidHeight = baseOffset + (maxFluidHeight - baseOffset) * inputFluidStack.getAmount() / (float) tileEntity.getFluidTank(0).getCapacity();

        for (int x = 0; x <= 1; x++) {
            for (int z = 0; z <= 1; z++) {
                renderFluid(inputFluidStack, inputFluidHeight, x, z, buffer, matrixStack, lightLevel);
            }
        }

    }

    private void renderFluid(FluidStack fluidStack, float height, int x, int z, IRenderTypeBuffer buffer, MatrixStack matrixStack, int lightLevel) {
        Fluid fluid = fluidStack.getFluid();
        FluidAttributes fluidAttributes = fluid.getAttributes();

        TextureAtlasSprite fluidTexture = minecraft.getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE)
                .apply(fluidAttributes.getStillTexture(fluidStack));

        int color = fluidAttributes.getColor(fluidStack);

        IVertexBuilder vertexBuilder = buffer.getBuffer(RenderType.getTranslucentMovingBlock());

        float u1 = fluidTexture.getInterpolatedU(x == 0 ? 2 : 0);
        float v1 = fluidTexture.getInterpolatedV(z == 0 ? 2 : 0);
        float u2 = fluidTexture.getInterpolatedU(x == 0 ? 16 : 14);
        float v2 = fluidTexture.getInterpolatedV(x == 0 ? 16 : 14);

        buildVertices(vertexBuilder, matrixStack, height, x, z, u1, v1, u2, v2, lightLevel, color);

    }

    private static void buildVertices(IVertexBuilder vertexBuilder, MatrixStack matrixStack, float height, int x, int z, float u1, float v1, float u2, float v2, int lightLevel, int color) {
        float minX = x == 0 ? 2 / 16F : 1;
        float minZ = z == 0 ? 2 / 16F : 1;
        float maxX = x == 0 ? 1 : 2 - 2 / 16F;
        float maxZ = z == 0 ? 1 : 2 - 2 / 16F;

        matrixStack.push();

        putVertex(vertexBuilder, matrixStack, minX, height, maxZ, color, u1, v2, lightLevel);
        putVertex(vertexBuilder, matrixStack, maxX, height, maxZ, color, u2, v2, lightLevel);
        putVertex(vertexBuilder, matrixStack, maxX, height, minZ, color, u2, v1, lightLevel);
        putVertex(vertexBuilder, matrixStack, minX, height, minZ, color, u1, v1, lightLevel);

        matrixStack.pop();
    }

    private static void putVertex(IVertexBuilder vertexBuilder, MatrixStack matrixStack, float x, float y, float z, int color, float u, float v, int lightLevel) {
        Vector3i normal = Direction.UP.getDirectionVec();
        MatrixStack.Entry peek = matrixStack.getLast();

        int a = color >> 24 & 0xFF;
        int r = color >> 16 & 0xFF;
        int g = color >> 8 & 0xFF;
        int b = color & 0xFF;

        vertexBuilder.normal(peek.getNormal(), x, y, z)
                .color(r, g, b, a)
                .lightmap((int) u, (int) v)
                .lightmap(lightLevel)
                .normal(normal.getX(), normal.getY(), normal.getZ())
                .endVertex();
    }
}
