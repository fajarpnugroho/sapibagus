package com.sapibagus.android.utils;

import com.sapibagus.android.api.model.entity.AttachmentEntity;

import java.util.List;

public class ImageUtils {
    public ImageUtils() {}

    public static AttachmentEntity getImageAttachment(List<AttachmentEntity> attachments) {
        for (AttachmentEntity attachmentEntity : attachments) {
            // If found attachment with mimeType image return attachment entity
            if (attachmentEntity.mimeType.contains("image")) {
                return attachmentEntity;
            }
        }

        return null;
    }
}
