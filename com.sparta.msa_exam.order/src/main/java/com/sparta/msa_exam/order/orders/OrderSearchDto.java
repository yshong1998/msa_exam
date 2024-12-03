package com.sparta.msa_exam.order.orders;

import com.sparta.msa_exam.order.core.enums.OrderStatus;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
public class OrderSearchDto {
    private OrderStatus status;
    private List<Long> orderItemIds;
    private String sortBy;
    private Pageable pageable;
}