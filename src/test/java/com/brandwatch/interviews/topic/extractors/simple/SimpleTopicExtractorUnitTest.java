package com.brandwatch.interviews.topic.extractors.simple;

import com.brandwatch.interviews.topic.extractors.TopicResults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest()
class SimpleTopicExtractorUnitTest {

    @Autowired
    private SimpleTopicExtractor simpleTopicExtractor;

    @Test
    void shouldReturnRankingDescendingOrder() throws Exception {
        // Given
        String text = getSampleText();

        // When
        TopicResults result = simpleTopicExtractor.extract(text);

        // Then
        assertThat(result.getTopics().get(0).getLabel().equals("kot"));
        assertThat(result.getTopics().get(1).getLabel().equals("pies"));
    }

    private String getSampleText() throws URISyntaxException, IOException {
        return Files.readString(Path.of(ClassLoader.getSystemResource("test.txt").toURI()));
    }

}