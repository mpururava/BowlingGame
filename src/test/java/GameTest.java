import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pojo.Score;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import static org.junit.Assert.*;

public class GameTest {
    private Game g1;
    @Before
    public void init(){
        g1= new Game();
    }
    @Test(expected = BowlException.class)
    public void testIllegalBowlException() {

        g1.throwBall(200);

    }
    @Test
    public void simple_score_for_single_frame()
    {

        g1.throwBall(5);
        ArrayList<Score> sc= g1.throwBall(4);
        Assert.assertEquals(9, sc.get(0).getSumScore());
    }
    @Test
    public void strike_score_for_single_frame()
    {
        Game g1= new Game();
        g1.throwBall(10);
        g1.throwBall(5);
        ArrayList<Score> sc= g1.throwBall(3);
        Assert.assertEquals(18, sc.get(0).getSumScore());
    }
    @Test
    public void spare_score_for_single_frame()
    {
        Game g1= new Game();
        g1.throwBall(5);
        g1.throwBall(5);
        g1.throwBall(5);
        ArrayList<Score> sc= g1.throwBall(3);
        Assert.assertEquals(15, sc.get(0).getSumScore());
    }

    @Test
    public void score_for_last_frame()
    {
        Game g1= new Game();
        for(int i=0;i<18;i++)
            g1.throwBall(0);

        g1.throwBall(5);
        ArrayList<Score> sc = g1.throwBall(3);
        Assert.assertEquals(8,sc.get(sc.size()-1).getSumScore());
    }

    @Test
    public void bonus_Toss_by_SPARE_for_last_frame()
    {
        Game g1= new Game();
        for(int i=0;i<18;i++)
            g1.throwBall(0);

        g1.throwBall(5);
        g1.throwBall(5);

        ArrayList<Score> sc = g1.throwBall(3);
        Assert.assertEquals(13,sc.get(sc.size()-1).getSumScore());
    }

    @Test
    public void bonus_Toss_by_STRIKE_for_last_frame()
    {
        Game g1= new Game();
        for(int i=0;i<18;i++)
            g1.throwBall(0);

        g1.throwBall(10);
        g1.throwBall(5);

        ArrayList<Score> sc = g1.throwBall(3);
        Assert.assertEquals(18,sc.get(sc.size()-1).getSumScore());
    }
    @Test
    public void two_strike_in_row_score() {
        Game g1 = new Game();
        g1.throwBall(10);
        g1.throwBall(10);
        g1.throwBall(5);
        ArrayList<Score> sc = g1.throwBall(5);
        Assert.assertEquals(25, sc.get(0).getSumScore());
    }
    @Test
    public void three_strike_in_row_score() {
        Game g1 = new Game();
        g1.throwBall(10);
        g1.throwBall(10);
        ArrayList<Score> sc = g1.throwBall(10);
        Assert.assertEquals(30, sc.get(0).getSumScore());
    }

}