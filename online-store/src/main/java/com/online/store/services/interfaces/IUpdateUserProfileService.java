package com.online.store.services.interfaces;

public interface IUpdateUserProfileService {

	void updateUserStringFields(String newFieldValue, String fieldToUpdate);

	void updateUserPassword(String newPassword);

	void updateUserProfileImage(byte[] newImage);

}