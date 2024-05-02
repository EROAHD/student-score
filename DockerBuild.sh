#!/bin/bash
# 运行 Maven
./mvnw clean package && \
cp target/student-score-0.0.1-SNAPSHOT.jar DockerBuild/student-score.jar && \
cd DockerBuild
sudo docker build -t student_score_java .