# 273Project

Open command line in your Kafka installation folder
Launch Zookeeper with .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
Open a second command line in your Kafka installation folder
Launch single Kafka broker: .\bin\windows\kafka-server-start.bat .\config\server.properties
Open a third command line in your Kafka installation folder
Create a topic: .\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test 
