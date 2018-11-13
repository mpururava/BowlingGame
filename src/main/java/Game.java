import pojo.Score;
import java.util.ArrayList;
import java.util.ListIterator;

public class Game {
    public int frameCounter=0;
    public final int TotalNoOfFrames=10;
    public final int MaxPinDown=10;
    ArrayList<Score> totalScoreBoard;
    ArrayList<Integer> scoreSoFar =new ArrayList<Integer>();

    /**
     * Constructor to initialise the scoreboard
     */
    public Game()
    {
        totalScoreBoard = new ArrayList<Score>(TotalNoOfFrames);
        totalScoreBoard.add(new Score() );
    }

    /**
     * Method to throw the ball
     * @param pinned
     */
    public ArrayList<Score> throwBall(int pinned) {

        if(pinned>MaxPinDown)
            throw new BowlException("Invalid Pins entered"+pinned);
        try {
            Score frameScore = getScoreOfCurrentFrame();

        System.out.println("FrameNo."+frameCounter+"the Throw "+pinned);

        //Set the pins knocked down in a Toss
        frameScore.setRoll(pinned);

        frameScore.setFrameNo(frameCounter);

        if(frameScore.getFrameNo()==9){

            lastFrame(frameScore);
        }
        else{
            if(frameScore.getAttempt()==2||frameScore.isStrike())
            {
                frameScoreKeeper();
                scoreTillDate();
                frameCounter++;
                totalScoreBoard.add(new Score() );
            }
        }
        }catch (Exception e) {
            throw new BowlException("--Error--; start a new game");
        }
    return totalScoreBoard;
    }

    /**
     * The last frame where the extra Toss is given
     * @param frameScore
     */
    private void lastFrame(Score frameScore) {
        if(frameScore.isStrike()||frameScore.isSpare())
        {
            if(frameScore.getAttempt()==3)
            {
                frameScoreKeeper();
                scoreTillDate();
                frameCounter++;
                System.out.println("GAME FINISHED.....start new game");
            }
        } else{
            if(frameScore.getAttempt()==2)
            {
                frameScoreKeeper();
                scoreTillDate();
                frameCounter++;
                System.out.println("GAME FINISHED.....start new game");
            }
        }
    }

    /**
     * Stores the frameScore of the frame for Summation
     */
    public void frameScoreKeeper(){
        Score frameScore = getScoreOfCurrentFrame();
        frameScore.setSumScore(frameScore.getFrameScore());

    }

    /**
     * Provides the frameDetails of the current running frame
     * @return current frame scoreboard
     */
    public Score getScoreOfCurrentFrame()
    {
        Score scoreFrame= totalScoreBoard.get(frameCounter);
        return scoreFrame;
    }

    /**
     * Method is to calculate all the scores stored by the frames till its called
     */
    public ArrayList<Score> scoreTillDate()
    {
        ListIterator<Score> itr = totalScoreBoard.listIterator();
        int i = 0;
        while (i < frameCounter) {
            int frameTotal = 0;
            Score current = itr.next();

            if (current.isStrike()) {
                frameTotal = current.getFrameScore();
                if (itr.hasNext()) {
                    //Next frame whole score is added(STRIKE BONUS)
                    //next Frame score
                    Score next = itr.next();
                    frameTotal += next.getFrameScore();

                    //Two strikes in a Row
                    if (next.isStrike())
                    {
                        if (itr.hasNext())
                        {
                            Score next2next = itr.next();
                            //Three strikes in a Row
                            if (next2next.isStrike())
                                frameTotal += next2next.getFrameScore();
                            else
                                frameTotal += next2next.getRoll().get(0);
                            itr.previous();
                        }
                    }
                    itr.previous();
                }
            } else if (current.isSpare())
            {
                frameTotal = current.getFrameScore();
                //Next frame first toss is added(SPARE BONUS)
                if (itr.hasNext()) {
                    Score abc = itr.next();

                    frameTotal += abc.getRoll().get(0);
                    itr.previous();
                }
            } else
                frameTotal = current.getFrameScore();

            Score frameScore=totalScoreBoard.get(i);
            frameScore.setSumScore(frameTotal);
        i++;
        }

        //Previous Score is added before SAVING
        totalScoreBoard= additionSeries(totalScoreBoard);

        for(Score sc:totalScoreBoard)
            System.out.println(sc);

    return totalScoreBoard;
    }


    /**
     * Methods adds the previos score to the current score
     * @param totalScoreBoard
     * @return AllFrameRecords
     */
    public ArrayList<Score> additionSeries(ArrayList<Score> totalScoreBoard){
        ListIterator<Score> fr=totalScoreBoard.listIterator();
        while(fr.hasNext())
        {
            int sum=0;
            Score current=fr.next();
            if(!(current.getFrameNo()==0))
            {
                if (fr.hasPrevious()){
                    Score previousFrame =fr.previous();
                    current.setSumScore(sum+current.getSumScore()+fr.previous().getSumScore());
                    fr.next();
                    fr.next();
                }
            }
        }
       return totalScoreBoard;
    }

}