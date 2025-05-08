package com.server.controller.blog;

import com.server.annotation.Log;
import com.server.core.controller.BaseController;
import com.server.core.domain.AjaxResult;
import com.server.domain.vo.*;
import com.server.enums.BusinessType;
import com.server.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "评论模块")
@RequestMapping("/blog/comment")
@RestController
public class CommentController extends BaseController {

    @Autowired
    private CommentService commentService;

//    @AccessLimit(seconds = 60, maxCount = 3)
    @Log(title = "评论管理", businessType = BusinessType.SAVE)
    @ApiOperation("添加评论")
    @PostMapping("/comments/save")
    public AjaxResult saveComment(@Valid @RequestBody CommentVO commentVO) {
        commentService.saveComment(commentVO);
        return success();
    }

    @ApiOperation("获取评论")
    @GetMapping("/comments")
    public AjaxResult getComments(CommentVO commentVO) {
        return success(commentService.listComments(commentVO));
    }

    @ApiOperation(value = "根据commentId获取回复")
    @GetMapping("/comments/{commentId}/replies")
    public AjaxResult listRepliesByCommentId(@PathVariable("commentId") Integer commentId) {
        return success(commentService.listRepliesByCommentId(commentId));
    }

    @ApiOperation("获取前六个评论")
    @GetMapping("/comments/topSix")
    public AjaxResult listTopSixComments() {
        return success(commentService.listTopSixComments());
    }

    @ApiOperation(value = "查询后台评论")
    @GetMapping("/admin/comments")
    public AjaxResult listCommentBackDTO(ConditionVO conditionVO) {
        return success(commentService.listCommentsAdmin(conditionVO));
    }

    @Log(title = "评论管理", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "审核评论")
    @PutMapping("/admin/comments/review")
    public AjaxResult updateCommentsReview(@Valid @RequestBody ReviewVO reviewVO) {
        commentService.updateCommentsReview(reviewVO);
        return success();
    }

    @Log(title = "评论管理", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除评论")
    @DeleteMapping("/admin/comments")
    public AjaxResult deleteComments(@RequestBody List<Integer> commentIdList) {
        commentService.removeByIds(commentIdList);
        return success();
    }

}
