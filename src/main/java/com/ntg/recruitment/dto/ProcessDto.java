package com.ntg.recruitment.dto;
import com.ntg.recruitment.entity.User;
import lombok.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProcessDto {
    private String id;
    private  String name;// matches TypeScript 'id'
//    private String status;                 // 'Active' | 'Completed'
    private boolean isActive;              // matches TypeScript 'isActive'
    private List<User> candidates;  // matches TypeScript 'candidates'

}
