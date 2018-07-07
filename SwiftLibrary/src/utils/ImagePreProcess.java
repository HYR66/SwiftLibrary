package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 * ImagePreProcess:正方验证码识别模组
 * 
 * @author 蜂鸟Swift
 *
 */
public class ImagePreProcess {
	private static Map<BufferedImage, String> trainMap = null;

	// 验证码识别入口
	public static String startOCR() throws Exception {
		File gifFile = new File(info.impPath.code_temp + "//codeNUPT.gif");
		if (gifFile.exists()) {
			gifFile.delete();
		}
		String resultStr = getAllOcr(info.impPath.code_temp + "//codeNUPT.png");
		return resultStr;
	}

	public static Map<BufferedImage, String> loadTrainData() throws Exception {
		if (trainMap == null) {
			Map<BufferedImage, String> map = new HashMap<BufferedImage, String>();
			File dir = new File(info.impPath.code_train);
			File[] files = dir.listFiles();
			for (File file : files) {
				map.put(ImageIO.read(file), file.getName().charAt(0) + "");
			}
			trainMap = map;
		}
		return trainMap;
	}

	// 获得所有验证码图片路径
	public static String getAllOcr(String file) throws Exception {
		BufferedImage img = removeBackgroud(file);
		List<BufferedImage> listImg = splitImage(img);
		Map<BufferedImage, String> map = loadTrainData();
		String result = "";
		for (BufferedImage bIMG : listImg) {
			result += getSingleCharOcr(bIMG, map);
		}
		return result;
	}

	// 去除验证码背景并二值化
	public static BufferedImage removeBackgroud(String picFile) throws Exception {
		BufferedImage img = ImageIO.read(new File(picFile));
		int width = img.getWidth();
		int height = img.getHeight();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (isBlue(img.getRGB(x, y)) == 1) {
					img.setRGB(x, y, Color.BLACK.getRGB());
				} else {
					img.setRGB(x, y, Color.WHITE.getRGB());
				}
			}
		}
		return img;
	}

	public static int isBlue(int colorInt) {
		Color color = new Color(colorInt);
		int rgb = color.getRed() + color.getGreen() + color.getBlue();
		if (rgb == 153) {
			return 1;
		}
		return 0;
	}

	public static int isBlack(int colorInt) {
		Color color = new Color(colorInt);
		if (color.getRed() + color.getGreen() + color.getBlue() <= 100) {
			return 1;
		}
		return 0;
	}

	public static int isWhite(int colorInt) {
		Color color = new Color(colorInt);
		if (color.getRed() + color.getGreen() + color.getBlue() > 600) {
			return 1;
		}
		return 0;
	}

	// 切割验证码图片
	public static List<BufferedImage> splitImage(BufferedImage img) throws Exception {
		List<BufferedImage> subImgs = new ArrayList<BufferedImage>();
		subImgs.add(img.getSubimage(5, 0, 12, 23));
		subImgs.add(img.getSubimage(17, 0, 12, 23));
		subImgs.add(img.getSubimage(29, 0, 12, 23));
		subImgs.add(img.getSubimage(41, 0, 12, 23));
		return subImgs;
	}

	// 识别切割的单个字符
	public static String getSingleCharOcr(BufferedImage img, Map<BufferedImage, String> map) {
		String result = "#";
		int width = img.getWidth();
		int height = img.getHeight();
		int min = width * height;
		for (BufferedImage bi : map.keySet()) {
			int count = 0;
			if (Math.abs(bi.getWidth() - width) > 2)
				continue;
			int widthmin = width < bi.getWidth() ? width : bi.getWidth();
			int heightmin = height < bi.getHeight() ? height : bi.getHeight();
			Label1: for (int x = 0; x < widthmin; ++x) {
				for (int y = 0; y < heightmin; ++y) {
					if (isBlack(img.getRGB(x, y)) != isBlack(bi.getRGB(x, y))) {
						count++;
						if (count >= min)
							break Label1;
					}
				}
			}
			if (count < min) {
				min = count;
				result = map.get(bi);
			}
		}
		return result;
	}
}
