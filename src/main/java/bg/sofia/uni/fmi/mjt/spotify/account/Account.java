package bg.sofia.uni.fmi.mjt.spotify.account;

import bg.sofia.uni.fmi.mjt.spotify.library.Library;
import bg.sofia.uni.fmi.mjt.spotify.library.UserLibrary;
import bg.sofia.uni.fmi.mjt.spotify.playable.Playable;

import java.util.Objects;

public abstract class Account {

    protected String email;
    protected Library library;
    protected double totalListenTime;
    protected int adsToListen;

    public Account(String email, Library library) {
        this.email = email;
        this.library = library;
        this.adsToListen = 0;
    }

    /**
     * Returns the number of ads listened to.
     * - Free accounts get one ad after every 5 pieces of content played
     * - Premium accounts get no ads
     */
    public abstract int getAdsListenedTo();

    /**
     * Returns the account type as an enum with possible values FREE and PREMIUM
     */
    public abstract AccountType getType();

    /**
     * Simulates listening of the specified content.
     * This should increment the total number of content listened and the total listen time for this account.
     *
     * @param playable
     */
    public void listen(Playable playable) {
        playable.play();
        this.totalListenTime += playable.getDuration();
    }

    /**
     * Returns the library for this account.
     */
    public Library getLibrary() {
        return library;
    }

    /**
     * Returns the total listen time for this account. The time for any ads listened is not included.
     */
    public double getTotalListenTime() {
        return totalListenTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Double.compare(account.totalListenTime, totalListenTime) == 0 && adsToListen == account.adsToListen && Objects.equals(email, account.email) && Objects.equals(library, account.library);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, library, totalListenTime, adsToListen);
    }
}
