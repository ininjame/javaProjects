import java.util.ArrayList;

public class Actor {
    private String name;
    private ArrayList<Movie> movies;

    public Actor(String name) {
        this.name = name;
        this.movies = new ArrayList<Movie>();
    }

    public void setActorName(String nameNew) {
        name = nameNew;
    }

    public String getActorName() {
        return name;
    }

    public void setMovies(ArrayList<Movie> moviesNew) {
        movies = moviesNew;
    }

    public void addMovies(Movie movie) {
        movies.add(movie);
    }

    public ArrayList<Movie> getMovieList() {
        return movies;
    }
}