package butbzdorov.client.guiLib.utils.CustomFont;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public enum CustomFont {

    DimkinBold("Dimkin-Bold", 24),
    DimkinLight("Dimkin-Light", 24),
    DimkinRegular("Dimkin-Regular", 24),
    DimkinSemibold("Dimkin-Semibold", 24),
    InterRegular("Inter-Regular", 16),
    KeaniaOneRegular("KeaniaOne-Regular", 36),
    minecraft("minecraft", 24),
    regular("regular", 24),
    TTNormsBold("TTNorms-Bold", 24),
    TTNormsMedium("TTNorms-Medium", 24);

    public final String font;
    public int size;

    CustomFont(String font, Number size) {
        this.font = font;
        this.size = size.intValue();
    }

    public CustomFont setSize(Number size) {
        this.size = size.intValue();
        return this;
    }
}