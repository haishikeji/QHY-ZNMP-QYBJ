package com.px.fw.message_manage.service;

import com.px.fw.message_manage.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageMapper messageMapper;

    @Override
    public int insertMessage(String id, String all_employ_id, String mes_content, String create_truename, String create_by, String create_date, String update_by, String update_date, String del_flag) {
        return messageMapper.insertMessage(id, all_employ_id, mes_content, create_truename, create_by, create_date, update_by, update_date, del_flag);
    }

    @Override
    public int addViewsEmploy(String id) {
        return messageMapper.addViewsEmploy(id);
    }

    @Override
    public Map<String, Object> selectCollectionCount(String userid, String employ_collection) {
        return messageMapper.selectCollectionCount(userid, employ_collection);
    }

    @Override
    public int updateEmployCollection(String userid, String employ_collection, String del_flag) {
        return messageMapper.updateEmployCollection(userid, employ_collection, del_flag);
    }

    @Override
    public int employCollection(String id, String employ_collection, String userid, String username, String create_date) {
        return messageMapper.employCollection(id, employ_collection, userid, username, create_date);
    }
}
