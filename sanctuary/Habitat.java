package sanctuary;

import sanctuary.Elephant;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Habitat {
    private int capacity;
    private List<Elephant> data;

    public Habitat(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void add(Elephant elephant) {
        if (this.data.size() < capacity) {
            this.data.add(elephant);
        }
    }

    public boolean remove(String name) {
        for (Elephant elephant : this.data) {
            if (elephant.getName().equals(name)) {
                return this.data.remove(elephant);
            }
        }
        return false;
    }

    public Elephant getElephant(String retiredFrom) {
        for (Elephant elephant : this.data) {
            if (elephant.getRetiredFrom().equals(retiredFrom)) {
                return elephant;
            }
        }
        return null;
    }


    public Elephant getOldestElephant() {
        return data.stream().max(Comparator.comparingInt(Elephant::getAge)).orElse(null);
    }

    public int getAllElephants() {
        return data.size();
    }

    public String getReport() {
        StringBuilder buider = new StringBuilder();
        buider.append("Saved elephants in the park:").append("%n");
        for (Elephant elephant : this.data) {
            buider.append(elephant.getName()).append(" ").append("came from: ").append(elephant.getRetiredFrom())
                    .append("%n");
        }
        return buider.toString();
    }
}
