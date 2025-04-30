package com.server.service.impl;

import com.server.domain.ArticleTag;
import com.server.mapper.ArticleTagMapper;
import com.server.service.ArticleTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

}
