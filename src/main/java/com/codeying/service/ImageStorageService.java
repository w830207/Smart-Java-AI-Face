package com.codeying.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * 图片存储服务
 * 负责人脸图片的保存、加载和删除
 */
@Service
public class ImageStorageService {

    private static final Logger log = LoggerFactory.getLogger(ImageStorageService.class);
    
    // 图片存储根目录
    private static final String STORAGE_DIR = "face-imgs";
    
    public ImageStorageService() {
        // 初始化存储目录
        initStorageDirectory();
    }
    
    /**
     * 初始化存储目录
     */
    private void initStorageDirectory() {
        File dir = new File(STORAGE_DIR);
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (created) {
                log.info("创建图片存储目录成功: {}", STORAGE_DIR);
            } else {
                log.error("创建图片存储目录失败: {}", STORAGE_DIR);
            }
        }
    }
    
    /**
     * 保存Base64图片到文件系统
     * @param base64Image Base64编码的图片
     * @param userId 用户ID
     * @return 保存的文件名
     */
    public String saveImage(String base64Image, String userId) throws IOException {
        // 去除Base64前缀
        String base64Data = base64Image;
        if (base64Image.contains(",")) {
            base64Data = base64Image.substring(base64Image.indexOf(",") + 1);
        }
        
        // 解码Base64
        byte[] imageBytes = Base64.getDecoder().decode(base64Data);
        
        // 生成文件名：用户ID_时间戳.jpg
        String fileName = userId + "_" + System.currentTimeMillis() + ".jpg";
        String filePath = STORAGE_DIR + File.separator + fileName;
        
        // 将字节数组转换为BufferedImage
        try (ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes)) {
            BufferedImage image = ImageIO.read(bis);
            
            // 保存图片
            File outputFile = new File(filePath);
            ImageIO.write(image, "jpg", outputFile);
            
            log.info("图片保存成功: {}", filePath);
            return fileName;
        }
    }
    
    /**
     * 保存BufferedImage到文件系统
     * @param image BufferedImage对象
     * @param userId 用户ID
     * @return 保存的文件名
     */
    public String saveBufferedImage(BufferedImage image, String userId) throws IOException {
        // 生成文件名：用户ID_时间戳.jpg
        String fileName = userId + "_" + System.currentTimeMillis() + ".jpg";
        String filePath = STORAGE_DIR + File.separator + fileName;
        
        // 保存图片
        File outputFile = new File(filePath);
        ImageIO.write(image, "jpg", outputFile);
        
        log.info("图片保存成功: {}", filePath);
        return fileName;
    }
    
    /**
     * 根据文件名加载图片
     * @param fileName 文件名
     * @return BufferedImage对象
     */
    public BufferedImage loadImage(String fileName) throws IOException {
        String filePath = STORAGE_DIR + File.separator + fileName;
        File file = new File(filePath);
        
        if (!file.exists()) {
            throw new IOException("图片文件不存在: " + filePath);
        }
        
        return ImageIO.read(file);
    }
    
    /**
     * 删除图片文件
     * @param fileName 文件名
     * @return 是否删除成功
     */
    public boolean deleteImage(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            return false;
        }
        
        try {
            String filePath = STORAGE_DIR + File.separator + fileName;
            Path path = Paths.get(filePath);
            boolean deleted = Files.deleteIfExists(path);
            
            if (deleted) {
                log.info("图片删除成功: {}", filePath);
            } else {
                log.warn("图片文件不存在或已删除: {}", filePath);
            }
            
            return deleted;
        } catch (IOException e) {
            log.error("删除图片失败: {}", fileName, e);
            return false;
        }
    }
    
    /**
     * 检查图片文件是否存在
     * @param fileName 文件名
     * @return 是否存在
     */
    public boolean imageExists(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            return false;
        }
        
        String filePath = STORAGE_DIR + File.separator + fileName;
        File file = new File(filePath);
        return file.exists();
    }
    
    /**
     * 获取图片访问路径（相对路径）
     * @param fileName 文件名
     * @return 访问路径
     */
    public String getImageUrl(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            return null;
        }
        return "/face-imgs/" + fileName;
    }
}

