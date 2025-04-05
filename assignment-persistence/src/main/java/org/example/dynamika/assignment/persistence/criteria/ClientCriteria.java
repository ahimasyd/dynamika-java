package org.example.dynamika.assignment.persistence.criteria;

import lombok.experimental.UtilityClass;
import org.example.dynamika.assignment.dto.client.ClientOrderField;
import org.example.dynamika.assignment.dto.client.ClientSearchRequest;
import org.example.dynamika.assignment.dto.commons.OrderDto;
import org.example.dynamika.assignment.persistence.entity.Client;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ClientCriteria {

    public static Specification<Client> search(ClientSearchRequest request) {
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

    private static List<Order> getOrders(CriteriaBuilder cb, Root<Client> root, List<OrderDto<ClientOrderField>> orders) {
        if (ObjectUtils.isEmpty(orders)) {
            return new ArrayList<>();
        }

        List<Order> criteriaOrders = new ArrayList<>();
        orders.forEach(order -> {
            switch (order.getField()) {
                case ID:
                    criteriaOrders.add(CriteriaUtils.getOrder(cb, root.get("id"), order.getDirection()));
                    break;
                case LAST_NAME:
                    criteriaOrders.add(CriteriaUtils.getOrder(cb, root.get("last_name"), order.getDirection()));
                    break;
            }
        });
        return criteriaOrders;
    }

}
