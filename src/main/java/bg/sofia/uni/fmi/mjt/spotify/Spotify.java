package bg.sofia.uni.fmi.mjt.spotify;

import bg.sofia.uni.fmi.mjt.spotify.account.Account;
import bg.sofia.uni.fmi.mjt.spotify.account.AccountType;
import bg.sofia.uni.fmi.mjt.spotify.exceptions.AccountNotFoundException;
import bg.sofia.uni.fmi.mjt.spotify.exceptions.PlayableNotFoundException;
import bg.sofia.uni.fmi.mjt.spotify.exceptions.PlaylistCapacityExceededException;
import bg.sofia.uni.fmi.mjt.spotify.exceptions.StreamingServiceException;
import bg.sofia.uni.fmi.mjt.spotify.playable.Playable;

public class Spotify implements StreamingService {
    private Account[] accounts;
    private Playable[] playableContent;

    public Spotify(Account[] accounts, Playable[] playableContent) {
        this.accounts = accounts;
        this.playableContent = playableContent;
    }

    @Override
    public void play(Account account, String title) throws AccountNotFoundException, PlayableNotFoundException {
        if (account == null || title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (!isRegisteredAccount(account)) {
            throw new AccountNotFoundException();
        }

        Playable playable = getPlayable(title);

        if (playable == null) {
            throw new PlayableNotFoundException();
        }

        account.listen(playable);

    }

    @Override
    public void like(Account account, String title) throws AccountNotFoundException, PlayableNotFoundException, StreamingServiceException {
        if (account == null || title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (!isRegisteredAccount(account)) {
            throw new AccountNotFoundException();
        }

        Playable playable = getPlayable(title);

        if (playable == null) {
            throw new PlayableNotFoundException();
        }

        try {
            account.getLibrary().getLiked().add(playable);
        } catch (PlaylistCapacityExceededException e) {
            throw new StreamingServiceException();
        }
    }

    @Override
    public Playable findByTitle(String title) throws PlayableNotFoundException {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        return getPlayable(title);
    }

    @Override
    public Playable getMostPlayed() {
        Playable mostPlayed = null;

        for (int i = 0; i < playableContent.length; i++) {
            if (mostPlayed == null) {
                mostPlayed = playableContent[i];
            } else {
                if (mostPlayed.getTotalPlays() < playableContent[i].getTotalPlays()) {
                    mostPlayed = playableContent[i];
                }
            }
        }
        return mostPlayed;
    }

    @Override
    public double getTotalListenTime() {
        double total = 0;
        for (int i = 0; i < accounts.length; i++) {
            total += accounts[i].getTotalListenTime();
        }
        return total;
    }

    @Override
    public double getTotalPlatformRevenue() {
        double total = 0;
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getType() == AccountType.FREE) {
                total += accounts[i].getAdsListenedTo() * 0.10;
            } else {
                total += 25.00;
            }
        }
        return total;
    }

    private boolean isRegisteredAccount(Account account) {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].equals(account)) {
                return true;
            }
        }
        return false;
    }

    private Playable getPlayable(String title) {
        for (Playable playable : playableContent) {
            if (playable.getTitle().equals(title)) {
                return playable;
            }
        }
        return null;
    }

}
