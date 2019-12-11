source /etc/profile
killall java
nohup java -jar /home/java/springcloud-eureka-0.0.1-SNAPSHOT.jar >> /home/java/myoutput.log 2>&1 &