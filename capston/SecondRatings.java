package capston;

import java.util.ArrayList;

/**
 * 
 * This class represents a set of second ratings for movies and is used to
 * retrieve information about movies and ratings.
 */
public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;

    /**
     * 
     * Constructs a new SecondRatings object and loads the movie and ratings data
     * from the specified files.
     * The default constructor uses the full dataset files.
     */
    public SecondRatings() {
        // default constructor
        this("data/capston/ratedmoviesfull.csv", "data/capston/ratings.csv");
        // this("data/capston/ratedmovies_short.csv", "data/capston/ratings_short.csv");
    }

    /**
     * 
     * Constructs a new SecondRatings object and loads the movie and ratings data
     * from the specified files.
     * 
     * @param moviefile   The filename of the CSV file containing movie data.
     * @param ratingsfile The filename of the CSV file containing rating data.
     */
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }

    /**
     * 
     * Returns the number of movies in the dataset.
     * 
     * @return The number of movies in the dataset.
     */
    public int getMovieSize() {
        return myMovies.size();
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
        for (Movie movie : myMovies) {
            double averageRating = getAverageByID(movie.getID(), minimalRaters);
            if (averageRating > 0.0) {
                Rating rating = new Rating(movie.getID(), averageRating);
                averageRatings.add(rating);
            }
        }
        return averageRatings;
    }

    /**
     * 
     * Returns the title of the movie with the given ID, if it exists in the list of
     * movies.
     * If the movie with the given ID does not exist in the list of movies, the
     * method returns "ID not found".
     * 
     * @param id the ID of the movie whose title is to be returned.
     * @return the title of the movie with the given ID, if it exists in the list of
     *         movies, or "ID not found" otherwise.
     */
    public String getTitle(String id) {
        for (Movie movie : myMovies) {
            if (movie.getID().equals(id)) {
                return movie.getTitle();
            }
        }
        return "ID not found";
    }

    /**
     * 
     * Returns the ID of the movie with the given title, if it exists in the list of
     * movies.
     * If the movie with the given title does not exist in the list of movies, the
     * method returns "NO SUCH TITLE.".
     * 
     * @param title the title of the movie whose ID is to be returned.
     * @return the ID of the movie with the given title, if it exists in the list of
     *         movies, or "NO SUCH TITLE." otherwise.
     */
    public String getID(String title) {
        for (Movie movie : myMovies) {
            if (movie.getTitle().equals(title)) {
                return movie.getID();
            }
        }
        return "NO SUCH TITLE.";
    }

}
