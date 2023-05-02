package SoftwereDesign.week3;

public interface IMarkovModel {
    public void setTraining(String text);

    public String getRandomText(int numChars);

    public void setRandom(int seed);

}
