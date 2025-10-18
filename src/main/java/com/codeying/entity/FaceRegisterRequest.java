package com.codeying.entity;

/**
 * 人脸注册请求VO
 */
public class FaceRegisterRequest {
    
    /** Base64编码的人脸图片 */
    private String base64Image;
    
    /** 用户ID */
    private String userId;
    
    /** 角色/身份 */
    private String role;
    
    public FaceRegisterRequest() {
    }
    
    public FaceRegisterRequest(String base64Image, String userId, String role) {
        this.base64Image = base64Image;
        this.userId = userId;
        this.role = role;
    }
    
    // Getters and Setters
    
    public String getBase64Image() {
        return base64Image;
    }
    
    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    @Override
    public String toString() {
        return "FaceRegisterRequest{" +
                "userId='" + userId + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

