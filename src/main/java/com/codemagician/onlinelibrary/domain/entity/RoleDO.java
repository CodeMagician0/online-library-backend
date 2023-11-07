package com.codemagician.onlinelibrary.domain.entity;

import com.codemagician.onlinelibrary.common.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/8/31 15:52
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class RoleDO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleEnum name;

    public RoleDO(RoleEnum name) {
        this.name = name;
    }
}
