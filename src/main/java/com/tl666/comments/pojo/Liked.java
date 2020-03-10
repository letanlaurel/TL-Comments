package com.tl666.comments.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Liked  implements Serializable {
    private Integer id; //主键

    private String objId;//对应对象的id

    private String userId;//点赞用户的id

    private Integer likeStatus;//点赞状态，对应的1标识已赞，0标识取消赞
}
