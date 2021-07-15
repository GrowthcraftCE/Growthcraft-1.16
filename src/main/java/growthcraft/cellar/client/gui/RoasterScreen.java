package growthcraft.cellar.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import growthcraft.cellar.client.container.RoasterContainer;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import growthcraft.lib.util.TextureHelper;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.ParametersAreNonnullByDefault;

public class RoasterScreen extends ContainerScreen<RoasterContainer> {

    private static final ResourceLocation TEXTURE = TextureHelper.getTextureGui(Reference.MODID, UnlocalizedName.ROASTER);

    public RoasterScreen(RoasterContainer container, PlayerInventory playerInventory, ITextComponent title) {
        super(container, playerInventory, title);

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        matrixStack.push();

        IRenderTypeBuffer.Impl renderTypeBuffer = IRenderTypeBuffer.getImpl(Tessellator.getInstance().getBuffer());
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(TEXTURE);

        // copy the image from the texture to the background layer
        int baseX = 0;
        int baseY = 0;
        int maxX = 256;
        int maxY = 256;

        blit(matrixStack, this.guiLeft, this.guiTop, baseX, baseY, this.xSize, this.ySize, maxX, maxY);

        // Progress Bar
        int guiProgressX = this.guiLeft + 74;
        int guiProgressY = this.guiTop + 45;

        this.blit(matrixStack, guiProgressX, guiProgressY, 176, 0, this.container.getProcessingTimeScaled(28), 9);

        // Heat indicator
        int guiHeatLevelX = this.guiLeft + 81;
        int guiHeatLevelY = this.guiTop + 57;

        if (this.container.isHeated()) {
            this.blit(matrixStack, guiHeatLevelX, guiHeatLevelY, 176, 28, 13, 13);
        }

        renderTypeBuffer.finish();

    }

    @Override
    @ParametersAreNonnullByDefault
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
        super.drawGuiContainerForegroundLayer(matrixStack, x, y);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);

        // Render tool tip with progress over the progress arrow.

        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }
}
