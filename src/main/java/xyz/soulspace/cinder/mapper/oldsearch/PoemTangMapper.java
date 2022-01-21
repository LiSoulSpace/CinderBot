package xyz.soulspace.cinder.mapper.oldsearch;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.soulspace.cinder.pojo.oldsearch.PoemTang;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface PoemTangMapper {
    List<PoemTang> getPoemTangList();

    PoemTang getPoemTangById(int id);

    List<PoemTang> getPoemTangByTitle(Map<String, Object> map);

    List<PoemTang> getPoemTangByAuthor(Map<String, Object> map);

    List<PoemTang> getPoemTangParagraphsLike(Map<String, Object> map);

    int getPoemTangNumberByAuthor(String author);

    int getPoemTangNumberByTitle(String title);

    int getPoemTangNumberByParagraphsLike(String par);
}
