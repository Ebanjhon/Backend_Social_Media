package com.eban.social_media.Services.ServiceImpl;

import com.eban.social_media.DTO.CommentCreateDTO;
import com.eban.social_media.DTO.CommentDTO;
import com.eban.social_media.DTO.CommentResponseDTO;
import com.eban.social_media.Models.Comment;
import com.eban.social_media.Models.Post;
import com.eban.social_media.Models.User;
import com.eban.social_media.Repositories.CommentRepository;
import com.eban.social_media.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private PostServiceImpl postServiceImpl;

    @Override
    public void saveComment(CommentCreateDTO comment) {
        Comment c = new Comment();
        User user = userServiceImpl.getUserById(comment.getIdUser());
        Post p = postServiceImpl.getPost(comment.getIdPost());
        c.setUser(user);
        c.setPost(p);
        c.setContent(comment.getContent());
        if(comment.getIdCmtParent() != 0)
        {
            Comment cmtParent = commentRepository.getReferenceById(comment.getIdCmtParent());
            c.setParentComment(cmtParent);
        }
        commentRepository.save(c);
    }

    @Override
    public Comment getCommentByIdComment(Long id) {
        return commentRepository.findById(id).get();
    }

    @Override
    public List<CommentResponseDTO> getCommentByPostId(Long postId) {
        List<CommentResponseDTO> comments = commentRepository.getCommentParentByPostId(postId); // Giả sử phương thức này trả về List<Comment>
        // lấy tất cả comment con
        List<CommentResponseDTO> responseDTOs = new ArrayList<>();
        for (CommentResponseDTO comment : comments) {
            List<CommentDTO> cmtChilds = commentRepository.GetCommentChildByParent(comment.getIdComment());
            comment.setCommentChild(cmtChilds);
        }
        return comments;  // Trả về danh sách các CommentResponseDTO
    }

    @Override
    public void deleteComment(Long id) {
        deletaCommentByCommentIdParent(id);// xóa cấp con
        commentRepository.deleteById(id);// xóa cha
    }

    @Override
    public void deleteCommentByPostId(Long postId) {
        commentRepository.deleteByIdPost(postId);
    }

    @Override
    public void deletaCommentByCommentIdParent(Long parentId) {
        commentRepository.deleteByIdCommentParent(parentId);
    }
}
