package com.server.mapper;

import com.server.domain.dto.CategoryAdminDTO;
import com.server.domain.dto.CategoryDTO;
import com.server.domain.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.server.domain.vo.ConditionVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryMapper extends BaseMapper<Category> {

    List<CategoryDTO> listCategories();

    List<CategoryAdminDTO> listCategoriesAdmin(@Param("current") Long current, @Param("size") Long size, @Param("conditionVO") ConditionVO conditionVO);

}
