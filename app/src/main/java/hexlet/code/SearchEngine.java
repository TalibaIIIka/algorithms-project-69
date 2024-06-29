package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SearchEngine {
    private static final Pattern CLEANER_PATTERN = Pattern.compile("\\w+");

    private SearchEngine() {}

    public static List<String> search(List<Map<String, String>> docs, String shoot) {
        var searchedDocs = new ArrayList<String>();
        var cleanedShootPattern = Pattern.compile("\\b" + cleanText(shoot) + "\\b");
        docs.forEach(doc -> {
            var cleanedTextDoc = cleanText(doc.get("text"));
            if (cleanedShootPattern.matcher(cleanedTextDoc).find()) {
                searchedDocs.add(doc.get("id"));
            }
        });
        return searchedDocs;
    }

    private static String cleanText(String text) {
        return CLEANER_PATTERN.matcher(text)
                .results()
                .map(MatchResult::group)
                .collect(Collectors.joining(" "));
    }
}
