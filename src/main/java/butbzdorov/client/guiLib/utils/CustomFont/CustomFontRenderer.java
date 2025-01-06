package butbzdorov.client.guiLib.utils.CustomFont;

import butbzdorov.Main;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class CustomFontRenderer {
    private static final Map<String, UnicodeFont> cache = new HashMap<String, UnicodeFont>();
    private static final Map<String, Color> colors = new HashMap<String, Color>();
    public static int guiScale;
    static float fontScale;

    private static UnicodeFont get(CustomFont font) {
        int size = (int)((float)font.size * fontScale);
        return cache.computeIfAbsent(font.font + size, o -> {
            Font s;
            try {
                try (InputStream in = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(Main.MODID, "fonts/" + font.font + ".ttf")).getInputStream();){
                    s = Font.createFont(0, in);
                }
            }
            catch (FontFormatException | IOException e) {
                throw new RuntimeException(font.font, e);
            }
            UnicodeFont uf = new UnicodeFont(s, size, false, false);
            uf.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
            String symbols = " +=0123456789абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!-_()?,./\"'[]{}*&^:%$£;№@#~`><•";
            uf.addGlyphs(symbols);
            try {
                uf.loadGlyphs();
            }
            catch (SlickException e) {
                throw new RuntimeException(font.font, (Throwable)e);
            }
            uf.setDisplayListCaching(true);
            return uf;
        });
    }

    public static int getStringWidth(CustomFont font, String string) {
        if (!string.startsWith("\u00a7")) {
            string = "\u00a7f" + string;
        }
        UnicodeFont uf = CustomFontRenderer.get(font);
        StringBuilder result = new StringBuilder();
        for (String s : string.split("\u00a7")) {
            if (s == null || s.length() <= 1) continue;
            result.append(s.substring(1));
        }
        int width = uf.getWidth(result.toString());
        return (int)((float)width / (fontScale * 2.0f));
    }

    public static int getStringHeight(CustomFont font, String string, int w) {
        if (!string.startsWith("\u00a7")) {
            string = "\u00a7f" + string;
        }
        if (w != -1) {
            w = (int)((float)w * (fontScale * 2.0f));
        }
        int y = 0;
        int width = 0;
        UnicodeFont uf = CustomFontRenderer.get(font);
        for (String s1 : string.split("\n")) {
            if (s1 == null || s1.length() == 0) continue;
            String source = "";
            for (String s : s1.split("§")) {
                if (s == null || s.length() <= 1) continue;
                for (String s2 : s.substring(1).split(" ")) {
                    String t = s2 + " ";
                    source = source + t;
                    if (w != -1 && width + uf.getWidth(t) > w) {
                        y += uf.getHeight(source) + 2;
                        width = 0;
                    }
                    width += uf.getWidth(t);
                }
            }
            y += uf.getHeight(source) + 2;
            width = 0;
        }
        return (int)((float)y / (fontScale * 2.0f));
    }

    public static void drawStringWithMaxWidth(String string, double x, double y, int w, int color, CustomFont font) {
        float guiScale = fontScale * 2.0f;
        float rscale = 1.0f / guiScale;
        x = (int)((float)x * guiScale);
        y = (int)((float)y * guiScale);
        if (w != -1) {
            w = (int)((float)w * guiScale);
        }
        GL11.glScalef((float)rscale, (float)rscale, (float)1.0f);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        if (!string.startsWith("\u00a7")) {
            string = "\u00a7f" + string;
        }
        double sx = x;
        int width = 0;
        UnicodeFont uf = CustomFontRenderer.get(font);
        for (String s1 : string.split("\n")) {
            if (s1 == null || s1.length() == 0) continue;
            String source = "";
            for (String s : s1.split("§")) {
                if (s == null || s.length() <= 1) continue;
                char col = s.charAt(0);
                for (String s2 : s.substring(1).split(" ")) {
                    String t = s2 + " ";
                    source = source + t;
                    if (w != -1 && width + uf.getWidth(t) > w) {
                        x = sx;
                        y += uf.getHeight(source) + 2;
                        width = 0;
                    }
                    uf.drawString((float)x, (float)y, t, new Color(color));
                    width += uf.getWidth(t);
                    x += uf.getWidth(t);
                }
            }
            x = sx;
            y += uf.getHeight(source) + 2;
            width = 0;
        }
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glScalef((float)guiScale, (float)guiScale, (float)1.0f);
    }

    public static void drawStringWithMaxWidth(String string, double x, double y, int w, Color color, CustomFont font) {
        float guiScale = fontScale * 2.0f;
        float rscale = 1.0f / guiScale;
        x = (int)((float)x * guiScale);
        y = (int)((float)y * guiScale);
        if (w != -1) {
            w = (int)((float)w * guiScale);
        }
        GL11.glScalef((float)rscale, (float)rscale, (float)1.0f);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        if (!string.startsWith("\u00a7")) {
            string = "\u00a7f" + string;
        }
        double sx = x;
        int width = 0;
        UnicodeFont uf = CustomFontRenderer.get(font);
        for (String s1 : string.split("\n")) {
            if (s1 == null || s1.length() == 0) continue;
            String source = "";
            for (String s : s1.split("§")) {
                if (s == null || s.length() <= 1) continue;
                char col = s.charAt(0);
                for (String s2 : s.substring(1).split(" ")) {
                    String t = s2 + " ";
                    source = source + t;
                    if (w != -1 && width + uf.getWidth(t) > w) {
                        x = sx;
                        y += uf.getHeight(source) + 2;
                        width = 0;
                    }
                    uf.drawString((float)x, (float)y, t, new Color(color));
                    width += uf.getWidth(t);
                    x += uf.getWidth(t);
                }
            }
            x = sx;
            y += uf.getHeight(source) + 2;
            width = 0;
        }
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glScalef((float)guiScale, (float)guiScale, (float)1.0f);
    }

    @SubscribeEvent
    public void preRender(TickEvent.RenderTickEvent ev) {
        if (ev.phase == TickEvent.Phase.START) {
            Minecraft mc = Minecraft.getMinecraft();
            guiScale = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight).getScaleFactor();
            fontScale = 1.0f + (float)(guiScale - 2) / 2.0f;
        }
    }

    static {
        FMLCommonHandler.instance().bus().register((Object)new CustomFontRenderer());
    }
}

