package com.server.service;

import com.server.domain.dto.CategoryAdminDTO;
import com.server.domain.dto.CategoryDTO;
import com.server.domain.Category;
import com.server.domain.dto.CategoryOptionDTO;
import com.server.domain.dto.PageResultDTO;
import com.server.domain.vo.CategoryVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.server.domain.vo.ConditionVO;

import java.util.List;

public interface CategoryService extends IService<Category> {

    List<CategoryDTO> listCategories();

    PageResultDTO<CategoryAdminDTO> listCategoriesAdmin(ConditionVO conditionVO);

    List<CategoryOptionDTO> listCategoriesBySearch(ConditionVO conditionVO);

    void deleteCategories(List<Integer> categoryIds);

    void saveOrUpdateCategory(CategoryVO categoryVO);

}
