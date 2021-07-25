package growthcraft.cellar.client.ter;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import growthcraft.cellar.common.tileentity.CultureJarTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;

import java.awt.*;

public class CultureJarTileEntityRenderer extends TileEntityRenderer<CultureJarTileEntity> {

    private final Minecraft minecraft = Minecraft.getInstance();

    public CultureJarTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean isGlobalRenderer(CultureJarTileEntity te) {
        return true;
    }

    @Override
    public void render(CultureJarTileEntity tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int lightLevel, int overlay) {
        if (tileEntity.getInputFluidTank(0).isEmpty()) {
            return;
        }

        FluidStack inputFluidStack = tileEntity.getInputFluidTank(0).getFluid();

        float capacity = (float) tileEntity.getInputFluidTank(0).getCapacity();
        float amount = inputFluidStack.getAmount();
        float inputFluidHeight = amount / capacity;

        renderCubeUsingQuads(tileEntity, partialTicks, matrixStack, buffer, inputFluidStack, inputFluidHeight, lightLevel, overlay);

    }

    public void renderCubeUsingQuads(CultureJarTileEntity cultureJarTileEntity, float partialTicks,
                                     MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer,
                                     FluidStack fluidStack, float inputFluidHeight, int lightLevel, int overlay) {
        final Vector3d TRANSLATION_OFFSET = new Vector3d(0, 0.01, 0);

        matrixStack.push();
        matrixStack.translate(TRANSLATION_OFFSET.x, TRANSLATION_OFFSET.y, TRANSLATION_OFFSET.z); // translate
        FluidAttributes fluidAttributes = fluidStack.getFluid().getAttributes();
        Color color = new Color(fluidAttributes.getColor(fluidStack));

        TextureAtlasSprite fluidStillTexture = minecraft.getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE)
                .apply(fluidAttributes.getStillTexture(fluidStack));

