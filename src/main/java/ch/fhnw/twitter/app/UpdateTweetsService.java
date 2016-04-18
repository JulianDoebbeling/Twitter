package ch.fhnw.twitter.app;

import java.util.List;
import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.social.twitter.api.Entities;
import org.springframework.social.twitter.api.MediaEntity;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.UrlEntity;
import org.springframework.stereotype.Service;

import ch.fhnw.twitter.app.HousekeepingService;
import ch.fhnw.twitter.model.Tweetpost;

/**
 * Performs a Request to get the UserTimeline from Twitter API and stores tweets
 * in Database
 */

@Service
public class UpdateTweetsService {
	private Log log = LogFactory.getLog(this.getClass());

	@Value("${spring.social.twitter.screenName}")
	private String screenName;

	@Value("${spring.social.twitter.screenNameTest}")
	private String screenNameTest;

	@Autowired
	HousekeepingService housekeepingService;

	private final Twitter twitter;

	@Inject
	public UpdateTweetsService(Twitter twitter) {
		this.twitter = twitter;

	}

	/**
	 * Scheduled cron trigger for get-request to twitter api
	 */
	@Scheduled(cron = "0 * * * * *")
	public void getTwitterTimeline() {
		if (twitter != null) {
			List<Tweet> tweets = twitter.timelineOperations().getUserTimeline(screenName);
			log.debug("Request to Twitter API returns tweets json:" + tweets);
			parseAndSaveTweets(tweets);

		}
		else{
			log.error("Couldn't connect to Twitter API.");
		}
	}

	/**
	 * Parse and save all tweets in database
	 */
	private void parseAndSaveTweets(List<Tweet> tweets) {

		for (Tweet tweet : tweets) {
			Tweetpost socialPost = new Tweetpost();
			socialPost.setId(tweet.getId());
			socialPost.setIdStr(tweet.getIdStr());
			socialPost.setProfileImageUrl(tweet.getProfileImageUrl());
			socialPost.setFromUser(tweet.getFromUser());
			socialPost.setUnmodifiedText(tweet.getUnmodifiedText());
			socialPost.setCreatedAt(tweet.getCreatedAt());
			Entities entities = tweet.getEntities();
			List<UrlEntity> urls = entities.getUrls();
			List<MediaEntity> medias = entities.getMedia();
			log.debug("Tweet");
			housekeepingService.saveOrUpdate(socialPost);

			for (UrlEntity url : urls) {

				socialPost.setUrl(url.getUrl());
				socialPost.setDisplayUrl(url.getDisplayUrl());
				socialPost.setExpandedUrl(url.getExpandedUrl());
				log.info("Urls");
				housekeepingService.saveOrUpdate(socialPost);

			}
			for (MediaEntity media : medias) {
				socialPost.setMediaUrl(media.getMediaUrl());
				log.debug("Media");
				housekeepingService.saveOrUpdate(socialPost);

			}
			log.debug("Saved tweet: " + socialPost);
		}

	}

}
