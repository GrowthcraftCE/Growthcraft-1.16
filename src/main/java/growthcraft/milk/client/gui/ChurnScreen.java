package growthcraft.milk.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import growthcraft.lib.util.ClientUtils;
import growthcraft.lib.util.TextureHelper;
import growthcraft.milk.client.container.ChurnContainer;
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

public class ChurnScreen extends ContainerScreen<ChurnContainer> {
    private static final ResourceLocation TEXTURE = TextureHelper.getTextureGui(Reference.MODID, UnlocalizedName.CHURN);

    public ChurnScreen(ChurnContainer container, PlayerInventory playerInventory, ITextComponent title) {
        super(container, playerInventory, title);

        this.guiLeft = 0;
        this.guiTop = 0;
        this.xSize = 176;
        this.ySize = 166; // Requires AccessTransformer
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
        IRenderTypeBuffer.Impl renderTypeBuffer = IRenderTypeBuffer.getImpl(
                Tessellator.getInstance().getBuffer()
        );

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(TEXTURE);
        // Copy the image from the texture to the background layer
        int baseX = 0;
        int baseY = 0;
        int maxX = 256;
        int maxY = 256;

        blit(matrixStack, this.guiLeft, this.guiTop, baseX, baseY, this.xSize, this.ySize, maxX, maxY);

        // FluidTank Render
        int guiFluidTankX = guiLeft + 65;
        int guiFluidTankY = guiTop + 18;
        int guiFluidTankHeight = 52;

        if (this.container.getFluidTank().getFluidAmount() > 0) {
            FluidStack fluidStack = this.container.getFluidTank().getFluid();

            int scaledFluidH = getScaledFluid(fluidStack.getAmount(),
                    this.container.getFluidTank().getCapacity(), guiFluidTankHeight);

            ClientUtils.drawRepeatedFluidSpriteGui(renderTypeBuffer, matrixStack, fluidStack,
                    guiFluidTankX, guiFluidTankY + (guiFluidTankHeight - scaledFluidH), 16, scaledFluidH);
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
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);

        // OnHoverText for the Fluid Tank
        int guiFluidTankX = guiLeft + 65;
        int guiFluidTankY = guiTop + 18;
        int guiFluidTankHeight = 52;

        if (mouseX > guiFluidTankX && mouseX < guiFluidTankX + 16 && mouseY > guiFluidTankY && mouseY < guiFluidTankY + guiFluidTankHeight) {
            FluidStack fluidStack = this.container.getFluidTank().getFluid();
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
