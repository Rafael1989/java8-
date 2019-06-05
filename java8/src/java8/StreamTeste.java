/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java8;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 *
 * @author User
 */
public class StreamTeste {
    public static void main(String []args){
        System.out.println("-----------------");
        //STREAM
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        filtered.forEach(c -> System.out.println(c));
        System.out.println("-----------------FOREACH");
        //FOREACH
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);
        System.out.println("-----------------MAP");
        //MAP
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        squaresList.forEach(System.out::println);
        System.out.println("-----------------FILTER");
        //FILTER
        List<String>strings2 = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        int count = (int)strings2.stream().filter(string -> string.isEmpty()).count();
        System.out.println(count);
        System.out.println("-----------------LIMIT");
        //LIMIT
        Random random2 = new Random();
        random2.ints(0,50).limit(5).forEach(System.out::println);
        System.out.println("-----------------SORTED");
        //SORTED
        Random random3 = new Random();
        random3.ints(0,50).limit(10).sorted().forEach(System.out::println);
        System.out.println("-----------------PARALLEL PROCESSING");
        //PARALLEL PROCESSING
        List<String> strings3 = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        long count2 = strings3.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println(count2);
        System.out.println("-----------------COLLECTORS");
        //COLLECTORS
        List<String>strings4 = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered2 = strings4.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println("Filtered List: " + filtered2);
        String mergedString = strings4.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("Merged String: " + mergedString);
        System.out.println("-----------------STATISTICS");
        //STATISTICS
        List<Integer> numbers2 = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        IntSummaryStatistics stats = numbers2.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest number in List : " + stats.getMax());
        System.out.println("Lowest number in List : " + stats.getMin());
        System.out.println("Sum of all numbers : " + stats.getSum());
        System.out.println("Average of all numbers : " + stats.getAverage());
    }
}
