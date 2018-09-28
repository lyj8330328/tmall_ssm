package util;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 98050
 * Time: 2018-09-17 20:44
 * Feature:
 */
public class UploadedImageFile {
    MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
