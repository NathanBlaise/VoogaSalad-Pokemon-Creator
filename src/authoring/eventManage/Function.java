package authoring.eventManage;

@FunctionalInterface
public interface Function<One, Two, Three> {
    public Three apply(One one, Two two);
}
