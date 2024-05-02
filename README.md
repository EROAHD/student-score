# 学生成绩查询系统后端实现

### 使用SpringBoot + mybatis plus + mariadb 实现

#### 1.首次运行

- 需要在数据库执行`数据库.sql`
- 修改`src/resources/application.yaml`中与数据库相关的设置

#### 2.构建Docker镜像

- 使用Linux执行项目根目录下的`DockerBuild.sh`脚本
- 此操作会自动下载jdk并配置环境变量，并使用自带的maven warp构建项目