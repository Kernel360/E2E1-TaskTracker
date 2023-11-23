#!/bin/bash

BUILD_JAR=$(ls /home/ec2-user/action/libs/project-0.0.1-SNAPSHOT.jar 2>/dev/null)
JAR_NAME=$(basename "$BUILD_JAR")

# 로그 디렉토리 생성 명령어 추가
LOG_DIR="/home/ec2-user/action/log"
mkdir -p "$LOG_DIR"

echo "> 현재 시간: $(date)" >> "$LOG_DIR/deploy_success.log"

echo "> build 파일명: $JAR_NAME" >> "$LOG_DIR/deploy_success.log"

echo "> build 파일 복사" >> "$LOG_DIR/deploy_success.log"
DEPLOY_PATH="/home/ec2-user/action/"
if [ -n "$BUILD_JAR" ]; then
  cp "$BUILD_JAR" "$DEPLOY_PATH"
else
  echo "> 빌드 파일이 존재하지 않습니다." >> "$LOG_DIR/deploy_success.log"
  exit 1
fi

echo "> 현재 실행중인 애플리케이션 pid 확인" >> "$LOG_DIR/deploy_success.log"
CURRENT_PID=$(pgrep -f "$JAR_NAME")

if [ -z "$CURRENT_PID" ]; then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다." >> "$LOG_DIR/deploy_error.log"
else
  echo "> kill -9 $CURRENT_PID" >> "$LOG_DIR/deploy_success.log"
  sudo kill -9 "$CURRENT_PID"
  sleep 5
fi

DEPLOY_JAR="$DEPLOY_PATH$JAR_NAME"
echo "> DEPLOY_JAR 배포" >> "$LOG_DIR/deploy_success.log"
sudo nohup java -jar "$DEPLOY_JAR" >> "$LOG_DIR/tomcat.log" 2> "$LOG_DIR/deploy_error.log" &
