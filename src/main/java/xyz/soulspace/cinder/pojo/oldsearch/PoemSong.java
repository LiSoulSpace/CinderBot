package xyz.soulspace.cinder.pojo.oldsearch;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PoemSong {
    private int value;
    private String author;
    private String paragraphs;
    private String title;
    private String id;

    public PoemSong(int value, String author, String paragraphs, String title, String id) {
        this.value = value;
        this.author = author;
        this.paragraphs = paragraphs;
        this.title = title;
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "value=" + value +
                ", author='" + author + '\'' +
                ", paragraphs='" + paragraphs + '\'' +
                ", title='" + title + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
