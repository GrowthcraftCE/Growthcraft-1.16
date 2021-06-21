package growthcraft.lib.util;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;

public class ClientUtils {

    public static void drawRepeatedFluidSpriteGui(IRenderTypeBuffer buffer, MatrixStack transform, FluidStack fluid, float x, float y, float w, float h) {
        RenderType renderType = getGui(PlayerContainer.LOCATION_BLOCKS_TEXTURE);
        IVertexBuilder builder = buffer.getBuffer(renderType);
        drawRepeatedFluidSprite(builder, transform, fluid, x, y, w, h);
    }

    public static void drawRepeatedFluidSprite(IVertexBuilder builder, MatrixStack transform, FluidStack fluid, float x, float y, float w, float h) {
        TextureAtlasSprite sprite = getSprite(fluid.getFluid().getAttributes().getStillTexture(fluid));
        int col = fluid.getFluid().getAttributes().getColor(fluid);
        int iW = sprite.getWidth();
        int iH = sprite.getHeight();
        if (iW > 0 && iH > 0)
            drawRepeatedSprite(builder, transform, x, y, w, h, iW, iH,
                    sprite.getMinU(), sprite.getMaxU(), sprite.getMinV(), sprite.getMaxV(),
                    (col >> 16 & 255) / 255.0f, (col >> 8 & 255) / 255.0f, (col & 255) / 255.0f, 1);
    }

    public static void drawRepeatedSprite(IVertexBuilder builder, MatrixStack transform, float x, float y, float w,
                                          float h, int iconWidth, int iconHeight, float uMin, float uMax, float vMin, float vMax,
                                          float r, float g, float b, float alpha) {
        int iterMaxW = (int) (w / iconWidth);
        int iterMaxH = (int) (h / iconHeight);
        float leftoverW = w % iconWidth;
        float leftoverH = h % iconHeight;
        float leftoverWf = leftoverW / (float) iconWidth;
        float leftoverHf = leftoverH / (float) iconHeight;
        float iconUDif = uMax - uMin;
        float iconVDif = vMax - vMin;
        for (int ww = 0; ww < iterMaxW; ww++) {
            for (int hh = 0; hh < iterMaxH; hh++)
                drawTexturedRect(builder, transform, x + ww * iconWidth, y + hh * iconHeight, iconWidth, iconHeight,
                        r, g, b, alpha, uMin, uMax, vMin, vMax);
            drawTexturedRect(builder, transform, x + ww * iconWidth, y + iterMaxH * iconHeight, iconWidth, leftoverH,
                    r, g, b, alpha, uMin, uMax, vMin, (vMin + iconVDif * leftoverHf));
        }
        if (leftoverW > 0) {
            for (int hh = 0; hh < iterMaxH; hh++)
                drawTexturedRect(builder, transform, x + iterMaxW * iconWidth, y + hh * iconHeight, leftoverW, iconHeight,
                        r, g, b, alpha, uMin, (uMin + iconUDif * leftoverWf), vMin, vMax);
            drawTexturedRect(builder, transform, x + iterMaxW * iconWidth, y + iterMaxH * iconHeight, leftoverW, leftoverH,
                    r, g, b, alpha, uMin, (uMin + iconUDif * leftoverWf), vMin, (vMin + iconVDif * leftoverHf));
        }
    }

    public static void drawTexturedRect(IVertexBuilder builder, MatrixStack transform, float x, float y, float w, float h,
                                        float r, float g, float b, float alpha, float u0, float u1, float v0, float v1) {
        Matrix4f mat = transform.getLast().getMatrix();
        builder.pos(mat, x, y + h, 0)
                .color(r, g, b, alpha)
                .tex(u0, v1)
                .overlay(OverlayTexture.NO_OVERLAY)
                .lightmap(0xf000f0)
                .normal(1, 1, 1)
                .endVertex();
        builder.pos(mat, x + w, y + h, 0)
                .color(r, g, b, alpha)
                .tex(u1, v1)
                .overlay(OverlayTexture.NO_OVERLAY)
                .lightmap(15728880)
                .normal(1, 1, 1)
                .endVertex();
        builder.pos(mat, x + w, y, 0)
                .color(r, g, b, alpha)
                .tex(u1, v0)
                .overlay(OverlayTexture.NO_OVERLAY)
                .lightmap(15728880)
                .normal(1, 1, 1)
                .endVertex();
        builder.pos(mat, x, y, 0)
                .color(r, g, b, alpha)
                .tex(u0, v0)
                .overlay(OverlayTexture.NO_OVERLAY)
                .lightmap(15728880)
                .normal(1, 1, 1)
                .endVertex();
    }

    public static void drawTexturedRect(IVertexBuilder builder, MatrixStack transform, int x, int y, int w, int h, float picSize,
                                        int u0, int u1, int v0, int v1) {
        drawTexturedRect(builder, transform, x, y, w, h, 1, 1, 1, 1, u0 / picSize, u1 / picSize, v0 / picSize, v1 / picSize);
    }

    public static RenderType getGui(ResourceLocation texture) {
        return RenderType.makeType(
                "gui_" + texture,
                DefaultVertexFormats.POSITION_COLOR_TEX,
                GL11.GL_QUADS,
                256,
                RenderType.State.getBuilder()
                        .texture(new RenderState.TextureState(texture, false, false))
                        .alpha(new RenderState.AlphaState(0.5F))
                        .build(false)
        );
    }

    public static TextureAtlasSprite getSprite(ResourceLocation rl) {
        return Minecraft.getInstance().getModelManager().getAtlasTexture(PlayerContainer.LOCATION_BLOCKS_TEXTURE).getSprite(rl);
    }
}
