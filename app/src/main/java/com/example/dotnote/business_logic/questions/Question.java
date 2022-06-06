package com.example.dotnote.business_logic.questions;

import java.util.HashMap;
import java.util.Set;


/**
 * A class representing a single question in our quiz game
 */
public class Question {

    private final String questionText;
    private final String correctAnswer;
    private HashMap<String, String> answers;
    private final boolean hasPicture;
    private String picture;
    private final QuestionType questionType;

    /**
     * The first of two constructors used to create a com.Question object without a picture
     *
     * @param questionText  the question text
     * @param correctAnswer the correct answer, depicted with a string
     * @param answers       a hashmap containing both the letters for the questions and the questions
     * @param questionType  the type of question (e.g. history, science etc)
     * @param hasPicture    a boolean value indicating if the object has an image associated with it, in this case, it doesn't
     */
    public Question(String questionText, String correctAnswer, HashMap<String, String> answers, QuestionType questionType, boolean hasPicture) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.answers = answers;
        this.questionType = questionType;
        this.hasPicture = hasPicture;
    }

    /**
     * Identical with the first constructor, only difference is that this one accepts an image string
     *
     * @param picture an image string
     */
    public Question(String questionText, String correctAnswer, HashMap<String, String> answers, QuestionType questionType, boolean hasPicture, String picture) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.answers = answers;
        this.questionType = questionType;
        this.hasPicture = hasPicture;
        this.picture = picture;
    }

    public Question(String stringFormattedQuestion) {
        String[] questionTokens = stringFormattedQuestion.split("/");
        this.questionText = questionTokens[0];
        this.answers = new HashMap<>();
        this.answers.put("A", questionTokens[1]);
        this.answers.put("B", questionTokens[2]);
        this.answers.put("C", questionTokens[3]);
        this.answers.put("D", questionTokens[4]);
        this.correctAnswer = questionTokens[5];
        this.questionType = QuestionType.valueOf(questionTokens[6]);
        this.hasPicture = Boolean.parseBoolean(questionTokens[7]);
        this.picture = questionTokens[8];
    }

    /**
     * Return's a com.Question object's question text
     *
     * @return the com.Question object's question text
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Return's a com.Question object's correct answer
     *
     * @return the com.Question Object's correct answer
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Returns the com.Question Object's question type
     *
     * @return the com.Question Object's question type
     */
    public QuestionType getQuestionType() {
        return questionType;
    }

    public HashMap<String, String> getAnswers() {
        return answers;
    }

    public boolean hasPicture() {
        return hasPicture;
    }

    public String getPicture() {
        return picture;
    }

    public Set<String> getAnswerKeySet() { // lowers the cohesion of the class, need to look into it
        return answers.keySet();
    }


    /**
     * A method that checks if the answer provided to it matches the question's correct answer
     *
     * @param playerChoice the answer that the object's correct answer will be checked against
     * @return <code>true</code> if the provided answer matches the Object's correct answer, else <code>false</code>
     */
    public boolean isCorrectAnswer(String playerChoice) {
        return answers.get(playerChoice).equals(correctAnswer);
    }

    public void setAnswers(HashMap<String, String> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionText='" + questionText + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", answers=" + answers.toString() +
                ", hasPicture=" + hasPicture +
                ", picture='" + picture + '\'' +
                ", questionType=" + questionType +
                '}';
    }
}

