package com.bbc.repository;

import com.bbc.api.Programme;

import java.util.concurrent.ConcurrentHashMap;

public class Store {

    private static final Store instance = new Store();
    private final ConcurrentHashMap<Long, Programme> repository = new ConcurrentHashMap<Long, Programme>();

    private Store() {
        resetStore();
    }

    public static synchronized Store getInstance() {
        return instance;
    }

    public ConcurrentHashMap<Long, Programme> getRepository() {
        return repository;
    }

    void resetStore() {
        repository.clear();

        Programme programme1 = new Programme(1L, "John Talk Show");
        programme1.setCategory("entertainment");
        programme1.setAvailable(true);
        repository.put(programme1.getId(), programme1);

        Programme programme2 = new Programme(2L, "Today News");
        programme2.setCategory("news");
        programme2.setAvailable(true);
        repository.put(programme2.getId(), programme2);

        Programme programme3 = new Programme(3L, "Disney Channel");
        programme3.setCategory("entertainment");
        programme3.setAvailable(false);
        repository.put(programme3.getId(), programme3);
    }
}
