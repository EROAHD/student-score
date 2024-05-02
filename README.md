# 学生成绩查询系统后端

## 前端实现：

- https://github.com/EROAHD/student-score-vue

### 此项目使用SpringBoot + mybatis plus + mariadb 实现

#### 1. 首次运行

- 需要在数据库执行`数据库.sql`
- 修改`src/resources/application.yaml`中与数据库相关的设置，url，账号密码等

#### 2. 构建Docker镜像

- 测试环境：wsl2 : Debian GNU/Linux 12 (bookworm) on Windows 10 x86_64
- 使用Linux执行项目根目录下的`DockerBuild.sh`脚本
- 此操作会自动下载jdk并配置环境变量，并使用自带的maven warp构建项目
- 后端默认运行地址：http://localhost:8080
