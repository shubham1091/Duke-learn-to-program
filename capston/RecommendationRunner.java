package capston;

import java.util.ArrayList;
import java.util.Random;

public class RecommendationRunner implements Recommender {
    public static void main(String[] args) {
        RecommendationRunner rr = new RecommendationRunner();
        rr.printRecommendationsFor("1041");
    }

    public ArrayList<String> getItemsToRate() {
        ArrayList<String> movieIds = new ArrayList<String>();
        Random rand = new Random();
        int numMovies = 15; // select 15 movies to rate

        // get a list of all movie IDs in the MovieDatabase
        ArrayList<String> allMovies = MovieDatabase.filterBy(new TrueFilter());

        // randomly select movie IDs to rate
        for (int i = 0; i < numMovies; i++) {
            int index = rand.nextInt(allMovies.size());
            String movieId = allMovies.get(index);
            movieIds.add(movieId);
            allMovies.remove(index);
        }

        return movieIds;
    }

    // implement the printRecommendationsFor method
    public void printRecommendationsFor(String webRaterID) {
        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        System.out.println("<p>Read data for " + Integer.toString(RaterDatabase.size()) + " raters</p>");
        System.out.println("<p>Read data for " + Integer.toString(MovieDatabase.size()) + " movies</p>");

        int numSimilarRaters = 50; // variable
        int minNumOfRatings = 5; // variable
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatings(webRaterID, numSimilarRaters,
                minNumOfRatings);

        if (similarRatings.size() == 0) {
            System.out.println("No matching movies were found");
        } else {
            String header = ("<table> <tr> <th>Movie Title</th> <th>Rating Value</th>  <th>Genres</th> </tr>");
            String body = "";
            for (Rating rating : similarRatings) {
                body += "<tr> <td>" + MovieDatabase.getTitle(rating.getItem()) + "</td> <td>"
                        + Double.toString(rating.getValue()) + "</td> <td>" + MovieDatabase.getGenres(rating.getItem())
                        + "</td> </tr> ";
            }
            System.out.println(header + body + "</table>");
        }
    }

}
