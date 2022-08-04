package com.brandwatch.interviews.topic.preprocessors;

import com.brandwatch.interviews.topic.model.StemmedToken;
import com.brandwatch.interviews.topic.model.Token;
import opennlp.tools.stemmer.snowball.SnowballStemmer;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static opennlp.tools.stemmer.snowball.SnowballStemmer.ALGORITHM.ENGLISH;

@Component
public class Stemmer {

    private final SnowballStemmer snowballStemmer;

    public Stemmer() {
        this.snowballStemmer = new SnowballStemmer(ENGLISH);
    }

    public List<StemmedToken> stem(List<Token> tokens) {
        return tokens.stream()
                .map(token -> new StemmedToken(stem(token.getOriginalToken()), token))
                .collect(toList());
    }

    private String stem(String token) {
        return snowballStemmer.stem(token)
                .toString()
                .toLowerCase();
    }
}
