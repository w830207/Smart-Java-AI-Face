package com.codeying.entity;

/**
 * 人脸搜索响应VO
 */
public class FaceSearchResponse {
    
    /** 是否找到匹配人脸 */
    private boolean found;
    
    /** 人脸ID */
    private String faceId;
    
    /** 用户ID */
    private String userId;
    
    /** 角色/身份 */
    private String role;
    
    /** 相似度（0-1之间） */
    private float similarity;
    
    /** 图片访问URL */
    private String imageUrl;
    
    /** 元数据JSON */
    private String metadata;
    
    public FaceSearchResponse() {
    }
    
    public FaceSearchResponse(boolean found, String faceId, String userId, String role, float similarity, String imageUrl, String metadata) {
        this.found = found;
        this.faceId = faceId;
        this.userId = userId;
        this.role = role;
        this.similarity = similarity;
        this.imageUrl = imageUrl;
        this.metadata = metadata;
    }
    
    // 静态工厂方法：未找到匹配
    public static FaceSearchResponse notFound() {
        return new FaceSearchResponse(false, null, null, null, 0.0f, null, null);
    }
    
    // Getters and Setters
    
    public boolean isFound() {
        return found;
    }
    
    public void setFound(boolean found) {
        this.found = found;
    }
    
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
    
    public float getSimilarity() {
        return similarity;
    }
    
    public void setSimilarity(float similarity) {
        this.similarity = similarity;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public String getMetadata() {
        return metadata;
    }
    
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }
    
    @Override
    public String toString() {
        return "FaceSearchResponse{" +
                "found=" + found +
                ", faceId='" + faceId + '\'' +
                ", userId='" + userId + '\'' +
                ", role='" + role + '\'' +
                ", similarity=" + similarity +
                ", imageUrl='" + imageUrl + '\'' +
                ", metadata='" + metadata + '\'' +
                '}';
    }
}

