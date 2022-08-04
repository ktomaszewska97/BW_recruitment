package com.brandwatch.interviews.topic.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

public class Keyphrase {

    private List<StemmedToken> keyphrases = new ArrayList<>(5);

    public Keyphrase() {
    }

    public void addKeyphrase(StemmedToken token) {
        keyphrases.add(token);
    }

    public boolean isEmpty() {
        return keyphrases.size() == 0;
    }

    public List<StemmedToken> getKeyphrases() {
        return keyphrases;
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
