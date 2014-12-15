package eting.repository;

import eting.domain.Incognito;
import eting.domain.Story;
import eting.domain.StoryPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lifenjoy51 on 12/15/14.
 */
public interface StoryRepository extends JpaRepository<Story, StoryPK> {

}
