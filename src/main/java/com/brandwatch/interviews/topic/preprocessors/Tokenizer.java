package com.brandwatch.interviews.topic.preprocessors;

import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class Tokenizer {

    private static final String MODEL_FILE = "models/en-token.bin";

    private final TokenizerME tokenizer;

    public Tokenizer() throws IOException, URISyntaxException {
        TokenizerModel model = new TokenizerModel(Path.of(ClassLoader.getSystemResource(MODEL_FILE).toURI()));
        tokenizer = new TokenizerME(model);
    }

    public List<String> tokenize(String text) {
        String[] tokenArray = tokenizer.tokenize(text);
        if (tokenArray != null) {
            return Arrays.asList(tokenArray);
        } else {
            return Collections.emptyList();
        }
    }
}
