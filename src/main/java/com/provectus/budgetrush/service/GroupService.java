package com.provectus.budgetrush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.group.Group;
import com.provectus.budgetrush.repository.GroupRepository;

@Service
@Transactional(readOnly = true)
public class GroupService extends GenericService<Group, GroupRepository> {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    protected GroupRepository getRepository() {
        return groupRepository;
    }

}
