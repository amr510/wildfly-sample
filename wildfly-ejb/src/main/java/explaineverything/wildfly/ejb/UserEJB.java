package explaineverything.wildfly.ejb;

import explaineverything.wildfly.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UserEJB {

    @PersistenceContext(unitName = "")
    private EntityManager em;

    public List<User> getAll() {
        TypedQuery<User> typedQuery = em.createQuery("SELECT u from User u", User.class);
        return typedQuery.getResultList();
    }

    @Transactional
    public User create(User user) {
        em.persist(user);
        return user;
    }

    public User get(long id) {
        return em.find(User.class, id);
    }

    @Transactional
    public User deleteById(long id) {
        User user = em.find(User.class, id);
        if( user != null){

        }
        return null;
    }

    @Transactional
    public User update(User user) {
        return em.merge(user);
    }

    public List<User> getUsersByGroupId(long groupId){
        TypedQuery<User> typedQuery = em.createNamedQuery("getGroupMembers", User.class);
        return typedQuery.getResultList();
    }
}
