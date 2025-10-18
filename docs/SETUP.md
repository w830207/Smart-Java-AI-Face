# 📦 详细配置指南

本文档提供项目的详细配置步骤，帮助你快速部署人脸识别管理系统。

## 📋 目录

- [环境准备](#环境准备)
- [下载项目](#下载项目)
- [下载模型文件](#下载模型文件)
- [配置路径](#配置路径)
- [启动应用](#启动应用)
- [验证部署](#验证部署)
- [故障排查](#故障排查)

## 🔧 环境准备

### 1. 安装 JDK 21

#### Windows

1. 访问 [Oracle JDK 下载页](https://www.oracle.com/java/technologies/downloads/#java21)
2. 下载 Windows 安装包
3. 运行安装程序
4. 配置环境变量 `JAVA_HOME`

验证安装：
```bash
java -version
# 应该显示 java version "21.x.x"
```

#### Linux

```bash
# Ubuntu/Debian
sudo apt update
sudo apt install openjdk-21-jdk

# CentOS/RHEL
sudo yum install java-21-openjdk-devel
```

#### macOS

```bash
# 使用 Homebrew
brew install openjdk@21

# 配置环境变量
echo 'export PATH="/usr/local/opt/openjdk@21/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```

### 2. 安装 Maven

#### Windows

1. 下载 [Maven](https://maven.apache.org/download.cgi)
2. 解压到目录，如 `C:\Program Files\Maven`
3. 配置环境变量 `MAVEN_HOME` 和 `PATH`

#### Linux/macOS

```bash
# Ubuntu/Debian
sudo apt install maven

# macOS
brew install maven
```

验证安装：
```bash
mvn -version
# 应该显示 Apache Maven 3.x.x
```

## 📥 下载项目

### 方式一：克隆仓库

```bash
git clone https://github.com/your-username/your-repo.git
cd your-repo/smart_java_ai_face
```

### 方式二：下载 ZIP

1. 访问 GitHub 仓库页面
2. 点击 "Code" → "Download ZIP"
3. 解压到本地目录

## 📦 下载模型文件

### 重要提示

⚠️ **模型文件约 355MB，必须单独下载！**

### 下载步骤

1. **访问百度网盘**
   - 链接：https://pan.baidu.com/s/10l22x5fRz_gwLr8EAHa1Jg?pwd=1234
   - 提取码：`1234`

2. **下载文件**
   - `retinaface.pt` (105MB) - 人脸检测模型
   - `elasticface.pt` (250MB) - 人脸识别模型

3. **创建模型目录**

   **方式一：放在项目根目录上一级（推荐）**
   ```
   Face_recognition/
   ├── smart_java_ai/           # 创建此目录
   │   ├── retinaface.pt        # 放在这里
   │   └── elasticface.pt       # 放在这里
   └── smart_java_ai_face/      # 项目代码
   ```

   **方式二：放在任意目录**
   ```
   D:/models/smart_java_ai/
   ├── retinaface.pt
   └── elasticface.pt
   ```

### 验证下载

检查文件大小：
- `retinaface.pt` ≈ 105 MB
- `elasticface.pt` ≈ 250 MB

## ⚙️ 配置路径

### 1. 找到配置文件

打开文件：
```
smart_java_ai_face/src/main/java/com/codeying/utils/FaceRecUtils.java
```

### 2. 修改模型路径

找到第 58 行左右：

```java
// ========== 👇 重要配置：首次运行请修改 👇 ==========
/**
 * 模型文件存储路径
 */
public static String MODAL_PATH = "smart_java_ai/";  // 修改这里！
// ========== 👆 重要配置：首次运行请修改 👆 ==========
```

### 3. 路径配置示例

#### 使用相对路径（推荐）

如果模型放在项目上一级目录：
```java
public static String MODAL_PATH = "../smart_java_ai/";
```

#### 使用绝对路径

**Windows**：
```java
// 使用正斜杠
public static String MODAL_PATH = "D:/models/smart_java_ai/";

// 或使用双反斜杠
public static String MODAL_PATH = "D:\\models\\smart_java_ai\\";
```

**Linux/macOS**：
```java
public static String MODAL_PATH = "/home/user/models/smart_java_ai/";
```

### 4. 注意事项

✅ **正确**：
- `"D:/models/smart_java_ai/"` - 末尾有斜杠
- `"D:\\models\\smart_java_ai\\"` - 使用双反斜杠

❌ **错误**：
- `"D:/models/smart_java_ai"` - 缺少斜杠
- `"D:\models\smart_java_ai\"` - 单反斜杠（会转义）

## 🚀 启动应用

### 方式一：使用 IDE（推荐）

#### IntelliJ IDEA

1. 用 IDEA 打开项目文件夹 `smart_java_ai_face`
2. 等待 Maven 依赖下载完成
3. 找到文件：`src/main/java/com/codeying/App.java`
4. 右键点击文件 → **Run 'App.main()'**
5. 查看控制台日志

#### Eclipse

1. Import → Existing Maven Projects
2. 选择 `smart_java_ai_face` 目录
3. 右键 `App.java` → Run As → Java Application

### 方式二：使用 Maven 命令

```bash
# 进入项目目录
cd smart_java_ai_face

# 清理并编译
mvn clean compile

# 打包
mvn package -DskipTests

# 运行
java -jar target/proj-boot-1.0-SNAPSHOT.jar
```

### 方式三：直接运行

```bash
cd smart_java_ai_face
mvn spring-boot:run
```

## ✅ 验证部署

### 1. 查看启动日志

启动成功的标志：

```
========================================
   人脸识别管理系统 v1.0
   Face Recognition Management System
========================================

✅ 模型文件检查通过
   - retinaface.pt: D:\models\smart_java_ai\retinaface.pt
   - elasticface.pt: D:\models\smart_java_ai\elasticface.pt

...

Tomcat started on port(s): 8080 (http)
Started App in 2.666 seconds (JVM running for 3.123)
```

### 2. 访问应用

打开浏览器，访问：
- 管理中心：http://localhost:8080/face/manage
- 首页：http://localhost:8080/

### 3. 测试功能

1. **允许摄像头权限**
   - 浏览器会请求摄像头权限
   - 点击"允许"

2. **测试人脸注册**
   - 点击"人脸注册"选项卡
   - 输入用户ID和角色
   - 点击"拍照并注册"

3. **查看注册结果**
   - 成功会显示绿色提示
   - 检查 `face-imgs/` 目录是否有图片
   - 检查 `smart_java_ai/face_index.json` 是否生成

## 🔍 故障排查

### 问题1：找不到模型文件

**错误日志**：
```
❌ 错误：找不到模型文件 retinaface.pt
```

**解决步骤**：

1. 检查文件是否存在
   ```bash
   # Windows
   dir "D:\models\smart_java_ai\*.pt"
   
   # Linux/Mac
   ls -lh ~/models/smart_java_ai/*.pt
   ```

2. 验证路径配置
   - 打开 `FaceRecUtils.java`
   - 检查 `MODAL_PATH` 是否正确
   - 注意路径分隔符（`/` 或 `\\`）

3. 使用绝对路径
   - 改用绝对路径避免相对路径问题

### 问题2：端口被占用

**错误日志**：
```
Port 8080 was already in use
```

**解决方案A：停止占用进程**
```bash
# Windows
netstat -ano | findstr :8080
taskkill /PID <进程ID> /F

# Linux/Mac
lsof -i :8080
kill -9 <进程ID>
```

**解决方案B：修改端口**

编辑 `application.properties`：
```properties
server.port=8081  # 改为其他端口
```

### 问题3：Maven 依赖下载失败

**解决方案**：

1. 检查网络连接
2. 配置 Maven 镜像

编辑 `~/.m2/settings.xml`：
```xml
<mirrors>
  <mirror>
    <id>alimaven</id>
    <name>Aliyun Maven</name>
    <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
    <mirrorOf>central</mirrorOf>
  </mirror>
</mirrors>
```

3. 删除本地仓库重新下载
   ```bash
   rm -rf ~/.m2/repository/cn/smartjavaai
   mvn clean install
   ```

### 问题4：启动很慢

**原因**：首次启动需要下载 PyTorch 运行库

**解决**：
- 耐心等待（约30-60秒）
- 后续启动会快很多
- 可以查看日志了解进度

### 问题5：摄像头无法访问

**解决方案**：

1. **检查浏览器权限**
   - 地址栏左侧锁图标 → 网站设置
   - 确保摄像头权限为"允许"

2. **使用 HTTPS**
   - 某些浏览器要求 HTTPS 才能访问摄像头
   - 或使用 `localhost`（通常被视为安全）

3. **尝试其他浏览器**
   - Chrome、Edge、Firefox 支持较好

## 📞 获取帮助

如果以上步骤无法解决问题：

1. **查看日志**：检查控制台完整日志
2. **查看 FAQ**：阅读 [FAQ.md](FAQ.md)
3. **提交 Issue**：[GitHub Issues](https://github.com/your-username/your-repo/issues)
4. **查看文档**：[官方文档](http://doc.smartjavaai.cn/)

## ✨ 下一步

部署成功后，可以：

- 📖 阅读 [README.md](../README.md) 了解功能特性
- 🔧 查看 [API 文档](../README.md#📡-api-接口文档)
- 🎨 自定义界面样式
- 🚀 部署到生产环境

---

**配置完成！开始体验人脸识别功能吧！** 🎉

