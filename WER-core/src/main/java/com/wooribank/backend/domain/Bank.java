package com.wooribank.backend.domain;

import com.wooribank.backend.vo.AccountVo;
import com.wooribank.backend.vo.WooriUserVo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "bank_mst")
//created 2021.09.07 bank 정보 테이블
//ex)bank_id 1 / name 우리은행
//   bank_id 2 / name 국민은행
public class Bank extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_id")
    private Long id;

    @Column(name = "name")
    private String name;

    public Bank(String name) {
        this.name = name;
    }

}
