package com.brandwatch.interviews.topic.preprocessors;

import com.brandwatch.interviews.topic.model.Token;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class PosTagger {

    private static final String MODEL_FILE = "models/en-pos-maxent.bin";

    private final POSTaggerME posTagger;

    public PosTagger() throws URISyntaxException, IOException {
        POSModel posModel = new POSModel(Path.of(ClassLoader.getSystemResource(MODEL_FILE).toURI()));
        this.posTagger = new POSTaggerME(posModel);
    }

    public List<Token> tag(List<String> tokens) {
        String[] tags = posTagger.tag(tokens.toArray(new String[]{}));
        if (tags == null) {
            return Collections.emptyList();
        }

        return IntStream.range(0, tokens.size())
                .mapToObj(i -> new Token(tokens.get(i), tags[i]))
                .collect(Collectors.toList());
    }
}
