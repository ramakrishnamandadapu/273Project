# 273Project

Open command line in your Kafka installation folder<br/>
Launch Zookeeper with Windows::.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties<br/>
Linux:: bin/zookeeper-server-start.sh config/zookeeper.properties
<br/><br/>
Open a second command line in your Kafka installation folder<br/>
Launch single Kafka broker: Windows:: .\bin\windows\kafka-server-start.bat .\config\server.properties<br/>
Linux:bin/kafka-server-start.sh config/server.properties
<br/><br/>
Open a third command line in your Kafka installation folder<br/>
Create a topic:  Windows:: .\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test <br/>
Linux:: bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test

We can now see that topic if we run the list topic command:

> Windows: .\bin\windows\kafka-topics.sh --list --zookeeper localhost:2181
> Linux:: bin/kafka-topics.sh --list --zookeeper localhost:2181

