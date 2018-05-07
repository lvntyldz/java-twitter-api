package com.ly.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);


    public static void printAllUserInfo(Twitter twitter) throws TwitterException {
        logger.info("Printing all user information...");

        ResponseList<Status> statuses = twitter.getHomeTimeline();

        for (Status s : statuses) {
            logger.info(s.getUser().getName());
            logger.info(s.getUser().getEmail());
            logger.info(s.getUser().getDescription());
            logger.info(" ----- * ----- * ----- ");
        }

    }

    public static void pirntByQuery(Twitter twitter, Query query) throws TwitterException {

        logger.info("Printing information by query...");

        QueryResult result;
        result = twitter.search(query);

        List<Status> tweets = result.getTweets();
        for (Status t : tweets) {
            logger.info(t.getUser().getScreenName());
            logger.info(t.getText());
            logger.info(" ----- * ----- * ----- ");
        }

    }


    public static ConfigurationBuilder getConfigurationBuilder() {
        logger.info("Creating configuration...");

        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();

        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(Keys.OAUTH_CONSUMER_KEY)
                .setOAuthConsumerSecret(Keys.OAUTH_CONSUMER_SECRET)
                .setOAuthAccessToken(Keys.OAUTH_ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(Keys.OAUTH_ACCESS_TOKEN_SECRET);

        return configurationBuilder;
    }


    public static void main(String[] args) throws TwitterException {
        logger.info("Starting to fetch twitter data...");

        ConfigurationBuilder configurationBuilder = getConfigurationBuilder();

        TwitterFactory factory = new TwitterFactory(configurationBuilder.build());
        twitter4j.Twitter twitter = factory.getInstance();

        //Print All User Info..
        printAllUserInfo(twitter);

        //Fetch Information by Query
        Query query = new Query("#fenerbahce");
        pirntByQuery(twitter, query);


    }
}
