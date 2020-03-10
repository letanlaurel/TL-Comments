package com.tl666.comments.handler;

import com.tl666.comments.pojo.CommentsReply;
import com.tl666.comments.pojo.CommentsRoot;
import com.tl666.comments.pojo.Liked;
import com.tl666.comments.resultpojo.ResultDT;
import com.tl666.comments.service.CommentService;
import com.tl666.comments.utils.ResultDTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
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
     * @param commentsRoot
     * @return
     */
    @PostMapping("addRootComments")
    public ResultDT addRootComments(CommentsRoot commentsRoot) {
        log.info("1" + commentsRoot.toString());
        if (commentsRoot.getContent().length() != 0) {
            commentsRoot.setCommentId(UUID.randomUUID().toString().replaceAll("-", ""));//设置评论唯一标识
            commentsRoot.setCreateTime(new Date());//设置添加评论时间
            log.info("2" + commentsRoot);
            boolean b = commentService.addRootCommentsService(commentsRoot); //调用service方法来完成评论的存储
            log.info("3" + commentsRoot.toString());
            if (b) {
                return ResultDTUtils.success(commentsRoot);
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
            commentsReply.setCommentId(UUID.randomUUID().toString().replaceAll("-",""));
            commentsReply.setCreateTime(new Date());
            log.info("2" + commentsReply);
            boolean b = commentService.addSonCommentsService(commentsReply);
            log.info("3" + commentsReply.toString());
            if (b) {
                return ResultDTUtils.success(commentsReply,null);
            }
        }
        return ResultDTUtils.error(ResultDTUtils.COMMENT_ERROR, "addError");
    }

    /**
     * 根据资源ID来回去该资源的所有评论
     * @param request
     * @return
     */
    @RequestMapping("getListByOwnerId")
    public ResultDT getListByOwnerId(HttpServletRequest request) {
        String ownerId = request.getParameter("ownerId");
        String userId = request.getParameter("userId");
        log.info(ownerId);
        log.info(userId);
        //查询所有评论
        List<CommentsRoot> byOwnerIdService = commentService.findByOwnerIdService(ownerId);
        log.info(byOwnerIdService.toString());
        return ResultDTUtils.success(byOwnerIdService);
    }

    /**
     * 点赞模块，已完善
     * @param liked
     * @return
     */
    @PostMapping("isLike")
    public ResultDT isLike(Liked liked,@RequestParam("commType") String commType) {
        log.info("isLike"+liked.toString());
       boolean b;
        Liked checked = commentService.checkedLikeService(liked);
        if(checked == null){
            liked.setLikeStatus(1);
            b = commentService.addLikedService(liked);
        }else {
            if(checked.getLikeStatus() == 0)
                liked.setLikeStatus(1);
            else
                liked.setLikeStatus(0);
            b = commentService.updateLikedService(liked);
        }
        if(b) {
            //更新评论的点赞次数
            if(liked.getLikeStatus() == 0)
                liked.setLikeStatus(-1);
            if(commType.equals("root"))
                commentService.updateRootLikeNumService(liked);
            else
                commentService.updateReplyLikeNumService(liked);
            return ResultDTUtils.success(liked);
        }
        else
            return ResultDTUtils.error(ResultDTUtils.SUBMIT_ERROR,"SubmitError");
    }

    @RequestMapping("getListLikeByUserId")
    public ResultDT getListLikeByUserId(@RequestParam("userId") String userId){
        //查询所有点赞信息
        List<Liked> listLikedService = commentService.getListLikedService(userId);
        log.info(listLikedService.toString());
        return ResultDTUtils.success(listLikedService);
    }
}
