package com.server.service;

import com.server.domain.dto.UniqueViewDTO;
import com.server.domain.UniqueView;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface UniqueViewService extends IService<UniqueView> {

    List<UniqueViewDTO> listUniqueViews();

}
