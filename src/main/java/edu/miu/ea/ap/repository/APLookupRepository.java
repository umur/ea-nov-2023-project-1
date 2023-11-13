package edu.miu.ea.ap.repository;

import edu.miu.ea.ap.helper.RefreshableCRUDRepository;
import edu.miu.ea.ap.model.domain.APLookup;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface APLookupRepository extends RefreshableCRUDRepository<APLookup, Long> {

    APLookup findByTypeAndCodeAndRetiredFalse(String lookupType, String code);
    List<APLookup> findAllByRetiredFalseOrderByTypeAscSortOrderAscLabelEnAsc();
    List<APLookup> findAllByIdInAndRetiredFalseOrderByTypeAscSortOrderAscLabelEnAsc(List<Long> IDs);
    List<APLookup> findAllByOrderByTypeAscSortOrderAscLabelEnAsc();
    List<APLookup> findAllByIdInOrderByTypeAscSortOrderAscLabelEnAsc(List<Long> IDs);
    List<APLookup> findAllByTypeOrderBySortOrderAscLabelEnAsc(String type);
    List<APLookup> findAllByTypeAndIdInOrderBySortOrderAscLabelEnAsc(String type, List<Long> IDs);
    List<APLookup> findAllByTypeAndRetiredFalseOrderBySortOrderAscLabelEnAsc(String type);
    List<APLookup> findAllByTypeAndIdInAndRetiredFalseOrderBySortOrderAscLabelEnAsc(String type, List<Long> IDs);

    List<APLookup> findAllByTypeOrderBySortOrderDescLabelEnAsc(String type);
    List<APLookup> findAllByTypeAndSubtypeOrderBySortOrderDescLabelEnAsc(String type, String subtype);

}
