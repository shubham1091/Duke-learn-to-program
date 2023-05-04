package capston;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * A class for running average movie rating-related tasks.
 */
public class MovieRunnerAverage {
    public static void main(String[] args) {
        MovieRunnerAverage mr = new MovieRunnerAverage();
        // mr.getAverageRatingOneMovie();
        mr.printAverageRatings();
    }

    /**
     * 
     * Prints the list of movies with an average rating of at least minimalRaters.
     * It also displays the number of movies and raters used for computing the
     * average rating.
     */
    public void printAverageRatings() {
        int minimalRaters = 12;
        SecondRatings sr = new SecondRatings();
        System.out.println("Number of Raters: " + sr.getRaterSize());
        System.out.println("Number of Movies: " + sr.getMovieSize());

        ArrayList<Rating> ratingList = sr.getAverageRatings(minimalRaters);

        Collections.sort(ratingList);

        System.out.println("Movies with at least " + minimalRaters + " ratings:");
        Rating r = ratingList.get(0);
        System.out.println(r.getValue() + " " + sr.getTitle(r.getItem()));

    }

    /**
     * 
     * Gets the average rating for a single movie.
     * It takes a movie title as an input and displays its average rating.
     */
    public void getAverageRatingOneMovie() {
        String movieTitle = "Vacation"; // Change to desired movie title
        SecondRatings sr = new SecondRatings();
        String movieID = sr.getID(movieTitle);
        if (movieID.equals("NO SUCH TITLE.")) {
            System.out.println(movieID);
        } else {
            ArrayList<Rating> ratingList = sr.getAverageRatings(1);
            for (Rating rt : ratingList) {
                if (rt.getItem().equals(movieID)) {
                    System.out.println("The average rating for " + movieTitle + " " + rt.getValue());
                    break;
                }
            }

        }
    }

}
