package com.tl666.comments.service;

import com.tl666.comments.pojo.CommentsInfo;
import com.tl666.comments.pojo.CommentsReply;

import java.util.List;

public interface CommentService {

    /**
     * 获取该文章或资源下的所有评论
     * @param ownerId 文章或资源id
     * @return
     */
    List<CommentsInfo> findByOwnerIdService(String ownerId);

    /**
     * 添加父评论
     * @param commentsInfo
     * @return
     */
    boolean addRootCommentsService(CommentsInfo commentsInfo);

    /**
     * 添加子评论或回复评论
     * @param commentsReply
     * @return
     */
    boolean addSonCommentsService(CommentsReply commentsReply);
}
