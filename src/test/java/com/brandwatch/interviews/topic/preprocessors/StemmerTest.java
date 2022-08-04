package com.brandwatch.interviews.topic.preprocessors;

import com.brandwatch.interviews.topic.model.StemmedToken;
import com.brandwatch.interviews.topic.model.Token;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StemmerTest {

    private Stemmer stemmer = new Stemmer();

    @Test
    void shouldStem() {
        // Given
        Token token1 = new Token("Risks", "UN");
        Token token2 = new Token("are", "UN");
        List<Token> tokens = List.of(
                token1,
                token2);

        // When
        List<StemmedToken> result = stemmer.stem(tokens);

        // Then
        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(
                new StemmedToken("risk", token1),
                new StemmedToken("are", token2));
    }
}