package growthcraft.milk.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import growthcraft.lib.util.ClientUtils;
import growthcraft.lib.util.TextureHelper;
import growthcraft.milk.client.container.MixingVatContainer;
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
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;

public class MixingVatScreen extends ContainerScreen<MixingVatContainer> {

    private static final ResourceLocation TEXTURE = TextureHelper.getTextureGui(
            Reference.MODID, UnlocalizedName.MIXING_VAT
    );

    public MixingVatScreen(MixingVatContainer container, PlayerInventory playerInventory, ITextComponent title) {
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
        // Copy the image from the texture to the background layer
        int baseX = 0;
        int baseY = 0;
        int maxX = 256;
        int maxY = 256;

        blit(matrixStack, this.guiLeft, this.guiTop, baseX, baseY, this.xSize, this.ySize, maxX, maxY);

        // Progress bar
        int guiProgressX = this.guiLeft + 98;
        int guiProgressY = this.guiTop + 30;

        this.blit(matrixStack, guiProgressX, guiProgressY, 176, 0, 9, this.container.getProgressionScaled(28));

        // Heated
        int guiHeatLevelX = this.guiLeft + 98;
        int guiHeatLevelY = this.guiTop + 69;

        if (this.container.isBurning()) {
            this.blit(matrixStack, guiHeatLevelX, guiHeatLevelY, 176, 28, 13, 13);
        }

        // InputFluidTank Render
        int guiInputTankX = guiLeft + 49;
        int guiInputTankY = guiTop + 32;
        int guiInputTankHeight = 37;

        if (this.container.getInputFluidTank().getFluidAmount() > 0) {
            FluidStack fluidStack = this.container.getInputFluidTank().getFluid();

            int scaledFluidH = getScaledFluid(fluidStack.getAmount(), this.container.getInputFluidTank().getCapacity(), guiInputTankHeight);
            ClientUtils.drawRepeatedFluidSpriteGui(renderTypeBuffer, matrixStack, fluidStack,
                    guiInputTankX, guiInputTankY + (guiInputTankHeight - scaledFluidH), 16, scaledFluidH);
        }

        // ReagentFluidTank
        int guiReagentTankX = guiLeft + 49;
        int guiReagentTankY = guiTop + 18;
        int guiReagentTankHeight = 10;

        if (this.container.getReagentFluidTank().getFluidAmount() > 0) {
            FluidStack fluidStack = this.container.getReagentFluidTank().getFluid();

            int scaledFluidH = getScaledFluid(fluidStack.getAmount(), this.container.getReagentFluidTank().getCapacity(), guiReagentTankHeight);
            ClientUtils.drawRepeatedFluidSpriteGui(renderTypeBuffer, matrixStack, fluidStack,
                    guiReagentTankX, guiReagentTankY + (guiReagentTankHeight - scaledFluidH), 16, scaledFluidH);
        }

        // OutputFluidTank Render
        int guiOutputTankX = guiLeft + 124;
        int guiOutputTankY = guiTop + 40;
        int guiOutputTankHeight = 29;

        if (this.container.getOutputFluidTank().getFluidAmount() > 0) {
            FluidStack fluidStack = this.container.getOutputFluidTank().getFluid();

            int scaledFluidH = getScaledFluid(fluidStack.getAmount(), this.container.getInputFluidTank().getCapacity(), guiOutputTankHeight);
            ClientUtils.drawRepeatedFluidSpriteGui(renderTypeBuffer, matrixStack, fluidStack,
                    guiOutputTankX, guiOutputTankY + (guiOutputTankHeight - scaledFluidH), 16, scaledFluidH);
        }

        renderTypeBuffer.finish();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(matrixStack, mouseX, mouseY);
        // GUI Title
        this.font.drawString(matrixStack, this.title.getString(), 8.0F, 6.0F, 0x404040);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    private int getScaledFluid(float amount, float capacity, int maxPixelSize) {
        float ratio = amount / capacity;
        float scaled = maxPixelSize * ratio;

        return (int) scaled;
    }

}
