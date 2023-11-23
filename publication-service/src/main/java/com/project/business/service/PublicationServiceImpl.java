package com.project.business.service;

import com.project.business.model.Publication;
import com.project.business.repository.PublicationRepository;
import com.project.business.model.type.PublicationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicationServiceImpl implements PublicationService {
    private final PublicationRepository publicationRepository;

    @Override
    public Publication getById(long id) {
        return publicationRepository.findById(id).orElseThrow(()->new RuntimeException("""
                no publication by the id:${id}"""));
    }

    @Override
    public Publication updateById(long id, Publication publication) {
        if(publicationRepository.existsById(id)){
            publication.setId(id);
            return publicationRepository.save(publication);
        }return  publicationRepository.save(publication);
    }

    @Override
    public Publication addPublication(Publication publication) {
        return publicationRepository.save(publication);
    }

    @Override
    public void deleteById(long id) {
          publicationRepository.deleteById(id);
    }

    @Override
    public List<Publication> findAllByPublicationType(PublicationType type) {
        return publicationRepository.findAllByPublicationType(type);
    }


}
