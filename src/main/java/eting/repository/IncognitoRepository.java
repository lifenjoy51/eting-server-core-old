package eting.repository;

import eting.domain.Device;
import eting.domain.Incognito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by lifenjoy51 on 12/14/14.
 */
public interface IncognitoRepository extends JpaRepository<Incognito, Long> {

}
