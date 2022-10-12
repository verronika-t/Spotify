package bg.sofia.uni.fmi.mjt.spotify.account;

import bg.sofia.uni.fmi.mjt.spotify.library.Library;

import java.util.Objects;

public class PremiumAccount extends Account {
    private static AccountType type;

    static {
        type = AccountType.PREMIUM;
    }

    public PremiumAccount(String email, Library library) {
        super(email, library);
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
        PremiumAccount that = (PremiumAccount) o;
        return type == that.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }
}
