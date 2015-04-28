package foo.bar.foo.bar.domain;

public class Observation {

    private final long id;
    private final String name;
    private final String time;
    //this field has the name D in the JSON, but I was not sure where to put the mapping
    private final String direction;

    public Observation(long id, String name, String time, String direction) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.direction = direction;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Observation observation = (Observation) o;

        if (id != observation.id) return false;
        if (!direction.equals(observation.direction)) return false;
        if (!name.equals(observation.name)) return false;
        if (!time.equals(observation.time)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + time.hashCode();
        result = 31 * result + direction.hashCode();
        return result;
    }
}
