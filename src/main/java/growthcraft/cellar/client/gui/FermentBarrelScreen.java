package growthcraft.cellar.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import growthcraft.cellar.client.container.FermentBarrelContainer;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import growthcraft.lib.util.ClientUtils;
import growthcraft.lib.util.TextureHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.ParametersAreNonnullByDefault;

public class FermentBarrelScreen extends ContainerScreen<FermentBarrelContainer> {

    private static final ResourceLocation TEXTURE = TextureHelper.getTextureGui(Reference.MODID, UnlocalizedName.FERMENT_BARREL);

    public FermentBarrelScreen(FermentBarrelContainer container, PlayerInventory playerInventory, ITextComponent title) {
        super(container, playerInventory, title);

        this.guiLeft = 0;
        this.guiTop = 0;
        this.xSize = 176;
        this.ySize = 166;
    }

    private static TextureAtlasSprite getStillFluidSprite(FluidStack fluidStack) {
        Minecraft minecraft = Minecraft.getInstance();
        Fluid fluid = fluidStack.getFluid();
        FluidAttributes attributes = fluid.getAttributes();
        ResourceLocation fluidStill = attributes.getStillTexture(fluidStack);
        return minecraft.getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).apply(fluidStill);
    }

    private int getScaledFluid(float amount, float capacity, int maxPixelSize) {
        float ratio = amount / capacity;
        float scaled = maxPixelSize * ratio;

        return (int) scaled;
    }

    @Override
    @SuppressWarnings("deprecation")
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
        int guiProgressX = this.guiLeft + 48;
        int guiProgressY = this.guiTop + 19 + 29;
        int textureProgressBarX = 188;
        int textureProgressBarY = 28;
        int textureProgressBarW = 9;

        int progressBarHeight = this.container.getProcessingTimeScaled(28);

        // matrix, guiX, guiY, textureX, textureY, textureWidth, textureHeight
        this.blit(matrixStack,
                guiProgressX, guiProgressY - progressBarHeight,
                textureProgressBarX, textureProgressBarY - progressBarHeight,
                textureProgressBarW, progressBarHeight);

        // Bubble Bar
        int guiBubbleX = this.guiLeft + 58;
        int guiBubbleY = this.guiTop + 19 + 29;

        int guiBubbleHeight = this.container.getProcessingTimeScaled(28);

        int textBubbleX = 176;
        int textBubbleY = 28;

        this.blit(matrixStack,
                guiBubbleX, guiBubbleY - guiBubbleHeight,
                textBubbleX, textBubbleY - guiBubbleHeight,
                9, guiBubbleHeight);

        // Input fluid tank
        int guiInputFluidTankX = guiLeft + 72;
        int guiInputFluidTankY = guiTop + 17;
        int guiInputFluidTankHeight = 52;

        if (this.container.getTileEntityFluidTank(0).getFluidAmount() > 0) {
            FluidStack fluidStack = this.container.getTileEntityFluidTank(0).getFluid();

            int scaledFluidHeight = getScaledFluid(fluidStack.getAmount(),
                    this.container.getTileEntityFluidTank(0).getCapacity(), guiInputFluidTankHeight);

            float adjustedGuiInputFluidTankY = (guiInputFluidTankY + (guiInputFluidTankHeight - scaledFluidHeight));

            ClientUtils.drawRepeatedFluidSpriteGui(renderTypeBuffer, matrixStack, fluidStack, guiInputFluidTankX,
                    adjustedGuiInputFluidTankY, 50, scaledFluidHeight);

        }

        renderTypeBuffer.finish();
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(matrixStack, mouseX, mouseY);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        // Render background before super
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);

        // on hover for input fluid tank
        int guiInputTankX = guiLeft + 64;
        int guiInputTankY = guiTop + 17;
        int guiInputTankHeight = 54;

        if (mouseX > guiInputTankX && mouseX < guiInputTankX + (121 - 64) && mouseY > guiInputTankY && mouseY < guiInputTankY + guiInputTankHeight) {
            String fluidTankTooltip = "Empty";

            FluidStack fluidStack = this.container.getTileEntityFluidTank(0).getFluid();

            if (!this.container.getTileEntityFluidTank(0).isEmpty()) {
                fluidTankTooltip = String.format("%s %dmb", fluidStack.getDisplayName().getString(), fluidStack.getAmount());
            }

            StringTextComponent stringTextComponent = new StringTextComponent(fluidTankTooltip);
            this.renderTooltip(matrixStack, stringTextComponent, mouseX, mouseY);
        }

        // finally any other tool tips
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);

    }
}
