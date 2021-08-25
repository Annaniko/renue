package com.airports.renuue;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@SpringBootApplication
public class RenuueApplication {
    public static final int COLUMN_INDEX = 1;

    @SuppressWarnings("rawtypes")
    public static void main(String[] args) throws IOException, InterruptedException {
    	SpringApplication.run(RenuueApplication.class, args);

        File br = new File("C:\\Users\\ПК\\Downloads\\airports.dat");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку: ");
        String searchRequest = scanner.nextLine();

        List<String> lines = Files.readAllLines(br.toPath());
        Map<String, List<String>> hashMap = new HashMap<>();

        long startTime = System.nanoTime();
        for (String line : lines) {
            String[] array = line.split(",");
            if (!hashMap.containsKey(array[COLUMN_INDEX])) {
                hashMap.put(array[COLUMN_INDEX], new LinkedList<>());
            }
            hashMap.get(array[COLUMN_INDEX]).add(line);
        }

        List<String> result = new LinkedList<>();
        for (Map.Entry<String, List<String>> airport : hashMap.entrySet()) {
            if (airport.getKey().contains(searchRequest)) {
                result.addAll(airport.getValue());
            }
        }

        Collections.sort(result);

        for (String resultAirport : result) {
            System.out.println(resultAirport);
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;

        System.out.println("Время, затраченное на поиск: " + duration + " мс");
        System.out.println("Количество найденных строк: " + result.size());
    }
}
