import java.util.*;
import java.io.*;

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
        

        for (String actorName: actors) {
            if (actorNameList.contains(actorName) == false) {
                Actor actorNew = new Actor(actorName);
                actorList.add(actorNew);
                actorNameList.add(actorName);
            }
        }

        if (movieNameList.contains(name) == false) {
            Movie movieNew = new Movie(name, 0.0);
            for (Actor actor: actorList) {
                if (Arrays.asList(actors).contains(actor.getActorName())) {
                    actor.addMovies(movieNew);
                    movieNew.addActor(actor);
                }
            }
            movieList.add(movieNew);
        } else {
            for (String actor : actors) {
                if (actorNameList.contains(actor) == false) {
                    Actor actorNew = new Actor(actor);
                    actorNew.addMovies(movieList.get(movieNameList.indexOf(name)));
                }
            }

        }
    }

    public void addRating(String name, double rating) {
        ArrayList<String> movieNameList = new ArrayList<String>();
        for (Movie movie : movieList) {
            movieNameList.add(movie.getMovieName());
        }
        int movieIndex = movieNameList.indexOf(name);
        Movie movieReferred = movieList.get(movieIndex);
        movieReferred.setRating(rating);
        movieList.set(movieIndex, movieReferred);  
    }

    public void updateRating(String name, double newRating) {
        ArrayList<String> movieNameList = new ArrayList<String>();
        for (Movie movie : movieList) {
            movieNameList.add(movie.getMovieName());
        }
        int movieIndex = movieNameList.indexOf(name);
        Movie movieReferred = movieList.get(movieIndex);
        movieReferred.setRating(newRating);
        movieList.set(movieIndex, movieReferred);  
    }

    public String getBestActor() {
        ArrayList<Double> actorScoreList = new ArrayList<Double>();
        for (Actor actor:actorList) {
            double avgScore = 0.0;
            for (Movie movie:actor.getMovieList()) {
                avgScore = avgScore + movie.getRating();
            }
            avgScore = avgScore / actor.getMovieList().size();
            actorScoreList.add(avgScore);
        }

        Actor actorHighestRated = actorList.get(actorScoreList.indexOf(Collections.max(actorScoreList)));

        return actorHighestRated.getActorName();
    }

    public String getBestMovie() {
        ArrayList<Double> movieScoreList = new ArrayList<Double>();
        for (Movie movie:movieList) {
            movieScoreList.add(movie.getRating());
        }
        Movie movieHighestRated = movieList.get(movieScoreList.indexOf(Collections.max(movieScoreList)));
        return movieHighestRated.getMovieName();
    }


    public static void main(String[] args) {
        MovieDatabase newDB = new MovieDatabase();
        try {
            Scanner file = new Scanner(new File("movies.txt"));
            file.useDelimiter("\\n");
            while (file.hasNext()) {
                String[] newLine = file.next().split(",",0);
                ArrayList<String> actorListNew = new ArrayList<String>();
                String movieNameNew = newLine[0];
                for (int i = 1; i < newLine.length; i++) {
                    actorListNew.add(newLine[i]);
                }
                String[] actorListNewArray = actorListNew.toArray(new String[0]);
                newDB.addMovie(movieNameNew, actorListNewArray);
            }
            // System.out.println(newDB.movieList.get(1).getMovieName());
            System.out.println(newDB.movieList.size());
            // System.out.println(file.next());
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(0);
        }
    }
}
