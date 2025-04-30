package com.server.controller.blog;

import com.server.annotation.Log;
import com.server.core.controller.BaseController;
import com.server.core.domain.AjaxResult;
import com.server.domain.vo.ConditionVO;
import com.server.enums.BusinessType;
import com.server.service.CategoryService;
import com.server.domain.vo.CategoryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/blog/category")
@Api(tags = "分类")
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("获取所有分类")
    @GetMapping("/list")
    public AjaxResult listCategories() {
        return success(categoryService.listCategories());
    }

    @ApiOperation(value = "查看后台分类列表")
    @GetMapping("/admin/categories")
    public AjaxResult listCategoriesAdmin(ConditionVO conditionVO) {
        return success(categoryService.listCategoriesAdmin(conditionVO));
    }

    @ApiOperation(value = "搜索文章分类")
    @GetMapping("/admin/categories/search")
    public AjaxResult listCategoriesAdminBySearch(ConditionVO conditionVO) {
        return success(categoryService.listCategoriesBySearch(conditionVO));
    }

    @Log(title = "分类管理", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除分类")
    @DeleteMapping("/admin/categories")
    public AjaxResult deleteCategories(@RequestBody List<Integer> categoryIds) {
        categoryService.deleteCategories(categoryIds);
        return success();
    }

    @Log(title = "分类管理", businessType = BusinessType.SAVE_AND_UPDATE)
    @ApiOperation(value = "添加或修改分类")
    @PostMapping("/admin/categories")
    public AjaxResult saveOrUpdateCategory(@Valid @RequestBody CategoryVO categoryVO) {
        categoryService.saveOrUpdateCategory(categoryVO);
        return success();
    }


}
