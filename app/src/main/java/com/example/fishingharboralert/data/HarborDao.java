package com.example.fishingharboralert.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HarborDao {
    @Query("SELECT * FROM harbors ORDER BY city, name")
    List<HarborEntity> getHarbors();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHarbors(List<HarborEntity> harbors);

    @Query("SELECT * FROM favorite_harbors ORDER BY createdAt DESC")
    List<FavoriteHarborEntity> getFavorites();

    @Query("SELECT COUNT(*) FROM favorite_harbors WHERE harborName = :harborName")
    int countFavorite(String harborName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addFavorite(FavoriteHarborEntity favorite);

    @Query("DELETE FROM favorite_harbors WHERE harborName = :harborName")
    void removeFavorite(String harborName);

    @Query("SELECT * FROM alert_rules WHERE id = 1 LIMIT 1")
    AlertRuleEntity getAlertRule();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAlertRule(AlertRuleEntity rule);
}
