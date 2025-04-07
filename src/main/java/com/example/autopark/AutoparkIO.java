package com.example.autopark;


import com.example.autopark.Autopark;
import com.example.autopark.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AutoparkIO {

    private static final ObjectMapper mapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    public static void serializeAutopark(Autopark autopark, File file) throws IOException {
        mapper.writeValue(file, autopark);
    }

    public static void serializeAutoparkSortedByModel(Autopark autopark, File file) throws IOException {
        List<Car> sorted = autopark.getCars().stream()
                .sorted(Comparator.comparing(Car::getModel))
                .collect(Collectors.toList());

        Autopark sortedAutopark = new Autopark();
        sortedAutopark.setCars(sorted);

        serializeAutopark(sortedAutopark, file);
    }

    public static Autopark deserializeAutopark(File file) throws IOException {
        return mapper.readValue(file, Autopark.class);
    }
}
