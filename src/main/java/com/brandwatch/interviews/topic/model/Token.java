package com.brandwatch.interviews.topic.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Token {

    private String originalToken;

    private String posTag;

    public Token(String originalToken, String posTag) {
        this.originalToken = originalToken;
        this.posTag = posTag;
    }

    public String getOriginalToken() {
        return originalToken;
    }

    public String getPosTag() {
        return posTag;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
