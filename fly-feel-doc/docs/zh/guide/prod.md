## 打包

1. 运行`mvn run clean`。

2. 运行`mvn run package`。

3. 在项目入口模块的`target`文件夹下即可看到打包好的JAR文件。

## 部署

### 防宕机的shell重启脚本

```shell script
#项目名称
jar_name='xxxxxxxx'
# shellcheck disable=SC2006
# shellcheck disable=SC2009
pid=$(ps -aux | grep $jar_name | grep -v grep | awk '{print $2}')
echo "$pid"
source /etc/profile
if [ ! "$pid" ]; then
    echo '项目重新启动' >./run.log &
    nohup java -Dfile.encoding=utf-8 -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar ./$jar_name.jar >./run.log &
else
    echo "项目已经启动" >./run.log &
fi
```

### 真机部署

#### 设置shell定时任务

1. 增加定时任务：`crontab -e`

2. 按下`Insert`键进入编辑模式

``` shell script
00 * * * * /home/zhangs/iot_tac.sh >/home/zhangs/log/iot_tac.log
```

该脚本表示每小时执行一次shell脚本，并生成日志文件

- minute: 区间为 0 – 59
- hour: 区间为0 – 23
- day-of-month: 区间为0 – 31
- month: 区间为1 – 12. 1 是1月. 12是12月.
- Day-of-week: 区间为0 – 7. 周日可以是0或7.

3. 保存并退出

### Docker部署

```dockerfile
FROM dragonwelljdk/build_jdk:8u

#这里可根据需要修改
ENV PROJECT_HOME /usr/xxxxxx

WORKDIR ${PROJECT_HOME}

#这里可根据需要修改
COPY ./target/xxxxxx.jar ./xxxxxx.jar

#这里可根据需要修改 可开放端口
EXPOSE 8888 5005

#这里可根据需要修改
VOLUME /usr/xxxxxx/log/* /etc/localtime

CMD ["java","-jar","xxxxxx.jar"]
```

构建运行即可。这里也可以改为shell脚本的方式运行。
