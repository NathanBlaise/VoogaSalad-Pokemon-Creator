package authoring.eventManage;

/**
 * Functional interface that acts as a function
 * that takes in two parameters and returns 
 * a result
 * @author Dan Sun for commenting
 *
 * @param <One> Type of parameter 1
 * @param <Two> Type of parameter 2
 * @param <Three> The type of the returned variable
 */
@FunctionalInterface
public interface Function<One, Two, Three> {
    public Three apply(One one, Two two);
}
