package com.provectus.budgetrush.datatest;

import com.provectus.budgetrush.data.contractor.Contractor;
import com.provectus.budgetrush.service.ContractorService;
import com.provectus.budgetrush.service.GroupService;
import com.provectus.budgetrush.service.UserService;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;

@ContextConfiguration(classes = {
        ContractorService.class,
        UserService.class,
        GroupService.class
})

public class ContractorsTest extends TestGenericService<Contractor, ContractorService> {
    @Inject
    private ContractorService contractorService;
    @Inject
    private UserService userService;

    @Override
    protected Contractor getEntity() {
        Contractor contractor = new Contractor();

        contractor.setName(Integer.toString(random.nextInt()));
        contractor.setUser(userService.getById(1));
        return contractor;
    }

    protected ContractorService getService() {
        return contractorService;
    }

    @Override
    public void delete() {
        //do nothing
    }
}
