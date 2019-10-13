package com.houseWork.dao.comment;

import com.houseWork.entity.comment.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zzc
 */
@Mapper
@Repository
public interface CommentDao {
    /**
     * 根据socketid获取历史聊天记录
     * @return
     */
    List<Comment> getCommentListBySId();

    /**
     * 插入聊天记录
     * @param comment
     */
    void insertComment(Comment comment);
}
