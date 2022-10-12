package bg.sofia.uni.fmi.mjt.spotify.account;

import bg.sofia.uni.fmi.mjt.spotify.library.Library;
import bg.sofia.uni.fmi.mjt.spotify.playable.Playable;

import java.util.Objects;

public class FreeAccount extends Account {
    private static AccountType type;
    private int listenPlayable;

    static {
        type = AccountType.FREE;
    }

    public FreeAccount(String email, Library library) {
        super(email, library);
        this.listenPlayable = 0;
    }

    @Override
    public int getAdsListenedTo() {
        return 0;
    }

    @Override
    public AccountType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FreeAccount)) return false;
        if (!super.equals(o)) return false;
        FreeAccount that = (FreeAccount) o;
        return (listenPlayable == that.listenPlayable) && (type == that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), listenPlayable, type);
    }

    public void listen(Playable playable) {
        this.listenPlayable++;
        if (listenPlayable == 5) {
            listenPlayable = 0;
            super.adsToListen++;
        }
        super.totalListenTime += playable.getDuration();

        playable.play();

    }
}
