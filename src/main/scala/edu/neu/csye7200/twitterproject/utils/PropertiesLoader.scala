package edu.neu.csye7200.twitterproject.utils

object PropertiesLoader {
  val sentiment140TrainingFilePath = "src/main/resources/training.1600000.processed.noemoticon.csv"
  val sentiment140TestingFilePath = "src/main/resources/testdata.manual.2009.06.14.csv"
  val nltkStopWordsFileName = "NLTK_English_Stopwords_Corpus.txt"

  val naiveBayesModelPath = "output"
  val naiveBayesModelAccuracyPath = "output"

//  val tweetsRawPath = conf.getString("TWEETS_RAW_ABSOLUTE_PATH")
//  val saveRawTweets = conf.getBoolean("SAVE_RAW_TWEETS")
//
//  val tweetsClassifiedPath = conf.getString("TWEETS_CLASSIFIED_ABSOLUTE_PATH")
//
//  val consumerKey = conf.getString("CONSUMER_KEY")
//  val consumerSecret = conf.getString("CONSUMER_SECRET")
//  val accessToken = conf.getString("ACCESS_TOKEN_KEY")
//  val accessTokenSecret = conf.getString("ACCESS_TOKEN_SECRET")
//
//  val microBatchTimeInSeconds = conf.getInt("STREAMING_MICRO_BATCH_TIME_IN_SECONDS")
//  val totalRunTimeInMinutes = conf.getInt("TOTAL_RUN_TIME_IN_MINUTES")
}
