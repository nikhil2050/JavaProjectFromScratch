package com.in28mins;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Getter @Setter @AllArgsConstructor
class Course {
        private String name;
        private String category;
        private int reviewScore;
        private int studentsCount;

    @Override
    public String toString() {
        return this.name+":"+this.category+":"+this.reviewScore+":"+this.studentsCount;
    }
}
public class FP05_CustomClass {
    static List<Course> courses = List.of(
            new Course("Spring", "Framework", 98, 18000),
            new Course("Spring Boot", "Framework", 95, 21000),
            new Course("API", "Microservices", 80, 22000),
            new Course("Microservices", "Microservices", 85, 21000),
            new Course("AWS", "Cloud", 95, 25000),
            new Course("Docker", "Cloud", 75, 24000),
            new Course("Kubernetes", "Cloud", 99, 24000)
    );

    static Course DEFAULT_COURSE = new Course("C","Basic",0,0);

    public static void main(String[] args) {
//        allMatch_NoneMatch_AnyMatch();                                // #1
//
//        System.out.println("\n2. comparingAndSorting() ::");
//        comparingAndSorting();                                        // #2
//
//        System.out.println("\n3. skipLimitTakewhileDropwhile() ::");
//        skipLimitTakewhileDropwhile();                                // #3
//
//        System.out.println("\n4. minMaxFindfirstFindAny() ::");
//        minMaxFindfirstFindAny();                                       // #4
//
//        System.out.println("\n5. sumAverageCount() ::");
//        sumAverageCount();

        System.out.println("\n6. groupingBy() ::");
        groupingBy();
    }

    /**
     * 1. allMatch, noneMatch, anyMatch
     */
    public static void allMatch_NoneMatch_AnyMatch() {
        Predicate<Course> reviewScoreGT95Predicate = course -> course.getReviewScore() > 95;
        Predicate<Course> reviewScoreGT90Predicate = course -> course.getReviewScore() > 90;
        Predicate<Course> reviewScoreLT70Predicate = course -> course.getReviewScore() < 70;

        System.out.println(
                courses.stream().allMatch(reviewScoreGT95Predicate));       // allMatch
        System.out.println(
                courses.stream().noneMatch(reviewScoreLT70Predicate));      // noneMatch
        System.out.println(
                courses.stream().anyMatch(reviewScoreGT90Predicate));       // anyMatch
    }

    /**
     * 2. compare
     */
    public static void comparingAndSorting() {
        Comparator<Course> byStudentsCountComparator = Comparator.comparing(Course::getStudentsCount);                // ASCENDING
        courses.stream()
                .sorted(byStudentsCountComparator)
                .forEach(System.out::println);

        Comparator<Course> byStudentsCountDescComparator = Comparator.comparing(Course::getStudentsCount).reversed(); // DESCENDING
        courses.stream()
                .sorted(byStudentsCountDescComparator)
                .forEach(System.out::println);

        Comparator<Course> byNoOfStudentsDescAndNameDescComparator
                = Comparator.comparing(Course::getStudentsCount).reversed()
                        .thenComparing(Course::getName).reversed();                        // NoStudents DESCENDING + Name ASCENDING
        courses.stream().sorted(byNoOfStudentsDescAndNameDescComparator).forEach(System.out::println);
    }

    /**
     * 3. skip, limit,
     * takeWhile, dropWhile
     */
    public static void skipLimitTakewhileDropwhile() {
        courses.stream()
                .sorted(Comparator.comparing(Course::getName))
                .skip(2)                                    // skip top 2 items
                .limit(3)                              // limit upto next top 3 items
                .forEach(System.out::println);

        System.out.println("\ntakeWhile(c -> c.getReviewScore() >= 95) ::");
        courses.stream()
                .takeWhile(c -> c.getReviewScore() >= 95)       // YOU SHOULD SORT PRIOR TO CALLING 'takeWhile'
                .forEach(System.out::println);

        System.out.println("\ndropWhile(c -> c.getReviewScore() >= 95) ::");
        courses.stream()
                .dropWhile(c -> c.getReviewScore() >= 95)       // YOU SHOULD SORT PRIOR TO CALLING 'dropWhile'
                .forEach(System.out::println);
    }

