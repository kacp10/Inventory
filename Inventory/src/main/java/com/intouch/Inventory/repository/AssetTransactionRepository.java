package com.intouch.Inventory.repository;

import com.intouch.Inventory.entity.AssetTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetTransactionRepository extends JpaRepository<AssetTransaction, Long> {
    List<AssetTransaction> findByAssetIdOrderByTimestampDesc(Long assetId);
}