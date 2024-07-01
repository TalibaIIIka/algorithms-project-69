package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SearchEngine {
    private static final Pattern CLEANER_PATTERN = Pattern.compile("\\w+");

    private SearchEngine() {}

    public static List<String> search(List<Map<String, String>> docs, String shoot) {
        var cleanedShootPattern = Pattern.compile("\\b" + cleanText(shoot) + "\\b");

        return docs.stream()
            .flatMap(doc -> Map.of(
                    doc.get("id"), cleanedShootPattern.matcher(cleanText(doc.get("text"))).results().count()
            ).entrySet().stream())
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .filter(entry -> entry.getValue() > 0)
            .map(Map.Entry::getKey)
            .toList();
    }

    private static String cleanText(String text) {
        return CLEANER_PATTERN.matcher(text)
                .results()
                .map(MatchResult::group)
                .collect(Collectors.joining(" "));
    }
}
