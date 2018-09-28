package com.li.tmall.service.impl;

import com.li.tmall.mapper.PropertyValueMapper;
import com.li.tmall.pojo.Product;
import com.li.tmall.pojo.Property;
import com.li.tmall.pojo.PropertyValue;
import com.li.tmall.pojo.PropertyValueExample;
import com.li.tmall.service.PropertyService;
import com.li.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 98050
 * Time: 2018-09-19 22:57
 * Feature:CRUD
 */
@Service
public class PropertyValueServiceImpl implements PropertyValueService {

    /**
     * 1. get(int ptid, int pid)
     * 根据属性id和产品id获取PropertyValue对象
     *
     * 2. list(int pid)
     * 根据产品id获取所有的属性值
     *
     * 3. init方法
     * 3.1 这个方法的作用是初始化PropertyValue。 为什么要初始化呢？ 因为对于PropertyValue的管理，没有增加，只有修改。 所以需要通过初始化来进行自动地增加，以便于后面的修改。
     * 3.2 首先根据产品获取分类，然后获取这个分类下的所有属性集合
     * 3.3 然后用属性id和产品id去查询，看看这个属性和这个产品，是否已经存在属性值了。
     * 3.4 如果不存在，那么就创建一个属性值，并设置其属性和产品，接着插入到数据库中。
     * 这样就完成了属性值的初始化。
     *
     * 4. update（PropertyValue pv)
     * 更新
     * @param product
     */
    @Autowired
    private PropertyValueMapper propertyValueMapper;

    @Autowired
    private PropertyService propertyService;


    @Override
    public void init(Product product) {
        List<Property> list = propertyService.list(product.getCid());

        for (Property property : list){
            PropertyValue propertyValue = get(property.getId(),product.getId());
            if (propertyValue == null){
                propertyValue = new PropertyValue();
                propertyValue.setPid(product.getId());
                propertyValue.setPtid(property.getId());
                propertyValueMapper.insertSelective(propertyValue);
            }
        }
    }

    @Override
    public void update(PropertyValue propertyValue) {
        propertyValueMapper.updateByPrimaryKeySelective(propertyValue);
    }

    @Override
    public PropertyValue get(Integer ptid, Integer pid) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPtidEqualTo(ptid).andPidEqualTo(pid);
        List<PropertyValue> list = propertyValueMapper.selectByExample(example);
        if (!list.isEmpty()){
            return list.get(0);
        }else {
            return null;
        }
    }

    @Override
    public List<PropertyValue> list(Integer pid) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPidEqualTo(pid);
        List<PropertyValue> list = propertyValueMapper.selectByExample(example);
        for (PropertyValue propertyValue : list){
            propertyValue.setProperty(propertyService.get(propertyValue.getPtid()));
        }
        return list;
    }
}
