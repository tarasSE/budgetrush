package com.provectus.budgetrush.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.provectus.budgetrush.data.TransferOrder;

public interface TransferOrderRepository extends JpaRepository<TransferOrder, Integer> {

}
