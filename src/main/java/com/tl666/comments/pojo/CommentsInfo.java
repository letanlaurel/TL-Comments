package com.tl666.comments.pojo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 评论表主表
 */
@Data
public class CommentsInfo implements Serializable{

    //评论主键id
    private Integer id;

    //评论唯一标识
    private String commentId;

    //评论的资源id。标记这条评论是属于哪个资源的。资源可以是文章、视频、资源
    private String ownerId;

    //评论类型。1文章，2视频评论，3资源评论
    private Integer type;

    //评论者id
    private String fromId;

    //评论者名字
    private String fromName;

    //评论者头像
    private String fromAvatar;

    //获得点赞的数量
    private Integer likeNum;

    //评论内容
    private String content;

    //创建时间
    private Date createTime;

    //子评论
    private List<CommentsReply> listCommentsReply;

}

