import java.util.*;

public class MovieDatabase {
    private ArrayList<Movie> movieList;
    private ArrayList<Actor> actorList;

    public MovieDatabase() {
        this.movieList = new ArrayList<Movie>();
        this.actorList = new ArrayList<Actor>();
    }

    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    public ArrayList<Actor> getActorList() {
        return actorList;
    }

    public void addMovie(String name, String[] actors) {
        ArrayList<String> movieNameList = new ArrayList<String>();
        ArrayList<String> actorNameList = new ArrayList<String>();
        for (Movie movie : movieList) {
            movieNameList.add(movie.getMovieName());
        }

        for (Actor actor: actorList) {
            actorNameList.add(actor.getActorName());
        }
        

        if (movieNameList.contains(name) == false) {
            Movie movieNew = new Movie(name, 0.0);
        }
    }
}