    /**
     * 4. min, max
     *    findFirst, findAny
     */
    public static void minMaxFindfirstFindAny() {
        Comparator<Course> byNoOfStudentsDescAndNameDescComparator
                = Comparator.comparing(Course::getStudentsCount).reversed()
                        .thenComparing(Course::getName).reversed();                        // NoStudents DESCENDING + Name ASCENDING

        Optional<Course> min = courses.stream()
                .max(byNoOfStudentsDescAndNameDescComparator);          // min()
        System.out.println(min.get());
//        System.out.println(min.orElse(DEFAULT_COURSE));

        Optional<Course> max = courses.stream()
                .max(byNoOfStudentsDescAndNameDescComparator);          // max()
        System.out.println(max.get());
//        System.out.println(max.orElse(DEFAULT_COURSE));

        // ====================================================
        Optional<Course> fetchedCourse = courses.stream()
                .filter(c -> c.getReviewScore()>95)
                .findFirst();                                           // 'findFirst' YOU SHOULD SORT PRIOR TO CALLING IT
        System.out.println("\nfindFirst ::\t" + fetchedCourse.get());

        Optional<Course> fetchedCourse2 = courses.stream()
                .filter(c -> c.getReviewScore()>95)
                .findAny();                                             // 'findAny' YOU SHOULD SORT PRIOR TO CALLING IT
        System.out.println("\nfindAny ::  \t" + fetchedCourse2.get());
    }

    /**
     * 5. sum, average, count
     */
    public static void sumAverageCount() {
        courses.stream()
                .filter(c -> c.getReviewScore()>95)
                .forEach(System.out::println);
        // [ (Spring|Framework|98|18000), (Kubernetes|Cloud|99|24000) ]

        System.out.println("Sum :: "+
                courses.stream()
                        .filter(c -> c.getReviewScore()>95)
                        .mapToInt(Course::getStudentsCount)
                        .sum());
        System.out.println("Average :: "+
                courses.stream()
                        .filter(c -> c.getReviewScore()>95)
                        .mapToInt(Course::getStudentsCount)
                        .average());
        System.out.println("Count :: "+
                courses.stream()
                        .filter(c -> c.getReviewScore()>95)
                        .mapToInt(Course::getStudentsCount)
                        .count());
        System.out.println("Max :: "+
                courses.stream()
                        .filter(c -> c.getReviewScore()>95)
                        .mapToInt(Course::getStudentsCount)
                        .max());
    }

    /**
     * 6. Grouping items into map using 'groupingBy'
     */
    public static void groupingBy() {
        Collector myCollector = Collectors.groupingBy(Course::getCategory);
        System.out.println(
            courses.stream()
                    .collect(myCollector)
        );
        /* {Cloud         =[AWS:Cloud:95:25000, Docker:Cloud:75:24000, Kubernetes:Cloud:99:24000],
            Microservices =[API:Microservices:80:22000, Microservices:Microservices:85:21000],
            Framework     =[Spring:Framework:98:18000, Spring Boot:Framework:95:21000]}         */

        Collector myCollector2 = Collectors.groupingBy(Course::getCategory,
                                                        Collectors.counting());
        System.out.println(
                courses.stream()
                        .collect(myCollector2)
        );
        /* {Cloud=3, Microservices=2, Framework=2}  */

        Collector myCollector3 = Collectors.groupingBy(Course::getCategory,
                                                        Collectors.maxBy(Comparator.comparing(Course::getReviewScore)));
        System.out.println(
                courses.stream()
                        .collect(myCollector3)
        );
        /* {Cloud         =Optional[Kubernetes:Cloud:99:24000],
            Microservices =Optional[Microservices:Microservices:85:21000],
            Framework     =Optional[Spring:Framework:98:18000]}
         */

        Collector myCollector4 = Collectors.groupingBy(Course::getCategory,
                                                        Collectors.mapping(Course::getName, Collectors.toList()));
        System.out.println(
                courses.stream()
                        .collect(myCollector4)
        );
        /* {Cloud=[AWS, Docker, Kubernetes],
            Microservices=[API, Microservices],
            Framework=[Spring, Spring Boot]}
         */

    }
}
