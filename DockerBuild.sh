#!/bin/bash
if [ ! -d jdk-21.0.3+9 ]; then
  echo "JDK 不存在 下载JDK"
  wget https://aka.ms/download-jdk/microsoft-jdk-21.0.3-linux-x64.tar.gz && \
  tar -zxvf microsoft-jdk-21.0.3-linux-x64.tar.gz
fi
export JAVA_HOME=`pwd jdk-21.0.3+9`/jdk-21.0.3+9  && chmod 777 ./mvbw && \
./mvnw clean package && \
cp target/student-score-0.0.1-SNAPSHOT.jar DockerBuild/student-score.jar && \
cd DockerBuild && \
sudo docker build -t student_score_java . && \
echo "尝试删除已存在的运行中的同名Docker容器"
sudo docker rm -f student_score_java
echo "运行docker容器：" && \
sudo docker run -d -p 8080:8080 --name student_score_java student_score_java && \
echo "容器已运行在 localhost:8080"
