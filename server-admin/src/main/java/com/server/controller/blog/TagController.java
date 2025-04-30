package com.server.controller.blog;


import com.server.annotation.Log;
import com.server.core.controller.BaseController;
import com.server.core.domain.AjaxResult;
import com.server.domain.vo.ConditionVO;
import com.server.enums.BusinessType;
import com.server.service.TagService;
import com.server.domain.vo.TagVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/blog/tag")
@Api(tags = "标签")
public class TagController extends BaseController {

    @Autowired
    private TagService tagService;

    @ApiOperation("获取所有标签")
    @GetMapping("/tags/all")
    public AjaxResult getAllTags() {
        return success(tagService.listTags());
    }

    @ApiOperation("获取前十个标签")
    @GetMapping("/tags/topTen")
    public AjaxResult getTopTenTags() {
        return success(tagService.listTopTenTags());
    }

    @ApiOperation(value = "查询后台标签列表")
    @GetMapping("/admin/tags")
    public AjaxResult listTagsAdmin(ConditionVO conditionVO) {
        return success(tagService.listTagsAdmin(conditionVO));
    }

    @ApiOperation(value = "搜索文章标签")
    @GetMapping("/admin/tags/search")
    public AjaxResult listTagsAdminBySearch(ConditionVO condition) {
        return success(tagService.listTagsAdminBySearch(condition));
    }

    @Log(title = "标签管理", businessType = BusinessType.SAVE_AND_UPDATE)
    @ApiOperation(value = "添加或修改标签")
    @PostMapping("/admin/tags")
    public AjaxResult saveOrUpdateTag(@Valid @RequestBody TagVO tagVO) {
        tagService.saveOrUpdateTag(tagVO);
        return success();
    }

    @Log(title = "标签管理", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除标签")
    @DeleteMapping("/admin/tags")
    public AjaxResult deleteTag(@RequestBody List<Integer> tagIdList) {
        tagService.deleteTag(tagIdList);
        return success();
    }
}
