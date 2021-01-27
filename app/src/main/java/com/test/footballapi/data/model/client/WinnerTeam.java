package com.test.footballapi.data.model.client;

import java.util.Objects;

public class WinnerTeam {
    private int id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinnerTeam that = (WinnerTeam) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }
}
