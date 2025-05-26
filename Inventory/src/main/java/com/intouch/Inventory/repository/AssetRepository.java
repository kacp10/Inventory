package com.intouch.Inventory.repository;

import com.intouch.Inventory.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findByStatus(String status);
}