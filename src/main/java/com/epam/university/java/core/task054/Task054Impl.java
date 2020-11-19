package com.epam.university.java.core.task054;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;

public class Task054Impl implements Task054 {
    BufferedImage resultImage;

    @Override
    public BufferedImage grayscaleFilter(String inputFilePath, String outputFilePath) {
        BufferedImage image = originalImage(inputFilePath);

        if (image != null) {
            resultImage = new BufferedImage(image.getWidth(),
                    image.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    Color color = new Color(image.getRGB(j, i), true);
                    int red = (int) (color.getRed() * 0.299);
                    int green = (int) (color.getGreen() * 0.587);
                    int blue = (int) (color.getBlue() * 0.114);

                    int rgb = red + green + blue;
                    Color newColor = new Color(rgb, rgb, rgb);
                    resultImage.setRGB(j, i, newColor.getRGB());
                }
            }

            try {
                ImageIO.write(resultImage, "jpg", new File(outputFilePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultImage;
    }

    @Override
    public BufferedImage sepiaFilter(String inputFilePath, String outputFilePath) {
        BufferedImage original = originalImage(inputFilePath);

        if (original != null) {
            resultImage = new BufferedImage(original.getWidth(),
                    original.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < original.getHeight(); i++) {
                for (int j = 0; j < original.getWidth(); j++) {
                    Color color = new Color(original.getRGB(j, i), true);
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();

                    int newRed = (int) ((0.393 * red) + (0.769 * green) + (0.189 * blue));
                    int newGreen = (int) ((0.349 * red) + (0.686 * green) + (0.168 * blue));
                    int newBlue = (int) ((0.272 * red) + (0.534 * green) + (0.131 * blue));

                    if (newRed > 255) {
                        newRed = 255;
                    }

                    if (newGreen > 255) {
                        newGreen = 255;
                    }

                    if (newBlue > 255) {
                        newBlue = 255;
                    }

                    Color newColor = new Color(newRed, newGreen, newBlue);
                    resultImage.setRGB(j, i, newColor.getRGB());
                }
            }

            try {
                ImageIO.write(resultImage, "jpg", new File(outputFilePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultImage;
    }

    @Override
    public BufferedImage reflectFilter(String inputFilePath, String outputFilePath) {
        BufferedImage original = originalImage(inputFilePath);

        if (original != null) {
            resultImage = new BufferedImage(original.getWidth(),
                    original.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            for (int j = 0; j < original.getHeight(); j++) {
                for (int i = 0, w = original.getWidth() - 1; i < original.getWidth(); i++, w--) {
                    int p = original.getRGB(i, j);
                    resultImage.setRGB(w, j, p);
                }
            }

            try {
                ImageIO.write(resultImage, "jpg", new File(outputFilePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultImage;
    }

    @Override
    public BufferedImage originalImage(String inputFilePath) {
        File file = new File(inputFilePath);
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public int getRed(int pixel) {
        ColorModel model = ColorModel.getRGBdefault();
        return model.getRed(pixel);
    }

    @Override
    public int getGreen(int pixel) {
        ColorModel model = ColorModel.getRGBdefault();
        return model.getGreen(pixel);
    }

    @Override
    public int getBlue(int pixel) {
        ColorModel model = ColorModel.getRGBdefault();
        return model.getBlue(pixel);
    }
}
