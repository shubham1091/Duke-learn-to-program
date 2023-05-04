package capston;

import java.util.ArrayList;
import java.util.Random;

public class RecommendationRunner implements Recommender {
    
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
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> recommendedMovies = fr.getSimilarRatings(webRaterID, 10, 3);
        
        if (recommendedMovies.size() == 0) {
            System.out.println("<p>Sorry, no movie recommendations at this time.</p>");
            return;
        }
        
        StringBuilder html = new StringBuilder();
        html.append("<style>table, th, td { border: 1px solid black; border-collapse: collapse; padding: 5px; }</style>");
        html.append("<table>");
        html.append("<tr><th>Title</th><th>Year</th><th>Genres</th></tr>");
        
        int count = 0;
        for (Rating rating : recommendedMovies) {
            Movie movie = MovieDatabase.getMovie(rating.getItem());
            if (count < 20 && !RaterDatabase.getRater(webRaterID).hasRating(movie.getID())) {
                html.append("<tr><td>" + movie.getTitle() + "</td><td>" + movie.getYear() + "</td><td>" + movie.getGenres() + "</td></tr>");
                count++;
            }
        }
        
        html.append("</table>");
        System.out.println(html.toString());
    }
    
}
