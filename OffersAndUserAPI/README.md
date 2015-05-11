# 273Project

Open command line in your Kafka installation folder<br/>
Launch Zookeeper with .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties<br/><br/>
Open a second command line in your Kafka installation folder<br/>
Launch single Kafka broker: .\bin\windows\kafka-server-start.bat .\config\server.properties<br/><br/>
Open a third command line in your Kafka installation folder<br/>
Create a topic: .\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test 
