package capston;

public class GenreFilter implements Filter {
    private String myGenre;
    
    public GenreFilter(String genre) {
        myGenre = genre;
    }
    
    @Override
    public boolean satisfies(String id) {
        String[] genres = MovieDatabase.getGenres(id).split(",");
        for (String genre : genres) {
            if (genre.trim().equals(myGenre)) {
                return true;
            }
        }
        return false;
    }
}
