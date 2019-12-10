package com.serjer.domain;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class LastLetter {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
		
	private String lastLetter;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "text_id")
	private Text text;
	
	private int wordsNumber;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "words", joinColumns = @JoinColumn(name = "last_letter_id"))
	private List<String> words;

	public LastLetter() {
		super();
	}



	public LastLetter(String lastLetter, Text text, int wordsNumber, List<String> words) {
		super();
		this.lastLetter = lastLetter;
		this.text = text;
		this.wordsNumber = wordsNumber;
		this.words = words;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastLetter() {
		return lastLetter;
	}

	public void setLastLetter(String lastLetter) {
		this.lastLetter = lastLetter;
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public List<String> getWords() {
		return words;
	}

	public void setWords(List<String> words) {
		this.words = words;
	}



	public int getWordsNumber() {
		return wordsNumber;
	}



	public void setWordsNumber(int wordsNumber) {
		this.wordsNumber = wordsNumber;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((words == null) ? 0 : words.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LastLetter other = (LastLetter) obj;
		if (words == null) {
			if (other.words != null)
				return false;
		} else if (!words.equals(other.words))
			return false;
		return true;
	}
	
	
	
}
