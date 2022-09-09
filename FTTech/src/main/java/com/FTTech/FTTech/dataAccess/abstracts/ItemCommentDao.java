package com.FTTech.FTTech.dataAccess.abstracts;

import com.FTTech.FTTech.entities.concretes.ItemComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ItemCommentDao extends JpaRepository<ItemComment,Integer> {
    List<ItemComment> getAllByItem_ItemId(Integer id);
    List<ItemComment> findByCommentDateBetween(LocalDate firstDate, LocalDate lastDate);
    List<ItemComment> getAllByUser_UserId(Integer id);
}
