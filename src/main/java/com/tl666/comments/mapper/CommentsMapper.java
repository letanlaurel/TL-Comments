package com.tl666.comments.mapper;

import com.tl666.comments.pojo.CommentsInfo;
import com.tl666.comments.pojo.CommentsReply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentsMapper {

    /**
     * 获取该文章或资源下的所有评论
     * @param ownerId 文章或资源id
     * @return
     */
//    @Select("select * from comments_info where owner_id = #{ownerId}")
    List<CommentsInfo> findByOwnerId(String ownerId);

    /**
     * 添加子评论或回复评论
     * @param commentsReply
     * @return
     */
    @Insert("insert into comments_reply (id,comment_id,from_id,from_name,from_avatar,to_id,to_name,to_avatar,like_num,content,create_time) " +
            "values(#{id},#{commentId},#{fromId},#{fromName},#{fromAvatar},#{toId},#{toName},#{toAvatar},#{likeNum},#{content},#{createTime})")
    boolean addSonComments(CommentsReply commentsReply);

    /**
     * 添加父评论
     * @param commentsInfo
     * @return
     */
//    @Insert("insert into comments_info (id,comment_id,owner_id,type,from_id,from_name,from_avatar,like_num,content,create_time) " +
//            "values(#{id},#{commentId},#{ownerId},#{type},#{fromId},#{fromName},#{fromAvatar},#{likeNum},#{content},#{createTime})")
    boolean addRootComments(CommentsInfo commentsInfo);
}
