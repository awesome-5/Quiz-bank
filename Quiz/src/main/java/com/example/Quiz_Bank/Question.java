package com.example.Quiz_Bank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * A entity object, like in any other Java application. In a typical real world
 * application this could for example be a JPA entity.
 */
@SuppressWarnings("serial")
public class Question implements Serializable, Cloneable {

	private Long id;

	private String questionText = "";
	
	private String questionAnswer = "";
	
	private QuestionType type;

	private String marks = "";

	private QuestionDifficulty difficulty;

	private String time = "";

	private Date lastUsed = null;
	
	private String variantOf;
	
	private String courseCode="";

	private int lines=0;
	
	private int space=0;

	private String options="";


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getMarks() {
		return marks;
	}

	public void setMarks(String marks) {
		this.marks = marks;
	}

	public QuestionDifficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(QuestionDifficulty difficulty) {
		this.difficulty = difficulty;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}
	
	public String getVariantOf() {
		return variantOf;
	}

	public void setVariantOf(String variantOf) {
		this.variantOf = variantOf;
	}


	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public Date getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

	public int getLines() {
		return lines;
	}

	public void setLines(int lines) {
		this.lines = lines;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public boolean isPersisted() {
		return id != null;
	}
	
	public int getSpace() {
		return space;
	}

	public void setSpace(int space) {
		this.space = space;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (this.id == null) {
			return false;
		}

		if (obj instanceof Question && obj.getClass().equals(getClass())) {
			return this.id.equals(((Question) obj).id);
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 43 * hash + (id == null ? 0 : id.hashCode());
		return hash;
	}

	@Override
	public Question clone() throws CloneNotSupportedException {
		return (Question) super.clone();
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", questionText=" + questionText + ", marks=" + marks + ", difficulty="
				+ difficulty + ", time=" + time + ", questionAnswer=" + questionAnswer + ", type=" + type +",lines="+lines+",options="+options+ "]";
	}




	


}