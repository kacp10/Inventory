package com.intouch.Inventory.repository;

import com.intouch.Inventory.entity.AssetType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetTypeRepository extends JpaRepository<AssetType, Long> {}