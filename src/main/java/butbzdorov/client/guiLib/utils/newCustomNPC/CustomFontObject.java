// 
// Decompiled by Procyon v0.5.36
// 

package butbzdorov.client.guiLib.utils.newCustomNPC;

public class CustomFontObject {
	public static CustomFontObject TTNormsMedium = new CustomFontObject("TTNorms-Medium", 24);


	public String font;
	public int size;


    public CustomFontObject(String font, int size) {
        this.font = font;
        this.size = size;
    }

	public String getFont() {
		return font;
	}

	public CustomFontObject setFont(String font) {
		this.font = font;
		return this;
	}

	public int getSize() {
		return size;
	}

	public CustomFontObject setSize(int size) {
		this.size = size;
		return this;
	}
}
