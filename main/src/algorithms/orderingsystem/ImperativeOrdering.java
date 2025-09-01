package algorithms.orderingsystem;

import java.util.*;

public class ImperativeOrdering {

    public static Map<String, Double> filterByCategoryAndMinValue(List<Order> orders, double minValue) {
        Map<String, Double> totals = new HashMap<>();
        for (Order o : orders) {
            if (o.value > minValue) {
                totals.put(o.category, totals.getOrDefault(o.category, 0.0) + o.value);
            }
        }
        return totals;
    }


    public static LinkedHashMap<String, Double> orderBy(List<Order> orders, boolean ascending) {
        Map<String, Double> totals = new HashMap<>();
        for (Order o : orders) {
            totals.put(o.category, totals.getOrDefault(o.category, 0.0) + o.value);
        }

        List<Map.Entry<String, Double>> list = new ArrayList<>(totals.entrySet());
        list.sort((e1, e2) -> ascending ?
                Double.compare(e1.getValue(), e2.getValue()) :
                Double.compare(e2.getValue(), e1.getValue())
        );

        LinkedHashMap<String, Double> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

}
