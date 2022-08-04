package com.brandwatch.interviews.topic.extractors.rake;

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
class RakeTopicExtractorIntegrationTest {

    @Autowired
    private RakeTopicExtractor rakeTopicExtractor;

    @Test
    void shouldExtractTopics() throws Exception {
        // Given
        String text = getSampleText();

        // When
        TopicResults result = rakeTopicExtractor.extract(text);

        // Then
        assertThat(result.getTopics()).isNotEmpty();
        result.getTopics()
                .forEach(t -> System.out.println(t.getLabel()));
    }

    private String getSampleText() throws URISyntaxException, IOException {
        return Files.readString(Path.of(ClassLoader.getSystemResource("blog.txt").toURI()));
    }

}