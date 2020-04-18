package me.jellysquid.mods.sodium.client.gui.options.control;

import me.jellysquid.mods.sodium.client.gui.options.Option;
import me.jellysquid.mods.sodium.client.gui.widgets.AbstractWidget;
import net.minecraft.client.util.Rect2i;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;

public class ControlElement<T> extends AbstractWidget {
    protected final Option<T> option;

    protected final Rect2i dim;

    protected boolean hovered;

    public ControlElement(Option<T> option, Rect2i dim) {
        this.option = option;
        this.dim = dim;
    }

    public boolean isHovered() {
        return this.hovered;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float delta) {
        String name = this.option.getName();
        String label;

        if (this.option.isAvailable()) {
            if (this.option.hasChanged()) {
                label = Formatting.ITALIC + name + " *";
            } else {
                label = Formatting.WHITE + name;
            }
        } else {
            label = String.valueOf(Formatting.GRAY) + Formatting.STRIKETHROUGH + name;
        }

        this.hovered = this.dim.contains(mouseX, mouseY);

        this.drawRect(this.dim.getX(), this.dim.getY(), this.dim.getX() + this.dim.getWidth(), this.dim.getY() + this.dim.getHeight(), this.hovered ? 0xE0000000 : 0x90000000);
        this.drawString(matrixStack, label, this.dim.getX() + 6, this.dim.getY() + (this.dim.getHeight() / 2) - 4, 0xFFFFFFFF);
    }

    public Option<T> getOption() {
        return this.option;
    }

    public Rect2i getDimensions() {
        return this.dim;
    }
}
