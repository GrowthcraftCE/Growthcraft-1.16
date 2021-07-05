package growthcraft.cellar.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import growthcraft.cellar.client.container.BrewKettleContainer;
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

public class BrewKettleScreen extends ContainerScreen<BrewKettleContainer> {

    private static final ResourceLocation TEXTURE = TextureHelper.getTextureGui(Reference.MODID, UnlocalizedName.BREW_KETTLE);

    public BrewKettleScreen(BrewKettleContainer container, PlayerInventory playerInventory, ITextComponent title) {
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

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {

        matrixStack.push();
        IRenderTypeBuffer.Impl renderTypeBuffer = IRenderTypeBuffer.getImpl(Tessellator.getInstance().getBuffer());

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(TEXTURE);
        // Copy the image from the texture to the background layer
        int baseX = 0;
        int baseY = 0;
        int maxX = 256;
        int maxY = 256;

        blit(matrixStack, this.guiLeft, this.guiTop, baseX, baseY, this.xSize, this.ySize, maxX, maxY);

        // Progress bar
        int guiProgressX = this.guiLeft + 98;
        int guiProgressY = this.guiTop + 30;

        this.blit(matrixStack, guiProgressX, guiProgressY, 176, 0, 9, this.container.getSmeltProgressionScaled(28));

        // Heated
        int guiHeatLevelX = this.guiLeft + 68;
        int guiHeatLevelY = this.guiTop + 53;

        if (this.container.isBurning()) {
            this.blit(matrixStack, guiHeatLevelX, guiHeatLevelY, 176, 28, 13, 13);
        }

        // InputFluidTank Render
        int guiInputTankX = guiLeft + 46;
        int guiInputTankY = guiTop + 17;
        int guiInputTankHeight = 52;

        if (this.container.getInputFluidTank().getFluidAmount() > 0) {
            FluidStack fluidStack = this.container.getInputFluidTank().getFluid();

            int scaledFluidH = getScaledFluid(fluidStack.getAmount(), this.container.getInputFluidTank().getCapacity(), guiInputTankHeight);
            ClientUtils.drawRepeatedFluidSpriteGui(renderTypeBuffer, matrixStack, fluidStack,
                    guiInputTankX, guiInputTankY + (guiInputTankHeight - scaledFluidH), 16, scaledFluidH);
        }

        // OutputFluidTank Render
        int guiOutputTankX = guiLeft + 114;
        int guiOutputTankY = guiTop + 17;
        int guiOutputTankHeight = 52;

        if (this.container.getOutputFluidTank().getFluidAmount() > 0) {
            FluidStack fluidStack = this.container.getOutputFluidTank().getFluid();

            int scaledFluidH = getScaledFluid(fluidStack.getAmount(), this.container.getInputFluidTank().getCapacity(), guiOutputTankHeight);
            ClientUtils.drawRepeatedFluidSpriteGui(renderTypeBuffer, matrixStack, fluidStack,
                    guiOutputTankX, guiOutputTankY + (guiOutputTankHeight - scaledFluidH), 16, scaledFluidH);
        }

        renderTypeBuffer.finish();
    }

    private int getScaledFluid(float amount, float capacity, int maxPixelSize) {
        float ratio = amount / capacity;
        float scaled = maxPixelSize * ratio;

        return (int) scaled;
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(matrixStack, mouseX, mouseY);
        // Brew Kettle Title
        this.font.drawString(matrixStack, this.title.getString(), 8.0F, 6.0F, 0x404040);

    }

    @Override
    @ParametersAreNonnullByDefault
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        // background before super
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);

        // on hover for input fluid tank
        int guiInputTankX = guiLeft + 46;
        int guiInputTankY = guiTop + 17;
        int guiInputTankHeight = 52;

        if (mouseX > guiInputTankX && mouseX < guiInputTankX + 16 && mouseY > guiInputTankY && mouseY < guiInputTankY + guiInputTankHeight) {
            FluidStack fluidStack = this.container.getInputFluidTank().getFluid();
            String tooltip = String.format("%s %dmb", fluidStack.getDisplayName().getString(), fluidStack.getAmount());
            StringTextComponent stringTextComponent = new StringTextComponent(tooltip);
            this.renderTooltip(matrixStack, stringTextComponent, mouseX, mouseY);
        }

        // on hover for output fluid tank
        int guiOutputTankX = guiLeft + 114;
        int guiOutputTankY = guiTop + 17;
        int guiOutputTankHeight = 52;

        if (mouseX > guiOutputTankX && mouseX < guiOutputTankX + 16 && mouseY > guiOutputTankY && mouseY < guiOutputTankY + guiOutputTankHeight) {
            FluidStack fluidStack = this.container.getOutputFluidTank().getFluid();
            String tooltip = String.format("%s %dmb", fluidStack.getDisplayName().getString(), fluidStack.getAmount());
            StringTextComponent stringTextComponent = new StringTextComponent(tooltip);
            this.renderTooltip(matrixStack, stringTextComponent, mouseX, mouseY);
        }
        // text after super
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }
}
