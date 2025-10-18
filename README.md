# 🎭 人脸识别管理系统

基于 Spring Boot + SmartJavaAI SDK 的人脸识别管理系统，支持人脸注册、识别、查询和管理功能。

## ✨ 功能特性

- 🎯 **人脸识别登录**：实时摄像头捕获，1:N 人脸匹配登录
- 👤 **人脸注册**：通过摄像头实时拍照注册新用户
- 🔍 **人脸查询**：
  - 上传图片进行 1:N 人脸搜索
  - 查看所有已注册人脸列表
- 📋 **人脸管理**：查看、删除已注册的人脸信息
- 🎨 **现代化UI**：炫酷的渐变背景 + 毛玻璃效果
- 💾 **数据持久化**：人脸图片和索引数据本地存储

## 🖼️ 效果展示

### 管理中心
- 人脸注册、查询、删除三大功能模块
- Tab 切换界面，操作简洁直观
- 动态渐变背景 + 光球装饰效果

### 人脸识别登录
- 实时摄像头捕获
- 自动人脸检测和识别
- 高精度匹配算法

## 🚀 快速开始

### 环境要求

- **JDK**：21+
- **Maven**：3.6+
- **浏览器**：支持摄像头的现代浏览器（Chrome、Edge、Firefox等）
- **系统**：Windows / Linux / macOS

### 一、下载模型文件

由于模型文件较大（约355MB），需要单独下载：

| 模型文件 | 大小 | 用途 |
|---------|------|------|
| `retinaface.pt` | 105MB | 人脸检测模型 |
| `elasticface.pt` | 250MB | 人脸识别模型 |

**下载地址**：
- 百度网盘：https://pan.baidu.com/s/10l22x5fRz_gwLr8EAHa1Jg?pwd=1234
- 提取码：`1234`

下载后，将两个模型文件放在项目根目录下的 `smart_java_ai/` 文件夹中：

```
Face_recognition/
├── smart_java_ai/           # 模型文件目录
│   ├── retinaface.pt        # 人脸检测模型
│   ├── elasticface.pt       # 人脸识别模型
│   └── pytorch/             # PyTorch运行库（自动下载）
└── smart_java_ai_face/      # 项目代码
```

### 二、配置模型路径

打开文件：`src/main/java/com/codeying/utils/FaceRecUtils.java`

找到第 58 行左右的配置项，修改为你的模型文件路径：

```java
// ========== 👇 重要配置：首次运行请修改 👇 ==========
/**
 * 模型文件存储路径
 * TODO: ⚠️ 首次运行前，请将此路径修改为你本地的模型文件目录
 */
public static String MODAL_PATH = "smart_java_ai/";  // 修改这里！
// ========== 👆 重要配置：首次运行请修改 👆 ==========
```

**路径配置示例**：

```java
// 使用相对路径（推荐，模型文件放在项目根目录的上一级）
public static String MODAL_PATH = "../smart_java_ai/";

// 使用绝对路径 - Windows
public static String MODAL_PATH = "D:/models/smart_java_ai/";

// 使用绝对路径 - Linux/Mac
public static String MODAL_PATH = "/home/user/models/smart_java_ai/";
```

**注意**：路径末尾必须包含斜杠 `/` 或 `\\`

### 三、启动应用

#### 方式一：使用 IDE（推荐）

1. 用 IDEA 打开项目
2. 找到文件：`src/main/java/com/codeying/App.java`
3. 右键点击 → **Run 'App.main()'**
4. 等待启动完成（首次启动约30-60秒）

#### 方式二：使用 Maven

```bash
# 进入项目目录
cd smart_java_ai_face

# 编译打包
mvn clean package -DskipTests

# 运行应用
java -jar target/proj-boot-1.0-SNAPSHOT.jar
```

#### 方式三：使用 Maven 直接运行

```bash
cd smart_java_ai_face
mvn spring-boot:run
```

### 四、访问应用

启动成功后，在浏览器中访问：

- **管理中心**：http://localhost:8080/face/manage
- **人脸识别登录**：http://localhost:8080/

**首次访问**：浏览器会请求摄像头权限，请点击"允许"。

## 📖 功能说明

### 1. 人脸注册

