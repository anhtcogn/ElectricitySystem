package com.electricitysystem.repository;

import ch.qos.logback.core.model.INamedModel;
import com.electricitysystem.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

}
