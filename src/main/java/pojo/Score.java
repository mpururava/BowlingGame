package pojo;

import java.util.ArrayList;

public class Score {
    private ArrayList<Integer> roll=new ArrayList<Integer>();
    private  int frameNo;
    private int frameScore;
    private boolean isStrike;
    private boolean isSpare;
    private int sumPins;
    private int attempt=0;
    private int sumScore;

    public int getSumScore() {
        return sumScore;
    }

    public void setSumScore(int sumScore) {
        this.sumScore = sumScore;
    }

    public int getAttempt() {
        return attempt;
    }
    public int getFrameNo() {
        return frameNo;
    }

    public void setFrameNo(int frameNo) {
        this.frameNo = frameNo;
    }

    public int getFrameScore() {
        return frameScore;
    }

    public void setFrameScore(int frameScore) {
        this.frameScore = frameScore;
    }

    public ArrayList<Integer> getRoll() {
        return roll;
    }

    public boolean isStrike()
    {
        return isStrike;
    }

    public void setStrike(boolean strike) {
        isStrike = strike;
    }
    public boolean isSpare() {
        return isSpare;
    }

    public void setSpare(boolean spare) {
        isSpare = spare;
    }

    /**
     * Adding the Toss Score to the Arraylist and setting isStrike or isSpare
     * @param pin
     */
    public void setRoll(int pin)
    {
        roll.add(attempt,pin);

        if(roll.get(0)==10) {
            setStrike(true);
            setFrameScore(10);
        }
        sumPins+=pin;
        setFrameScore(sumPins);
        if(isStrike==false&&sumPins==10)
            setSpare(true);
        attempt++;
    }

    @Override
    public String toString() {
        return ("FrameNO:"+(this.getFrameNo()+1)+
                " Roll: "+ this.getRoll() +
                " score : " + this.getSumScore())+
                " is Strike " + this.isStrike()+
                " is Spare "+ this.isSpare();
    }
}
