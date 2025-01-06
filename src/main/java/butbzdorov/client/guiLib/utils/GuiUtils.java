package butbzdorov.client.guiLib.utils;

import butbzdorov.client.guiLib.utils.CustomFont.CustomFont;
import butbzdorov.client.guiLib.utils.CustomFont.CustomFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;


public class GuiUtils {

    private static final Tessellator tessellator;
    private static final RenderItem itemRenderer;
    private static final FontRenderer fontRenderer;

    private static final Map<ResourceLocation, SimpleTexture> textureCache = new HashMap<>();


    public static class RenderText {
        public static <T> void drawText(double posX, double posY, int color, T text, CustomFont font) {
            CustomFontRenderer.drawStringWithMaxWidth(String.valueOf(text), posX, posY, -1, color, font);
        }


        public static <T> void drawCenteredText(double posX, double posY, int color, T text, CustomFont font) {
            CustomFontRenderer.drawStringWithMaxWidth(String.valueOf(text), posX - ((double) CustomFontRenderer.getStringWidth(font, String.valueOf(text)) / 2), posY - (CustomFontRenderer.getStringHeight(font, String.valueOf(text), -1) / 2), -1,  color, font);
        }

        public static <T> void drawCenteredText(double posX, double posY, int color, String text, CustomFont font) {
            CustomFontRenderer.drawStringWithMaxWidth(text, posX - ((double) CustomFontRenderer.getStringWidth(font, String.valueOf(text)) / 2), posY - (CustomFontRenderer.getStringHeight(font, String.valueOf(text), -1) / 2), -1,  color, font);
        }

        public static <T> void drawSplitText(double posX, double posY, int wrapWidth, int color, T text, CustomFont font) {
            CustomFontRenderer.drawStringWithMaxWidth(String.valueOf(text), posX, posY, wrapWidth, color, font);
        }

        public static <T> void drawTextWithAlpha(double posX, double posY, double alpha, int color, T text, CustomFont font) {
            int alphaF = (int) (234 * alpha);
            if (alphaF > 234) alphaF = 234;
            if (alphaF < 0) alphaF = 0;
            glPushMatrix();
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            CustomFontRenderer.drawStringWithMaxWidth(String.valueOf(text), posX, posY, -1, color | alphaF + 22 << 24, font);
            glDisable(GL_BLEND);
            glPopMatrix();
        }

        public static <T> double getTextWidth(T text, CustomFont font) {
            return CustomFontRenderer.getStringWidth(font, String.valueOf(text));
        }

        public static <T> double getTextHeight(T text, CustomFont font) {
            return CustomFontRenderer.getStringHeight(font, String.valueOf(text), -1);
        }

        public static <T> double getTextHeight(T text, CustomFont font, int wrapWidth) {
            return CustomFontRenderer.getStringHeight(font, String.valueOf(text), wrapWidth);
        }

    }









