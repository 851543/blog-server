package com.server.service;

import com.server.domain.Talk;
import com.server.domain.dto.PageResultDTO;
import com.server.domain.dto.TalkAdminDTO;
import com.server.domain.dto.TalkDTO;
import com.server.domain.vo.ConditionVO;
import com.server.domain.vo.TalkVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface TalkService extends IService<Talk> {

    PageResultDTO<TalkDTO> listTalks();

    TalkDTO getTalkById(Integer talkId);

    void saveOrUpdateTalk(TalkVO talkVO);

    void deleteTalks(List<Integer> talkIdList);

    PageResultDTO<TalkAdminDTO> listBackTalks(ConditionVO conditionVO);

    TalkAdminDTO getBackTalkById(Integer talkId);

}
