package com.sparta.msa_exam.product.core;

import com.sparta.msa_exam.product.products.ProductRequestDto;
import com.sparta.msa_exam.product.products.ProductResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Integer price;
    private Integer quantity;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private LocalDateTime deletedAt;
    private String deletedBy;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public static Product createProduct(ProductRequestDto requestDto, String userId) {
        return Product.builder()
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .price(requestDto.getPrice())
                .quantity(requestDto.getQuantity())
                .createdBy(userId)
                .build();
    }

    public void updateProduct(String name, String description, Integer price, Integer quantity, String updatedBy) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.updatedBy = updatedBy;
        this.updatedAt = LocalDateTime.now();
    }

    public void deleteProduct(String deletedBy) {
        this.deletedBy = deletedBy;
        this.deletedAt = LocalDateTime.now();
    }

    // DTO로 변환하는 메서드
    public ProductResponseDto toResponseDto() {
        return new ProductResponseDto(
                this.id,
                this.name,
                this.description,
                this.price,
                this.quantity,
                this.createdAt,
                this.createdBy,
                this.updatedAt,
                this.updatedBy
        );
    }

    public void reduceQuantity(int i) {
        this.quantity = this.quantity - i;
    }
}