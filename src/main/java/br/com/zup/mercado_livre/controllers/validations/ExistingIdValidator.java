package br.com.zup.mercado_livre.controllers.validations;

import javax.persistence.*;
import javax.validation.*;
import java.util.*;

public class ExistingIdValidator implements ConstraintValidator<ExistingId, Object> {

    private String domainAttribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void initialize(ExistingId params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = em.createQuery("SELECT 1 FROM " + klass.getName() + " WHERE " + domainAttribute + " = :value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();

        return !list.isEmpty();
    }

}
