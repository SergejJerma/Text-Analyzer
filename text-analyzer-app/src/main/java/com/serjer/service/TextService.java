package com.serjer.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serjer.domain.LastLetter;
import com.serjer.domain.Text;
import com.serjer.repo.LastLetterRepo;
import com.serjer.repo.TextRepo;


@Service
public class TextService  {
	

	@Autowired
	private LastLetterRepo lastLetterRepo;
	
	@Autowired
	private TextRepo textRepo;
	
	public void countWordsByLastLetter(String inputText) {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Text text = new Text(inputText, String.valueOf(dateTime.format(formatter)));
		textRepo.save(text);
		
		Arrays.stream(inputText.replaceAll("^ +| +$|( )+", "$1").split(" "))
				.filter(s -> s.matches("^[a-zA-Z]*$"))
				.collect(Collectors.groupingBy(s -> String.valueOf(s.toLowerCase().charAt(s.length() - 1))))
				.entrySet()
				.forEach(entry -> {
					LastLetter letter = new LastLetter(entry.getKey(), 
													   text, 
													   entry.getValue().size(), 
													   entry.getValue());
					lastLetterRepo.save(letter);
				});
	}
	
}
