package com.example.EKart.Dao.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;
import java.util.Date;


@Where(clause = "IsActive=TRUE")
@Data
@Entity
@Table(name="production")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Column(name="idproduction")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ProductCode;

    @Column(name="productname")
    private String ProductName;

    @Column(name ="productdescription")
    private String ProductDescription;

    @Column(name="producttype")
    private String ProductType;

    @Column(name="created")
    private Date Created;

    @Column(name="modified")
    private Date Modified;

    @Column(name="isactive")
    private Boolean IsActive=Boolean.TRUE;
}
