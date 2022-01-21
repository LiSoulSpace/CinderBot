package xyz.soulspace.cinder.mapper.oldsearch;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.soulspace.cinder.pojo.oldsearch.PoemSong;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface PoemSongMapper {
    List<PoemSong> getPoemSongList();

    PoemSong getPoemSongById(int id);

    List<PoemSong> getPoemSongByTitle(Map<String, Object> params);

    List<PoemSong> getPoemSongByAuthor(Map<String, Object> params);

    List<PoemSong> getPoemSongParagraphsLike(Map<String, Object> params);

    int getPoemSongNumberByAuthor(String author);

    int getPoemSongNumberByTitle(String title);

    int getPoemSongNumberByParagraphsLike(String par);
}
