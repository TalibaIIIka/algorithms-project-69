package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchEngine {
    private SearchEngine() {}

    public static List<String> search(List<Map<String, String>> docs, String shoot) {
        var searchedDocs = new ArrayList<String>();
        docs.forEach(doc -> {
            var arrayWords = doc.get("text").split("\\s+");
            for (var word : arrayWords) {
                if (word.equals(shoot)) {
                    searchedDocs.add(doc.get("id"));
                }
            }
        });
        return searchedDocs;
    }
}
