package com.project.alumni.repository;

import com.project.alumni.entity.NewsAndAnnouncements;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsAndAnnouncementsRepo extends ListCrudRepository<NewsAndAnnouncements, Long> {
}
