package com.example.assignment.model;

public class Post {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column POSTS.ID
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column POSTS.TITLE
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column POSTS.GMT_CREATE
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column POSTS.GMT_MODIFIED
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    private Long gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column POSTS.CREATOR
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    private Integer creator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column POSTS.COMMENT_COUNT
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    private Integer commentCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column POSTS.VIEW_COUNT
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    private Integer viewCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column POSTS.LIKE_COUNT
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    private Integer likeCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column POSTS.TAG
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    private String tag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column POSTS.DESCRIPTION
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column POSTS.ID
     *
     * @return the value of POSTS.ID
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column POSTS.ID
     *
     * @param id the value for POSTS.ID
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column POSTS.TITLE
     *
     * @return the value of POSTS.TITLE
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column POSTS.TITLE
     *
     * @param title the value for POSTS.TITLE
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column POSTS.GMT_CREATE
     *
     * @return the value of POSTS.GMT_CREATE
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column POSTS.GMT_CREATE
     *
     * @param gmtCreate the value for POSTS.GMT_CREATE
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column POSTS.GMT_MODIFIED
     *
     * @return the value of POSTS.GMT_MODIFIED
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column POSTS.GMT_MODIFIED
     *
     * @param gmtModified the value for POSTS.GMT_MODIFIED
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column POSTS.CREATOR
     *
     * @return the value of POSTS.CREATOR
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column POSTS.CREATOR
     *
     * @param creator the value for POSTS.CREATOR
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column POSTS.COMMENT_COUNT
     *
     * @return the value of POSTS.COMMENT_COUNT
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column POSTS.COMMENT_COUNT
     *
     * @param commentCount the value for POSTS.COMMENT_COUNT
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column POSTS.VIEW_COUNT
     *
     * @return the value of POSTS.VIEW_COUNT
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    public Integer getViewCount() {
        return viewCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column POSTS.VIEW_COUNT
     *
     * @param viewCount the value for POSTS.VIEW_COUNT
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column POSTS.LIKE_COUNT
     *
     * @return the value of POSTS.LIKE_COUNT
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column POSTS.LIKE_COUNT
     *
     * @param likeCount the value for POSTS.LIKE_COUNT
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column POSTS.TAG
     *
     * @return the value of POSTS.TAG
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    public String getTag() {
        return tag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column POSTS.TAG
     *
     * @param tag the value for POSTS.TAG
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column POSTS.DESCRIPTION
     *
     * @return the value of POSTS.DESCRIPTION
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column POSTS.DESCRIPTION
     *
     * @param description the value for POSTS.DESCRIPTION
     *
     * @mbg.generated Sat Sep 07 13:59:56 CST 2019
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}