package com.provectus.budgetrush.data.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.provectus.budgetrush.data.BaseEntity;
import com.provectus.budgetrush.data.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
	
	private boolean predefined = false;
	
    private String name;


    @ManyToOne
    @JoinColumn(name = "parent")
    //@NotNull
    private Category parent;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private User user;
    
    @JsonIgnore
    public void setPredefined(boolean predefined){
    	this.predefined = predefined;
    }

}