package explaineverything.wildfly.ejb;

import explaineverything.wildfly.model.Group;
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
        group.removeUser(user);
        return group;
    }


    @Transactional
    public Group getGroupWithMembers(long groupId) {
        EntityGraph<?> groupWithMembersGraph = em.getEntityGraph("groupWithMembersGraph");
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", groupWithMembersGraph);
        Group group = em.find(Group.class, groupId, properties);
        return group;
    }

    @Transactional
    public boolean deleteById(long id){
        Group group = em.find(Group.class, id);
        if( group != null){
            em.remove(group);
            return true;
        }
        return false;
    }

}