        drawCubeQuads(matrixStack, renderTypeBuffer, inputFluidHeight, fluidStillTexture, color, lightLevel);
        matrixStack.pop();
    }

    private void drawCubeQuads(MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, float inputFluidHeight, TextureAtlasSprite textureAtlasSprite, Color color, int lightLevel) {
        IVertexBuilder vertexBuilderBlockQuads = renderTypeBuffer.getBuffer(RenderType.getEntityTranslucent(
                new ResourceLocation("growthcraft_cellar:textures/block/fluid/fluid_still.png")
        ));

        Matrix4f matrixPos = matrixStack.getLast().getMatrix();
        Matrix3f matrixNormal = matrixStack.getLast().getNormal();

        Vector2f bottomLeftUV = new Vector2f(0.0F, 1.0F);
        float uVwidth = 1.0F;
        float uVheight = 1.0F;

        final float WIDTH = 0.25F;
        final float HEIGHT = 0.25F;

        final Vector3d EAST_FACE_MIDPOINT = new Vector3d(6.0 / 16.0, (2.0 / 16.0) * inputFluidHeight, 0.5);
        final Vector3d WEST_FACE_MIDPOINT = new Vector3d(10.0 / 16.0, (2.0 / 16.0) * inputFluidHeight, 0.5);
        final Vector3d NORTH_FACE_MIDPOINT = new Vector3d(0.5, (2.0 / 16.0) * inputFluidHeight, 6.0 / 16.0);
        final Vector3d SOUTH_FACE_MIDPOINT = new Vector3d(0.5, (2.0 / 16.0) * inputFluidHeight, 10.0 / 16.0);

        final Vector3d UP_FACE_MIDPOINT = new Vector3d(0.5, 0.25 * inputFluidHeight, 0.5);

        final Vector3d DOWN_FACE_MIDPOINT = new Vector3d(0.5, 0.0, 0.5);

        addFace(Direction.EAST, matrixPos, matrixNormal, vertexBuilderBlockQuads,
                color, EAST_FACE_MIDPOINT, WIDTH, HEIGHT * inputFluidHeight, textureAtlasSprite, bottomLeftUV, uVwidth, uVheight, lightLevel);
        addFace(Direction.WEST, matrixPos, matrixNormal, vertexBuilderBlockQuads,
                color, WEST_FACE_MIDPOINT, WIDTH, HEIGHT * inputFluidHeight, textureAtlasSprite, bottomLeftUV, uVwidth, uVheight, lightLevel);
        addFace(Direction.NORTH, matrixPos, matrixNormal, vertexBuilderBlockQuads,
                color, NORTH_FACE_MIDPOINT, WIDTH, HEIGHT * inputFluidHeight, textureAtlasSprite, bottomLeftUV, uVwidth, uVheight, lightLevel);
        addFace(Direction.SOUTH, matrixPos, matrixNormal, vertexBuilderBlockQuads,
                color, SOUTH_FACE_MIDPOINT, WIDTH, HEIGHT * inputFluidHeight, textureAtlasSprite, bottomLeftUV, uVwidth, uVheight, lightLevel);
        addFace(Direction.UP, matrixPos, matrixNormal, vertexBuilderBlockQuads,
                color, UP_FACE_MIDPOINT, WIDTH, HEIGHT, textureAtlasSprite, bottomLeftUV, uVwidth, uVheight, lightLevel);
        addFace(Direction.DOWN, matrixPos, matrixNormal, vertexBuilderBlockQuads,
                color, DOWN_FACE_MIDPOINT, WIDTH, HEIGHT, textureAtlasSprite, bottomLeftUV, uVwidth, uVheight, lightLevel);
    }

    private void addFace(Direction face, Matrix4f matrixPos, Matrix3f matrixNormal,
                         IVertexBuilder vertexBuilderBlockQuads, Color color, Vector3d centrePos,
                         float width, float height, TextureAtlasSprite textureAtlasSprite, Vector2f bottomLeftUV, float uVwidth, float uVheight,
                         int lightLevel) {

        Vector3f leftToRightDirection;
        Vector3f bottomToTopDirection;

        switch (face) {
            case NORTH: {
                leftToRightDirection = new Vector3f(-1, 0, 0);
                bottomToTopDirection = new Vector3f(0, 1, 0);
                break;
            }
            case SOUTH: {
                leftToRightDirection = new Vector3f(1, 0, 0);
                bottomToTopDirection = new Vector3f(0, 1, 0);
                break;
            }
            case EAST: {
                leftToRightDirection = new Vector3f(0, 0, -1);
                bottomToTopDirection = new Vector3f(0, 1, 0);
                break;
            }

            case UP: {
                leftToRightDirection = new Vector3f(-1, 0, 0);
                bottomToTopDirection = new Vector3f(0, 0, 1);
                break;
            }
            case DOWN: {
                leftToRightDirection = new Vector3f(1, 0, 0);
                bottomToTopDirection = new Vector3f(0, 0, 1);
                break;
            }
            case WEST:
            default: {
                leftToRightDirection = new Vector3f(0, 0, 1);
                bottomToTopDirection = new Vector3f(0, 1, 0);
                break;
            }
        }

        leftToRightDirection.mul(0.5F * width);
        bottomToTopDirection.mul(0.5F * height);

        Vector3f bottomLeftPos = new Vector3f(centrePos);
        bottomLeftPos.sub(leftToRightDirection);
        bottomLeftPos.sub(bottomToTopDirection);

        Vector3f bottomRightPos = new Vector3f(centrePos);
        bottomRightPos.add(leftToRightDirection);
        bottomRightPos.sub(bottomToTopDirection);

        Vector3f topRightPos = new Vector3f(centrePos);
        topRightPos.add(leftToRightDirection);
        topRightPos.add(bottomToTopDirection);

        Vector3f topLeftPos = new Vector3f(centrePos);
        topLeftPos.sub(leftToRightDirection);
        topLeftPos.add(bottomToTopDirection);

        Vector3f normalVector = face.toVector3f();

        addQuad(matrixPos, matrixNormal, vertexBuilderBlockQuads,
                bottomLeftPos, bottomRightPos, topRightPos, topLeftPos,
                normalVector, textureAtlasSprite, color, lightLevel);
    }

    private void addQuad(Matrix4f matrixPos, Matrix3f matrixNormal, IVertexBuilder renderBuffer,
                         Vector3f bottomLeftPos, Vector3f bottomRightPos, Vector3f topRightPos, Vector3f topLeftPos,
                         Vector3f normalVector, TextureAtlasSprite textureAtlasSprite, Color color, int lightLevel) {

        addQuadVertex(matrixPos, matrixNormal, renderBuffer, bottomLeftPos, normalVector, textureAtlasSprite, color, lightLevel);
        addQuadVertex(matrixPos, matrixNormal, renderBuffer, bottomRightPos, normalVector, textureAtlasSprite, color, lightLevel);
        addQuadVertex(matrixPos, matrixNormal, renderBuffer, topRightPos, normalVector, textureAtlasSprite, color, lightLevel);
        addQuadVertex(matrixPos, matrixNormal, renderBuffer, topLeftPos, normalVector, textureAtlasSprite, color, lightLevel);
    }

    private void addQuadVertex(Matrix4f matrixPos, Matrix3f matrixNormal, IVertexBuilder renderBuffer,
                               Vector3f topLeftPos, Vector3f normalVector,
                               TextureAtlasSprite textureAtlasSprite, Color color, int lightLevel) {
        renderBuffer.pos(matrixPos, topLeftPos.getX(), topLeftPos.getY(), topLeftPos.getZ()) // position coordinate
                .color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha())
                .tex(textureAtlasSprite.getMaxU(), textureAtlasSprite.getMinV())
                .overlay(OverlayTexture.NO_OVERLAY)
                .lightmap(lightLevel)
                .normal(normalVector.getX(), normalVector.getY(), normalVector.getZ())
                .endVertex();
    }
}
