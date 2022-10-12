package bg.sofia.uni.fmi.mjt.spotify.playlist;

import bg.sofia.uni.fmi.mjt.spotify.exceptions.PlaylistCapacityExceededException;
import bg.sofia.uni.fmi.mjt.spotify.playable.Playable;

public class UserPlaylist implements Playlist {

    private Playable[] playables;
    private String name;
    private int current;

    public UserPlaylist(String name) {
        this.playables = new Playable[20];
        this.name = name;
        this.current = 0;
    }

    @Override
    public void add(Playable playable) throws PlaylistCapacityExceededException {
        if (current >= 20) {
            throw new PlaylistCapacityExceededException();
        }
        this.playables[current] = playable;
        this.current++;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
