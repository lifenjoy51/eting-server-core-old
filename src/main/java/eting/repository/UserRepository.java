package eting.repository;

import eting.domain.Device;
import eting.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lifenjoy51 on 12/14/14.
 */
public interface UserRepository extends JpaRepository<User, String> {

}
