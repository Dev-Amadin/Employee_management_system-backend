package com.amadin.ems.employee;

import java.util.ArrayList;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class EmployeeSpecification {

    public static Specification<Employee> getSpecification(String search) {

        return new Specification<Employee>() {

            @Override
            public @Nullable Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query,
                    CriteriaBuilder criteriaBuilder) {

                // if (search == null || search.isEmpty()) {
                // criteriaBuilder.conjunction();
                // }

                List<Predicate> list = new ArrayList<>();

                if (search != null && !search.isEmpty()) {
                    list.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")),
                            "%" + search.toLowerCase() + "%"));
                    list.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")),
                            "%" + search.toLowerCase() + "%"));
                }

                return criteriaBuilder.or(list.toArray(new Predicate[0]));

            }

        };
    }

}
