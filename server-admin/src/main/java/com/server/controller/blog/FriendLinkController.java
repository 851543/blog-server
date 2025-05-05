package com.server.controller.blog;

import com.server.annotation.Log;
import com.server.core.controller.BaseController;
import com.server.core.domain.AjaxResult;
import com.server.enums.BusinessType;
import com.server.service.FriendLinkService;
import com.server.domain.vo.ConditionVO;
import com.server.domain.vo.FriendLinkVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "友链模块")
@RestController
public class FriendLinkController extends BaseController {

    @Autowired
    private FriendLinkService friendLinkService;

    @ApiOperation(value = "查看友链列表")
    @GetMapping("/links")
    public AjaxResult listFriendLinks() {
        return success(friendLinkService.listFriendLinks());
    }

    @ApiOperation(value = "查看后台友链列表")
    @GetMapping("/admin/links")
    public AjaxResult listFriendLinkDTO(ConditionVO conditionVO) {
        return success(friendLinkService.listFriendLinksAdmin(conditionVO));
    }

    @Log(title = "友链管理", businessType = BusinessType.SAVE_AND_UPDATE)
    @ApiOperation(value = "保存或修改友链")
    @PostMapping("/admin/links")
    public AjaxResult saveOrUpdateFriendLink(@Valid @RequestBody FriendLinkVO friendLinkVO) {
        friendLinkService.saveOrUpdateFriendLink(friendLinkVO);
        return success();
    }

    @Log(title = "友链管理", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除友链")
    @DeleteMapping("/admin/links")
    public AjaxResult deleteFriendLink(@RequestBody List<Integer> linkIdList) {
        friendLinkService.removeByIds(linkIdList);
        return success();
    }
}
