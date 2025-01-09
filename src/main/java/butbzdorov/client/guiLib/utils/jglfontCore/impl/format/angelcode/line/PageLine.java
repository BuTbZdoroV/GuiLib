package butbzdorov.client.guiLib.utils.jglfontCore.impl.format.angelcode.line;

import butbzdorov.client.guiLib.utils.jglfontCore.impl.format.JGLAbstractFontData;
import butbzdorov.client.guiLib.utils.jglfontCore.impl.format.angelcode.AngelCodeLine;
import butbzdorov.client.guiLib.utils.jglfontCore.impl.format.angelcode.AngelCodeLineData;

/**
 * PageLine
 * @author void
 */
public class PageLine implements AngelCodeLine {

  @Override
  public boolean process(final AngelCodeLineData line, final JGLAbstractFontData font) {
    if (!line.hasValue("id") ||
        !line.hasValue("file")) {
      return false;
    }
    font.addBitmap(line.getInt("id"), line.getString("file"));
    return true;
  }
}