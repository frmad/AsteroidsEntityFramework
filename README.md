# AsteroidsEntityFramework
This is an asteroid game that was developed during the 4th semester at SDU in the class CBSE. It resembles the classical asteroids arcade game. 

# Prerequisites
```
Maven
JDK 19
```

# Setup Guide

## Step 1:
  Locate the folder .m2 on your computer.
  Change the file inside the folder called settings.xml
  with the the example located below.
  Note that username and password needs to be changed
  ```
  <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
  http://maven.apache.org/xsd/settings-1.0.0.xsd">

<activeProfiles>
  <activeProfile>github</activeProfile>
</activeProfiles>

<profiles>
  <profile>
    <id>github</id>
    <repositories>
      <repository>
        <id>central</id>
        <url>https://repo1.maven.org/maven2</url>
      </repository>
      <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/sweat-tek/MavenRepository</url>
        <snapshots>
          <enabled>true</enabled>
        </snapshots>
      </repository>
    </repositories>
  </profile>
</profiles>
  
    <servers>
        <server>
            <id>github</id>
            <username>USERNAME</username>
            <password>PASSWORD</password>
        </server>
    </servers>
</settings>
```

## Step 2
### Open IDE
Now open your IDE or Terminal and run the following commands
```
mvn clean install
mvn exec:exec
```
