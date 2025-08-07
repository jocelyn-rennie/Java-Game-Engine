package org.uob.a2.gameobjects;

public class Score
{
    int score;
    // score gained per puzzle
    int puzzleScore;
    //score gained when exit a room
    int roomScore;

    public Score(int puzzleScore, int roomScore)
    {
        //score initially zero always
        this.score = 0;
        this.puzzleScore = puzzleScore;
        this.roomScore = roomScore;
    }

    // updates score if puzzle completed
    public void puzzleCompleted()
    {
        score = score + puzzleScore;
    }

    //updates score if room visited
    public void roomVisited()
    {
        score = score + roomScore;
    }

    //returns score
    public int getScore()
    {
        return score;
    }

    public int getPuzzleScore()
    {
        return puzzleScore;
    }

    public void extraScore(double elapsedTime)
    {
        //decide once see how long it takes
        if (elapsedTime < 260.0)
        {
            score = score + 100;
        }
        else if(elapsedTime < 280.0)
        {
            score = score + 80;
        }
        else if(elapsedTime < 300.0)
        {
            score = score + 60;
        }
        else if(elapsedTime < 350.0)
        {
            score = score + 40;
        }
        else if(elapsedTime < 400.0)
        {
            score = score + 20;
        }

    }
}