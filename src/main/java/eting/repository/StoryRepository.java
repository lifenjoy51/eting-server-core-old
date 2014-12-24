package eting.repository;

import eting.domain.Incognito;
import eting.domain.Story;
import eting.domain.StoryPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by lifenjoy51 on 12/15/14.
 */
public interface StoryRepository extends JpaRepository<Story, StoryPK> {
/*
    @Query("SELECT i FROM Incognito i JOIN FETCH i.incognito WHERE i.incognito_id = (:incognitoId)")
    public Incognito findByIncognitoIdAndFetchIncognitoEagerly(@Param("id") Long incognitoId);*/
}
