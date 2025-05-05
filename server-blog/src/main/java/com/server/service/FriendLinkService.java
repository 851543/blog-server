package com.server.service;

import com.server.domain.FriendLink;
import com.server.domain.dto.FriendLinkAdminDTO;
import com.server.domain.dto.FriendLinkDTO;
import com.server.domain.dto.PageResultDTO;
import com.server.domain.vo.ConditionVO;
import com.server.domain.vo.FriendLinkVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface FriendLinkService extends IService<FriendLink> {

    List<FriendLinkDTO> listFriendLinks();

    PageResultDTO<FriendLinkAdminDTO> listFriendLinksAdmin(ConditionVO conditionVO);

    void saveOrUpdateFriendLink(FriendLinkVO friendLinkVO);

}
