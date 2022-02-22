package xyz.soulspace.cinder.entity.oldsearch;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ci {
    private int value;
    private String rhythmic;
    private String author;
    private String content;

    public Ci(int value, String rhythmic, String author, String content) {
        this.value = value;
        this.rhythmic = rhythmic;
        this.author = author;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Ci{" +
                "value=" + value +
                ", rhythmic='" + rhythmic + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
