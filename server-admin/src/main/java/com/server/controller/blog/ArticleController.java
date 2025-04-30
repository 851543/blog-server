package com.server.controller.blog;

import com.server.annotation.Log;
import com.server.core.controller.BaseController;
import com.server.core.domain.AjaxResult;
import com.server.enums.BusinessType;
import com.server.service.ArticleService;
import com.server.domain.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/blog/article")
@Api(tags = "文章")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation("获取置顶和推荐文章")
    @GetMapping("/topAndFeatured")
    public AjaxResult listTopAndFeaturedArticles() {
        return success(articleService.listTopAndFeaturedArticles());
    }

    @ApiOperation("获取所有文章")
    @GetMapping("/list")
    public AjaxResult listArticles() {
        return success(articleService.listArticles());
    }

    @ApiOperation("根据分类id获取文章")
    @GetMapping("/categoryId")
    public AjaxResult getArticlesByCategoryId(@RequestParam Integer categoryId) {
        return success(articleService.listArticlesByCategoryId(categoryId));
    }

    @ApiOperation("根据id获取文章")
    @GetMapping("/{articleId}")
    public AjaxResult getArticleById(@PathVariable("articleId") Integer articleId) {
        return success(articleService.getArticleById(articleId));
    }

    @ApiOperation("校验文章访问密码")
    @PostMapping("/access")
    public AjaxResult accessArticle(@Valid @RequestBody ArticlePasswordVO articlePasswordVO) {
        articleService.accessArticle(articlePasswordVO);
        return success();
    }

    @ApiOperation("根据标签id获取文章")
    @GetMapping("/tagId")
    public AjaxResult listArticlesByTagId(@RequestParam Integer tagId) {
        return success(articleService.listArticlesByTagId(tagId));
    }

    @ApiOperation("获取所有文章归档")
    @GetMapping("/archives/list")
    public AjaxResult listArchives() {
        return success(articleService.listArchives());
    }

    @ApiOperation("获取后台文章")
    @GetMapping("/admin")
    public AjaxResult listArticlesAdmin(ConditionVO conditionVO) {
        return success(articleService.listArticlesAdmin(conditionVO));
    }

    @Log(title = "文章管理", businessType = BusinessType.SAVE_AND_UPDATE)
    @ApiOperation("保存和修改文章")
    @PostMapping("/admin")
    public AjaxResult saveOrUpdateArticle(@Valid @RequestBody ArticleVO articleVO) {
        articleService.saveOrUpdateArticle(articleVO);
        return success();
    }

    @Log(title = "文章管理", businessType = BusinessType.UPDATE)
    @ApiOperation("修改文章是否置顶和推荐")
    @PutMapping("/admin/topAndFeatured")
    public AjaxResult updateArticleTopAndFeatured(@Valid @RequestBody ArticleTopFeaturedVO articleTopFeaturedVO) {
        articleService.updateArticleTopAndFeatured(articleTopFeaturedVO);
        return success();
    }

    @ApiOperation("删除或者恢复文章")
    @PutMapping("/admin")
    public AjaxResult updateArticleDelete(@Valid @RequestBody DeleteVO deleteVO) {
        articleService.updateArticleDelete(deleteVO);
        return success();
    }

    @Log(title = "文章管理", businessType = BusinessType.DELETE)
    @ApiOperation(value = "物理删除文章")
    @DeleteMapping("/admin/delete")
    public AjaxResult deleteArticles(@RequestBody List<Integer> articleIds) {
        articleService.deleteArticles(articleIds);
        return success();
    }

    @ApiOperation("根据id查看后台文章")
    @ApiImplicitParam(name = "articleId", value = "文章id", required = true, dataType = "Integer")
    @GetMapping("/admin/{articleId}")
    public AjaxResult getArticleBackById(@PathVariable("articleId") Integer articleId) {
        return success(articleService.getArticleByIdAdmin(articleId));
    }

    @Log(title = "文章管理", businessType = BusinessType.EXPORT)
    @ApiOperation(value = "导出文章")
    @ApiImplicitParam(name = "articleIdList", value = "文章id", required = true, dataType = "List<Integer>")
    @PostMapping("/admin/export")
    public AjaxResult exportArticles(@RequestBody List<Integer> articleIds) {
        return success(articleService.exportArticles(articleIds));
    }

    @ApiOperation(value = "搜索文章")
    @GetMapping("/search")
    public AjaxResult listArticlesBySearch(ConditionVO condition) {
        return success(articleService.listArticlesBySearch(condition));
    }

}
