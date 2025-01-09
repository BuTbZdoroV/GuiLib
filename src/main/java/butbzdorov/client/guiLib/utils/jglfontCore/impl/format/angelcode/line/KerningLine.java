package butbzdorov.client.guiLib.utils.jglfontCore.impl.format.angelcode.line;

import butbzdorov.client.guiLib.utils.jglfontCore.impl.format.JGLFontGlyphInfo;
import butbzdorov.client.guiLib.utils.jglfontCore.impl.format.JGLAbstractFontData;
import butbzdorov.client.guiLib.utils.jglfontCore.impl.format.angelcode.AngelCodeLine;
import butbzdorov.client.guiLib.utils.jglfontCore.impl.format.angelcode.AngelCodeLineData;

/**
 * KerningLine
 * @author void
 */
public class KerningLine implements AngelCodeLine {

  @Override
  public boolean process(final AngelCodeLineData line, final JGLAbstractFontData font) {
    if (!line.hasValue("first") ||
        !line.hasValue("second") ||
        !line.hasValue("amount")) {
      return false;
    }
    int first = line.getInt("first");
    int second = line.getInt("second");
    int amount = line.getInt("amount");

    JGLFontGlyphInfo info = font.getGlyphs().get(first);
    if (info == null) {
      return false;
    }
    info.getKerning().put(second, amount);
    return true;
  }
}