package authoring.eventManage;

/**
 * Functional interface that acts as a function
 * that takes in three parameters and returns 
 * a result
 * @author Dan Sun for commenting
 *
 * @param <One> Type of parameter 1
 * @param <Two> Type of parameter 2
 * @param <Three> Type of parameter 3
 * @param <Four> The type of the returned variable
 */
@FunctionalInterface
public interface Function3<One, Two, Three, Four> {
    public Four apply(One one, Two two, Three three);
}
