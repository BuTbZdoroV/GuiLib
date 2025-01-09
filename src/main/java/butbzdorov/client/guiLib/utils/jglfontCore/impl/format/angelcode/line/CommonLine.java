package butbzdorov.client.guiLib.utils.jglfontCore.impl.format.angelcode.line;

import butbzdorov.client.guiLib.utils.jglfontCore.impl.format.JGLAbstractFontData;
import butbzdorov.client.guiLib.utils.jglfontCore.impl.format.angelcode.AngelCodeLine;
import butbzdorov.client.guiLib.utils.jglfontCore.impl.format.angelcode.AngelCodeLineData;

/**
 * CommonLine
 * @author void
 */
public class CommonLine implements AngelCodeLine {

  @Override
  public boolean process(final AngelCodeLineData line, final JGLAbstractFontData font) {
    if (!line.hasValue("scaleW") &&
        !line.hasValue("scaleH")) {
      return false;
    }
    font.setBitmapWidth(line.getInt("scaleW"));
    font.setBitmapHeight(line.getInt("scaleH"));
    return true;
  }
}