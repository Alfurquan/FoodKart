package com.foodkart.models;

/**
 * Model class for Review
 */
public class Review {
    private final String id;
    private final int score;
    private final String comment;

    /**
     * @param id id of the review
     * @param score score of the review
     * @param comment comment of the review
     */
    public Review(String id, int score, String comment) {
        this.id = id;
        this.score = score;
        this.comment = comment;
    }

    /**
     * @return id of the review
     */
    public String getId() {
        return id;
    }

    /**
     * @return score of the review
     */
    public int getScore() {
        return score;
    }

    /**
     * @return comment of the review
     */
    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id='" + id + '\'' +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                '}';
    }
}
