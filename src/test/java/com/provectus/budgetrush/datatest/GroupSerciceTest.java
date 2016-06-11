package com.provectus.budgetrush.datatest;

import com.provectus.budgetrush.data.group.Group;
import com.provectus.budgetrush.service.GroupService;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;

@ContextConfiguration(classes = {
        GroupService.class
})

public class GroupSerciceTest extends TestGenericService<Group, GroupService> {
    @Inject
    private GroupService groupService;

    @Override
    protected Group getEntity() {
        Group group = new Group();
        group.setName(Integer.toString(random.nextInt()));
        return group;
    }

    protected GroupService getService() {
        return groupService;
    }

    @Override
    public void delete() {
        //do nothing
    }
}
