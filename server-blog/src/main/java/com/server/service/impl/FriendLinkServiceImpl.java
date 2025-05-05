package com.server.service.impl;

import com.server.domain.FriendLink;
import com.server.mapper.FriendLinkMapper;
import com.server.domain.dto.FriendLinkAdminDTO;
import com.server.domain.dto.FriendLinkDTO;
import com.server.domain.dto.PageResultDTO;
import com.server.domain.vo.ConditionVO;
import com.server.domain.vo.FriendLinkVO;
import com.server.service.FriendLinkService;
import com.server.util.BeanCopyUtil;
import com.server.util.PageUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendLinkServiceImpl extends ServiceImpl<FriendLinkMapper, FriendLink> implements FriendLinkService {

    @Autowired
    private FriendLinkMapper friendLinkMapper;

    @Override
    public List<FriendLinkDTO> listFriendLinks() {
        List<FriendLink> friendLinks = friendLinkMapper.selectList(null);
        return BeanCopyUtil.copyList(friendLinks, FriendLinkDTO.class);
    }

    @Override
    public PageResultDTO<FriendLinkAdminDTO> listFriendLinksAdmin(ConditionVO conditionVO) {
        Page<FriendLink> page = new Page<>(PageUtil.getCurrent(), PageUtil.getSize());
        Page<FriendLink> friendLinkPage = friendLinkMapper.selectPage(page, new LambdaQueryWrapper<FriendLink>()
                .like(StringUtils.isNotBlank(conditionVO.getKeywords()), FriendLink::getLinkName, conditionVO.getKeywords()));
        List<FriendLinkAdminDTO> friendLinkBackDTOs = BeanCopyUtil.copyList(friendLinkPage.getRecords(), FriendLinkAdminDTO.class);
        return new PageResultDTO<>(friendLinkBackDTOs, (int) friendLinkPage.getTotal());
    }

    @Override
    public void saveOrUpdateFriendLink(FriendLinkVO friendLinkVO) {
        FriendLink friendLink = BeanCopyUtil.copyObject(friendLinkVO, FriendLink.class);
        this.saveOrUpdate(friendLink);
    }

}
