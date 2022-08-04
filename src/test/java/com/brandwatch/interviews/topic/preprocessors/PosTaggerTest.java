package com.brandwatch.interviews.topic.preprocessors;

import com.brandwatch.interviews.topic.model.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PosTaggerTest {

    private PosTagger posTagger;

    @BeforeEach
    void setup() throws IOException, URISyntaxException {
        this.posTagger = new PosTagger();
    }

    @Test
    void shouldTagTokens() {
        // Given
        List<String> tokens = List.of("Risks", "are", "an", "inherent", "part", "of", "operating", "a", "business", ".");

        // When
        List<Token> result = posTagger.tag(tokens);

        // Then
        assertThat(result).hasSize(10);
    }
}