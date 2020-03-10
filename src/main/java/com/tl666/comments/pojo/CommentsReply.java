package com.tl666.comments.pojo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论表子表
 */
@Data
public class CommentsReply implements Serializable{

    //父评论的主键id
    private String id;

    //该条评论的唯一标识
    private String commentId;

    //评论者id
    private String fromId;

    //评论者名字
    private String fromName;

    //评论者头像
    private String fromAvatar;

    //被评论者id
    private String toId;

    //被评论者名字
    private String toName;

    //被评论者头像
    private String toAvatar;

    //获得点赞的数量
    private Integer likeNum;

    //评论内容
    private String content;

    //创建时间
    private Date createTime;

}

