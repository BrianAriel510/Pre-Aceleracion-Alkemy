package com.alekmy.peliculas.repository.specifications;

import com.alekmy.peliculas.dto.PersonajeFilterDTO;
import com.alekmy.peliculas.entity.PeliculaEntity;
import com.alekmy.peliculas.entity.PersonajeEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Component
public class PersonajeSpecification {

    public Specification<PersonajeEntity> getByFilters(PersonajeFilterDTO filterDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filterDTO.getName())) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("nombre")),
                        "%" + filterDTO.getName().toLowerCase() + "%")
                );
            }

            if (filterDTO.getAge() != null) {
                predicates.add(criteriaBuilder.equal(root.get("edad"), filterDTO.getAge()));
            }
//TODO: Arreglar este filtro
            if (!CollectionUtils.isEmpty(filterDTO.getMovies())) {
                Join<PeliculaEntity, PersonajeEntity> join = root.join("peliculasAsociadas", JoinType.INNER);
                Expression<String> movie = join.get("peliculas");
                predicates.add(movie.in(filterDTO.getMovies()));
            }

            query.distinct(true);

            /*String orderByField = "nombre";
            query.orderBy(filterDTO.isASC()
                    ? criteriaBuilder.asc(root.get(orderByField))
                    : criteriaBuilder.desc(root.get(orderByField)));*/
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
