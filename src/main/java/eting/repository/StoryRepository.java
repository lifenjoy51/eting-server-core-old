package eting.repository;

import eting.domain.Story;
import eting.domain.pk.StoryPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lifenjoy51 on 12/15/14.
 */
public interface StoryRepository extends JpaRepository<Story, StoryPK> {

    public List<Story> findByIncognitoId(long incognitoId);
}
