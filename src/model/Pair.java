package model;

/**
 *
 * @param <X> type of the first element of the pair
 * @param <Y> type of the second element of the pair
 */
public class Pair<X, Y> {

    private final X first;
    private final Y second;

    /**
     * Constructor method.
     * 
     * @param first the first element of the pair
     * @param second the second element of the pair
     */
    public Pair(final X first, final Y second) {
            this.first = first;
            this.second = second;
    }

    /**
     * Method that returns the first element of the pair.
     * 
     * @return the first element of the pair
     */
    public X getFirst() {
            return first;
    }

    /**
     * Method that returns the second element of the pair.
     * 
     * @return the second element of the pair
     */
    public Y getSecond() {
            return second;
    }

}
