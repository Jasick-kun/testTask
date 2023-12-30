package kz.jasulan.testtask.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class CreateSpecification<T> implements Specification<T> {

    private List<SearchCriteria> list;

    public CreateSpecification(){
        this.list= new ArrayList<>();
    }

    public void add(SearchCriteria searchCriteria){
        list.add(searchCriteria);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder){
        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria : list) {
            Predicate predicate = null;
            switch (criteria.getOperation()) {
                case GREATER_THAN:
                    predicate = builder.greaterThan(
                        root.get(criteria.getKey()), criteria.getValue().toString());
                    break;
                case LESS_THAN:
                    predicate = builder.lessThan(
                        root.get(criteria.getKey()), criteria.getValue().toString());
                    break;
                case GREATER_THAN_EQUAL:
                    predicate = builder.greaterThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString());
                    break;
                case LESS_THAN_EQUAL:
                    predicate = builder.lessThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString());
                    break;
                case NOT_EQUAL:
                    predicate = builder.notEqual(
                        root.get(criteria.getKey()), criteria.getValue());
                    break;
                case EQUAL:
                    predicate = builder.equal(
                        root.get(criteria.getKey()), criteria.getValue());
                    break;
                case MATCH:
                    predicate = builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase() + "%");
                    break;
                case MATCH_END:
                    predicate = builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        criteria.getValue().toString().toLowerCase() + "%");
                    break;
                case MATCH_START:
                    predicate = builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase());
                    break;
                case IN:
                    predicate = builder.in(root.get(criteria.getKey())).value(criteria.getValue());
                    break;
                case NOT_IN:
                    predicate = builder.not(root.get(criteria.getKey())).in(criteria.getValue());
                    break;
            }
            if (predicate != null) { predicates.add(predicate); }
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
