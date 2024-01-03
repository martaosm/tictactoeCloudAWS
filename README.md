# tictactoeCloudAWS
TicTacToe Game with Cognito Authentication and RDS database

This guide is addressing users with Windows OS!!!

**EC2 Configuration and Docker Image Deployment**

1. Create EC2 instance and make sure that it is launched. Make sure to allow port 8080 in Inbound Rules in Security Groups
2. Download PuTTY, emulator that will allow us to ssh into our EC2 instance
3. Connect to your EC2 instance using PuTTY with the help of this tutorial: https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/putty.html
4. After establishing connection, download docker. To do that run this commands:
   > sudo yum update -y
   > sudo yum install docker
   > sudo service docker start
5. To build docker image you have to move your app .jar file to EC2 instance:
   - run 'mvn clean package' in your IDE terminal
   - after .jar file is created run this command in your cmd:
     > pscp -i path\to\your\.ppk path\to\.jar\file ec2-user@<Public DNS>:/home/ec2-user
6. Make sure that .jar file was moved to PuTTY, using > ls command
7. Create Dockerfile, it should look like this:
     FROM openjdk:17-oracle
     ARG JAR_FILE=<jar_file_name>.jar
     COPY ${JAR_FILE} .
     EXPOSE 8080
     CMD [ "java", "-jar",  "/<jar_file_name>.jar"]
8. Move Dockerfile.prod to EC2 instance as well:
   >pscp -i path\to\your\.ppk path\to\Dockerfile ec2-user@<Public DNS>:/home/ec2-user
9. To build and run docker image, run this commands:
    > docker build -t <your_choice>/docker -f Dockerfile.prod .
    > docker run -p 8080:8080 <your_choice>/docker
10. Now your app should beaccessible from http://<public DNS>:8080
   

