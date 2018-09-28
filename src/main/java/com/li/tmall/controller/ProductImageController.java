package com.li.tmall.controller;

import com.li.tmall.pojo.Product;
import com.li.tmall.pojo.ProductImage;
import com.li.tmall.service.ProductImageService;
import com.li.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import util.ImageUtil;
import util.UploadedImageFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author: 98050
 * Time: 2018-09-19 17:12
 * Feature:
 */
@Controller
@RequestMapping("")
public class ProductImageController {

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private ProductService productService;

    @RequestMapping("admin_productImage_add")
    public String add(ProductImage productImage, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
        productImageService.add(productImage);
        imageAddProcess(productImage,session,uploadedImageFile);
        return "redirect:admin_productImage_list?pid="+productImage.getPid();
    }

    @RequestMapping("admin_productImage_delete")
    public String delete(Integer id,HttpSession session){
        ProductImage productImage = productImageService.get(id);
        imageDeleteProcess(productImage,session);
        productImageService.delete(id);
        return "redirect:admin_productImage_list?pid="+productImage.getPid();
    }

    @RequestMapping("admin_productImage_list")
    public String list(Integer pid, Model model){
        Product product = productService.get(pid);

        List<ProductImage> imageSingle = productImageService.list(pid,ProductImageService.TYPE_SINGLE);
        List<ProductImage> imageDetail = productImageService.list(pid,ProductImageService.TYPE_DETAIL);

        model.addAttribute("product",product);
        model.addAttribute("imageSingle",imageSingle);
        model.addAttribute("imageDetail",imageDetail);

        return "admin/listProductImage";
    }


    public void imageAddProcess(ProductImage productImage,HttpSession session,UploadedImageFile uploadedImageFile) throws IOException{
        String fileName = productImage.getId() + ".jpg";
        String imageFolder;
        String imageFolderSmall = null;
        String imageFolderMiddle = null;

        //如果上传的是产品单个图片的缩略图，将图片转换成两种不同规格大小的图片
        if (ProductImageService.TYPE_SINGLE.equals(productImage.getType())){
            imageFolder = session.getServletContext().getRealPath("img/productSingle");
            imageFolderSmall = session.getServletContext().getRealPath("img/productSingle_small");
            imageFolderMiddle = session.getServletContext().getRealPath("img/productSingle_middle");
        }else {
            //如果上传的是产品详情图片缩略图
            imageFolder = session.getServletContext().getRealPath("img/productDetail");
        }

        //创建文件夹
        File file = new File(imageFolder,fileName);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }

        uploadedImageFile.getImage().transferTo(file);
        BufferedImage image = ImageUtil.change2jpg(file);
        ImageIO.write(image,"jpg",file);

        if (ProductImageService.TYPE_SINGLE.equals(productImage.getType())){
            File fileSmall = new File(imageFolderSmall,fileName);
            File fileMiddle = new File(imageFolderMiddle,fileName);

            ImageUtil.resizeImage(file,56,56,fileSmall);
            ImageUtil.resizeImage(file,217,190,fileMiddle);
        }
    }

    public void imageDeleteProcess(ProductImage productImage,HttpSession session){
        String fileName = productImage.getId() + ".jpg";
        String imageFolder;
        String imageFolderSmall;
        String imageFolderMiddle;

        if (ProductImageService.TYPE_SINGLE.equals(productImage.getType())){
            imageFolder = session.getServletContext().getRealPath("img/productSingle");
            imageFolderSmall = session.getServletContext().getRealPath("img/productSingle_small");
            imageFolderMiddle = session.getServletContext().getRealPath("img/productSingle_middle");

            File imageFile = new File(imageFolder,fileName);
            File fileSmall = new File(imageFolderSmall,fileName);
            File fileMiddle = new File(imageFolderMiddle,fileName);

            imageFile.delete();
            fileSmall.delete();
            fileMiddle.delete();
        }else {
            imageFolder = session.getServletContext().getRealPath("img/productDetail");
            File imageFile = new File(imageFolder,fileName);
            imageFile.delete();
        }
    }
}
