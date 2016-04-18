package ch.fhnw.twitter.persistance;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ch.fhnw.twitter.model.Tweetpost;

public interface TweetRepository extends JpaRepository<Tweetpost, Long> {

	/**
	 * Counts the tweets in database
	 */
	@Query("SELECT COUNT(*) FROM Tweetpost t")
	int findRowCount();
	
	/**
	 * Delete all tweets older than 1 year
	 */
	@Modifying
	@Transactional
	@Query("DELETE FROM Tweetpost m WHERE m.createdAt < :date")
	int removeOlderThan(@Param("date") java.sql.Date date);

	List<Tweetpost> findAllByOrderByIdDesc();

}
