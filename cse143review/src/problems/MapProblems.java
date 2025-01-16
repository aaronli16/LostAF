package problems;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * See the spec on the website for example behavior.
 */
public class MapProblems {

    /**
     * Returns true if any string appears at least 3 times in the given list; false otherwise.
     */
    public static boolean contains3(List<String> list) {
        Map<String, Integer> wordCount = new HashMap<>();
        for (String str: list){
            if (!wordCount.containsKey(str)){
                wordCount.put(str, 1);
            }
            else {
                wordCount.put(str, wordCount.get(str) + 1);
            }
            if (wordCount.get(str) >= 3){
                return true;
            }
        }
        return false;
    }
    /**
     * Returns a map containing the intersection of the two input maps.
     * A key-value pair exists in the output iff the same key-value pair exists in both input maps.
     */
    public static Map<String, Integer> intersect(Map<String, Integer> m1, Map<String, Integer> m2) {
        Map<String, Integer> result = new HashMap<>();
        for (String name: m1.keySet()){
            if (m1.get(name) == m2.get(name)){
                result.put(name, m1.get(name));
            }

        }
        return result;
    }
}
