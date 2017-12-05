package authoring.eventManage;

@FunctionalInterface
public interface Function3<One, Two, Three, Four> {
    public Four apply(One one, Two two, Three three);
}
