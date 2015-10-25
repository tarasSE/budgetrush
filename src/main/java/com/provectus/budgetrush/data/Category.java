package com.provectus.budgetrush.data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
	
	private boolean predefined = false;
	
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent")
    private Category parent;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private User user;
    
    @JsonIgnore
    public void setPredefined(boolean predefined){
    	this.predefined = predefined;
    }

}