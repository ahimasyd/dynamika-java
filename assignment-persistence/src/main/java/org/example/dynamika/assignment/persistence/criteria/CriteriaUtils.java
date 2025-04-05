package org.example.dynamika.assignment.persistence.criteria;

import lombok.experimental.UtilityClass;
import org.example.dynamika.assignment.dto.commons.OrderDirection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;

@UtilityClass
public class CriteriaUtils {

    public static Order getOrder(CriteriaBuilder cb, Path<?> path, OrderDirection direction) {
        switch (direction) {
            case ASC:
                return cb.asc(path);
            case DESC:
                return cb.desc(path);
        }
        throw new RuntimeException("Unexpected order direction");
    }

}
