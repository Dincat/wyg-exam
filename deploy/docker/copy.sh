#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}


# copy sql
echo "begin copy sql "
cp ../sql/ry_20210908.sql ./mysql/db
cp ../sql/ry_config_20220114.sql ./mysql/db

# copy html
echo "begin copy html "
cp -r ../wyg-ui/dist/** ./nginx/html/dist


# copy jar
echo "begin copy wyg-gateway "
cp ../wyg-gateway/target/wyg-gateway.jar ./wyg/gateway/jar

echo "begin copy wyg-auth "
cp ../wyg-auth/target/wyg-auth.jar ./wyg/auth/jar

echo "begin copy wyg-visual "
cp ../wyg-visual/wyg-monitor/target/wyg-visual-monitor.jar  ./wyg/visual/monitor/jar

echo "begin copy wyg-modules-system "
cp ../wyg-modules/wyg-system/target/wyg-modules-system.jar ./wyg/modules/system/jar

echo "begin copy wyg-modules-file "
cp ../wyg-modules/wyg-file/target/wyg-modules-file.jar ./wyg/modules/file/jar

echo "begin copy wyg-modules-job "
cp ../wyg-modules/wyg-job/target/wyg-modules-job.jar ./wyg/modules/job/jar

echo "begin copy wyg-modules-gen "
cp ../wyg-modules/wyg-gen/target/wyg-modules-gen.jar ./wyg/modules/gen/jar

