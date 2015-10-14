package com.provectus.budgetrush.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.provectus.budgetrush.data.Group;
import com.provectus.budgetrush.repository.GroupRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupService extends GenericService<Group, GroupRepository> {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    protected GroupRepository getRepository() {
        return groupRepository;
    }

}
