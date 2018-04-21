package com.secondeal.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageUtil {
    public static Icon getFixedIcon(String filePath, int width, int height) throws Exception{
        File f = new File(filePath);

        BufferedImage bi = ImageIO.read(f);

        double wRatio = (new Integer(width)).doubleValue() / bi.getWidth(); //宽度的比例

        double hRatio = (new Integer(height)).doubleValue() / bi.getHeight(); //高度的比例

        Image image = bi.getScaledInstance(width,height,Image.SCALE_SMOOTH); //设置图像的缩放大小

        AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(wRatio,hRatio),null);   //设置图像的缩放比例

        image = op.filter(bi,null);

        int lastLength = filePath.lastIndexOf(".");
        String subFilePath = filePath.substring(0,lastLength);  //得到图片输出路径
        String fileType = filePath.substring(lastLength);  //图片类型
        File zoomFile = new File(subFilePath + fileType);

        Icon ret = null;
        try
        {
            ImageIO.write((BufferedImage)image, "jpg", zoomFile);
            ret = new ImageIcon(zoomFile.getPath());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ret;
    }
}
