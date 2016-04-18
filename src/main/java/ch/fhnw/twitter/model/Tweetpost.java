package ch.fhnw.twitter.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tweets")
public class Tweetpost {

	@Id
	@Column(columnDefinition = "LONG")
	private long id;

	@NotEmpty
	@Length(max = 255)
	@Column(columnDefinition = "VARCHAR(255)", length = 64, nullable = false)
	private String idStr;

	@NotEmpty
	@Length(max = 255)
	@Column(columnDefinition = "VARCHAR(255)", length = 255, nullable = false)
	private String profileImageUrl;

	@NotEmpty
	@Length(max = 64)
	@Column(columnDefinition = "VARCHAR(64)", length = 64, nullable = false)
	private String fromUser;

	@NotEmpty
	@Column(columnDefinition = "TEXT", nullable = false)
	private String unmodifiedText;

	@NotNull
	@Column(columnDefinition = "DATE", nullable = false)
	private Date createdAt;

	@Length(max = 255)
	@Column(columnDefinition = "VARCHAR(255)", length = 255, nullable = false)
	private String url;

	@Length(max = 255)
	@Column(columnDefinition = "VARCHAR(255)", length = 255, nullable = false)
	private String displayUrl;

	@Length(max = 255)
	@Column(columnDefinition = "VARCHAR(255)", length = 255, nullable = false)
	private String expandedUrl;
	
	@Length(max = 255)
	@Column(columnDefinition = "VARCHAR(255)", length = 255, nullable = false)
	private String mediaUrl;
	
	

	public Tweetpost(Long id, String idStr, String profileImageUrl, String fromUser, String unmodifiedText,
			Date createdAt, String url, String displayUrl, String expandedUrl, String mediaUrl) {
		this.id = id;
		this.idStr = idStr;
		this.profileImageUrl = profileImageUrl;
		this.fromUser = fromUser;
		this.unmodifiedText = unmodifiedText;
		this.createdAt = createdAt;
		this.url = url;
		this.displayUrl = displayUrl;
		this.expandedUrl = expandedUrl;
		this.mediaUrl = mediaUrl;

	}

	public Tweetpost() {

	}

	/*
	 * Getter & Setter
	 */

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdStr() {
		return idStr;
	}

	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getUnmodifiedText() {
		return unmodifiedText;
	}

	public void setUnmodifiedText(String text) {
		this.unmodifiedText = text;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDisplayUrl() {
		return displayUrl;
	}

	public void setDisplayUrl(String displayUrl) {
		this.displayUrl = displayUrl;
	}

	public String getExpandedUrl() {
		return expandedUrl;
	}

	public void setExpandedUrl(String expandedUrl) {
		this.expandedUrl = expandedUrl;
	}

	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}

}
