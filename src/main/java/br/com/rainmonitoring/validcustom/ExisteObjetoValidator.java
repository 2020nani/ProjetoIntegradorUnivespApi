package br.com.rainmonitoring.validcustom;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExisteObjetoValidator implements ConstraintValidator<ExisteObjeto, Object> {

    private String domainAttribute;
    private Class<?> Klass;
    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(ExisteObjeto params) {
        domainAttribute = params.fieldName();
        Klass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = manager.createQuery("select 1 from "+Klass.getName()+" where "+domainAttribute+"=:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        Assert.isTrue(list.size() <=1, "Foi encontrado mais de um objeto com o atributo "+domainAttribute+" = "+value);

        return !list.isEmpty();
    }

}
