package com.example.backendinventory.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 200, nullable = false)
    private String name;
    @Column(nullable = false, columnDefinition = "decimal(6,2)")
    private Double price;
    @Column(nullable = false)
    private Integer quantity;
    @Column(length = 1000)
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] picture;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category", foreignKey = @ForeignKey(name = "category_id"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Category category;
}
