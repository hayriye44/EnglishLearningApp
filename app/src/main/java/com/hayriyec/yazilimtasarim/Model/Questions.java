package com.hayriyec.yazilimtasarim.Model;
public class Questions {

    private String Question;
    private String AnswerA,AnswerB,AnswerC,AnswerD;
    private String CorrectAnswer;

    public Questions()
    {

    }

    public Questions(String Question,String AnswerA,String AnswerB,String AnswerC,String AnswerD,String CorrectAnswer)
    {
        this.Question=Question;
        this.AnswerA=AnswerA;
        this.AnswerB=AnswerB;
        this.AnswerC=AnswerC;
        this.AnswerD=AnswerD;
        this.CorrectAnswer=CorrectAnswer;
    }

    public String getAnswerA() {
        return AnswerA;
    }
    public void setAnswerA(String answerA) {
        AnswerA = answerA;
    }
    public String getAnswerB() {
        return AnswerB;
    }
    public void setAnswerB(String answerB) {
        AnswerA = answerB;
    }
    public String getAnswerC() {
        return AnswerC;
    }
    public void setAnswerC(String answerC) {
        AnswerA = answerC;
    }
    public String getAnswerD() {
        return AnswerD;
    }
    public void setAnswerD(String answerD) {
        AnswerA = answerD;
    }

    public String getCorrectAnswer() {
        return CorrectAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        CorrectAnswer = correctAnswer;
    }


    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

}

