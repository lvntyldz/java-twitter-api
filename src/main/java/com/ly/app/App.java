package com.ly.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Hello world!
 */
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws TwitterException {
        logger.info("Starting to fetch twitter data...");

        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();

        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(Keys.OAUTH_CONSUMER_KEY)
                .setOAuthConsumerSecret(Keys.OAUTH_CONSUMER_SECRET)
                .setOAuthAccessToken(Keys.OAUTH_ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(Keys.OAUTH_ACCESS_TOKEN_SECRET);

        TwitterFactory factory = new TwitterFactory(configurationBuilder.build());
        twitter4j.Twitter twitter = factory.getInstance();

        ResponseList<Status> statuses = twitter.getHomeTimeline();

        for (Status s : statuses) {
            logger.info(s.getUser().getName());
            logger.info(s.getUser().getEmail());
            logger.info(s.getUser().getDescription());
            logger.info(" ----- * ----- * ----- ");
        }

    }
}
