package growthcraft.cellar.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import growthcraft.cellar.client.container.CultureJarContainer;
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

public class CultureJarScreen extends ContainerScreen<CultureJarContainer> {

    private static final ResourceLocation TEXTURE = TextureHelper.getTextureGui(Reference.MODID, UnlocalizedName.CULTURE_JAR);

    public CultureJarScreen(CultureJarContainer container, PlayerInventory playerInventory, ITextComponent title) {
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
        int guiProgressX = this.guiLeft + 55;
        int guiProgressY = this.guiTop + 35;

        this.blit(matrixStack, guiProgressX, guiProgressY, 176, 0, 197 - 176,
                this.container.getProcessingTimeScaled(197 - 176));

        // Heat indicator
        int guiHeatLevelX = this.guiLeft + 81;
        int guiHeatLevelY = this.guiTop + 57;

        if (this.container.isHeated()) {
            this.blit(matrixStack, guiHeatLevelX, guiHeatLevelY, 176, 16, 13, 13);
        }

        // Input fluid tank
        int guiInputFluidTankX = guiLeft + 36;
        int guiInputFluidTankY = guiTop + 14;
        int guiInputFluidTankHeight = 52;

        if (this.container.getTileEntityFluidTank(0).getFluidAmount() > 0) {
            FluidStack fluidStack = this.container.getTileEntityFluidTank(0).getFluid();

            int scaledFluidHeight = getScaledFluid(fluidStack.getAmount(),
                    this.container.getTileEntityFluidTank(0).getCapacity(), guiInputFluidTankHeight);

            ClientUtils.drawRepeatedFluidSpriteGui(renderTypeBuffer, matrixStack, fluidStack, guiInputFluidTankX,
                    guiInputFluidTankY + (guiInputFluidTankHeight - scaledFluidHeight), 16, scaledFluidHeight);

        }

        renderTypeBuffer.finish();
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(matrixStack, mouseX, mouseY);
        // Culture Jar Title
        //this.font.drawString(matrixStack, this.title.getString(), 7.0F, 5.0F, 0x404040);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        // Render background before super
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);

        // on hover for input fluid tank
        int guiInputTankX = guiLeft + 46;
        int guiInputTankY = guiTop + 17;
        int guiInputTankHeight = 52;

        if (mouseX > guiInputTankX && mouseX < guiInputTankX + 16 && mouseY > guiInputTankY && mouseY < guiInputTankY + guiInputTankHeight) {
            FluidStack fluidStack = this.container.getTileEntityFluidTank(0).getFluid();
            String tooltip = String.format("%s %dmb", fluidStack.getDisplayName().getString(), fluidStack.getAmount());
            StringTextComponent stringTextComponent = new StringTextComponent(tooltip);
            this.renderTooltip(matrixStack, stringTextComponent, mouseX, mouseY);
        }

        // finally any other tool tips
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);

    }
}
