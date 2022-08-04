package com.brandwatch.interviews.topic.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class StemmedToken {

    private String stemmedToken;

    private Token originalToken;

    public StemmedToken(String stemmedToken, Token originalToken) {
        this.stemmedToken = stemmedToken;
        this.originalToken = originalToken;
    }

    public String getStemmedToken() {
        return stemmedToken;
    }

    public Token getOriginalToken() {
        return originalToken;
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
