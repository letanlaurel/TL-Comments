package com.tl666.comments.handler;

import com.tl666.comments.cache.TLCache;
import com.tl666.comments.pojo.CommentsInfo;
import com.tl666.comments.pojo.CommentsReply;
import com.tl666.comments.resultpojo.ResultDT;
import com.tl666.comments.service.CommentService;
import com.tl666.comments.utils.ResultDTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 点赞评论控制器
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600) //跨域
@Slf4j
public class CommentsHandler {
    @Autowired
    CommentService commentService;

    /**
     * 添加父评论   直接对标文章，资源等下面的评论
     * @param commentsInfo
     * @return
     */
    @PostMapping("addRootComments")
    public ResultDT addRootComments(CommentsInfo commentsInfo) {
        log.info("1" + commentsInfo.toString());
        if (commentsInfo.getContent().length() != 0) {
            commentsInfo.setCommentId(UUID.randomUUID().toString().replaceAll("-", ""));//设置评论唯一标识
            commentsInfo.setCreateTime(new Date());//设置添加评论时间
            log.info("2" + commentsInfo);
            boolean b = commentService.addRootCommentsService(commentsInfo); //调用service方法来完成评论的存储
            log.info("3" + commentsInfo.toString());
            if (b) {
                return ResultDTUtils.success(commentsInfo);
            }
        }
        //评论内容为空 返回错误信息
        return ResultDTUtils.error(ResultDTUtils.COMMENT_ERROR, "addError");
    }

    /**
     * 添加子评论，对应父评论
     * @param commentsReply
     * @return
     */
    @PostMapping("addSonComments")
    public ResultDT addSonComments(CommentsReply commentsReply) {
        log.info("1" + commentsReply.toString());
        if (commentsReply.getContent().length() != 0) {
            commentsReply.setCreateTime(new Date());
            log.info("2" + commentsReply);
            boolean b = commentService.addSonCommentsService(commentsReply);
            log.info("3" + commentsReply.toString());
            if (b) {
                return ResultDTUtils.success(commentsReply);
            }
        }
        return ResultDTUtils.error(ResultDTUtils.COMMENT_ERROR, "addError");
    }

    /**
     * 根据资源ID来回去该资源的所有评论
     * @param request
     * @return
     */
    @GetMapping("getListByOwnerId")
    public ResultDT getListByOwnerId(HttpServletRequest request) {
        String ownerId = request.getParameter("owner_id");
        log.info(ownerId);
        List<CommentsInfo> byOwnerIdService = commentService.findByOwnerIdService(ownerId);
        log.info(byOwnerIdService.toString());
        return ResultDTUtils.success(byOwnerIdService);
    }

    /**
     * 点赞模块，暂时未完善
     * @param request
     * @return
     */
    @PostMapping("isLike")
    public ResultDT isLike(HttpServletRequest request) {
        String id = request.getParameter("id");
        String userId = request.getParameter("userId");
        String commentId = request.getParameter("commentId");
        Map<String, Object> map = TLCache.getMap();
        if (map.get(id + userId + commentId) == null || map.get(id + userId + commentId).toString().equals("0")) {
            map.put(id + userId + commentId, 1);
            return ResultDTUtils.error(1, "liked");
        } else {
            map.put(id + userId + commentId, 0);
            return ResultDTUtils.error(1, "unliked");
        }

    }
}
