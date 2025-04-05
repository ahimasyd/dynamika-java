package org.example.dynamika.assignment.persistence.criteria;

import lombok.experimental.UtilityClass;
import org.example.dynamika.assignment.dto.book.BookOrderField;
import org.example.dynamika.assignment.dto.book.BookSearchRequest;
import org.example.dynamika.assignment.dto.commons.OrderDto;
import org.example.dynamika.assignment.persistence.entity.Book;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class BookCriteria {

    public static Specification<Book> search(BookSearchRequest request) {
        return (root, query, cb) -> {
            boolean countMode = query.getResultType() == Long.class;

            List<Order> orders = null;
            if (!countMode) {
                orders = getOrders(cb, root, request.getOrders());
            }

            return query
                    .distinct(true)
                    .orderBy(orders)
                    .getRestriction();
        };
    }

    private static List<Order> getOrders(CriteriaBuilder cb, Root<Book> root, List<OrderDto<BookOrderField>> orders) {
        if (ObjectUtils.isEmpty(orders)) {
            return new ArrayList<>();
        }

        List<Order> criteriaOrders = new ArrayList<>();
        orders.forEach(order -> {
            switch (order.getField()) {
                case ID:
                    criteriaOrders.add(CriteriaUtils.getOrder(cb, root.get("id"), order.getDirection()));
                    break;
                case TITLE:
                    criteriaOrders.add(CriteriaUtils.getOrder(cb, root.get("title"), order.getDirection()));
                    break;
            }
        });
        return criteriaOrders;
    }

}
