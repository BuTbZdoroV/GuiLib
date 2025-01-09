package butbzdorov.client.guiLib.utils.jglfontCore.impl.format.angelcode.line;

import butbzdorov.client.guiLib.utils.jglfontCore.impl.format.JGLAbstractFontData;
import butbzdorov.client.guiLib.utils.jglfontCore.impl.format.angelcode.AngelCodeLine;
import butbzdorov.client.guiLib.utils.jglfontCore.impl.format.angelcode.AngelCodeLineData;

/**
 * InfoLine
 * @author void
 */
public class InfoLine implements AngelCodeLine {

  @Override
  public boolean process(final AngelCodeLineData line, final JGLAbstractFontData font) {
    if (!line.hasValue("face")) {
      return false;
    }
    font.setName(line.getString("face"));
    return true;
  }
}