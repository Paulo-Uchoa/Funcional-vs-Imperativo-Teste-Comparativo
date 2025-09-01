package algorithms.orderingsystem;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FunctionalOrdering {

    public static Map<String, Double> filterByCategoryAndMinValue(List<Order> orders, double minValue) {
        return orders.stream()
                .filter(o -> o.value > minValue)
                .collect(Collectors.groupingBy(
                        o -> o.category,
                        Collectors.summingDouble(o -> o.value)
                ));
    }

    public static LinkedHashMap<String, Double> orderBy(List<Order> orders, boolean ascending) {
        Map<String, Double> totals = orders.stream()
                .collect(Collectors.groupingBy(
                        o -> o.category,
                        Collectors.summingDouble(o -> o.value)
                ));

        return totals.entrySet()
                .stream()
                .sorted(ascending ?
                        Map.Entry.comparingByValue() :
                        Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

}
