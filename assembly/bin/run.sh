#!/bin/bash
#这里可替换为你自己的执行程序，其他代码无需更改
PROJECT_NAME=springboot-easyexcel

if [ -n "$2" ]; then
  APP_PORT=$2
  echo "$APP_PORT"
else
  echo "Usage: sh 执行脚本.sh [start|stop|restart|status] [port]"
  exit 1
fi

APP_NAME=$PROJECT_NAME-$APP_PORT.jar

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

#使用说明，用来提示输入参数
usage() {
    echo "Usage: sh 执行脚本.sh [start|stop|restart|status] [port]"
    exit 1
}


#检查程序是否在运行
is_exist(){
  pid=`ps -ef|grep $APP_NAME|grep -v grep|awk '{print $2}' `
  #如果不存在返回1，存在返回0
  if [ -z "${pid}" ]; then
    return 1
  else
    return 0
  fi
}

#启动方法
#-Xms4096m -Xmx4096m \
start(){
  is_exist
  if [ $? -eq "0" ]; then
    echo "${APP_NAME} is already running. pid=${pid} ."
  else
    `cp -r $DIR/../jar/$PROJECT_NAME.jar $DIR/../jar/$APP_NAME`
    nohup java -jar \
    -Dserver.port=$APP_PORT \
    -Dspring.config.location=$DIR/../config/bootstrap.yml \
    -Dlogging.config=$DIR/../config/logback-spring.xml \
    $DIR/../jar/$APP_NAME > $DIR/../logs/out.log 2>&1 &
  fi
}

#停止方法
stop(){
  is_exist
  if [ $? -eq "0" ]; then
    kill -9 $pid
    `rm -rf $DIR/../jar/$APP_NAME`
  else
    echo "${APP_NAME} is not running"
  fi
}

#输出运行状态
status(){
  is_exist
  if [ $? -eq "0" ]; then
    echo "${APP_NAME} is running. Pid is ${pid}"
  else
    echo "${APP_NAME} is NOT running."
  fi
}

#重启
restart(){
  stop
  start
}
#根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
  "start")
    start
    ;;
  "stop")
    stop
    ;;
  "status")
    status
    ;;
  "restart")
    restart
    ;;
  *)
    usage
    ;;
esac
