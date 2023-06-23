package com.laba.solvd.databases.interfacedao;

import com.laba.solvd.databases.model.Purchase;
import java.util.List;

public interface IPurchaseDAO {
  void createPurchase(Purchase purchase);
  Purchase getPurchaseById(int purchaseId);
  List<Purchase> getAllPurchases();

}
