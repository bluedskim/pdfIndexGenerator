git checkout main
echo "pulling..."
git pull
chmod +x ./gradlew
./gradlew clean bootJar
#./gradlew bootRun --args='--spring.profiles.active=stg --server.port=7001' #프로세스를 죽이기 힘들어서 아래 방법으로 띄움
pkill -9 -f build/libs/pdfIndexGenerator-0.0.1-SNAPSHOT.jar
nohup java -jar build/libs/pdfIndexGenerator-0.0.1-SNAPSHOT.jar --server.port=8001 > out.log 2>&1&

for i in `seq 5 -1 1` ; do echo -ne "\r로그읽기 $i초전 " ; sleep 1 ; done

#./tailLog.sh
tail -f out.log