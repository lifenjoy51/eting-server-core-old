package eting.repository;

import eting.domain.Exchange;
import eting.domain.ExchangePK;
import eting.domain.Story;
import eting.domain.StoryPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lifenjoy51 on 12/15/14.
 */
public interface ExchangeRepository extends JpaRepository<Exchange, ExchangePK> {

}
