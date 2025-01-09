package butbzdorov.client.guiLib.utils.jglfontCore.impl.format.angelcode.line;

import butbzdorov.client.guiLib.utils.jglfontCore.impl.format.JGLFontGlyphInfo;
import butbzdorov.client.guiLib.utils.jglfontCore.impl.format.JGLAbstractFontData;
import butbzdorov.client.guiLib.utils.jglfontCore.impl.format.angelcode.AngelCodeLine;
import butbzdorov.client.guiLib.utils.jglfontCore.impl.format.angelcode.AngelCodeLineData;

/**
 * CharLine
 * @author void
 */
public class CharLine implements AngelCodeLine {

  @Override
  public boolean process(final AngelCodeLineData line, final JGLAbstractFontData font) {
    if (!line.hasValue("id") ||
        !line.hasValue("x") ||
        !line.hasValue("y") ||
        !line.hasValue("width") ||
        !line.hasValue("height") ||
        !line.hasValue("page")) {
      return false;
    }
    JGLFontGlyphInfo c = new JGLFontGlyphInfo();
    c.setId(line.getInt("id"));
    c.setX(line.getInt("x"));
    c.setY(line.getInt("y"));
    c.setWidth(line.getInt("width"));
    c.setHeight(line.getInt("height"));
    c.setXoffset(line.getInt("xoffset"));
    c.setYoffset(line.getInt("yoffset"));
    c.setXadvance(line.getInt("xadvance"));
    c.setPage(font.getName() + "-" + line.getInt("page"));

    font.addGlyph(c.getId(), c);
    font.setLineHeight(Math.max(c.getHeight() + c.getYoffset(), font.getLineHeight()));

    return true;
  }
}