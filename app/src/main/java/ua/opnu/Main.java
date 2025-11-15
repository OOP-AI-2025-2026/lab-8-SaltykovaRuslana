package ua.opnu;

import javax.annotation.processing.SupportedSourceVersion;
import java.awt.print.Book;
import java.time.Period;
import java.util.Arrays;
import java.util.function.Predicate;

public class Main {
    public static <T> T[] filter(T[] input, Predicate<T> predicate) {
        T[] result = (T[]) java.lang.reflect.Array.newInstance(input.getClass().getComponentType(), input.length);
        int counter = 0;

        for (T item : input) {
            if (predicate.test(item)) {
                result[counter] = item;
                counter++;
            }
        }
        return Arrays.copyOfRange(result, 0, counter);
    }

    public static <T extends Comparable<T>, V extends  T> boolean contains (T[] array, V element) {
        for (T str : array) {
            if (str.equals(element)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Тестування завдання 1: MyOptional<T>");
        MyOptional<String> middleName = new MyOptional<>();
        System.out.println(middleName);
        System.out.println("isPresent: " + middleName.isPresent());
        System.out.println("orElse: " + middleName.orElse("немає"));

        MyOptional<String> username = new MyOptional<>("admin");
        System.out.println(username);
        System.out.println("isPresent: " + username.isPresent());
        System.out.println("get(): " + username.get());
        System.out.println("orElse: " + username.orElse("guest"));

        try {
            String test = middleName.get();
            System.out.println("unexpected: " + test);
        } catch (IllegalStateException ex) {
            System.out.println("Очікуваний виняток: " + ex.getMessage());
        }

        try {
            MyOptional<String> broken = new MyOptional<>(null);
            System.out.println("unexpected: " + broken);
        } catch (IllegalArgumentException ex) {
            System.out.println("Правильно не дозволив null: " + ex.getMessage());
        }

        System.out.println("Тестування Завданя 2: BookData");
        BookData book1 = new BookData("Holes", "L.Lowry", 240, 3500.0);
        BookData book2 = new BookData("1984 ", "G.Orwell", 600, 2000.0);
        BookData book3 = new BookData("Middlemarch", "G.Eliot", 800, 4500.0);

        BookData[] books = {book1, book2, book3};
        System.out.println("До сортування: " + Arrays.toString(books));
        Arrays.sort(books);
        System.out.println("Після сортування: " + Arrays.toString(books));

        System.out.println("Тестування Завдання 3: Printer");
        Printer myPrinter = new Printer();
        Integer[] intArray = {1, 2, 3};
        String[] stringArray = {"Hello", "Word"};

        System.out.println("Друк Integer[]:");
        myPrinter.printArray(intArray);

        System.out.println("Друк String[]:");
        myPrinter.printArray(stringArray);

        System.out.println("Тестування Завдання 4: filter() ");
        String[] mixedStrings = {"Banana", "Grape", "Apple", " "};
        Predicate<String> notBlank = str -> str != null && !str.trim().isEmpty();
        String[] filteredStrings = filter(mixedStrings, notBlank);
        System.out.println("Відфільтровані рядки: " + Arrays.toString(filteredStrings));

        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8};
        Integer[] evenNumbers = filter(numbers, n -> n % 2 == 0);
        System.out.println("Відфільтровані числа (парні): " +Arrays.toString(evenNumbers));

        System.out.println("Тестування Завдання 5: contains() ");
        String[] names = {"Andriy", "Ruslana", "Oleksandr"};
        System.out.println("Чи містить " + Arrays.toString(names) + " 'Ruslana'? " + contains(names, "Ruslana"));
        System.out.println("Чи містить " + Arrays.toString(names) + " 'David'? " + contains(names, "David"));

        System.out.println("Тестування Завдання 6: Tuples ");
        GenericTwoTuple<String, Integer> personAge = new GenericTwoTuple<>("Olena", 40);
        System.out.println("TwpTuple: " + personAge);
        System.out.println("Ім'я: " + personAge.first + ", Вік: " + personAge.second);

        GenericThreeTuple<String, String, Double> product =
                new GenericThreeTuple<>("LapTop", "SMG-11111", 16000.00);
        System.out.println("ThreeTuple: " + product);
        System.out.println("Товар: " + product.first + ", SMG: " + product.second + ", ціна: " + product.third);
    }
}
