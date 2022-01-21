package xyz.soulspace.cinder.mapper.oldsearch;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.soulspace.cinder.pojo.oldsearch.Ci;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CiMapper {
    List<Ci> getCiList();

    Ci getCiById(int id);

    List<Ci> getCiByRhythmic(Map<String, Object> map);

    List<Ci> getCiByAuthor(Map<String, Object> map);

    List<Ci> getCiContentLike(Map<String, Object> map);

    int getCiNumberByAuthor(String author);

    int getCiNumberByTitle(String title);

    int getCiNumberByParagraphsLike(String paragraph);
}
