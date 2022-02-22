package xyz.soulspace.cinder.entity.oldsearch;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WordCET6 {
    private int id;
    private String word;
    private String phonetic;
    private String paraphrase;

    public WordCET6(int id, String word, String phonetic, String paraphrase) {
        this.id = id;
        this.word = word;
        this.phonetic = phonetic;
        this.paraphrase = paraphrase;
    }

    @Override
    public String toString() {
        return "WordCET6{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", phonetic='" + phonetic + '\'' +
                ", paraphrase='" + paraphrase + '\'' +
                '}';
    }
}
