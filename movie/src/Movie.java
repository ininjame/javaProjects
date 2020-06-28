import java.util.ArrayList;

public class Movie {
    private String name;
    private ArrayList<Actor> actors;
    double rating;

    public Movie(String name, double rating) {
        this.name = name;
        this.actors = new ArrayList<Actor>();
        this.rating = rating;
    }

    public void setMovieName(String nameNew) {
        name = nameNew;
    }

    public String getMovieName() {
        return name;
    }

    public void setActorList(ArrayList<Actor> actorListNew) {
        actors = actorListNew;
    }

    public void addActor(Actor actorNew) {
        actors.add(actorNew);
    }

    public ArrayList<Actor> getActorList() {
        return actors;
    }

    public void setRating(double ratingNew) {
        rating = ratingNew;
    }

    public double getRating() {
        return rating;
    }
}