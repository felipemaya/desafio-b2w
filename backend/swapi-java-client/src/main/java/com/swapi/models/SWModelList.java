package com.swapi.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Oleur on 22/12/2014.
 * Film list model
 */
public class SWModelList<T> implements Serializable {
    public int count;
    public String next;
    public String previous;
    public List<T> results;

    public boolean hasMore() {
        return (next != null && !next.isEmpty());
    }
}
