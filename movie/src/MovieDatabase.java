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
            movieNameList.add(name);
        } else {
            for (String actor : actors) {
                ArrayList<String> movieNameListActor = new ArrayList<String>();
                for (Movie movie : actorList.get(actorNameList.indexOf(actor)).getMovieList()) {
                    movieNameListActor.add(movie.getMovieName());
                }
                if (movieNameListActor.contains(name) == false) {
                    actorList.get(actorNameList.indexOf(actor)).addMovies(movieList.get(movieNameList.indexOf(name)));
                }
                // if (actorNameList.contains(actor) == false) {
                //     Actor actorNew = new Actor(actor);
                //     actorNew.addMovies(movieList.get(movieNameList.indexOf(name)));
                //     actorList.add(actorNew);
                // }
            }

        }
    }

    public void addRating(String name, double rating) {
        for (Movie movie : movieList) {
            if (movie.getMovieName().equals(name)) {
                movie.setRating(rating);
            }
        }
        for (Actor actor : actorList) {
            for (Movie movie : actor.getMovieList()) {
                if (movie.getMovieName().equals(name)) {
                    movie.setRating(rating);
                }
            }
        } 
    }

    public void updateRating(String name, double newRating) {
        addRating(name, newRating);
    }

    public static double calcActorRating(Actor actor) {

        double avgScore = 0.0;
        for (Movie movie:actor.getMovieList()) {
            avgScore = avgScore + movie.getRating();
        }
        avgScore = avgScore / actor.getMovieList().size();
        
        return avgScore;
    }

    public String getBestActor() {
        ArrayList<Double> actorScoreList = new ArrayList<Double>();
        for (Actor actor:actorList) {
            double avgScore = calcActorRating(actor);
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
                String[] newLine = file.next().split(", ");
                String[] actorListNew = new String[1];
                for (int i = 1; i < newLine.length;i++) {
                    String movieNameNew = newLine[i];
                    actorListNew[0] = newLine[0];
                    newDB.addMovie(movieNameNew, actorListNew);
                }
            }
            
            // System.out.println(file.next());
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(0);
        }

        ArrayList<String> movieNameList = new ArrayList<String>();
        for (Movie movie : newDB.movieList) {
            movieNameList.add(movie.getMovieName());
        }

        try {
            ArrayList<String> movieRatedList = new ArrayList<String>();
            ArrayList<Double> movieRatingList = new ArrayList<Double>();
            Scanner ratingFile = new Scanner(new File("ratings.txt"));
            ratingFile.nextLine();
            while (ratingFile.hasNextLine()) {
                String[] movieAndRating = ratingFile.nextLine().split("\\t+");
                // System.out.println(movieAndRating.length);
                movieRatedList.add(movieAndRating[0]);
                movieRatingList.add(Double.parseDouble(movieAndRating[1]));
            }
            for (int i = 0; i < movieRatedList.size(); i++) {
                newDB.addRating(movieRatedList.get(i), movieRatingList.get(i));
            }
            // for (Movie movie : newDB.movieList) {
                // if (movie.getMovieName() == movieRatedList.get(1)) {
                    // System.out.println(movieRatedList.get(1));
                // }
            // }


        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(0);
        }

  
        double bestRating = 0.0;
        String bestActor = "";
        for (Actor actor : newDB.actorList) {
            // System.out.println(actor.getActorName() + ": " + calcActorRating(actor));
            if (bestRating < calcActorRating(actor)) {
                bestRating = calcActorRating(actor);
                bestActor = actor.getActorName();
            }
        }
        System.out.println(newDB.getBestActor());
        System.out.println(bestActor + ": " + bestRating);
        
        double bestRatingMovie = 0.0;
        String bestMovie = "";
        for (Movie movie : newDB.movieList) {
            if (bestRatingMovie < movie.getRating()) {
                bestRatingMovie = movie.getRating();
                bestMovie = movie.getMovieName();
            }
        }

        System.out.println(newDB.getBestMovie());
        System.out.println(bestMovie + ": " + bestRatingMovie);
    }
}
