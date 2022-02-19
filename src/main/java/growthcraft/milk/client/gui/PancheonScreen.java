package growthcraft.milk.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import growthcraft.lib.util.ClientUtils;
import growthcraft.lib.util.TextureHelper;
import growthcraft.milk.client.container.PancheonContainer;
import growthcraft.milk.shared.Reference;
import growthcraft.milk.shared.UnlocalizedName;
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

public class PancheonScreen extends ContainerScreen<PancheonContainer> {

    private static final ResourceLocation TEXTURE = TextureHelper.getTextureGui(
            Reference.MODID, UnlocalizedName.PANCHEON
    );

    public PancheonScreen(PancheonContainer container, PlayerInventory playerInventory, ITextComponent title) {
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
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {

        matrixStack.push();
        IRenderTypeBuffer.Impl renderTypeBuffer = IRenderTypeBuffer.getImpl(Tessellator.getInstance().getBuffer());

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(TEXTURE);

        // TODO: Fix FLuidTank positions.
        // Copy the image from the texture to the background layer
        int baseX = 0;
        int baseY = 0;
        int maxX = 256;
        int maxY = 256;

        blit(matrixStack, this.guiLeft, this.guiTop, baseX, baseY, this.xSize, this.ySize, maxX, maxY);

        // Progress bar
        int guiProgressX = this.guiLeft + 82;
        int guiProgressY = this.guiTop + 57;

        this.blit(matrixStack, guiProgressX, guiProgressY, 176, 42, 13, this.container.getProgressionScaled(28));

        // InputFluidTank Render
        int guiInputTankX = guiLeft + 62;
        int guiInputTankY = guiTop + 18;
        int guiInputTankHeight = 52;

        if (this.container.getInputFluidTank(0).getFluidAmount() > 0) {
            FluidStack fluidStack = this.container.getInputFluidTank(0).getFluid();

            int scaledFluidH = getScaledFluid(fluidStack.getAmount(), this.container.getInputFluidTank(0).getCapacity(), guiInputTankHeight);
            ClientUtils.drawRepeatedFluidSpriteGui(renderTypeBuffer, matrixStack, fluidStack,
                    guiInputTankX, guiInputTankY + (guiInputTankHeight - scaledFluidH), 16, scaledFluidH);
        }

        // OutputFluidTank0 Render
        int guiOutputTank0X = guiLeft + 98;
        int guiOutputTank0Y = guiTop + 18;
        int guiOutputTank0Height = 23;

        if (this.container.getOutputFluidTank(1).getFluidAmount() > 0) {
            FluidStack fluidStack = this.container.getOutputFluidTank(1).getFluid();

            int scaledFluidH = getScaledFluid(fluidStack.getAmount(), this.container.getOutputFluidTank(1).getCapacity(), guiOutputTank0Height);
            ClientUtils.drawRepeatedFluidSpriteGui(renderTypeBuffer, matrixStack, fluidStack,
                    guiOutputTank0X, guiOutputTank0Y + (guiOutputTank0Height - scaledFluidH), 16, scaledFluidH);
        }

        // OutputFluidTank1 Render
        int guiOutputTank1X = guiLeft + 98;
        int guiOutputTank1Y = guiTop + 47;
        int guiOutputTank1Height = 23;

        if (this.container.getOutputFluidTank(2).getFluidAmount() > 0) {
            FluidStack fluidStack = this.container.getOutputFluidTank(2).getFluid();

            int scaledFluidH = getScaledFluid(fluidStack.getAmount(), this.container.getOutputFluidTank(2).getCapacity(), guiOutputTank1Height);
            ClientUtils.drawRepeatedFluidSpriteGui(renderTypeBuffer, matrixStack, fluidStack,
                    guiOutputTank1X, guiOutputTank1Y + (guiOutputTank1Height - scaledFluidH), 16, scaledFluidH);
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
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        // background before super
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);

        // on hover for input fluid tank
        int guiInputTankX = guiLeft + 62;
        int guiInputTankY = guiTop + 18;
        int guiInputTankHeight = 52;

        if (mouseX > guiInputTankX && mouseX < guiInputTankX + 16 && mouseY > guiInputTankY && mouseY < guiInputTankY + guiInputTankHeight) {
            FluidStack fluidStack = this.container.getInputFluidTank(0).getFluid();
            String tooltip = fluidStack.getAmount() > 0
                    ? String.format("%s %dmb", fluidStack.getDisplayName().getString(), fluidStack.getAmount())
                    : "Empty";
            StringTextComponent stringTextComponent = new StringTextComponent(tooltip);
            this.renderTooltip(matrixStack, stringTextComponent, mouseX, mouseY);
        }

        // on hover for output fluid tank0
        int guiOutputTank0X = guiLeft + 98;
        int guiOutputTank0Y = guiTop + 18;
        int guiOutputTank0Height = 23;

        if (mouseX > guiOutputTank0X && mouseX < guiOutputTank0X + 16 && mouseY > guiOutputTank0Y && mouseY < guiOutputTank0Y + guiOutputTank0Height) {
            FluidStack fluidStack = this.container.getOutputFluidTank(1).getFluid();
            String tooltip = fluidStack.getAmount() > 0
                    ? String.format("%s %dmb", fluidStack.getDisplayName().getString(), fluidStack.getAmount())
                    : "Empty";
            StringTextComponent stringTextComponent = new StringTextComponent(tooltip);
            this.renderTooltip(matrixStack, stringTextComponent, mouseX, mouseY);
        }

        // on hover for output fluid tank1
        int guiOutputTank1X = guiLeft + 98;
        int guiOutputTank1Y = guiTop + 47;
        int guiOutputTank1Height = 23;

        if (mouseX > guiOutputTank1X && mouseX < guiOutputTank1X + 16 && mouseY > guiOutputTank1Y && mouseY < guiOutputTank1Y + guiOutputTank1Height) {
            FluidStack fluidStack = this.container.getOutputFluidTank(2).getFluid();
            String tooltip = fluidStack.getAmount() > 0
                    ? String.format("%s %dmb", fluidStack.getDisplayName().getString(), fluidStack.getAmount())
                    : "Empty";
            StringTextComponent stringTextComponent = new StringTextComponent(tooltip);
            this.renderTooltip(matrixStack, stringTextComponent, mouseX, mouseY);
        }

        // text after super
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);

    }
}
