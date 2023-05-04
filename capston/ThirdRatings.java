package capston;

import java.util.ArrayList;

/**
 * 
 * This class represents a set of second ratings for movies and is used to
 * retrieve information about movies and ratings.
 */
public class ThirdRatings {
    // private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;

    /**
     * 
     * Constructs a new SecondRatings object and loads the movie and ratings data
     * from the specified files.
     * The default constructor uses the full dataset files.
     */
    public ThirdRatings() {
        // default constructor
        this("data/capston/ratings.csv");
        // this( "data/capston/ratings_short.csv");
    }

    /**
     * 
     * Constructs a new SecondRatings object and loads the movie and ratings data
     * from the specified files.
     * 
     * @param moviefile   The filename of the CSV file containing movie data.
     * @param ratingsfile The filename of the CSV file containing rating data.
     */
    public ThirdRatings(String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        // myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }

    /**
     * 
     * Returns the number of raters in the dataset.
     * 
     * @return The number of raters in the dataset.
     */
    public int getRaterSize() {
        return myRaters.size();
    }

    /**
     * 
     * Calculates and returns the average rating for the movie with the specified
     * ID, provided that it has been rated by
     * at least minimalRaters raters.
     * 
     * @param id            The ID of the movie to calculate the average rating for.
     * @param minimalRaters The minimum number of raters required to calculate an
     *                      average rating.
     * @return The average rating for the movie with the specified ID, or 0.0 if the
     *         movie has not been rated by enough raters.
     */
    private double getAverageByID(String id, int minimalRaters) {
        double totalRating = 0.0;
        int numRaters = 0;
        for (EfficientRater rater : myRaters) {
            if (rater.hasRating(id)) {
                totalRating += rater.getRating(id);
                numRaters++;
            }
        }
        if (numRaters >= minimalRaters) {
            return totalRating / numRaters;
        } else {
            return 0.0;
        }
    }

    /**
     * 
     * Calculates and returns a list of Rating objects representing the average
     * ratings for all movies in the dataset that
     * have been rated by at least minimalRaters raters.
     * 
     * @param minimalRaters The minimum number of raters required to calculate an
     *                      average rating for a movie.
     * @return A list of Rating objects representing the average ratings for all
     *         movies in the dataset that have been rated by at least minimalRaters
     *         raters.
     */
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        ArrayList<String> movieIDs = MovieDatabase.filterBy(new TrueFilter());
        for (String movieID : movieIDs) {
            double averageRating = getAverageByID(movieID, minimalRaters);
            if (averageRating > 0.0) {
                Rating rating = new Rating(movieID, averageRating);
                averageRatings.add(rating);
            }
        }
        return averageRatings;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (String movieID : movies) {
            double averageRating = getAverageByID(movieID, minimalRaters);
            if (averageRating > 0.0) {
                Rating rating = new Rating(movieID, averageRating);
                averageRatings.add(rating);
            }
        }
        return averageRatings;
    }
}
