package se.lexicon.jpa_workshop.data;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.jpa_workshop.model.Details;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository

public class DetailsDAORepository implements DetailsDAO {
    @PersistenceContext
    EntityManager entityManager;


    @Override
    @Transactional
    public Details create(Details details) {
        entityManager.persist(details);
        return details;
    }

    @Override
    @Transactional
    public Details findById(int id) {
        return entityManager.find(Details.class, id);
    }

    @Override
    @Transactional
    public Collection<Details> findAll() {
        return entityManager.createQuery("SELECT details FROM Details details",Details.class).getResultList();
    }

    @Override
    @Transactional
    public Details update(Details details) {
        return entityManager.merge(details);
    }

    @Override
    @Transactional
    public void delete(int id) {

        entityManager.remove(findById(id));
    }
}
