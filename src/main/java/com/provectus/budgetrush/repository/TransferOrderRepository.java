package com.provectus.budgetrush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.provectus.budgetrush.data.TransferOrder;

@Repository
public interface TransferOrderRepository extends JpaRepository<TransferOrder, Integer> {

}
