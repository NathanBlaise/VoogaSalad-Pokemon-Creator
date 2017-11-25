package authoring.eventManage;

@FunctionalInterface
interface Function<One, Two, Three> {
    public Three apply(One one, Two two);
}
