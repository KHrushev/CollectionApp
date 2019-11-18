package collectionapp;

import java.util.Objects;

public class Hobby {
    private String name;
    private int yearsSpent;

    public Hobby(String name, int yearsSpent) {
        this.name = name;
        this.yearsSpent = yearsSpent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearsSpent() {
        return yearsSpent;
    }

    public void setYearsSpent(int yearsSpent) {
        this.yearsSpent = yearsSpent;
    }

    @Override
    public String toString() {
        return "Hobby{" +
                "name='" + name + '\'' +
                ", yearsSpent=" + yearsSpent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hobby)) return false;
        Hobby hobby = (Hobby) o;
        return yearsSpent == hobby.yearsSpent &&
                name.equals(hobby.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, yearsSpent);
    }
}
