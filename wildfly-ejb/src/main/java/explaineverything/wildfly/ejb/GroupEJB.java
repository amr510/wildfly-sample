package explaineverything.wildfly.ejb;

import explaineverything.wildfly.model.Group;
import explaineverything.wildfly.model.User;
import org.hibernate.Hibernate;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class GroupEJB {

    @PersistenceContext(unitName = "")
    private EntityManager em;

    @Transactional
    public Group create(Group group) {
        em.persist(group);
        return group;
    }

    public Group get(long id) {
        return em.find(Group.class, id);
    }

    public List<Group> getAll() {
        TypedQuery<Group> typedQuery = em.createQuery("SELECT g from Group g", Group.class);
        return typedQuery.getResultList();
    }

    @Transactional
    public Group update(Group group) {
        return em.merge(group);
    }

    @Transactional
    public Group addGroupMember(long groupId, long userId) {
        Group group = em.find(Group.class, groupId);
        User user = em.find(User.class, userId);

        group.addUser(user);
        return group;
    }

    @Transactional
    public Group deleteGroupMember(long groupId, long userId) {
        Group group = em.find(Group.class, groupId);
        User user = em.find(User.class, userId);
        group.removeUsers(user);
        return group;
    }


    @Transactional
    public Set<User> getGroupMembers(long groupId) {
        Group group = em.find(Group.class, groupId);
        Hibernate.initialize(group.getUsers());
        return group.getUsers();
    }

}
