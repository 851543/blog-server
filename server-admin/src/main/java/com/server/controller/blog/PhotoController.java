package com.server.controller.blog;

import com.server.annotation.Log;
import com.server.core.controller.BaseController;
import com.server.core.domain.AjaxResult;
import com.server.enums.BusinessType;
import com.server.enums.FilePathEnum;
import com.server.domain.dto.PageResultDTO;
import com.server.domain.vo.*;
import com.server.service.PhotoService;
import com.server.strategy.context.UploadStrategyContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "照片模块")
@RequestMapping("/blog/photo")
@RestController
public class PhotoController extends BaseController {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @Log(title = "照片管理", businessType = BusinessType.UPLOAD)
    @ApiOperation(value = "上传照片")
    @ApiImplicitParam(name = "file", value = "照片", required = true, dataType = "MultipartFile")
    @PostMapping("/admin/photos/upload")
    public AjaxResult savePhotoAlbumCover(MultipartFile file) {
        return success(uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.PHOTO.getPath()));
    }

    @ApiOperation(value = "根据相册id获取照片列表")
    @GetMapping("/admin/photos")
    public AjaxResult listPhotos(ConditionVO conditionVO) {
        return success(photoService.listPhotos(conditionVO));
    }

    @Log(title = "照片管理", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "更新照片信息")
    @PutMapping("/admin/photos")
    public AjaxResult updatePhoto(@Valid @RequestBody PhotoInfoVO photoInfoVO) {
        photoService.updatePhoto(photoInfoVO);
        return success();
    }

    @Log(title = "照片管理", businessType = BusinessType.SAVE)
    @ApiOperation(value = "保存照片")
    @PostMapping("/admin/photos")
    public AjaxResult savePhotos(@Valid @RequestBody PhotoVO photoVO) {
        photoService.savePhotos(photoVO);
        return success();
    }

    @Log(title = "照片管理", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "移动照片相册")
    @PutMapping("/admin/photos/album")
    public AjaxResult updatePhotosAlbum(@Valid @RequestBody PhotoVO photoVO) {
        photoService.updatePhotosAlbum(photoVO);
        return success();
    }

    @Log(title = "照片管理", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "更新照片删除状态")
    @PutMapping("/admin/photos/delete")
    public AjaxResult updatePhotoDelete(@Valid @RequestBody DeleteVO deleteVO) {
        photoService.updatePhotoDelete(deleteVO);
        return success();
    }

    @Log(title = "照片管理", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除照片")
    @DeleteMapping("/admin/photos")
    public AjaxResult deletePhotos(@RequestBody List<Integer> photoIds) {
        photoService.deletePhotos(photoIds);
        return success();
    }

    @ApiOperation(value = "根据相册id查看照片列表")
    @GetMapping("/albums/{albumId}/photos")
    public AjaxResult listPhotosByAlbumId(@PathVariable("albumId") Integer albumId) {
        return success(photoService.listPhotosByAlbumId(albumId));
    }

}
