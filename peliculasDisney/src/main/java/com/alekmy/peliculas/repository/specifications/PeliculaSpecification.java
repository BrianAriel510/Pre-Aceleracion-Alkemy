package com.alekmy.peliculas.repository.specifications;

import com.alekmy.peliculas.dto.filters.PeliculaFilterDTO;
import com.alekmy.peliculas.entity.GeneroEntity;
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
import org.springframework.util.StringUtils;

@Component
public class PeliculaSpecification {

    public Specification<PeliculaEntity> getByFilters(PeliculaFilterDTO filterDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filterDTO.getName())) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("titulo")),
                        "%" + filterDTO.getName().toLowerCase() + "%")
                );
            }

            if (filterDTO.getGenre() != null) {
                Join<GeneroEntity, PeliculaEntity> join = root.join("genero", JoinType.INNER);
                Expression<String> generoId = join.get("idGenero");
                predicates.add(generoId.in(filterDTO.getGenre()));
            }
            
            query.distinct(true);

            String orderByField = "fechaDeCreacion";
            query.orderBy(filterDTO.isASC()
                    ? criteriaBuilder.asc(root.get(orderByField))
                    : criteriaBuilder.desc(root.get(orderByField)));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
