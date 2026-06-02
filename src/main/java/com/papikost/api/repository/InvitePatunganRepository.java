package com.papikost.api.repository;

import com.papikost.api.entity.InvitePatungan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InvitePatunganRepository extends JpaRepository<InvitePatungan, Long> {
    List<InvitePatungan> findByToUserIdOrderByCreatedAtDesc(String toUserId);
    List<InvitePatungan> findByFromUserIdOrderByCreatedAtDesc(String fromUserId);
}