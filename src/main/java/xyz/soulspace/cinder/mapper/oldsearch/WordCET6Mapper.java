package xyz.soulspace.cinder.mapper.oldsearch;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.soulspace.cinder.pojo.oldsearch.WordCET6;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface WordCET6Mapper {
    WordCET6 getWordById(int id);

    List<WordCET6> getWordByWord(Map<String, Object> map);

    List<WordCET6> getWordParaphraseLike(Map<String, Object> map);

    List<WordCET6> getWordLimitPage(Map<String, Object> map);
}
