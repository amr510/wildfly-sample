package explaineverything.wildfly.ejb;

import explaineverything.wildfly.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public boolean deleteById(long id) {
        EntityGraph<?> userWithGroupsGraph = em.getEntityGraph("userWithGroupsGraph");
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", userWithGroupsGraph);
        User user = em.find(User.class, id, properties);
        if( user != null){
            user.getGroups().forEach(group -> group.removeUser(user));
            em.remove(user);
            return true;
        }
        return false;
    }

    @Transactional
    public User update(User user) {
        return em.merge(user);
    }
}
