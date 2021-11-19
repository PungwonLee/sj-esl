package sj.sjesl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sj.sjesl.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
