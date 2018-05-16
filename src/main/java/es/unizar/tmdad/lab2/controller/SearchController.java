package es.unizar.tmdad.lab2.controller;

import es.unizar.tmdad.lab2.service.TwitterLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.social.UncategorizedApiException;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Trends;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class SearchController {

	@Autowired
	TwitterLookupService twitter;

	@MessageMapping("/search")
	public void search(String q) {
		twitter.search(q);
	}

	/*@RequestMapping("/search")
	public SearchResults search(@RequestParam("q") String q, Model m) {
		return twitter.search(q);
	}*/


	@RequestMapping("/trends")
	public Trends trends(@RequestParam("c") String c, Model m) {
		return twitter.trends(c);
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UncategorizedApiException.class)
	public SearchResults handleUncategorizedApiException() {
		return twitter.emptyAnswer();
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NullPointerException.class)
	public Trends handleNullPointerException(Model m) {
		return twitter.trendsEmptyAnswer();
	}

}