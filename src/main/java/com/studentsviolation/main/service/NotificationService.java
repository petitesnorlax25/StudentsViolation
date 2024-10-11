package com.studentsviolation.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentsviolation.main.entity.Notification;
import com.studentsviolation.main.repository.NotificationRepository;
import org.springframework.transaction.annotation.Transactional;

@Service

public class NotificationService {
	@Autowired
	private NotificationRepository notificationRepository;
	@Transactional
	public Notification updateNotif(Long id, Notification notifDetails) {
		Notification notification = notificationRepository.findById(id).orElse(null);
        if (notification != null) {
        	notification.setIsNotif(notifDetails.getIsNotif());
        	notification.setCrinNotif(notifDetails.getCrinNotif());
        	notification.setOaNotif(notifDetails.getOaNotif());
        	notification.setEducNotif(notifDetails.getEducNotif());
        	notification.setGuideNotif(notifDetails.getGuideNotif());
        	notification.setDiscNotif(notifDetails.getDiscNotif());
        	notification.setNotif(notifDetails.getNotif());
            return notificationRepository.save(notification);
        }
        return null;
    }
}
