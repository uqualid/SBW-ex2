package com.example.ex2.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity                     //Entity class
@Table(name = "tbl_memo")   //table name, index setting
@ToString
@Getter                     //create Getter Method
@Builder                    //use with ArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK Auto Create(Auto Increasement)
    private Long mno;

    @Column(length = 200, nullable = false)
    private String memoText;

}
