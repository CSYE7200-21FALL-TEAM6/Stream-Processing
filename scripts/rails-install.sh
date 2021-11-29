#! /bin/bash
cd /home/ubuntu || exit
mkdir spark
cp -r /home/ubuntu/spark-install/real_time_twitter_sentiment_analytics_system-1.0-SNAPSHOT-jar-with-dependencies.jar /home/ubuntu/spark/
cd /home/ubuntu/spark-3.2.0-bin-hadoop3.2-scala2.13 || exit
mkdir output
cp -r /home/ubuntu/spark-install/output/ /home/ubuntu/spark-3.2.0-bin-hadoop3.2-scala2.13/
cd /home/ubuntu/spark || exit
