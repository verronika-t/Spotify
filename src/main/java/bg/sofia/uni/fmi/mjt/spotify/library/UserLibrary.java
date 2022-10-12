package bg.sofia.uni.fmi.mjt.spotify.library;

import bg.sofia.uni.fmi.mjt.spotify.exceptions.EmptyLibraryException;
import bg.sofia.uni.fmi.mjt.spotify.exceptions.LibraryCapacityExceededException;
import bg.sofia.uni.fmi.mjt.spotify.exceptions.PlaylistCapacityExceededException;
import bg.sofia.uni.fmi.mjt.spotify.exceptions.PlaylistNotFoundException;
import bg.sofia.uni.fmi.mjt.spotify.playable.Playable;
import bg.sofia.uni.fmi.mjt.spotify.playlist.Playlist;
import bg.sofia.uni.fmi.mjt.spotify.playlist.UserPlaylist;

public class UserLibrary implements Library {

    private Playlist[] playlists;
    private Playlist likedContent;
    private int current;

    {
        this.playlists = new Playlist[21];
        this.likedContent = new UserPlaylist("Liked Content");
        this.current = 0;
    }

    @Override
    public void add(Playlist playlist) throws LibraryCapacityExceededException {
        if (current >= 21) {
            throw new LibraryCapacityExceededException();
        }
        this.playlists[current] = playlist;
        this.current++;
    }

    @Override
    public void remove(String name) throws EmptyLibraryException, PlaylistNotFoundException {
        if (name.equals("Liked Content")) {
            throw new IllegalArgumentException();
        }
        if (this.current == 0) {
            throw new EmptyLibraryException();
        }

        int index = getIndexOfPlaylist(name);
        removePlaylist(index);
    }

    @Override
    public Playlist getLiked() {
        return this.likedContent;
    }

    public void addToLiked(Playable playable) throws PlaylistCapacityExceededException {
        this.likedContent.add(playable);
    }

    private int getIndexOfPlaylist(String name) throws PlaylistNotFoundException {
        for (int i = 0; i < current; i++) {
            if (playlists[current].getName().equals(name)) {
                return i;
            }
        }
        throw new PlaylistNotFoundException();
    }

    private void removePlaylist(int index) {
        for (int i = index; i < current; i++) {
            this.playlists[i] = this.playlists[i + 1];
        }
    }
}
