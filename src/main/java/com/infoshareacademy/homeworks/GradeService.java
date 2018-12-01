package com.infoshareacademy.homeworks;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class GradeService {

    public String[][] calculateAverage(String[][] data) {

        if (data == null) {
            return new String[][]{{}};
        } else if (data.length == 0) {
            return new String[][]{{}};
        } else {
            DecimalFormat format = new DecimalFormat("0.00");

            List<List<String>> test = Arrays.stream(data)
                    .map(Arrays::asList)
                    .collect(Collectors.toList());

            Map<String, String> studentGrades = new HashMap<>();

            for (int i = 0; i < test.size(); i++) {
                String name = test.get(i).get(0);
                Double average = test.stream()
                        .filter(list -> list.get(0).equalsIgnoreCase(name))
                        .mapToInt(list -> Integer.valueOf(list.get(1)))
                        .average().getAsDouble();
                studentGrades.put(name, String.valueOf(format.format(average)));
            }

            Map<String, String> sortedList = new TreeMap<>(studentGrades);

            String[][] finalList = new String[sortedList.size()][2];
            Set key = sortedList.entrySet();
            Iterator value = key.iterator();

            int i = 0;
            while (value.hasNext()) {

                Map.Entry mapping = (Map.Entry) value.next();

                finalList[i][0] = mapping.getKey().toString();
                finalList[i][1] = mapping.getValue().toString().replace(",", ".");

                i++;
            }
            return finalList;
        }
    }
}
