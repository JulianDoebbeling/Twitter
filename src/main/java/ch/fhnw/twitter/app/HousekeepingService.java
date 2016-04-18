package ch.fhnw.twitter.app;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.fhnw.twitter.model.Tweetpost;
import ch.fhnw.twitter.persistance.TweetRepository;

@Service
public class HousekeepingService {
	private Log log = LogFactory.getLog(this.getClass());
	@Autowired
	TweetRepository tweetRepository;

	/**
	 * Returns a List of all Tweetpost items from the database.
	 * 
	 * @return List of Tweetpost items from the database
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Tweetpost> getAll() {
		return tweetRepository.findAllByOrderByIdDesc();
	}
	
	

	/**
	 * @return number of Tweets stored in the database
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public int getRowCount() {

		return tweetRepository.findRowCount();
	}

	/**
	 * save or update tweets to database
	 */
	public void saveOrUpdate(Tweetpost tweetpost) {
		tweetRepository.saveAndFlush(tweetpost);
	}

	/**
	 * Removes the Tweetpost items from the database which have expired.
	 */
	@Scheduled(cron = "0 * * * * *")
	public void removeOldItems() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -360);

		java.sql.Date oneYear = new java.sql.Date(cal.getTimeInMillis());

		tweetRepository.removeOlderThan(oneYear);
		log.debug("DB Cleanup Ausgefuehrt. " + getRowCount() + "tweets sind in der Datenbank gespeichert.");

	}
}