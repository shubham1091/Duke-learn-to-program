package capston;

import java.util.*;

public class DirectorsFilter implements Filter {
    private Set<String> directorsSet;

    public DirectorsFilter(String directors) {
        directorsSet = new HashSet<>(Arrays.asList(directors.split(",")));
    }

    @Override
    public boolean satisfies(String id) {
        String directors = MovieDatabase.getDirector(id);
        for (String director : directors.split(",")) {
            if (directorsSet.contains(director)) {
                return true;
            }
        }
        return false;
    }
}