1. 访问管理中心，点击"人脸注册"选项卡
2. 允许浏览器访问摄像头
3. 输入用户ID和角色信息
4. 点击"拍照并注册"按钮
5. 系统自动拍照、提取特征并保存

**数据存储**：
- 人脸图片：`face-imgs/用户ID_时间戳.jpg`
- 索引信息：`../smart_java_ai/face_index.json`

### 2. 人脸查询

#### 方式一：上传图片搜索（1:N 匹配）

1. 点击"人脸查询"选项卡
2. 点击上传区域，选择包含人脸的图片
3. 点击"开始搜索"
4. 系统在人脸库中查找最匹配的人脸
5. 显示匹配结果和相似度

#### 方式二：查看所有人脸列表

1. 在"人脸查询"选项卡中
2. 点击"查看所有人脸"按钮
3. 浏览所有已注册的人脸信息
4. 查看人脸图片、用户ID、角色等信息

### 3. 人脸管理与删除

1. 点击"人脸管理"选项卡
2. 点击"刷新列表"加载所有人脸
3. 找到要删除的人脸
4. 点击"删除"按钮
5. 确认后删除（同时删除图片文件和索引记录）

### 4. 人脸识别登录

1. 访问首页：http://localhost:8080/
2. 系统自动启动摄像头
3. 将人脸对准摄像头
4. 系统每秒自动识别一次
5. 识别成功后跳转到成功页面

## 🏗️ 项目结构

```
smart_java_ai_face/
├── src/main/
│   ├── java/com/codeying/
│   │   ├── App.java                      # 启动类（含模型检查）
│   │   ├── config/
│   │   │   └── WebMvcConfig.java         # Web配置（静态资源）
│   │   ├── controller/
│   │   │   ├── IndexController.java      # 人脸识别登录控制器
│   │   │   └── FaceManageController.java # 人脸管理控制器
│   │   ├── service/
│   │   │   └── ImageStorageService.java  # 图片存储服务
│   │   ├── entity/
│   │   │   ├── FaceInfo.java             # 人脸信息实体
│   │   │   ├── FaceRegisterRequest.java  # 注册请求VO
│   │   │   ├── FaceSearchResponse.java   # 搜索响应VO
│   │   │   └── ApiResponse.java          # 统一API响应
│   │   └── utils/
│   │       └── FaceRecUtils.java         # 人脸识别工具类★
│   └── resources/
│       ├── templates/
│       │   ├── hello.html                # 人脸识别登录页
│       │   ├── manage.html               # 管理中心页面★
│       │   └── success.html              # 识别成功页
│       ├── application.properties        # 应用配置
│       └── iu_*.jpg                      # 示例图片
├── face-imgs/                            # 人脸图片存储目录
│   └── .gitkeep
├── pom.xml                               # Maven配置
└── README.md                             # 本文件
```

**重点文件**（标★）：
- `FaceRecUtils.java`：需要配置 `MODAL_PATH`
- `manage.html`：管理中心前端页面

## 📡 API 接口文档

### 人脸管理接口

| 接口 | 方法 | 说明 | 请求参数 |
|------|------|------|----------|
| `/face/manage` | GET | 访问管理页面 | - |
| `/face/register` | POST | 注册新人脸 | `{base64Image, userId, role}` |
| `/face/search` | POST | 搜索人脸（1:N） | `{base64Image}` |
| `/face/list` | GET | 获取所有人脸列表 | - |
| `/face/detail/{faceId}` | GET | 获取人脸详情 | 路径参数：faceId |
| `/face/delete/{faceId}` | DELETE | 删除人脸 | 路径参数：faceId |

### 人脸识别登录接口

| 接口 | 方法 | 说明 | 请求参数 |
|------|------|------|----------|
| `/` | GET | 识别登录页 | - |
| `/getInfoByFace` | POST | 人脸识别 | `{base64String}` |
| `/success` | GET | 识别成功页 | - |

### 请求示例

**注册人脸**：
```bash
curl -X POST http://localhost:8080/face/register \
  -H "Content-Type: application/json" \
  -d '{
    "base64Image": "data:image/jpeg;base64,...",
    "userId": "user001",
    "role": "员工"
  }'
```

**搜索人脸**：
```bash
curl -X POST http://localhost:8080/face/search \
  -H "Content-Type: application/json" \
  -d '{
    "base64Image": "data:image/jpeg;base64,..."
  }'
```

