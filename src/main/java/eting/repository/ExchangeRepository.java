package eting.repository;

import eting.domain.Exchange;
import eting.domain.pk.ExchangePK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lifenjoy51 on 12/15/14.
 */
public interface ExchangeRepository extends JpaRepository<Exchange, ExchangePK> {

}
