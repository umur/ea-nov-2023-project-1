package com.alumni.repository;

import com.alumni.entity.NewsAndAnnouncements;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsAndAnnouncementsRepo extends ListCrudRepository<NewsAndAnnouncements, Long> {
}
