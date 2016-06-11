package com.provectus.budgetrush.datatest;

import com.provectus.budgetrush.data.user.User;
import com.provectus.budgetrush.service.GroupService;
import com.provectus.budgetrush.service.UserService;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = {
        UserService.class,
        GroupService.class
})

public class UsersTest extends TestGenericService<User, UserService> {
    @Inject
    private UserService userService;

    @Test
    @Transactional
    public void testGetRoleByName() {
        User user = getEntity();
        getService().create(user);
        Enum<?> role = getService().getRoleByName(user.getName());
        assertNotNull(role);
    }

    @Test
    @Transactional
    public void testFind() throws Exception {
        User user = getEntity();
        getService().create(user);
        User user2 = getService().find(user.getName());
        assertNotNull(user2.getId());
    }

    @Test
    @Transactional
    public void testFindByEmail() {
        User user = getEntity();
        getService().create(user);
        User user2 = getService().findByEmail(user.getEmail());
        assertNotNull(user2.getId());
    }

    //    @Test
//    @Transactional
//    public void testGetUserGroups() throws Exception {
//        User user = saveTestUser();
//        Set<Group> groups = service.getById(user.getId()).getGroups();
//        assertFalse(groups.isEmpty());
//        log.info("User id " + user.getId() + " groups: \n");
//
//        for (Group group : groups) {
//
//            log.info(group.toString() + "\n");
//
//        }
//    }

    @Override
    protected User getEntity() {
        User user = new User();

        user.setName(Integer.toString(random.nextInt()));
        user.setPassword(Integer.toString(random.nextInt()));
        user.setEmail("test@Mail.com");
        return user;
    }

    protected UserService getService() {
        return userService;
    }

    @Override
    public void delete() {
        //do nothing
    }
}
