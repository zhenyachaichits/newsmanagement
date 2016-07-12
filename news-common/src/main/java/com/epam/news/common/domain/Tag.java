package com.epam.news.common.domain;


/**
 * The type Tag.
 */
public class Tag {
    private Long tagId;
    private String tagName;

    /**
     * Gets tag id.
     *
     * @return the tag id
     */
    public Long getTagId() {
        return tagId;
    }

    /**
     * Sets tag id.
     *
     * @param tagId the tag id
     */
    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    /**
     * Gets tag name.
     *
     * @return the tag name
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * Sets tag name.
     *
     * @param tagName the tag name
     */
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        if (tagId != null ? !tagId.equals(tag.tagId) : tag.tagId != null) return false;
        return tagName != null ? tagName.equals(tag.tagName) : tag.tagName == null;

    }

    @Override
    public int hashCode() {
        int result = tagId != null ? tagId.hashCode() : 0;
        result = 31 * result + (tagName != null ? tagName.hashCode() : 0);
        return result;
    }
}
