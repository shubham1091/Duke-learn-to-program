package capston;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FourthRatings {

    public double getAverageByID(String id, int minimalRaters) {
        double sum = 0.0;
        int count = 0;
        for (Rater rater : RaterDatabase.getRaters()) {
            if (rater.hasRating(id)) {
                sum += rater.getRating(id);
                count++;
            }
        }
        if (count >= minimalRaters) {
            return sum / count;
        }
        return 0.0;
    }
    

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String movie : movies) {
            double avg = getAverageByID(movie, minimalRaters);
            if (avg != 0.0) {
                avgRatings.add(new Rating(movie, avg));
            }
        }
        return avgRatings;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (String movie : movies) {
            double avg = getAverageByID(movie, minimalRaters);
            if (avg != 0.0) {
                avgRatings.add(new Rating(movie, avg));
            }
        }
        return avgRatings;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> weightedRatings = new ArrayList<>();

        // Get the list of movies and their ratings from the MovieDatabase
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

        // Create a map to store the total weighted ratings and number of raters for
        // each movie
        Map<String, Double> movieWeightedRatingSum = new HashMap<>();
        Map<String, Integer> movieNumRaters = new HashMap<>();

        // Loop through all raters to find the top similar raters
        ArrayList<Rating> similarities = getSimilarities(id);
        for (int i = 0; i < numSimilarRaters && i < similarities.size(); i++) {
            Rating similarity = similarities.get(i);
            String raterID = similarity.getItem();
            Rater rater = RaterDatabase.getRater(raterID);

            // Loop through all movies to calculate the weighted rating for each movie
            for (String movieID : movies) {
                if (rater.hasRating(movieID)) {
                    double rating = rater.getRating(movieID);
                    double weightedRating = rating * similarity.getValue();

                    // Update the total weighted rating and number of raters for the movie
                    movieWeightedRatingSum.put(movieID,
                            movieWeightedRatingSum.getOrDefault(movieID, 0.0) + weightedRating);
                    movieNumRaters.put(movieID, movieNumRaters.getOrDefault(movieID, 0) + 1);
                }
            }
        }

        // Loop through all movies to calculate the weighted average rating
        for (String movieID : movies) {
            int numRaters = movieNumRaters.getOrDefault(movieID, 0);

            // Skip the movie if it doesn't have enough minimalRaters
            if (numRaters < minimalRaters) {
                continue;
            }

            double weightedRatingSum = movieWeightedRatingSum.getOrDefault(movieID, 0.0);
            double weightedAverageRating = weightedRatingSum / numRaters;
            weightedRatings.add(new Rating(movieID, weightedAverageRating));
        }

        Collections.sort(weightedRatings, Collections.reverseOrder());
        return weightedRatings;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters,
            Filter filterCriteria) {
        ArrayList<Rating> weightedRatings = new ArrayList<>();

        // Get the list of movies and their ratings that match the filter criteria
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);

        // Create a map to store the total weighted ratings and number of raters for
        // each movie
        Map<String, Double> movieWeightedRatingSum = new HashMap<>();
        Map<String, Integer> movieNumRaters = new HashMap<>();

        // Loop through all raters to find the top similar raters
        ArrayList<Rating> similarities = getSimilarities(id);
        for (int i = 0; i < numSimilarRaters && i < similarities.size(); i++) {
            Rating similarity = similarities.get(i);
            String raterID = similarity.getItem();
            Rater rater = RaterDatabase.getRater(raterID);

            // Loop through all movies to calculate the weighted rating for each movie
            for (String movieID : movies) {
                if (rater.hasRating(movieID)) {
                    double rating = rater.getRating(movieID);
                    double weightedRating = rating * similarity.getValue();

                    // Update the total weighted rating and number of raters for the movie
                    movieWeightedRatingSum.put(movieID,
                            movieWeightedRatingSum.getOrDefault(movieID, 0.0) + weightedRating);
                    movieNumRaters.put(movieID, movieNumRaters.getOrDefault(movieID, 0) + 1);
                }
            }
        }

        // Loop through all movies to calculate the weighted average rating
        for (String movieID : movies) {
            int numRaters = movieNumRaters.getOrDefault(movieID, 0);

            // Skip the movie if it doesn't have enough minimalRaters
            if (numRaters < minimalRaters) {
                continue;
            }

            double weightedRatingSum = movieWeightedRatingSum.getOrDefault(movieID, 0.0);
            double weightedAverageRating = weightedRatingSum / numRaters;
            weightedRatings.add(new Rating(movieID, weightedAverageRating));
        }

        Collections.sort(weightedRatings, Collections.reverseOrder());
        return weightedRatings;
    }

    private double dotProduct(Rater me, Rater r) {
        double dotProduct = 0.0;
        for (String item : me.getItemsRated()) {
            if (r.hasRating(item)) {
                double rating1 = me.getRating(item) - 5;
                double rating2 = r.getRating(item) - 5;
                dotProduct += rating1 * rating2;
            }
        }
        return dotProduct;
    }

    private ArrayList<Rating> getSimilarities(String id) {
        Rater me = RaterDatabase.getRater(id);
        ArrayList<Rating> ratings = new ArrayList<>();
        for (Rater r : RaterDatabase.getRaters()) {
            if (!r.getID().equals(id)) {
                double dotProduct = dotProduct(me, r);
                if (dotProduct > 0) {
                    ratings.add(new Rating(r.getID(), dotProduct));
                }
            }
        }
        Collections.sort(ratings, Collections.reverseOrder());
        return ratings;
    }

}
