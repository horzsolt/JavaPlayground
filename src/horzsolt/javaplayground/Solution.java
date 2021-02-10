package horzsolt.javaplayground;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {

    public static void main(String[] args) {
        Map<Long, Float> smallMap = new HashMap<>();
        smallMap.put(1L, 1F);
        smallMap.put(2L, 2F);
        smallMap.put(3L, 3F);

        Map<Long, Map<Long, Float>> bigMap = new HashMap<>();
        bigMap.put(1L, smallMap);
        bigMap.put(2L, smallMap);
        bigMap.put(3L, smallMap);

        Set<Long> collect = bigMap.values().stream()
                .findAny()
                .map(Map::keySet)
                .orElse(Collections.emptySet())
                .stream()
                .filter(innerKey -> bigMap.values().removeIf(innerMap -> innerMap.values().stream().anyMatch(innerValue -> innerValue < 5F)))
                .collect(Collectors.toSet());

        System.out.println(collect);

    }
}
