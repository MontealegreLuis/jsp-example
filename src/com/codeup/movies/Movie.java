/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.movies;

public class Movie {
    private int id;
    private String title;
    private int rating;
    private Category category;

    Movie(int id, String title, int rating) {
        this.id = id;
        this.title = title;
        this.rating = rating;
    }

    private Movie(String title, Category category) {
        this(0, title, 0);
        this.category = category;
    }

    void setId(int id) {
        this.id = id;
    }

    public Category category() {
        return category;
    }

    public void rate(int rate) {
        rating = rate;
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

    public static Movie titled(String title, Category category) {
        return new Movie(title, category);
    }
}
