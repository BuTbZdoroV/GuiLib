// 
// Decompiled by Procyon v0.5.36
// 

package butbzdorov.client.guiLib.utils.newCustomNPC;

import lombok.Getter;

@Getter
public class CustomFontObject {
	public static CustomFontObject TTNormsMedium = new CustomFontObject("TTNorms-Medium", 24);


	public String font;
	public int size;


    public CustomFontObject(String font, int size) {
        this.font = font;
        this.size = size;
    }

    public CustomFontObject setFont(String font) {
		this.font = font;
		return this;
	}

    public CustomFontObject setSize(Number size) {
		this.size = size.intValue();
		return this;
	}
}
