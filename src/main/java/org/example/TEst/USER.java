package org.example.TEst;

public class USER {
    private  int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "USER{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public USER(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
