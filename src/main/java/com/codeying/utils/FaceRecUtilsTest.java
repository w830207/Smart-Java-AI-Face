package com.codeying.utils;

import cn.smartjavaai.common.entity.face.FaceSearchResult;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * 控制台应用程序主类
 * 提供人脸注册和查询功能
 */
public class FaceRecUtilsTest {

    public static void main(String[] args) {
        FaceRecUtils.init();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请选择操作：");
            System.out.println("1. 人脸注册");
            System.out.println("2. 人脸查询");
            System.out.println("3. 删除人脸");
            System.out.print("请输入选项（1-3）：");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    handleFaceRegistration(scanner);
                    break;
                case "2":
                    handleFaceSearch(scanner);
                    break;
                case "3":
                    handleFaceDelete(scanner);
                    break;
                default:
                    System.out.println("无效选项，请重新输入！");
            }
        }
    }

    /**
     * 处理人脸删除
     * @param scanner
     */
    private static void handleFaceDelete(Scanner scanner) {
        System.out.println("\n=== 删除人脸 ===");
        System.out.print("请输入人脸ID: ");
        String faceId = scanner.nextLine();
        FaceRecUtils.deleteFace(faceId);
    }

    /**
     * 处理人脸注册
     */
    private static void handleFaceRegistration(Scanner scanner) {
        System.out.println("\n=== 人脸注册 ===");
        
        try {
            // 获取图片路径
            System.out.print("请输入图片路径(输入1、2、3、4): ");
            String imagePath = scanner.nextLine();
            if(imagePath.equals("1")){
                imagePath = System.getProperty("user.dir") + "/src/main/resources/iu_1.jpg";
            }else if(imagePath.equals("2")){
                imagePath = System.getProperty("user.dir") + "/src/main/resources/iu_2.jpg";
            }else if(imagePath.equals("3")){
                imagePath = System.getProperty("user.dir") + "/src/main/resources/iu_3.jpg";
            }else if(imagePath.equals("4")){
                imagePath = System.getProperty("user.dir") + "/src/main/resources/img.png";
            }
            
            // 检查文件是否存在
            File imageFile = new File(imagePath);
            if (!imageFile.exists()) {
                System.out.println("错误：文件不存在！");
                return;
            }
            BufferedImage image = ImageIO.read(imageFile);
            // 获取用户信息
            System.out.print("请输入用户ID: ");
            String userId = scanner.nextLine();
            System.out.print("请输入角色: ");
            String role = scanner.nextLine();
            // 调用注册方法
            String faceId = FaceRecUtils.registerFace(image, userId, role);
            if (faceId != null) {
                System.out.println("人脸注册成功！人脸ID: " + faceId);
            } else {
                System.out.println("人脸注册失败！");
            }
        } catch (IOException e) {
            System.out.println("读取图片文件时出错: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("人脸注册过程中出错: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 处理人脸查询
     */
    private static void handleFaceSearch(Scanner scanner) {
        System.out.println("\n=== 人脸查询 ===");
        try {
            // 获取图片路径
            System.out.print("请输入图片路径(输入1、2、3): ");
            String imagePath = scanner.nextLine();
            if(imagePath.equals("1")){
                imagePath = System.getProperty("user.dir") + "/src/main/resources/iu_1.jpg";
            }else if(imagePath.equals("2")){
                imagePath = System.getProperty("user.dir") + "/src/main/resources/iu_2.jpg";
            }else if(imagePath.equals("3")){
                imagePath = System.getProperty("user.dir") + "/src/main/resources/iu_3.jpg";
            }else if(imagePath.equals("4")){
                imagePath = System.getProperty("user.dir") + "/src/main/resources/img.png";
            }
            // 检查文件是否存在
            File imageFile = new File(imagePath);
            if (!imageFile.exists()) {
                System.out.println("错误：文件不存在！");
                return;
            }
            BufferedImage image = ImageIO.read(imageFile);
            FaceSearchResult result = FaceRecUtils.queryFace(image);
            
            if (result != null) {
                System.out.println("人脸识别结果！");
                System.out.println("人脸ID: " + result.getId());
                System.out.println("相似度: " + result.getSimilarity());
                System.out.println("元数据: " + result.getMetadata());
            } else {
                System.out.println("未找到匹配的人脸信息！");
            }
            
        } catch (IOException e) {
            System.out.println("读取图片文件时出错: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("人脸查询过程中出错: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
