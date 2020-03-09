package com.tl666.comments.service.impl;

import com.tl666.comments.mapper.CommentsMapper;
import com.tl666.comments.pojo.CommentsInfo;
import com.tl666.comments.pojo.CommentsReply;
import com.tl666.comments.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论的service类，衔接控制层与持久化层
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentsMapper commentsMapper;

    @Override
    public List<CommentsInfo> findByOwnerIdService(String ownerId) {
        if(ownerId == null || ownerId.equals("")){
            return null;
        }
        return commentsMapper.findByOwnerId(ownerId);
    }

    @Override
    public boolean addRootCommentsService(CommentsInfo commentsInfo) {
        if(commentsInfo == null){
            return false;
        }
        return commentsMapper.addRootComments(commentsInfo);
    }

    @Override
    public boolean addSonCommentsService(CommentsReply commentsReply) {
        if(commentsReply == null){
            return false;
        }
        return commentsMapper.addSonComments(commentsReply);
    }
}
