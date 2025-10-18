package com.codeying.controller;

import cn.smartjavaai.common.entity.face.FaceSearchResult;
import com.codeying.utils.FaceRecUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

/** 登陆、注册、登出 */
@Controller
public class IndexController {

  @Autowired
  HttpServletRequest request;

  @Autowired
  ObjectMapper objectMapper;

  @PostConstruct
  public void init() {
    //初始化加载模型
    new Thread(() -> {
      FaceRecUtils.init();
    }).start();
  }

  /**
   * 访问人脸识别页面
   * @return
   */
  @RequestMapping("/")
  public String index() {
    return "hello";
  }

  /**
   * 人脸识别成功跳转
   * @return
   */
  @RequestMapping("success")
  public String success() throws JsonProcessingException {
    FaceSearchResult faceSearchResult = (FaceSearchResult) request.getSession().getAttribute("faceSearchResult");
    String jsonData = faceSearchResult.getMetadata();
    Map<String, String> map = objectMapper.readValue(jsonData, Map.class);
    System.out.println(map);
    return "success";
  }

  /**
   * 人脸识别接口
   * @param reqData
   * @return
   * @throws IOException
   */
  @ResponseBody
  @RequestMapping("/getInfoByFace")
  public FaceSearchResult getInfoByFace(@RequestBody Map<String,String> reqData) throws IOException {
    String base64String = reqData.get("base64String");
    // 去除Base64字符串中的前缀（如"data:image/png;base64,"）
    String base64Data = base64String;
    if (base64String.contains(",")) {
      base64Data = base64String.substring(base64String.indexOf(",") + 1);
    }
    // 解码Base64字符串
    byte[] imageBytes = Base64.getDecoder().decode(base64Data);
    // 将字节数组转换为BufferedImage
    try (ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes)) {
      BufferedImage image = ImageIO.read(bis);
      FaceSearchResult faceSearchResult = FaceRecUtils.queryFace(image);
      request.getSession().setAttribute("faceSearchResult", faceSearchResult);
      return faceSearchResult;
    }
  }

}

