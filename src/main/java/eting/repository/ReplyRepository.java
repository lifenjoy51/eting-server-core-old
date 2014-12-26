package eting.repository;

import eting.domain.pk.ExchangePK;
import eting.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lifenjoy51 on 12/15/14.
 */
public interface ReplyRepository extends JpaRepository<Reply, ExchangePK> {

}
