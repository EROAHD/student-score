#!/bin/bash
if [ ! -d jdk-21.0.3+9 ]; then
  echo "JDK 不存在 下载JDK"
  wget https://aka.ms/download-jdk/microsoft-jdk-21.0.3-linux-x64.tar.gz && \
  tar -zxvf microsoft-jdk-21.0.3-linux-x64.tar.gz
else
  export JAVA_HOME=`pwd jdk-21.0.3+9`/jdk-21.0.3+9  && \
  ./mvnw clean package && \
  cp target/student-score-0.0.1-SNAPSHOT.jar DockerBuild/student-score.jar && \
  cd DockerBuild &&
  sudo docker build -t student_score_java .
fi
