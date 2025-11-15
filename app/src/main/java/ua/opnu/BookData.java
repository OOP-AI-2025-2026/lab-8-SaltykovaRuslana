package ua.opnu;

import java.awt.print.Book;
import java.util.Objects;

public class BookData implements Comparable<BookData> {
    private String title;
    private String author;
    private int reviews;
    private double total;

    public BookData(String title, String author, int reviews, double total) {
        this.title = title;
        this.author = author;
        this.reviews = reviews;
        this.total = total;
    }

    public double getRating() {
        if (reviews == 0) {
            return 0.0;
        }
        return total / reviews;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int compareTo(BookData other) {
        double rating1 = this.getRating();
        double rating2 = other.getRating();

        int ratingCompare = Double.compare(rating2, rating1);

        if (ratingCompare != 0) {
            return ratingCompare;
        } else {
            return this.title.compareTo(other.title);
        }
    }

    @Override
    public String toString() {
        return String.format("Book[title=%s, rating=%.2f]", title, getRating());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookData bookData = (BookData) o;
        return reviews == bookData.reviews &&
                Double.compare(bookData.total, total) == 0 &&
                Objects.equals(title, bookData.title) &&
                Objects.equals(author,bookData.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, reviews, total);
    }
}
