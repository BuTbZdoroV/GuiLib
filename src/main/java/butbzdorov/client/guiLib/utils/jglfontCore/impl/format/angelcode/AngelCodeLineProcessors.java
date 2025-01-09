package butbzdorov.client.guiLib.utils.jglfontCore.impl.format.angelcode;

import java.util.Hashtable;
import java.util.Map;

import butbzdorov.client.guiLib.utils.jglfontCore.impl.format.angelcode.line.CharLine;
import butbzdorov.client.guiLib.utils.jglfontCore.impl.format.angelcode.line.CharsLine;
import butbzdorov.client.guiLib.utils.jglfontCore.impl.format.angelcode.line.CommonLine;
import butbzdorov.client.guiLib.utils.jglfontCore.impl.format.angelcode.line.InfoLine;
import butbzdorov.client.guiLib.utils.jglfontCore.impl.format.angelcode.line.KerningLine;
import butbzdorov.client.guiLib.utils.jglfontCore.impl.format.angelcode.line.KerningsLine;
import butbzdorov.client.guiLib.utils.jglfontCore.impl.format.angelcode.line.PageLine;


public class AngelCodeLineProcessors {
  private Map<String, AngelCodeLine> lineProcessors = new Hashtable<String, AngelCodeLine>();

  public AngelCodeLineProcessors() {
    lineProcessors.put("char", new CharLine());
    lineProcessors.put("chars", new CharsLine());
    lineProcessors.put("common", new CommonLine());
    lineProcessors.put("info", new InfoLine());
    lineProcessors.put("kerning", new KerningLine());
    lineProcessors.put("kernings", new KerningsLine());
    lineProcessors.put("page", new PageLine());
  }

  public AngelCodeLine get(final String lineId) {
    return lineProcessors.get(lineId);
  }
}
