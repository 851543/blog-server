package com.server.controller.blog;

import com.server.annotation.Log;
import com.server.core.controller.BaseController;
import com.server.core.domain.AjaxResult;
import com.server.enums.BusinessType;
import com.server.enums.FilePathEnum;
import com.server.service.AuroraInfoService;
import com.server.strategy.context.UploadStrategyContext;
import com.server.domain.vo.AboutVO;
import com.server.domain.vo.WebsiteConfigVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Api(tags = "网站信息")
@RequestMapping("/blog/website")
@RestController
public class WebsiteController extends BaseController {

    @Autowired
    private AuroraInfoService auroraInfoService;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @ApiOperation(value = "上报访客信息")
    @PostMapping("/report")
    public AjaxResult report() {
        auroraInfoService.report();
        return success();
    }

    @ApiOperation(value = "获取系统信息")
    @GetMapping("/")
    public AjaxResult getBlogHomeInfo() {
        return success(auroraInfoService.getAuroraHomeInfo());
    }

    @ApiOperation(value = "获取系统后台信息")
    @GetMapping("/admin")
    public AjaxResult getBlogBackInfo() {
        return success(auroraInfoService.getAuroraAdminInfo());
    }

    @Log(title = "网站管理", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "更新网站配置")
    @PutMapping("/admin/website/config")
    public AjaxResult updateWebsiteConfig(@Valid @RequestBody WebsiteConfigVO websiteConfigVO) {
        auroraInfoService.updateWebsiteConfig(websiteConfigVO);
        return success();
    }

    @ApiOperation(value = "获取网站配置")
    @GetMapping("/admin/website/config")
    public AjaxResult getWebsiteConfig() {
        return success(auroraInfoService.getWebsiteConfig());
    }

    @ApiOperation(value = "查看关于我信息")
    @GetMapping("/about")
    public AjaxResult getAbout() {
        return success(auroraInfoService.getAbout());
    }

    @Log(title = "网站管理", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改关于我信息")
    @PutMapping("/admin/about")
    public AjaxResult updateAbout(@Valid @RequestBody AboutVO aboutVO) {
        auroraInfoService.updateAbout(aboutVO);
        return success();
    }

    @Log(title = "网站管理", businessType = BusinessType.UPLOAD)
    @ApiOperation(value = "上传博客配置图片")
    @ApiImplicitParam(name = "file", value = "图片", required = true, dataType = "MultipartFile")
    @PostMapping("/admin/config/images")
    public AjaxResult savePhotoAlbumCover(MultipartFile file) {
        return success(uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.CONFIG.getPath()));
    }

}
