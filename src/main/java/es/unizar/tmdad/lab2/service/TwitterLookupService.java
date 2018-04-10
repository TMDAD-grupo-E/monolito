package es.unizar.tmdad.lab2.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.twitter.api.SearchMetadata;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Trends;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

@Service
public class TwitterLookupService {
	@Value("${twitter.consumerKey}")
	private String consumerKey;

	@Value("${twitter.consumerSecret}")
	private String consumerSecret;

	@Value("${twitter.accessToken}")
	private String accessToken;

	@Value("${twitter.accessTokenSecret}")
	private String accessTokenSecret;

	private static final long WORLDWIDE_WOE = 1L;

	public SearchResults search(String query) {
		Twitter twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
		return twitter.searchOperations().search(query);
	}

	public Trends trends(String query) {
		Twitter twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
		return twitter.searchOperations().getLocalTrends(WORLDWIDE_WOE);
	}

	public SearchResults emptyAnswer() {
		return new SearchResults(Collections.emptyList(), new SearchMetadata(0, 0));
	}
}
