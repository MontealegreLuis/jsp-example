/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.movies;

public class Movie {
    private final int id;
    private final String title;
    private int rating;

    public Movie(int id, String title, int rating) {
        this.id = id;
        this.title = title;
        this.rating = rating;
    }

    public String title() {
        return title;
    }

    public int id() {
        return id;
    }

    public int rating() {
        return rating;
    }
}
