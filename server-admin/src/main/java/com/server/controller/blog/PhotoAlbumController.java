package com.server.controller.blog;

import com.server.annotation.Log;
import com.server.core.controller.BaseController;
import com.server.core.domain.AjaxResult;
import com.server.enums.BusinessType;
import com.server.enums.FilePathEnum;
import com.server.domain.vo.ConditionVO;
import com.server.domain.vo.PhotoAlbumVO;
import com.server.service.PhotoAlbumService;
import com.server.strategy.context.UploadStrategyContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Api(tags = "相册模块")
@RequestMapping("/blog/photo")
@RestController
public class PhotoAlbumController extends BaseController {

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @Autowired
    private PhotoAlbumService photoAlbumService;


    @Log(title = "相册管理", businessType = BusinessType.UPLOAD)
    @ApiOperation(value = "上传相册封面")
    @ApiImplicitParam(name = "file", value = "相册封面", required = true, dataType = "MultipartFile")
    @PostMapping("/admin/photos/albums/upload")
    public AjaxResult savePhotoAlbumCover(MultipartFile file) {
        return success(uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.PHOTO.getPath()));
    }

    @Log(title = "相册管理", businessType = BusinessType.SAVE_AND_UPDATE)
    @ApiOperation(value = "保存或更新相册")
    @PostMapping("/admin/photos/albums")
    public AjaxResult saveOrUpdatePhotoAlbum(@Valid @RequestBody PhotoAlbumVO photoAlbumVO) {
        photoAlbumService.saveOrUpdatePhotoAlbum(photoAlbumVO);
        return success();
    }

    @ApiOperation(value = "查看后台相册列表")
    @GetMapping("/admin/photos/albums")
    public AjaxResult listPhotoAlbumBacks(ConditionVO conditionVO) {
        return success(photoAlbumService.listPhotoAlbumsAdmin(conditionVO));
    }

    @ApiOperation(value = "获取后台相册列表信息")
    @GetMapping("/admin/photos/albums/info")
    public AjaxResult listPhotoAlbumBackInfos() {
        return success(photoAlbumService.listPhotoAlbumInfosAdmin());
    }

    @ApiOperation(value = "根据id获取后台相册信息")
    @ApiImplicitParam(name = "albumId", value = "相册id", required = true, dataType = "Integer")
    @GetMapping("/admin/photos/albums/{albumId}/info")
    public AjaxResult getPhotoAlbumBackById(@PathVariable("albumId") Integer albumId) {
        return success(photoAlbumService.getPhotoAlbumByIdAdmin(albumId));
    }

    @Log(title = "相册管理", businessType = BusinessType.DELETE)
    @ApiOperation(value = "根据id删除相册")
    @ApiImplicitParam(name = "albumId", value = "相册id", required = true, dataType = "Integer")
    @DeleteMapping("/admin/photos/albums/{albumId}")
    public AjaxResult deletePhotoAlbumById(@PathVariable("albumId") Integer albumId) {
        photoAlbumService.deletePhotoAlbumById(albumId);
        return success();
    }

    @ApiOperation(value = "获取相册列表")
    @GetMapping("/photos/albums")
    public AjaxResult listPhotoAlbums() {
        return success(photoAlbumService.listPhotoAlbums());
    }

}