**获取人脸列表**：
```bash
curl http://localhost:8080/face/list
```

## 🛠️ 技术栈

| 技术 | 版本 | 说明 |
|------|------|------|
| Spring Boot | 3.5.0 | 后端框架 |
| JDK | 21 | Java开发工具包 |
| Thymeleaf | 3.x | 模板引擎 |
| SmartJavaAI Face SDK | 1.0.24 | 人脸识别SDK |
| PyTorch | 2.5.1 (CPU) | 深度学习框架 |
| RetinaFace | - | 人脸检测模型 |
| ElasticFace | - | 人脸识别模型 |
| SQLite | - | 向量数据库（人脸特征存储） |
| HTML/CSS/JS | - | 前端技术 |

## ❓ 常见问题

### 1. 启动时提示找不到模型文件

**问题**：
```
❌ 错误：找不到模型文件 retinaface.pt
```

**解决方法**：
1. 确认已下载模型文件（百度网盘链接见上方）
2. 检查文件是否放在正确的目录
3. 修改 `FaceRecUtils.java` 中的 `MODAL_PATH` 配置

### 2. 浏览器无法访问摄像头

**问题**：
```
NotAllowedError: Permission denied
```

**解决方法**：
1. 点击浏览器地址栏左侧的锁图标
2. 允许摄像头权限
3. 刷新页面
4. 注意：某些浏览器在非HTTPS下可能限制摄像头访问

### 3. 访问管理中心显示404

**问题**：访问 `http://localhost:8080/face/manage` 显示 404

**解决方法**：
1. 确认应用已成功启动（查看控制台日志）
2. 检查端口是否正确（默认8080）
3. 不要直接在IDE中打开HTML文件，必须通过浏览器访问 `localhost:8080`

### 4. 首次启动很慢

**问题**：启动需要很长时间

**原因**：首次启动需要：
- 下载 PyTorch 运行库（约300MB）
- 加载人脸识别模型

**解决方法**：
- 耐心等待30-60秒
- 后续启动会快很多（模型已缓存）

### 5. 内存不足错误

**问题**：
```
OutOfMemoryError: Java heap space
```

**解决方法**：
增加JVM内存：
```bash
java -Xmx2048m -jar target/proj-boot-1.0-SNAPSHOT.jar
```

### 6. 人脸识别不准确

**问题**：识别结果不准确或无法识别

**优化建议**：
1. **光线充足**：确保拍照环境光线良好
2. **正面拍摄**：尽量正对摄像头
3. **距离适中**：与摄像头保持30-50cm
4. **高质量图片**：使用清晰的人脸图片注册
5. **调整阈值**：在代码中修改相似度阈值（默认0.8）

### 更多问题？

- 查看控制台日志
- 参考官方文档：http://doc.smartjavaai.cn/
- 提交 Issue：[GitHub Issues](https://github.com/your-username/your-repo/issues)

## 📝 开发说明

### 本地开发

```bash
# 克隆项目
git clone https://github.com/your-username/your-repo.git

# 进入目录
cd smart_java_ai_face

# 编译
mvn clean compile

# 运行
mvn spring-boot:run
```

### 修改端口

编辑 `src/main/resources/application.properties`：

```properties
server.port=8080  # 修改为你想要的端口
```

### 启用调试日志

```properties
logging.level.com.codeying=DEBUG
```

## 📄 开源协议

本项目采用 **MIT License** 开源协议。

## 🙏 致谢

- **SmartJavaAI**：提供强大的人脸识别SDK
  - 官网：http://doc.smartjavaai.cn/
  - GitHub：https://github.com/SmartJavaAI
- **PyTorch**：深度学习框架
- **Spring Boot**：快速开发框架

## 👨‍💻 作者

**CodeYing**

如果这个项目对你有帮助，欢迎 Star ⭐️

## 📮 联系方式

- 提交 Issue：[GitHub Issues](https://github.com/your-username/your-repo/issues)
- 发起讨论：[GitHub Discussions](https://github.com/your-username/your-repo/discussions)

## 🔄 更新日志

查看 [CHANGELOG.md](CHANGELOG.md) 了解版本更新记录。

---

**Happy Coding! 🎉**
