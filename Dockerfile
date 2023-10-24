FROM jdk1.8:latest
ADD target/springboot-easyexcel-1.0.0.jar //
ADD lib/readLog.sh /usr/scripts/
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN  echo 'Asia/Shanghai' >/etc/timezone
ENTRYPOINT ["java", "-jar","-Xmx2048m","-Xms2048m", "-Duser.timezone=GMT+08", "-Djava.library.path=/solibrarys", "/faceBucket-V1.1.0.jar"]
VOLUME /tmp