package com.example.assignment.model;

public class Like {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LIKES.USER_ID
     *
     * @mbg.generated Wed Sep 11 13:46:04 CST 2019
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LIKES.COMMENT_ID
     *
     * @mbg.generated Wed Sep 11 13:46:04 CST 2019
     */
    private Long commentId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LIKES.USER_ID
     *
     * @return the value of LIKES.USER_ID
     *
     * @mbg.generated Wed Sep 11 13:46:04 CST 2019
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LIKES.USER_ID
     *
     * @param userId the value for LIKES.USER_ID
     *
     * @mbg.generated Wed Sep 11 13:46:04 CST 2019
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LIKES.COMMENT_ID
     *
     * @return the value of LIKES.COMMENT_ID
     *
     * @mbg.generated Wed Sep 11 13:46:04 CST 2019
     */
    public Long getCommentId() {
        return commentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LIKES.COMMENT_ID
     *
     * @param commentId the value for LIKES.COMMENT_ID
     *
     * @mbg.generated Wed Sep 11 13:46:04 CST 2019
     */
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
}