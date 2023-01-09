package com.sideproject.sideproject.comment.dto.response;

import com.sideproject.sideproject.comment.dto.CommentDTO;
import com.sideproject.sideproject.comment.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    Long id;
    String content;
    LocalDateTime createDate;
    UserDTO userDTO;

    public static CommentResponse of(
            Long id,
            String content,
            LocalDateTime createDate,
            UserDTO userDTO
    ){
        return new CommentResponse(id,content,createDate,userDTO);
    }


    public static CommentResponse from(CommentDTO dto){
        return CommentResponse.of(
                dto.getId(),
                dto.getContent(),
                dto.getCreateDate(),
                dto.getUserDTO()
        );
    }
}
