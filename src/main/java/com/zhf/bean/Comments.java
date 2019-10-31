package com.zhf.bean;

/**
 * Created on 2019/10/24 0024.
 */
public class Comments {
    private int commentId;
    private User user;
    private Film film;
    private double score;
    private String content;

    public Comments() {
    }

    public Comments(User user, Film film, double score, String content) {
        this.user = user;
        this.film = film;
        this.score = score;
        this.content = content;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("%-25s\t%-10.2f%s", film.getfName(), score, content);
    }
}
