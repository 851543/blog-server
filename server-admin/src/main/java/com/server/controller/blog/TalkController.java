package com.server.controller.blog;

import com.server.annotation.Log;
import com.server.core.controller.BaseController;
import com.server.core.domain.AjaxResult;
import com.server.enums.BusinessType;
import com.server.enums.FilePathEnum;
import com.server.domain.vo.ConditionVO;
import com.server.domain.vo.TalkVO;
import com.server.service.TalkService;
import com.server.strategy.context.UploadStrategyContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "说说模块")
@RequestMapping("/blog/talk")
@RestController
public class TalkController extends BaseController {

    @Autowired
    private TalkService talkService;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @ApiOperation(value = "查看说说列表")
    @GetMapping("/talks")
    public AjaxResult listTalks() {
        return success(talkService.listTalks());
    }

    @ApiOperation(value = "根据id查看说说")
    @ApiImplicitParam(name = "talkId", value = "说说id", required = true, dataType = "Integer")
    @GetMapping("/talks/{talkId}")
    public AjaxResult getTalkById(@PathVariable("talkId") Integer talkId) {
        return success(talkService.getTalkById(talkId));
    }

    @Log(title = "说说管理", businessType = BusinessType.UPLOAD)
    @ApiOperation(value = "上传说说图片")
    @ApiImplicitParam(name = "file", value = "说说图片", required = true, dataType = "MultipartFile")
    @PostMapping("/admin/talks/images")
    public AjaxResult saveTalkImages(MultipartFile file) {
        return success(uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.TALK.getPath()));
    }

    @Log(title = "说说管理", businessType = BusinessType.SAVE_AND_UPDATE)
    @ApiOperation(value = "保存或修改说说")
    @PostMapping("/admin/talks")
    public AjaxResult saveOrUpdateTalk(@Valid @RequestBody TalkVO talkVO) {
        talkService.saveOrUpdateTalk(talkVO);
        return success();
    }

    @Log(title = "说说管理", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除说说")
    @DeleteMapping("/admin/talks")
    public AjaxResult deleteTalks(@RequestBody List<Integer> talkIds) {
        talkService.deleteTalks(talkIds);
        return success();
    }

    @ApiOperation(value = "查看后台说说")
    @GetMapping("/admin/talks")
    public AjaxResult listBackTalks(ConditionVO conditionVO) {
        return success(talkService.listBackTalks(conditionVO));
    }

    @ApiOperation(value = "根据id查看后台说说")
    @ApiImplicitParam(name = "talkId", value = "说说id", required = true, dataType = "Integer")
    @GetMapping("/admin/talks/{talkId}")
    public AjaxResult getBackTalkById(@PathVariable("talkId") Integer talkId) {
        return success(talkService.getBackTalkById(talkId));
    }

}
