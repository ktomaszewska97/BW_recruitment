package com.brandwatch.interviews.topic.preprocessors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TokenizerTest {

    private Tokenizer tokenizer;

    @BeforeEach
    void setup() throws IOException, URISyntaxException {
        this.tokenizer = new Tokenizer();
    }

    @Test
    void shouldTokenizeValidText() {
        // Given
        String text = "Risks are an inherent part of operating a business.";

        // When
        List<String> result = tokenizer.tokenize(text);

        // Then
        assertThat(result).hasSize(10);
        assertThat(result).containsExactly("Risks", "are", "an", "inherent", "part", "of", "operating", "a", "business", ".");
    }
}