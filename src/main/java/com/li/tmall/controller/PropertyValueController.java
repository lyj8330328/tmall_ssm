package com.li.tmall.controller;

import com.li.tmall.pojo.Product;
import com.li.tmall.pojo.PropertyValue;
import com.li.tmall.service.ProductService;
import com.li.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: 98050
 * Time: 2018-09-20 09:55
 * Feature:
 */
@Controller
@RequestMapping("")
public class PropertyValueController {

    @Autowired
    private PropertyValueService propertyValueService;

    @Autowired
    private ProductService productService;

    @RequestMapping("admin_propertyValue_edit")
    public String edit(Model model, Integer pid){
        Product product = productService.get(pid);

        propertyValueService.init(product);

        List<PropertyValue> list = propertyValueService.list(pid);

        model.addAttribute("product",product);
        model.addAttribute("list",list);
        return "admin/editPropertyValue";
    }

    @RequestMapping("admin_propertyValue_update")
    @ResponseBody
    public String update(PropertyValue propertyValue){
        propertyValueService.update(propertyValue);
        return "success";
    }



}
