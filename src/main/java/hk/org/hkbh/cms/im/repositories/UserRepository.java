package hk.org.hkbh.cms.im.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hk.org.hkbh.cms.im.models.eos.UserEo;

@Repository
public interface UserRepository extends JpaRepository<UserEo, Long> {
    public UserEo findByUsername(String username);
}