    public static void drawTextures(int posX, int posY, int width, int height, ResourceLocation image, float alpha) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        Tessellator tessellator = Tessellator.instance;

        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glTranslatef((float) posX, (float) posY, 0.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);

        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        tessellator.addVertexWithUV(0.0D, (double) height, 0.0D, 0.0D, 1.0D);
        tessellator.addVertexWithUV((double) width, (double) height, 0.0D, 1.0D, 1.0D);
        tessellator.addVertexWithUV((double) width, 0.0D, 0.0D, 1.0D, 0.0D);
        tessellator.draw();

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }



    public static void drawLine(double startX, double startY, double endX, double endY, double lineWidth, int zLevel, int color, double alpha) {
        glPushMatrix();
        glDisable(3553);
        glEnable(3042);
        glLineWidth((float) lineWidth);
        GuiUtils.RenderColor.renderColor(color, alpha);
        tessellator.startDrawing(1);
        tessellator.addVertex(startX , startY, zLevel);
        tessellator.addVertex(endX, endY, zLevel);
        tessellator.draw();
        GuiUtils.RenderColor.renderColor(0xFFFFFF, 1);
        glDisable(3042);
        glEnable(3553);
        glPopMatrix();
    }

    public static void drawTracing(float posX, float posY, float sizeX, float sizeY, float sizeWidth) {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor4f(1f, 1f, 1f, 1F);
        GL11.glLineWidth(sizeWidth);
        GL11.glBegin(GL11.GL_LINE_STRIP);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        GL11.glVertex2d(posX+sizeX, posY);
        GL11.glVertex2d(posX+sizeX, posY+sizeY);
        GL11.glEnd();

        GL11.glBegin(GL11.GL_LINE_STRIP);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        GL11.glVertex2d(posX, posY);
        GL11.glVertex2d(posX, posY+sizeY);
        GL11.glEnd();

        GL11.glBegin(GL11.GL_LINE_STRIP);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        GL11.glVertex2d(posX, posY);
        GL11.glVertex2d(posX+sizeX, posY);
        GL11.glEnd();

        GL11.glBegin(GL11.GL_LINE_STRIP);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        GL11.glVertex2d(posX, posY+sizeY);
        GL11.glVertex2d(posX+sizeX, posY+sizeY);
        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPopMatrix();
    }

    public static void drawRectS(double posX, double posY, double endX, double endY, org.newdawn.slick.Color color, double alpha) {
        glPushMatrix();
        glDisable(3553);
        glEnable(3042);
        glDisable(3008);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        glShadeModel(7425);
        GuiUtils.RenderColor.renderColor(color, alpha);
        tessellator.startDrawingQuads();
        tessellator.addVertex(posX, posY + endY, 0.0);
        tessellator.addVertex(posX + endX, posY + endY, 0.0);
        tessellator.addVertex(posX + endX, posY, 0.0);
        tessellator.addVertex(posX, posY, 0.0);
        tessellator.draw();
        GuiUtils.RenderColor.renderColor(0xFFFFFF, 1);
        glShadeModel(7424);
        glDisable(3042);
        glEnable(3008);
        glEnable(3553);
        glPopMatrix();
    }

    public static void drawGradientLine(double startX, double startY, double endX, double endY, double lineWidth, int zLevel, int color1, int color2, double alpha) {
        float f1 = (float)(color1 >> 16 & 255) / 255.0f;
        float f2 = (float)(color1 >> 8 & 255) / 255.0f;
        float f3 = (float)(color1 & 255) / 255.0f;
        float f4 = (float)(color2 >> 16 & 255) / 255.0f;
        float f5 = (float)(color2 >> 8 & 255) / 255.0f;
        float f6 = (float)(color2 & 255) / 255.0f;
        glPushMatrix();
        glDisable(3553);
        glEnable(3042);
        glLineWidth((float) lineWidth);
        tessellator.startDrawing(1);
        tessellator.setColorRGBA_F(f1, f2, f3, (float)alpha);
        tessellator.addVertex(startX, startY, zLevel);
        tessellator.setColorRGBA_F(f4, f5, f6, (float)alpha);
        tessellator.addVertex(endX, endY, zLevel);
        tessellator.draw();
        glDisable(3042);
        glEnable(3553);
        glPopMatrix();
    }

    public static void drawRectS(double posX, double posY, double endX, double endY, int color, double alpha) {
        glPushMatrix();
        glDisable(3553);
        glEnable(3042);
        glDisable(3008);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        glShadeModel(7425);
        GuiUtils.RenderColor.renderColor(color, alpha);
        tessellator.startDrawingQuads();
        tessellator.addVertex(posX, posY + endY, 0.0);
        tessellator.addVertex(posX + endX, posY + endY, 0.0);
        tessellator.addVertex(posX + endX, posY, 0.0);
        tessellator.addVertex(posX, posY, 0.0);
        tessellator.draw();
        GuiUtils.RenderColor.renderColor(0xFFFFFF, 1);
        glShadeModel(7424);
        glDisable(3042);
        glEnable(3008);
        glEnable(3553);
        glPopMatrix();
    }


    public static void drawGradientRectS(double posX, double posY, double endX, double endY, int color1, int color2, double alpha1, double alpha2) {
        float f1 = (float)(color1 >> 16 & 255) / 255.0f;
        float f2 = (float)(color1 >> 8 & 255) / 255.0f;
        float f3 = (float)(color1 & 255) / 255.0f;
        float f4 = (float)(color2 >> 16 & 255) / 255.0f;
        float f5 = (float)(color2 >> 8 & 255) / 255.0f;
        float f6 = (float)(color2 & 255) / 255.0f;
        glPushMatrix();
        glDisable(3553);
        glEnable(3042);
        glDisable(3008);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        glShadeModel(7425);
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_F(f1, f2, f3, (float)alpha1);
        tessellator.addVertex(posX, posY + endY, 0.0);
        tessellator.addVertex(posX + endX, posY + endY, 0.0);
        tessellator.setColorRGBA_F(f4, f5, f6, (float) alpha2);
        tessellator.addVertex(posX + endX, posY, 0.0);
        tessellator.addVertex(posX, posY, 0.0);
        tessellator.draw();
        glShadeModel(7424);
        glDisable(3042);
        glEnable(3008);
        glEnable(3553);
        glPopMatrix();
    }



    public static void drawGradientRectS(double posX, double posY, double endX, double endY, int color1, int color2, double alpha) {
        float f1 = (float)(color1 >> 16 & 255) / 255.0f;
        float f2 = (float)(color1 >> 8 & 255) / 255.0f;
        float f3 = (float)(color1 & 255) / 255.0f;
        float f4 = (float)(color2 >> 16 & 255) / 255.0f;
        float f5 = (float)(color2 >> 8 & 255) / 255.0f;
        float f6 = (float)(color2 & 255) / 255.0f;
        glPushMatrix();
        glDisable(3553);
        glEnable(3042);
        glDisable(3008);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        glShadeModel(7425);
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_F(f1, f2, f3, (float)alpha);
        tessellator.addVertex(posX, posY + endY, 0.0);
        tessellator.addVertex(posX + endX, posY + endY, 0.0);
        tessellator.setColorRGBA_F(f4, f5, f6, (float) alpha);
        tessellator.addVertex(posX + endX, posY, 0.0);
        tessellator.addVertex(posX, posY, 0.0);
        tessellator.draw();
        glShadeModel(7424);
        glDisable(3042);
        glEnable(3008);
        glEnable(3553);
        glPopMatrix();
    }

    public static void drawCircle(double posX, double posY, double radius, int color, double alpha) {
        glEnable(3042);
        glDisable(3553);
        glEnable(2881);
        glBlendFunc(770, 771);
        glHint(3155, 4354);
        GuiUtils.RenderColor.renderColor(color, alpha);
        tessellator.startDrawing(9);

        for(int i = 0; i <= 360; ++i) {
            double x = Math.sin((double)i * 3.141526D / 180.0D) * radius;
            double y = Math.cos((double)i * 3.141526D / 180.0D) * radius;
            tessellator.addVertex(posX + x, posY + y, 0.0D);
        }

        tessellator.draw();
        glDisable(2881);
        glEnable(3553);
        glDisable(3042);
    }

    public static void drawGradientCircle(double posX, double posY, double radius, int color1, int color2, double alpha) {
        float f = (float)(color1 >> 24 & 255) / 255.0F;
        float f1 = (float)(color1 >> 16 & 255) / 255.0F;
        float f2 = (float)(color1 >> 8 & 255) / 255.0F;
        float f3 = (float)(color2 >> 24 & 255) / 255.0F;
        float f4 = (float)(color2 >> 16 & 255) / 255.0F;
        float f5 = (float)(color2 >> 8 & 255) / 255.0F;
        glEnable(3042);
        glDisable(3553);
        glEnable(2881);
        glBlendFunc(770, 771);
        glHint(3155, 4354);
        glShadeModel(7425);
        tessellator.startDrawing(9);
        tessellator.setColorRGBA_F(f, f1, f2, (float) alpha);
        tessellator.addVertex(posX, posY, 0.0D);
        tessellator.setColorRGBA_F(f3, f4, f5, (float) alpha);

        for(int i = 1; i <= 361; ++i) {
            double x = Math.sin((double)i * 3.141526D / 180.0D) * radius;
            double y = Math.cos((double)i * 3.141526D / 180.0D) * radius;
            tessellator.addVertex(posX + x, posY + y, 0.0D);
        }

        tessellator.draw();
        glShadeModel(7424);
        glDisable(2881);
        glEnable(3553);
        glDisable(3042);
    }

    public static void drawCircleLine(double posX, double posY, double radius, int color, double alpha) {
        glEnable(3042);
        glDisable(3553);
        glEnable(2881);
        glBlendFunc(770, 771);
        glHint(3155, 4354);
        GuiUtils.RenderColor.renderColor(color, alpha);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawing(2);

        for(int i = 0; i <= 360; ++i) {
            double x = Math.sin(i * Math.PI / 180.0D) * radius;
            double y = Math.cos(i * Math.PI / 180.0D) * radius;
            tessellator.addVertex(posX + x, posY + y, 0.0D);
        }

        tessellator.draw();
        glDisable(2881);
        glEnable(3553);
        glDisable(3042);
    }

    public static void drawWedge(double posX, double posY, double rotation, double radius, double size, int color, double alpha) {
        glEnable(3042);
        glDisable(3553);
        glEnable(2881);
        glBlendFunc(770, 771);
        glHint(3155, 4354);
        glTranslated(posX, posY, 0.0D);
        glRotated(rotation, 0.0D, 0.0D, 1.0D);
        GuiUtils.RenderColor.renderColor(color, alpha);
        tessellator.startDrawing(9);
        tessellator.addVertex(0.0D, 0.0D, 0.0D);

        for(int i = 0; (double)i <= size; ++i) {
            double x = Math.sin((double)i * 3.141526D / 180.0D) * radius;
            double y = Math.cos((double)i * 3.141526D / 180.0D) * radius;
            tessellator.addVertex(x, y, 0.0D);
        }

        tessellator.draw();
        glRotated(-rotation, 0.0D, 0.0D, 1.0D);
        glTranslated(-posX, -posY, 0.0D);
        glDisable(2881);
        glEnable(3553);
        glDisable(3042);
    }

    public static void glScissor(int posX, int posY, int endX, int endY, boolean test){
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution resolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int scale = resolution.getScaleFactor();

        if (test) GuiUtils.drawRectS(posX, posY, endX, endY, 0xFFFFFF, 0.3);

        int scissorWidth = endX * scale;
        int scissorHeight = endY * scale;
        int scissorX = posX * scale;
        int scissorY = mc.displayHeight - scissorHeight - (posY * scale);
        glEnable(3089);
        GL11.glScissor(scissorX, scissorY, scissorWidth, scissorHeight);
    }

    public static void drawItemStackIntoGUI(ItemStack itemstack, int posX, int posY, double scale) {
        if (itemstack != null) {
            glPushMatrix();
            glScaled(scale, scale, 1);
            itemRenderer.renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().getTextureManager(), itemstack, (int) (posX / scale), (int) (posY / scale));
            glDisable(2896);
            glPopMatrix();
        } else return;
    }

    public static boolean isInBox(double posX, double posY, double endX, double endY, double checkX, double checkY) {
        return checkX >= posX && checkY >= posY && checkX <= posX + endX && checkY <= posY + endY;
    }

    public static class RenderColor {
        public static void renderColor(int color, double alpha) {
            Color color1 = Color.decode((new StringBuilder()).append("").append(color).toString());
            float red = (float)color1.getRed() / 255F;
            float green = (float)color1.getGreen() / 255F;
            float blue = (float)color1.getBlue() / 255F;
            glColor4f(red, green, blue, (float)alpha);
        }

        public static void renderColor(org.newdawn.slick.Color color, double alpha) {
            float red = (float) color.getRed() / 255F;
            float green = (float) color.getGreen() / 255F;
            float blue = (float) color.getBlue() / 255F;
            glColor4f(red, green, blue, (float)alpha);
        }

        public static void renderColor(Color color, double alpha) {
            float red = (float) color.getRed() / 255F;
            float green = (float) color.getGreen() / 255F;
            float blue = (float) color.getBlue() / 255F;
            glColor4f(red, green, blue, (float)alpha);
        }



        public static int getRGBA(int r, int g, int b, int a) {
            return (r & 255) << 24 | (g & 255) << 16 | (b & 255) << 8 | a & 255;
        }

        public static int getRGB(int r, int g, int b) {
            return (r & 255) << 16 | (g & 255) << 8 | b & 255;
        }

        public static int toHex(int r, int g, int b) {
            int h = 0;
            int hex = h | r << 16;
            hex |= g << 8;
            hex |= b;
            return hex;
        }
    }



    public static double getDistanceAtoB(double startX, double startY, double endX, double endY) {
        double x = startX - endX;
        double y = startY - endY;
        return Math.sqrt((x * x) + (y * y));
    }

    public static void disableGlScissor() {
        GL11.glDisable((int)3089);
    }

    public static void drawImage(ResourceLocation image, double posX, double posY, double endX, double endY, float alpha) {

        if (image == null) return;

            Minecraft.getMinecraft().renderEngine.bindTexture(image);
            glPushMatrix();
            glEnable(3042);
            glEnable(2832);
            glHint(3153, 4353);
            glScaled(1, 1, 1);
            GuiUtils.RenderColor.renderColor(0xFFFFFF, alpha);
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(posX, posY + endY, 0.0, 0.0, 1.0);
            tessellator.addVertexWithUV(posX + endX, posY + endY, 0.0, 1.0, 1.0);
            tessellator.addVertexWithUV(posX + endX, posY, 0.0, 1.0, 0.0);
            tessellator.addVertexWithUV(posX, posY, 0.0, 0.0, 0.0);
            tessellator.draw();
            glDisable(3042);
            glDisable(2832);
            glPopMatrix();
    }

    public static void drawImage(ResourceLocation image, double posX, double posY, double endX, double endY, float alpha, double rotationAngle) {
        if (image == null) return;

        Minecraft.getMinecraft().renderEngine.bindTexture(image);
        glPushMatrix();
        glEnable(GL_BLEND);
        glEnable(GL_ALPHA_TEST);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);

        // Перемещение к точке вращения
        glTranslated(posX + endX / 2, posY + endY / 2, 0);
        // Вращение
        glRotated(rotationAngle, 0, 0, 1);
        // Возвращение к исходной позиции
        glTranslated(-(posX + endX / 2), -(posY + endY / 2), 0);

        GuiUtils.RenderColor.renderColor(0xFFFFFF, alpha);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(posX, posY + endY, 0.0, 0.0, 1.0);
        tessellator.addVertexWithUV(posX + endX, posY + endY, 0.0, 1.0, 1.0);
        tessellator.addVertexWithUV(posX + endX, posY, 0.0, 1.0, 0.0);
        tessellator.addVertexWithUV(posX, posY, 0.0, 0.0, 0.0);
        tessellator.draw();

        glDisable(GL_BLEND);
        glDisable(GL_ALPHA_TEST);
        glPopMatrix();
    }


    public static void drawCutImage(ResourceLocation image, double posX, double posY, double endX, double endY, double texEndX) {
        if (image == null) return;

        Minecraft.getMinecraft().renderEngine.bindTexture(image);
        glPushMatrix();
        glEnable(3042);
        glEnable(2832);
        glHint(3153, 4353);
        glScaled(1, 1, 1);
        GuiUtils.RenderColor.renderColor(0xFFFFFF, 1);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(posX / 1, (posY / 1) + endY, 0.0, 0.0, 1.0);
        tessellator.addVertexWithUV((posX / 1) + endX, (posY / 1) + endY, 0.0, texEndX, 1.0);
        tessellator.addVertexWithUV((posX / 1) + endX, posY / 1, 0.0, texEndX, 0.0);
        tessellator.addVertexWithUV(posX / 1, posY / 1, 0.0, 0.0, 0.0);
        tessellator.draw();
        glDisable(3042);
        glDisable(2832);
        glPopMatrix();

    }

    public static void drawImageColor(ResourceLocation image, double posX, double posY, double endX, double endY, int color, double scale) {
        if (image == null) return;
        Minecraft.getMinecraft().renderEngine.bindTexture(image);
        glPushMatrix();
        glEnable(3042);
        glEnable(2832);
        glHint(3153, 4353);
        glScaled(scale, scale, scale);
        GuiUtils.RenderColor.renderColor(color, 1);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(posX / scale, (posY / scale) + endY, 0.0, 0.0, 1.0);
        tessellator.addVertexWithUV((posX / scale) + endX, (posY / scale) + endY, 0.0, 1.0, 1.0);
        tessellator.addVertexWithUV((posX / scale) + endX, posY / scale, 0.0, 1.0, 0.0);
        tessellator.addVertexWithUV(posX / scale, posY / scale, 0.0, 0.0, 0.0);
        tessellator.draw();
        glDisable(3042);
        glDisable(2832);
        glPopMatrix();
    }

    public static void drawImageColor(ResourceLocation image, double posX, double posY, double endX, double endY, Color color) {
        if (image == null) return;
        Minecraft.getMinecraft().renderEngine.bindTexture(image);
        glPushMatrix();
        glEnable(3042);
        glEnable(2832);
        glHint(3153, 4353);
        glScaled(1, 1, 1);
        GuiUtils.RenderColor.renderColor(color, 1);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(posX / 1, (posY / 1) + endY, 0.0, 0.0, 1.0);
        tessellator.addVertexWithUV((posX / 1) + endX, (posY / 1) + endY, 0.0, 1.0, 1.0);
        tessellator.addVertexWithUV((posX / 1) + endX, posY / 1, 0.0, 1.0, 0.0);
        tessellator.addVertexWithUV(posX / 1, posY / 1, 0.0, 0.0, 0.0);
        tessellator.draw();
        glDisable(3042);
        glDisable(2832);
        glPopMatrix();
    }

    public static void drawImageColorWithAlpha(ResourceLocation image, double posX, double posY, double endX, double endY, int color, double alpha, double scale) {
        if (image == null) return;

        Minecraft.getMinecraft().renderEngine.bindTexture(image);
        glPushMatrix();
        glEnable(3042);
        glEnable(2832);
        glHint(3153, 4353);
        glScaled(scale, scale, scale);
        GuiUtils.RenderColor.renderColor(color, alpha);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(posX / scale, (posY / scale) + endY, 0.0, 0.0, 1.0);
        tessellator.addVertexWithUV((posX / scale) + endX, (posY / scale) + endY, 0.0, 1.0, 1.0);
        tessellator.addVertexWithUV((posX / scale) + endX, posY / scale, 0.0, 1.0, 0.0);
        tessellator.addVertexWithUV(posX / scale, posY / scale, 0.0, 0.0, 0.0);
        tessellator.draw();
        glDisable(3042);
        glDisable(2832);
        glPopMatrix();
    }

    public static void drawImageColorWithAlpha(ResourceLocation image, double posX, double posY, double endX, double endY, int color, double alpha) {
        if (image == null) return;
        float scale = 1;


        Minecraft.getMinecraft().renderEngine.bindTexture(image);
        glPushMatrix();
        glEnable(3042);
        glEnable(2832);
        glHint(3153, 4353);
        glScaled(scale, scale, scale);
        GuiUtils.RenderColor.renderColor(color, alpha);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(posX / scale, (posY / scale) + endY, 0.0, 0.0, 1.0);
        tessellator.addVertexWithUV((posX / scale) + endX, (posY / scale) + endY, 0.0, 1.0, 1.0);
        tessellator.addVertexWithUV((posX / scale) + endX, posY / scale, 0.0, 1.0, 0.0);
        tessellator.addVertexWithUV(posX / scale, posY / scale, 0.0, 0.0, 0.0);
        tessellator.draw();
        glDisable(3042);
        glDisable(2832);
        glPopMatrix();
    }


    private Dimension getTextureSize(ResourceLocation texture){
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        int width = GL11.glGetTexLevelParameteri(GL11.GL_TEXTURE_2D, 0, GL11.GL_TEXTURE_WIDTH);
        int height = GL11.glGetTexLevelParameteri(GL11.GL_TEXTURE_2D, 0, GL11.GL_TEXTURE_HEIGHT);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        return new Dimension(width, height);
    }

    static {
        tessellator = Tessellator.instance;
        itemRenderer = new RenderItem();
        fontRenderer = Minecraft.getMinecraft().fontRenderer;
    }

}
