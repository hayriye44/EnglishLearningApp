package com.hayriyec.yazilimtasarim;
public class Word {

    private String Question;
    private String ImageQuestion;
    private String CorrectAnswer;
    private String Cümle;
    private String CAnlam;
    public Word() {
    }

    public Word(String Question,String CorrectAnswer,String Cümle,String CAnlam) {
        this.Question = Question;
        this.CorrectAnswer=CorrectAnswer;
        this.Cümle=Cümle;
        this.CAnlam=CAnlam;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        this.Question = question;
    }


    public void setImageQuestion(String ImageQuestion) {
        this.ImageQuestion=ImageQuestion;
    }

    public String getCorrectAnswer() {
        return CorrectAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.CorrectAnswer=correctAnswer;
    }

    public String getCümle() {
        return Cümle;
    }

    public void setCümle(String cümle) {
        this.Cümle = cümle;
    }

    public String getCAnlam() {
        return CAnlam;
    }

    public void setCAnlam(String cAnlam) {
        this.CAnlam = cAnlam;
    }

}