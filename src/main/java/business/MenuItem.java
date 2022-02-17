package business;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {
    public abstract int computePrice();
    public abstract String getTitle();
    public abstract double getRating();
    public abstract int getCalories();
    public abstract int getProtein();
    public abstract int getFat();
    public abstract int getSodium();
    public abstract String toString();
}
