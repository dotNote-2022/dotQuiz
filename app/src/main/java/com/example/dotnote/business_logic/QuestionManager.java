package com.example.dotnote.business_logic;


import com.example.dotnote.db.DBManager;

import java.io.IOException;
import java.util.*;

/**
 * Class which handles the management of Question objects
 */
public class QuestionManager {
    private ArrayList<Question> listOfQuestions;
    private DBManager dbManager;

    /**
     * Default constructor which sets up the listOfQuestions
     */
    public QuestionManager(DBManager dbManager) {
        listOfQuestions = new ArrayList<>();
        this.dbManager = dbManager;
    }

    /**
     * Adds new question to the listOfQuestions
     * @param newQuestion question to be added
     */
    public void addNewQuestion(Question newQuestion) {
        listOfQuestions.add(newQuestion);
    }

    /**
     * Removes the first element of the questions list
     */
    public void removeAnsweredQuestion() {
        listOfQuestions.remove(0);
    }

    /**
     * Shuffles the list of question using <code>Collections.shuffle()</code>
     */
    private void shuffleQuestions() {
        Collections.shuffle(listOfQuestions);
    }

    /**
     * Method for accessing the size of the list of questions [TEMPORARY]
     * @return the number of remaining questions in the list
     */
    public int questionsRemaining() {
        return listOfQuestions.size();
    }

    /**
     * Returns the next question for the player(s)
     */
    public Question getNextQuestion() {
        Question returnQuestion = listOfQuestions.get(0);
        return returnQuestion;
    }

    public void createQuestions(ArrayList<String> labels) {

       this.listOfQuestions = dbManager.fetchQuestions(labels);

        System.out.println(listOfQuestions);

    }

    public void resetQuestions() {
        this.listOfQuestions.clear();
    }

    /**
     * Method that returns a hashMap containing the answers provided in the arguments, after they have been shuffled
     *
     * @param answerValues a String array containing the answers
     * @return a <code>HashMap</code> with alphabetically ordered keys and the shuffled answerValues as the values
     */
    private HashMap<String, String> getQuestionAnswers(ArrayList<String> answerValues) {

        ArrayList<String> answers = answerValues;
        Collections.shuffle(answers);

        HashMap<String, String> finalAnswers = new HashMap<>();
        Character answerIndex = 'a';
        for(String i: answers)
            finalAnswers.put((answerIndex++).toString(), i);
        return finalAnswers;

    }

}
