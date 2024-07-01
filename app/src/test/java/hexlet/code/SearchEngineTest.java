package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class SearchEngineTest {

    public static final String DOC_1 = "I can't shoot straight unless I've had a pint!";
    public static final String DOC_2 = "Don't shoot shoot shoot that thing at me.";
    public static final String DOC_3 = "I'm your shooter.";

    @Test
    void testSearchSuccess() {

        List<Map<String, String>> docs = List.of(
                Map.of("id", "doc1", "text", DOC_1),
                Map.of("id", "doc2", "text", DOC_2),
                Map.of("id", "doc3", "text", DOC_3)
        );

        List<String> result = SearchEngine.search(docs, "shoot");

        assertThat(List.of("doc1", "doc2")).hasSameElementsAs(result);
    }

    @Test
    void testSearchEmptyDocs() {
        List<String> result = SearchEngine.search(new ArrayList<>(), "shoot");
        assertThat(result).hasSameElementsAs(List.of());
    }

    @Test
    void testSearchPunctuation() {

        List<Map<String, String>> docs = List.of(Map.of("id", "doc1", "text", DOC_1));

        List<String> result1 = SearchEngine.search(docs, "pint");
        assertThat(List.of("doc1")).hasSameElementsAs(result1);

        List<String> result2 = SearchEngine.search(docs, "pint!");
        assertThat(result2).hasSameElementsAs(List.of("doc1"));
    }

    @Test
    void TestSearchEngineSorted() {
        List<Map<String, String>> docs = List.of(
                Map.of("id", "doc1", "text", DOC_1),
                Map.of("id", "doc2", "text", DOC_2),
                Map.of("id", "doc3", "text", DOC_3)
        );

        List<String> result = SearchEngine.search(docs, "shoot");
        assertThat(result).isEqualTo(List.of("doc2", "doc1"));
    }
}
