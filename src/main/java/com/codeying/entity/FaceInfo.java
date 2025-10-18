package com.codeying.entity;

/**
 * 人脸信息实体类
 */
public class FaceInfo {
    
    /** 人脸ID（系统生成） */
    private String faceId;
    
    /** 用户ID */
    private String userId;
    
    /** 角色/身份 */
    private String role;
    
    /** 图片文件名 */
    private String imageFileName;
    
    /** 图片访问URL */
    private String imageUrl;
    
    /** 注册时间戳 */
    private Long registerTime;
    
    /** 元数据JSON */
    private String metadata;
    
    public FaceInfo() {
    }
    
    public FaceInfo(String faceId, String userId, String role, String imageFileName, String imageUrl, Long registerTime, String metadata) {
        this.faceId = faceId;
        this.userId = userId;
        this.role = role;
        this.imageFileName = imageFileName;
        this.imageUrl = imageUrl;
        this.registerTime = registerTime;
        this.metadata = metadata;
    }
    
    // Getters and Setters
    
    public String getFaceId() {
        return faceId;
    }
    
    public void setFaceId(String faceId) {
        this.faceId = faceId;
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
    
    public String getImageFileName() {
        return imageFileName;
    }
    
    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public Long getRegisterTime() {
        return registerTime;
    }
    
    public void setRegisterTime(Long registerTime) {
        this.registerTime = registerTime;
    }
    
    public String getMetadata() {
        return metadata;
    }
    
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }
    
    @Override
    public String toString() {
        return "FaceInfo{" +
                "faceId='" + faceId + '\'' +
                ", userId='" + userId + '\'' +
                ", role='" + role + '\'' +
                ", imageFileName='" + imageFileName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", registerTime=" + registerTime +
                ", metadata='" + metadata + '\'' +
                '}';
    }
}

