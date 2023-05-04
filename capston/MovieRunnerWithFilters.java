package capston;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {
    public static void main(String[] args) {
        MovieRunnerWithFilters mf = new MovieRunnerWithFilters();
        // mf.printAverageRatings();
        // mf.printAverageRatingsByYear();
        // mf.printAverageRatingsByGenre();
        // mf.printAverageRatingsByMinutes();
        // mf.printAverageRatingsByDirectors();
        // mf.printAverageRatingsByYearAfterAndGenre();
        mf.printAverageRatingsByDirectorsAndMinutes();
    }

    /**
     * 
     * Prints the list of movies with an average rating of at least minimalRaters.
     * It also displays the number of movies and raters used for computing the
     * average rating.
     */
    public void printAverageRatings() {
        int minimalRaters = 35;
        ThirdRatings sr = new ThirdRatings("data/capston/ratings.csv");
        MovieDatabase.initialize("capston/ratedmoviesfull.csv");
        System.out.println("Number of Raters: " + sr.getRaterSize());
        System.out.println("Number of Movies: " + MovieDatabase.size());

        ArrayList<Rating> ratingList = sr.getAverageRatings(minimalRaters);

        Collections.sort(ratingList);

        System.out.println("Movies with at least " + minimalRaters + " ratings: " + ratingList.size());
        for (Rating r : ratingList) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }

    }

    public void printAverageRatingsByYear() {
        ThirdRatings thirdRatings = new ThirdRatings("data/capston/ratings.csv");
        MovieDatabase.initialize("capston/ratedmoviesfull.csv");

        System.out.println("Read data for " + thirdRatings.getRaterSize() + " raters");

        System.out.println("Read data for " + MovieDatabase.size() + " movies");

        int minimalRaters = 20;
        int year = 2000;
        YearAfterFilter filterCriteria = new YearAfterFilter(year);

        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(minimalRaters, filterCriteria);

        System.out.println("Found " + averageRatings.size() + " movies");

        Collections.sort(averageRatings);

        for (Rating rating : averageRatings) {
            double averageRating = rating.getValue();
            String movieID = rating.getItem();
            int movieYear = MovieDatabase.getYear(movieID);
            String movieTitle = MovieDatabase.getTitle(movieID);

            System.out.println(averageRating + " " + movieYear + " " + movieTitle);
        }
    }

    public void printAverageRatingsByGenre() {
        ThirdRatings thirdRatings = new ThirdRatings("data/capston/ratings.csv");
        MovieDatabase.initialize("capston/ratedmoviesfull.csv");

        int minimalRaters = 20;
        String genre = "Comedy";
        GenreFilter genreFilter = new GenreFilter(genre);
        ArrayList<Rating> ratings = thirdRatings.getAverageRatingsByFilter(minimalRaters, genreFilter);

        System.out.println("read data for " + thirdRatings.getRaterSize() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("found " + ratings.size() + " movies");

        for (Rating rating : ratings) {
            String movieID = rating.getItem();
            Movie movie = MovieDatabase.getMovie(movieID);
            System.out.println(rating.getValue() + " " + movie.getTitle());

            System.out.print("    ");
            System.out.println(movie.getGenres());
        }
    }

    public void printAverageRatingsByMinutes() {
        int minMinutes = 105;
        int maxMinutes = 135;
        int minimalRaters = 5;

        ThirdRatings thirdRatings = new ThirdRatings("data/capston/ratings.csv");
        MovieDatabase.initialize("capston/ratedmoviesfull.csv");
        MinutesFilter filter = new MinutesFilter(minMinutes, maxMinutes);

        ArrayList<Rating> ratings = thirdRatings.getAverageRatingsByFilter(minimalRaters, filter);

        System.out.println("Read data for " + thirdRatings.getRaterSize() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        System.out.println("Found " + ratings.size() + " movies");

        Collections.sort(ratings);

        for (Rating rating : ratings) {
            String movieID = rating.getItem();
            int minutes = MovieDatabase.getMinutes(movieID);
            String title = MovieDatabase.getTitle(movieID);

            System.out.printf("%.2f Time: %d %s\n", rating.getValue(), minutes, title);
        }
    }

    public void printAverageRatingsByDirectors() {
        ThirdRatings thirdRatings = new ThirdRatings("data/capston/ratings.csv");
        MovieDatabase.initialize("capston/ratedmoviesfull.csv");

        System.out.println("read data for " + thirdRatings.getRaterSize() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");

        String directorsList = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        DirectorsFilter directorsFilter = new DirectorsFilter(directorsList);

        int minimalRaters = 4;
        ArrayList<Rating> filteredRatings = thirdRatings.getAverageRatingsByFilter(minimalRaters, directorsFilter);

        System.out.println("Found " + filteredRatings.size() + " movies");

        Collections.sort(filteredRatings);

        for (Rating rating : filteredRatings) {
            String movieID = rating.getItem();
            String movieTitle = MovieDatabase.getTitle(movieID);
            String directors = MovieDatabase.getDirector(movieID);
            System.out.println(rating.getValue() + " " + movieTitle + "\n\t" + directors);
        }
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        int minRatings = 8;
        int yearAfter = 1990;
        String genre = "Drama";

        ThirdRatings thirdRatings = new ThirdRatings("data/capston/ratings.csv");
        MovieDatabase.initialize("capston/ratedmoviesfull.csv");

        System.out.println("read data for " + thirdRatings.getRaterSize() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");

        AllFilters filters = new AllFilters();
        filters.addFilter(new YearAfterFilter(yearAfter));
        filters.addFilter(new GenreFilter(genre));

        ArrayList<Rating> avgRatings = thirdRatings.getAverageRatingsByFilter(minRatings, filters);
        System.out.println("Number of movies found: " + avgRatings.size());

        if (avgRatings.size() > 0) {
            for (Rating rating : avgRatings) {
                String movieTitle = MovieDatabase.getTitle(rating.getItem());
                int movieYear = MovieDatabase.getYear(rating.getItem());
                String movieGenres = MovieDatabase.getGenres(rating.getItem());

                System.out.printf("%.2f %d %s\n", rating.getValue(), movieYear, movieTitle);
                System.out.println("    " + movieGenres);
            }
        } else {
            System.out.println("No movies found");
        }
    }

    public void printAverageRatingsByDirectorsAndMinutes() {

        ThirdRatings thirdRatings = new ThirdRatings("data/capston/ratings.csv");
        MovieDatabase.initialize("capston/ratedmoviesfull.csv");

        System.out.println("read data for " + thirdRatings.getRaterSize() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        allFilters.addFilter(new MinutesFilter(90, 180));

        int minimalRaters = 3;
        ArrayList<Rating> ratings = thirdRatings.getAverageRatingsByFilter(minimalRaters, allFilters);
        Collections.sort(ratings);
        System.out.println(ratings.size() + " movies matched");
        for (Rating rating : ratings) {
            String movieID = rating.getItem();
            double movieRating = rating.getValue();
            String movieTitle = MovieDatabase.getTitle(movieID);
            int movieMinutes = MovieDatabase.getMinutes(movieID);
            String movieDirectors = MovieDatabase.getDirector(movieID);
            System.out.println(movieRating + " Time: " + movieMinutes + " " + movieTitle);
            System.out.println("    " + movieDirectors);
        }
    }

}
