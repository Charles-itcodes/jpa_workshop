package se.lexicon.jpa_workshop.data;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.jpa_workshop.model.AppUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class AppUserDAORepository implements AppUserDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public AppUser findById(int id) {
        return entityManager.find(AppUser.class, id);
    }

    @Transactional
    public Collection<AppUser> findAll() {
        return entityManager.createQuery("SELECT appUser FROM AppUser appUser", AppUser.class).getResultList();
    }

    @Override
    @Transactional
    public AppUser create(AppUser appUser) {
        entityManager.persist(appUser);
        return appUser; // Will return with generated id.
    }

    @Override
    @Transactional
    public AppUser update(AppUser appUser) {
        return entityManager.merge(appUser);
    }

    @Override
    @Transactional
    public void delete(int id) {
        entityManager.remove(findById(id));
    }
}
