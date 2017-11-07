package vlad.kucher.spribe.model;

/**
 * Created by Vlad on 07.11.2017.
 */
public class Player {

    private String name;
    private double rating;

    public Player(String name, double rating) {
        this.name = name;
        this.rating = rating;
    }

    public Player(String name) {
        this.name = name;
        this.rating = 0.0;
    }

    private Player(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;

        Player player = (Player) o;

        return Double.compare(player.getRating(), getRating()) == 0 && (getName() != null ? getName().equals(player.getName()) : player.getName() == null);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getName() != null ? getName().hashCode() : 0;
        temp = Double.doubleToLongBits(getRating());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
