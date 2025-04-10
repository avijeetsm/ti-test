package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Tests for the CollectionUtils class.
 */
public class CollectionUtilsTest {

    @Test
    public void testFilter() {
        // Test with non-empty list
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Predicate<Integer> isEven = n -> n % 2 == 0;
        List<Integer> evenNumbers = CollectionUtils.filter(numbers, isEven);
        assertEquals(Arrays.asList(2, 4, 6), evenNumbers);

        // Test with empty list
        List<Integer> emptyList = Collections.emptyList();
        List<Integer> filteredEmpty = CollectionUtils.filter(emptyList, isEven);
        assertEquals(Collections.emptyList(), filteredEmpty);

        // Test with null list
        List<Integer> nullResult = CollectionUtils.filter(null, isEven);
        assertEquals(Collections.emptyList(), nullResult);

        // Test with null predicate
        List<Integer> nullPredicateResult = CollectionUtils.filter(numbers, null);
        assertEquals(Collections.emptyList(), nullPredicateResult);
    }

    @Test
    public void testMap() {
        // Test with non-empty list
        List<String> strings = Arrays.asList("1", "2", "3");
        Function<String, Integer> toInt = Integer::parseInt;
        List<Integer> integers = CollectionUtils.map(strings, toInt);
        assertEquals(Arrays.asList(1, 2, 3), integers);

        // Test with empty list
        List<String> emptyList = Collections.emptyList();
        List<Integer> mappedEmpty = CollectionUtils.map(emptyList, toInt);
        assertEquals(Collections.emptyList(), mappedEmpty);

        // Test with null list
        List<Integer> nullResult = CollectionUtils.map(null, toInt);
        assertEquals(Collections.emptyList(), nullResult);

        // Test with null mapper
        List<Integer> nullMapperResult = CollectionUtils.map(strings, null);
        assertEquals(Collections.emptyList(), nullMapperResult);
    }

    @Test
    public void testRemoveDuplicates() {
        // Test with list containing duplicates
        List<Integer> withDuplicates = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
        List<Integer> withoutDuplicates = CollectionUtils.removeDuplicates(withDuplicates);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), withoutDuplicates);

        // Test with list without duplicates
        List<Integer> noDuplicates = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> result = CollectionUtils.removeDuplicates(noDuplicates);
        assertEquals(noDuplicates, result);

        // Test with empty list
        List<Integer> emptyList = Collections.emptyList();
        List<Integer> emptyResult = CollectionUtils.removeDuplicates(emptyList);
        assertEquals(Collections.emptyList(), emptyResult);

        // Test with null list
        List<Integer> nullResult = CollectionUtils.removeDuplicates(null);
        assertEquals(Collections.emptyList(), nullResult);
    }

    @Test
    public void testIntersection() {
        // Test with lists having common elements
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(4, 5, 6, 7, 8);
        List<Integer> intersection = CollectionUtils.intersection(list1, list2);
        assertEquals(Arrays.asList(4, 5), intersection);

        // Test with disjoint lists
        List<Integer> disjointList = Arrays.asList(9, 10, 11);
        List<Integer> emptyIntersection = CollectionUtils.intersection(list1, disjointList);
        assertEquals(Collections.emptyList(), emptyIntersection);

        // Test with null lists
        List<Integer> nullResult1 = CollectionUtils.intersection(null, list2);
        assertEquals(Collections.emptyList(), nullResult1);

        List<Integer> nullResult2 = CollectionUtils.intersection(list1, null);
        assertEquals(Collections.emptyList(), nullResult2);
    }

    @Test
    public void testPartition() {
        // Test with non-empty list
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Map<Boolean, List<Integer>> partitioned = CollectionUtils.partition(numbers, isEven);
        
        assertEquals(Arrays.asList(2, 4, 6), partitioned.get(true));
        assertEquals(Arrays.asList(1, 3, 5), partitioned.get(false));

        // Test with empty list
        List<Integer> emptyList = Collections.emptyList();
        Map<Boolean, List<Integer>> emptyPartitioned = CollectionUtils.partition(emptyList, isEven);
        assertTrue(emptyPartitioned.getOrDefault(true, Collections.emptyList()).isEmpty());
        assertTrue(emptyPartitioned.getOrDefault(false, Collections.emptyList()).isEmpty());

        // Test with null list
        Map<Boolean, List<Integer>> nullResult = CollectionUtils.partition(null, isEven);
        assertTrue(nullResult.isEmpty());

        // Test with null predicate
        Map<Boolean, List<Integer>> nullPredicateResult = CollectionUtils.partition(numbers, null);
        assertTrue(nullPredicateResult.isEmpty());
    }

    @Test
    public void testGroupBy() {
        // Test with non-empty list
        List<String> words = Arrays.asList("apple", "banana", "cherry", "blueberry", "avocado");
        Function<String, Character> firstLetter = s -> s.charAt(0);
        Map<Character, List<String>> grouped = CollectionUtils.groupBy(words, firstLetter);
        
        assertEquals(Arrays.asList("apple", "avocado"), grouped.get('a'));
        assertEquals(Arrays.asList("banana", "blueberry"), grouped.get('b'));
        assertEquals(Collections.singletonList("cherry"), grouped.get('c'));

        // Test with empty list
        List<String> emptyList = Collections.emptyList();
        Map<Character, List<String>> emptyGrouped = CollectionUtils.groupBy(emptyList, firstLetter);
        assertTrue(emptyGrouped.isEmpty());

        // Test with null list
        Map<Character, List<String>> nullResult = CollectionUtils.groupBy(null, firstLetter);
        assertTrue(nullResult.isEmpty());

        // Test with null classifier
        Map<Character, List<String>> nullClassifierResult = CollectionUtils.groupBy(words, null);
        assertTrue(nullClassifierResult.isEmpty());
    }
}
