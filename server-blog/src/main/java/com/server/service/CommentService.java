package com.server.service;

import com.server.domain.Comment;
import com.server.domain.dto.CommentAdminDTO;
import com.server.domain.dto.CommentDTO;
import com.server.domain.dto.PageResultDTO;
import com.server.domain.dto.ReplyDTO;
import com.server.domain.vo.CommentVO;
import com.server.domain.vo.ConditionVO;
import com.server.domain.vo.ReviewVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CommentService extends IService<Comment> {

    void saveComment(CommentVO commentVO);

    PageResultDTO<CommentDTO> listComments(CommentVO commentVO);

    List<ReplyDTO> listRepliesByCommentId(Integer commentId);

    List<CommentDTO> listTopSixComments();

    PageResultDTO<CommentAdminDTO> listCommentsAdmin(ConditionVO conditionVO);

    void updateCommentsReview(ReviewVO reviewVO);

}
