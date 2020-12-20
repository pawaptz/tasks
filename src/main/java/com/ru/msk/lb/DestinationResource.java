package com.ru.msk.lb;

import java.util.Objects;

public class DestinationResource implements Resource {

    private final String url;
    private final int hash;

    public DestinationResource(String url) {
        this.url = url;
        this.hash = Objects.hash(url);
    }

    @Override
    public String description() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DestinationResource that = (DestinationResource) o;
        return Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return hash;
    }

    @Override
    public String toString() {
        return "DestinationResource{" +
                "url='" + url + '\'' +
                '}';
    }
}
