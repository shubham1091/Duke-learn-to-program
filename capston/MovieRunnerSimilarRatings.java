package capston;

import java.util.ArrayList;

public class MovieRunnerSimilarRatings {

    private static final String RATING_FILENAME = "ratings.csv";
    private static final String MOVIE_FILENAME = "ratedmoviesfull.csv";

    public static void main(String[] args) {
        MovieRunnerSimilarRatings runner = new MovieRunnerSimilarRatings();
        runner.printAverageRatings();
        runner.printAverageRatingsByYearAfterAndGenre();
        runner.printSimilarRatings();
        runner.printSimilarRatingsByGenre();
        runner.printSimilarRatingsByDirector();
        runner.printSimilarRatingsByGenreAndMinutes();
        runner.printSimilarRatingsByYearAfterAndMinutes();
    }

    public void printAverageRatings() {
        int minNumOfRatings = 35;

        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(RATING_FILENAME);
        MovieDatabase.initialize(MOVIE_FILENAME);
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");

        ArrayList<Rating> avgRatings = fr.getAverageRatings(minNumOfRatings);
        System.out.println("found " + avgRatings.size() + " movies");

        // Sort by rating value in descending order
        avgRatings.sort((r1, r2) -> Double.compare(r2.getValue(), r1.getValue()));

        // Print each movie's rating
        for (Rating rating : avgRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        int year = 1990;
        String genre = "Drama";
        int minNumOfRatings = 8;

        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(RATING_FILENAME);
        MovieDatabase.initialize(MOVIE_FILENAME);
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");

        AllFilters filterCriteria = new AllFilters();
        filterCriteria.addFilter(new YearAfterFilter(year));
        filterCriteria.addFilter(new GenreFilter(genre));
        ArrayList<Rating> avgRatings = fr.getAverageRatingsByFilter(minNumOfRatings, filterCriteria);
        System.out.println("found " + avgRatings.size() + " movies");

        // Sort by rating value in descending order
        avgRatings.sort((r1, r2) -> Double.compare(r2.getValue(), r1.getValue()));

        // Print each movie's rating
        for (Rating rating : avgRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) + " "
                    + MovieDatabase.getTitle(rating.getItem()) + "\n    " + MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printSimilarRatings() {
        String raterID = "337";
        int minimalRaters = 3;
        int numSimilarRaters = 10;

        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(RATING_FILENAME);
        MovieDatabase.initialize(MOVIE_FILENAME);
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");

        ArrayList<Rating> similarRatings = fr.getSimilarRatings(raterID, numSimilarRaters, minimalRaters);
        if (similarRatings.isEmpty()) {
            System.out.println("No movies found that meet the criteria.");
            return;
        }

        // Sort by rating value in descending order
        similarRatings.sort((r1, r2) -> Double.compare(r2.getValue(), r1.getValue()));

        // Print the top rated movie
        Rating topRatedMovie = similarRatings.get(0);
        System.out.println(
                "The movie returned with the top rated average is: " + MovieDatabase.getTitle(topRatedMovie.getItem()));
    }

    public void printSimilarRatingsByGenre() {
        String raterID = "964";
        int minimalRaters = 5;
        int numSimilarRaters = 20;
        String genre = "Mystery";

        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(RATING_FILENAME);
        MovieDatabase.initialize(MOVIE_FILENAME);
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");

        Filter genreFilter = new GenreFilter(genre);
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters,
                genreFilter);

        if (similarRatings.isEmpty()) {
            System.out.println("No movies found that meet the criteria.");
            return;
        }

        // Sort by rating value in descending order
        similarRatings.sort((r1, r2) -> Double.compare(r2.getValue(), r1.getValue()));

        // Print the top rated movie
        Rating topRatedMovie = similarRatings.get(0);
        System.out.println(
                "The movie returned with the top rated average is: " + MovieDatabase.getTitle(topRatedMovie.getItem()));

    }

    public void printSimilarRatingsByDirector() {
        String raterID = "120";
        int minimalRaters = 2;
        int numSimilarRaters = 10;
        String directors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";

        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(RATING_FILENAME);
        MovieDatabase.initialize(MOVIE_FILENAME);
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");

        Filter directorFilter = new DirectorsFilter(directors);
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters,
                directorFilter);

        if (similarRatings.isEmpty()) {
            System.out.println("No movies found that meet the criteria.");
            return;
        }

        // Sort by rating value in descending order
        similarRatings.sort((r1, r2) -> Double.compare(r2.getValue(), r1.getValue()));

        // Print the top rated movie
        Rating topRatedMovie = similarRatings.get(0);
        System.out.println(
                "The movie returned with the top rated average is: " + MovieDatabase.getTitle(topRatedMovie.getItem()));

    }

    public void printSimilarRatingsByGenreAndMinutes() {
        String raterID = "168";
        int minimalRaters = 3;
        int numSimilarRaters = 10;
        String genre = "Drama";
        int minMinutes = 80;
        int maxMinutes = 160;

        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(RATING_FILENAME);
        MovieDatabase.initialize(MOVIE_FILENAME);
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");

        AllFilters filters = new AllFilters();
        filters.addFilter(new GenreFilter(genre));
        filters.addFilter(new MinutesFilter(minMinutes, maxMinutes));

        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters,
                filters);

        if (similarRatings.isEmpty()) {
            System.out.println("No movies found that meet the criteria.");
            return;
        }

        // Sort by rating value in descending order
        similarRatings.sort((r1, r2) -> Double.compare(r2.getValue(), r1.getValue()));

        // Print the top rated movie
        Rating topRatedMovie = similarRatings.get(0);
        System.out.println(
                "The movie returned with the top rated average is: " + MovieDatabase.getTitle(topRatedMovie.getItem()));
    }

    public void printSimilarRatingsByYearAfterAndMinutes() {
        String raterID = "314";
        int minimalRaters = 5;
        int numSimilarRaters = 10;
        int yearAfter = 1975;
        int minMinutes = 70;
        int maxMinutes = 200;

        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(RATING_FILENAME);
        MovieDatabase.initialize(MOVIE_FILENAME);
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");

        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(yearAfter));
        allFilters.addFilter(new MinutesFilter(minMinutes, maxMinutes));

        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters,
                allFilters);

        if (similarRatings.isEmpty()) {
            System.out.println("No movies found that meet the criteria.");
            return;
        }

        // Sort by rating value in descending order
        similarRatings.sort((r1, r2) -> Double.compare(r2.getValue(), r1.getValue()));

        // Print the top rated movie
        Rating topRatedMovie = similarRatings.get(0);
        System.out.println(
                "The movie returned with the top rated average is: " + MovieDatabase.getTitle(topRatedMovie.getItem()));

    }

}
