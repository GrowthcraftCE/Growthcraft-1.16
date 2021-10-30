package growthcraft.core.client.ter;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import growthcraft.core.common.tileentity.RopeTileEntity;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.*;

import java.awt.*;

public class RopeTileEntityRenderer extends TileEntityRenderer<RopeTileEntity> {

    private final Minecraft minecraft = Minecraft.getInstance();

    public RopeTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(RopeTileEntity tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int lightLevel, int overlay) {
        if (tileEntity.getFenceItemStack().equals(ItemStack.EMPTY)) {
            return;
        }

        ItemStack itemStack = tileEntity.getFenceItemStack();
        ResourceLocation resourceLocation = Blocks.OAK_FENCE.getRegistryName();

        float inputFluidHeight = 1;

        renderCubeUsingQuads(tileEntity, partialTicks, matrixStack, buffer, lightLevel, overlay, resourceLocation);

    }

    public void renderCubeUsingQuads(RopeTileEntity ropeTileEntity, float partialTicks,
                                     MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer,
                                     int lightLevel, int overlay, ResourceLocation resourceLocation) {
        final Vector3d TRANSLATION_OFFSET = new Vector3d(0, 0.01, 0);

        matrixStack.push();
        matrixStack.translate(TRANSLATION_OFFSET.x, TRANSLATION_OFFSET.y, TRANSLATION_OFFSET.z); // translate
        Color color = Color.BLUE;

        drawCubeQuads(matrixStack, renderTypeBuffer, color, lightLevel, resourceLocation);
        matrixStack.pop();
    }

    private void drawCubeQuads(MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, Color color, int lightLevel, ResourceLocation resourceLocation) {
        IVertexBuilder vertexBuilderBlockQuads = renderTypeBuffer.getBuffer(RenderType.getEntitySolid(
                resourceLocation
        ));

        Matrix4f matrixPos = matrixStack.getLast().getMatrix();
        Matrix3f matrixNormal = matrixStack.getLast().getNormal();

        Vector2f bottomLeftUV = new Vector2f(0.0F, 1.0F);
        float uVwidth = 1.0F;
        float uVheight = 1.0F;

        final float WIDTH = 0.25F;
        final float HEIGHT = 0.25F;

        float inputFluidHeight = 0.5F;

        final Vector3d EAST_FACE_MIDPOINT = new Vector3d(0.625, 0.5, 0.5);
        final Vector3d WEST_FACE_MIDPOINT = new Vector3d(0.375, 0.5, 0.5);
        final Vector3d NORTH_FACE_MIDPOINT = new Vector3d(0.5, 0.5, 0.375);
        final Vector3d SOUTH_FACE_MIDPOINT = new Vector3d(0.5, 0.5, 0.625);
        final Vector3d UP_FACE_MIDPOINT = new Vector3d(0.5, 0.99, 0.5);
        final Vector3d DOWN_FACE_MIDPOINT = new Vector3d(0.5, 0.0, 0.5);

        addFace(Direction.EAST, matrixPos, matrixNormal, vertexBuilderBlockQuads,
                color, EAST_FACE_MIDPOINT, WIDTH, 0.99F, bottomLeftUV, uVwidth, uVheight, lightLevel);
        addFace(Direction.WEST, matrixPos, matrixNormal, vertexBuilderBlockQuads,
                color, WEST_FACE_MIDPOINT, WIDTH, 0.99F, bottomLeftUV, uVwidth, uVheight, lightLevel);
        addFace(Direction.NORTH, matrixPos, matrixNormal, vertexBuilderBlockQuads,
                color, NORTH_FACE_MIDPOINT, WIDTH, 0.99F, bottomLeftUV, uVwidth, uVheight, lightLevel);
        addFace(Direction.SOUTH, matrixPos, matrixNormal, vertexBuilderBlockQuads,
                color, SOUTH_FACE_MIDPOINT, WIDTH, 0.99F, bottomLeftUV, uVwidth, uVheight, lightLevel);
        addFace(Direction.UP, matrixPos, matrixNormal, vertexBuilderBlockQuads,
                color, UP_FACE_MIDPOINT, WIDTH, HEIGHT, bottomLeftUV, uVwidth, uVheight, lightLevel);
        addFace(Direction.DOWN, matrixPos, matrixNormal, vertexBuilderBlockQuads,
                color, DOWN_FACE_MIDPOINT, WIDTH, HEIGHT, bottomLeftUV, uVwidth, uVheight, lightLevel);
    }

    private void addFace(Direction face, Matrix4f matrixPos, Matrix3f matrixNormal,
                         IVertexBuilder vertexBuilderBlockQuads, Color color, Vector3d centrePos,
                         float width, float height, Vector2f bottomLeftUV, float texUwidth, float texVheight,
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

        Vector2f bottomLeftUVpos = new Vector2f(bottomLeftUV.x, bottomLeftUV.y);
        Vector2f bottomRightUVpos = new Vector2f(bottomLeftUV.x + texUwidth, bottomLeftUV.y);
        Vector2f topLeftUVpos = new Vector2f(bottomLeftUV.x + texUwidth, bottomLeftUV.y + texVheight);
        Vector2f topRightUVpos = new Vector2f(bottomLeftUV.x, bottomLeftUV.y + texVheight);

        Vector3f normalVector = face.toVector3f();

        addQuad(matrixPos, matrixNormal, vertexBuilderBlockQuads,
                bottomLeftPos, bottomRightPos, topRightPos, topLeftPos,
                bottomLeftUVpos, bottomRightUVpos, topLeftUVpos, topRightUVpos,
                normalVector, color, lightLevel);
    }

    private void addQuad(Matrix4f matrixPos, Matrix3f matrixNormal, IVertexBuilder renderBuffer,
                         Vector3f bottomLeftPos, Vector3f bottomRightPos, Vector3f topRightPos, Vector3f topLeftPos,
                         Vector2f bottomLeftUVpos, Vector2f bottomRightUVpos, Vector2f topLeftUVpos, Vector2f topRightUVpos,
                         Vector3f normalVector, Color color, int lightLevel) {

        addQuadVertex(matrixPos, matrixNormal, renderBuffer, bottomLeftPos, bottomLeftUVpos, normalVector, color, lightLevel);
        addQuadVertex(matrixPos, matrixNormal, renderBuffer, bottomRightPos, bottomRightUVpos, normalVector, color, lightLevel);
        addQuadVertex(matrixPos, matrixNormal, renderBuffer, topRightPos, topRightUVpos, normalVector, color, lightLevel);
        addQuadVertex(matrixPos, matrixNormal, renderBuffer, topLeftPos, topLeftUVpos, normalVector, color, lightLevel);
    }

    private void addQuadVertex(Matrix4f matrixPos, Matrix3f matrixNormal, IVertexBuilder renderBuffer,
                               Vector3f pos, Vector2f texUV, Vector3f normalVector,
                               Color color, int lightLevel) {
        renderBuffer.pos(matrixPos, pos.getX(), pos.getY(), pos.getZ()) // position coordinate
                .color(color.getRed(), color.getGreen(), color.getBlue(), 25)
                .tex(texUV.x, texUV.y)
                .overlay(OverlayTexture.NO_OVERLAY)
                .lightmap(lightLevel)
                .normal(normalVector.getX(), normalVector.getY(), normalVector.getZ())
                .endVertex();
    }
}
