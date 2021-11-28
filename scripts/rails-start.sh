#! /bin/bash

cd /home/ubuntu/spark || exit

nohup ./bin/spark-submit \
--class edu.neu.csye7200.twitterproject.StreamProcessing \
--master local \
real_time_twitter_sentiment_analytics_system-1.0-SNAPSHOT-jar-with-dependencies.jar \ > ~/application.log 2>&1 &