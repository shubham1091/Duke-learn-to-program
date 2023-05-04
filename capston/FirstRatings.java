package capston;

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {

    /**
     * A method to test the loadRatings and its helper methods.
     */
    public void testLoadRatings() {
        ArrayList<EfficientRater> ratings = loadRaters("data/capston/ratings.csv");
        System.out.println(ratings.size());
        String rt_id = "193";
        System.out.println("Number of rating by " + rt_id + " is " + countRating(ratings, rt_id));
        maxRatings(ratings);
        String md = "1798709";
        System.out.println("the movie id " + md + " were rated " + mvRating(ratings, md) + " times");
        System.out.println("There are " + alMovie(ratings) + " movies rated");
    }

    /**
     * A method to test the loadMovies and its helper methods.
     */
    public void testLoadMovies() {
        ArrayList<Movie> movies = loadMovies("data/capston/ratedmoviesfull.csv");
        System.out.println("size of array list " + movies.size());
        String gener = "Comedy";
        System.out.println("Number of movie by " + gener + " as gener is " + numType(movies, gener));
        int dur = 150;
        System.out.println("Number of movie with duration grater then " + dur + " is " + grtDuration(movies, 150));
        numDir(movies);
    }

    /**
     * A method to load movie data from a CSV file.
     * 
     * @param filename the name of the CSV file to load
     * @return an ArrayList of Movie objects representing the movies in the file
     */
    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();

        for (CSVRecord record : parser) {
            String anID = record.get("id");
            String aTitle = record.get("title");
            String aYear = record.get("year");
            String theGenres = record.get("genre");
            String aDirector = record.get("director");
            String aCountry = record.get("country");
            String aPoster = record.get("poster");
            int theMinutes = Integer.parseInt(record.get("minutes"));
            Movie mv = new Movie(anID, aTitle, aYear, theGenres, aDirector, aCountry, aPoster, theMinutes);
            movies.add(mv);
        }
        return movies;
    }

    /**
     * A method to count the number of movies with a given genre.
     * 
     * @param movies list of movies to search for genre
     * @param type   genre to search for
     * @return the number of movies with the given genre
     */
    public int numType(ArrayList<Movie> movies, String type) {
        int count = 0;
        for (Movie mv : movies) {
            if (mv.getGenres().contains(type)) {
                count++;
            }
        }
        return count;
    }

    /**
     * A method to count the number of movies with a duration greater than or equal
     * to a given value.
     * 
     * @param movies   list of movies to search for duration
     * @param duration minimum duration to search for
     * @return the number of movies with duration greater than or equal to the given
     *         value
     */
    public int grtDuration(ArrayList<Movie> movies, int duration) {
        int count = 0;
        for (Movie mv : movies) {
            if (mv.getMinutes() > duration) {
                count++;
            }
        }
        return count;
    }

    /**
     * A method to count the number of movies directed by each director and print
     * the maximum count and corresponding director(s).
     * 
     * @param movies list of movies to search for directors
     */
    public void numDir(ArrayList<Movie> movies) {
        HashMap<String, Integer> mp = new HashMap<String, Integer>();
        int maxCount = 0;
        String maxDirector = "";

        for (Movie mv : movies) {
            String[] directors = mv.getDirector().split("\\s*,\\s*");
            for (String director : directors) {
                mp.put(director, mp.getOrDefault(director, 0) + 1);
                int count = mp.get(director);
                if (count > maxCount) {
                    maxCount = count;
                    maxDirector = director;
                }
            }
        }

        System.out.println(maxCount);
        System.out.println(maxDirector);
    }

    /**
     * A method to load rater data from a CSV file.
     * 
     * @param filename the name of the CSV file to load
     * @return an ArrayList of Rater objects representing the raters in the file
     */
    public ArrayList<EfficientRater> loadRaters(String filename) {
        ArrayList<EfficientRater> raters = new ArrayList<EfficientRater>();
        HashMap<String, EfficientRater> raterMap = new HashMap<String, EfficientRater>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();

        for (CSVRecord mv : parser) {
            String rtrID = mv.get("rater_id");
            String itemID = mv.get("movie_id");
            double rating = Double.parseDouble(mv.get("rating"));

            EfficientRater rater;
            if (raterMap.containsKey(rtrID)) {
                rater = raterMap.get(rtrID);
            } else {
                rater = new EfficientRater(rtrID);
                raters.add(rater);
                raterMap.put(rtrID, rater);
            }
            rater.addRating(itemID, rating);
        }

        return raters;
    }

    /**
     * A method to count the number of ratings for a rater with a given ID.
     * 
     * @param raterList list of raters to search for ID
     * @param id        rater ID to search for
     * @return the number of ratings for the given rater ID
     */
    public int countRating(ArrayList<EfficientRater> raterList, String id) {
        HashMap<String, EfficientRater> raterMap = new HashMap<>();
        for (EfficientRater rater : raterList) {
            raterMap.put(rater.getID(), rater);
        }
        EfficientRater rater = raterMap.get(id);
        if (rater != null) {
            return rater.numRatings();
        } else {
            return 0;
        }
    }

    /**
     * A method to find the maximum number of ratings by any rater.
     * 
     * @param raterList list of raters to search
     */
    public void maxRatings(ArrayList<EfficientRater> raterList) {
        int maxRatings = 0;
        ArrayList<String> maxRaters = new ArrayList<String>();

        for (EfficientRater rater : raterList) {
            int numRatings = rater.numRatings();
            if (numRatings > maxRatings) {
                maxRatings = numRatings;
                maxRaters.clear();
                maxRaters.add(rater.getID());
            } else if (numRatings == maxRatings) {
                maxRaters.add(rater.getID());
            }
        }

        System.out.println("Most ratings by a rater: " + maxRatings);
        System.out.print("Raters with the most ratings: ");
        for (String raterId : maxRaters) {
            System.out.print(raterId + " ");
        }
        System.out.println();
    }

    /**
     * A method to find the number of ratings for a movie with a given ID.
     * 
     * @param raterList list of raters to search
     * @param movieID   movie ID to search for
     * @return the number of ratings for the given movie ID
     */
    public int mvRating(ArrayList<EfficientRater> raterList, String movie_id) {
        int count = 0;
        for (EfficientRater rater : raterList) {
            if (rater.hasRating(movie_id)) {
                count++;
            }
        }
        return count;
    }
    

    /**
     * A method to find the total number of movies rated by all raters.
     * 
     * @param raterList list of raters to search
     * @return the total number of movies rated
     */
    public int alMovie(ArrayList<EfficientRater> raterList) {
        Set<String> movieIds = new HashSet<String>();
        for (EfficientRater rater : raterList) {
            movieIds.addAll(rater.getItemsRated());
        }
        return movieIds.size();
    }
    

}
