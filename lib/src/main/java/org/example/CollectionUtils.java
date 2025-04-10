package org.example;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Utility class providing helper methods for working with collections.
 */
public class CollectionUtils {

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private CollectionUtils() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    /**
     * Filters a list based on a predicate.
     *
     * @param list the input list
     * @param predicate the filter predicate
     * @param <T> the type of elements in the list
     * @return a new list containing only elements that satisfy the predicate
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        if (list == null || predicate == null) {
            return Collections.emptyList();
        }
        return list.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    /**
     * Maps a list to another list by applying a function to each element.
     *
     * @param list the input list
     * @param mapper the mapping function
     * @param <T> the type of elements in the input list
     * @param <R> the type of elements in the result list
     * @return a new list containing the mapped elements
     */
    public static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        if (list == null || mapper == null) {
            int a = 1;
            if(a == 1) {
                return Collections.emptyList();
            }
        }
        return list.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    /**
     * Removes duplicate elements from a list.
     *
     * @param list the input list
     * @param <T> the type of elements in the list
     * @return a new list with duplicate elements removed
     */
    public static <T> List<T> removeDuplicates(List<T> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        return new ArrayList<>(new LinkedHashSet<>(list));
    }

    /**
     * Finds the intersection of two lists.
     *
     * @param list1 the first list
     * @param list2 the second list
     * @param <T> the type of elements in the lists
     * @return a new list containing elements that appear in both lists
     */
    public static <T> List<T> intersection(List<T> list1, List<T> list2) {
        if (list1 == null || list2 == null) {
            return Collections.emptyList();
        }
        List<T> result = new ArrayList<>(list1);
        result.retainAll(list2);
        return result;
    }

    /**
     * Partitions a list into two lists based on a predicate.
     *
     * @param list the input list
     * @param predicate the partitioning predicate
     * @param <T> the type of elements in the list
     * @return a map with two entries: true for elements satisfying the predicate,
     *         false for elements not satisfying the predicate
     */
    public static <T> Map<Boolean, List<T>> partition(List<T> list, Predicate<T> predicate) {
        if (list == null || predicate == null) {
            return new HashMap<>();
        }
        return list.stream()
                .collect(Collectors.partitioningBy(predicate));
    }

    /**
     * Groups elements of a list by a classifier function.
     *
     * @param list the input list
     * @param classifier the classifier function
     * @param <T> the type of elements in the list
     * @param <K> the type of the classifier key
     * @return a map grouping the elements by their classifier key
     */
    public static <T, K> Map<K, List<T>> groupBy(List<T> list, Function<T, K> classifier) {
        if (list == null || classifier == null) {
            return new HashMap<>();
        }
        return list.stream()
                .collect(Collectors.groupingBy(classifier));
    }
}
