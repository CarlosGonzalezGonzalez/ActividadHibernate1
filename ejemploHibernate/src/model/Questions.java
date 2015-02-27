package model;

// Generated 27-feb-2015 10:59:16 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * Questions generated by hbm2java
 */
public class Questions implements java.io.Serializable {

	private int id;
	private String text;
	private Set answerses = new HashSet(0);

	public Questions() {
	}

	public Questions(int id) {
		this.id = id;
	}

	public Questions(int id, String text, Set answerses) {
		this.id = id;
		this.text = text;
		this.answerses = answerses;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Set getAnswerses() {
		return this.answerses;
	}

	public void setAnswerses(Set answerses) {
		this.answerses = answerses;
	}

}
