package com.server.mapper;

import com.server.domain.dto.TagAdminDTO;
import com.server.domain.dto.TagDTO;
import com.server.domain.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.server.domain.vo.ConditionVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMapper extends BaseMapper<Tag> {

    List<TagDTO> listTags();

    List<TagDTO> listTopTenTags();

    List<String> listTagNamesByArticleId(Integer articleId);

    List<TagAdminDTO> listTagsAdmin(@Param("current") Long current, @Param("size") Long size, @Param("conditionVO") ConditionVO conditionVO);

}
