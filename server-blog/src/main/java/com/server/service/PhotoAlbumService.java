package com.server.service;

import com.server.domain.PhotoAlbum;
import com.server.domain.dto.PageResultDTO;
import com.server.domain.dto.PhotoAlbumAdminDTO;
import com.server.domain.dto.PhotoAlbumDTO;
import com.server.domain.vo.ConditionVO;
import com.server.domain.vo.PhotoAlbumVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface PhotoAlbumService extends IService<PhotoAlbum> {

    void saveOrUpdatePhotoAlbum(PhotoAlbumVO photoAlbumVO);

    PageResultDTO<PhotoAlbumAdminDTO> listPhotoAlbumsAdmin(ConditionVO condition);

    List<PhotoAlbumDTO> listPhotoAlbumInfosAdmin();

    PhotoAlbumAdminDTO getPhotoAlbumByIdAdmin(Integer albumId);

    void deletePhotoAlbumById(Integer albumId);

    List<PhotoAlbumDTO> listPhotoAlbums();

}
