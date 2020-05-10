package com.asu.storia.model;

import java.util.List;

public class UserStories {
    private String name;
    private List<String> stories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getStories() {
        return stories;
    }

    public void setStories(List<String> stories) {
        this.stories = stories;
    }
}
