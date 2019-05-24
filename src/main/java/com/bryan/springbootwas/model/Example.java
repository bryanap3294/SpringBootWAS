package com.bryan.springbootwas.model;

public class Example {

    private Integer id;
    private String Description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return "Example{" +
                "id=" + id +
                ", Description='" + Description + '\'' +
                '}';
    }
}
