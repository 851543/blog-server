package com.server.service;

import com.server.domain.dto.PageResultDTO;
import com.server.domain.dto.TagAdminDTO;
import com.server.domain.dto.TagDTO;
import com.server.domain.Tag;
import com.server.domain.vo.ConditionVO;
import com.server.domain.vo.TagVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface TagService extends IService<Tag> {

    List<TagDTO> listTags();

    List<TagDTO> listTopTenTags();

    PageResultDTO<TagAdminDTO> listTagsAdmin(ConditionVO conditionVO);

    List<TagAdminDTO> listTagsAdminBySearch(ConditionVO conditionVO);

    void saveOrUpdateTag(TagVO tagVO);

    void deleteTag(List<Integer> tagIds);

}
