# csye6225-spring2019-lambda
AWS cloud Lambda function

## Team Information

| Name | NEU ID | Email Address |
| --- | --- | --- |
| Satish Kumar Anbalagan| 001351994| anbalagan.s@husky.neu.edu|
| Paavan Gopala Reddy| 001813403| gopalareddy.p@husky.neu.edu|
| HemalKumar Gadhiya|001460577 |gadhiya.h@husky.neu.edu|
| Akshay Murgod|001635872 |murgod.a@husky.neu.edu |


---------------------------------------------------------------
##Gradle istallation instructions:

Step1: Download manually gradld 5.3 
https://gradle.org/releases/

step2: 
$ mkdir /opt/gradle
$ unzip -d /opt/gradle gradle-5.3-bin.zip
$ ls /opt/gradle/gradle-5.3
LICENSE  NOTICE  bin  getting-started.html  init.d  lib  media


step3: 
export PATH=$PATH:/opt/gradle/gradle-5.3/bin

step4: test installation
$ gradle -v

------------------------------------------------------------
Gradle 5.3
------------------------------------------------------------



#To build and run using gradle build
At the root level of the project -(place where 'build.gradle' is located)
# gradle clean
# gradle build

Zip deploy package will be generated at /build/dictributions


