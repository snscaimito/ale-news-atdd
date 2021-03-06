package alenews.content.acquisition;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.net.URL;
import java.util.*;

public class Content {
    private String description;
    private String language ;
    private URL sourceLocation;
    private String title ;
    private Date publishedDate ;
    private String author ;
    private SortedSet<String> categories = new TreeSet<>();
    private Set<Content> discussion = new HashSet<>();

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE) ;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj) ;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(17, 5, this) ;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setSourceLocation(URL location) {
        this.sourceLocation = location;
    }

    public URL getSourceLocation() {
        return sourceLocation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public SortedSet<String> getCategories() {
        return categories;
    }

    public void addCategory(String category) {
        categories.add(category) ;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Set<Content> getDiscussion() {
        return discussion ;
    }
}
