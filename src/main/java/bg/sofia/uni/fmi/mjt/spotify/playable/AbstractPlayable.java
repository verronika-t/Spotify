package bg.sofia.uni.fmi.mjt.spotify.playable;

public abstract class AbstractPlayable implements Playable {
    private String title;
    private String artist;
    private int year;
    private double duration;

    private int totalPlays;
    private String type;

    public AbstractPlayable(String title, String artist, int year, double duration, String type) {
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.duration = duration;
        this.type = type;
    }


    public String play() {
        this.totalPlays++;
        return String.format("Currently playing %s content: %s.", this.type, this.title);
    }

    public int getTotalPlays() {
        return this.totalPlays;
    }

    public String getTitle() {
      return this.title;
    }

    public String getArtist() {
        return this.artist;
    }


    public int getYear() {
     return this.year;
    }

    public double getDuration(){
       return this.duration;
    }
}
