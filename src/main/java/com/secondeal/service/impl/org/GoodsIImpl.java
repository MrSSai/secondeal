package com.secondeal.service.impl.org;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.secondeal.dao.account.AccountDao;
import com.secondeal.dao.org.GoodsDao;
import com.secondeal.model.account.Account;
import com.secondeal.model.org.Goods;
import com.secondeal.service.org.GoodsI;
import com.secondeal.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GoodsIImpl implements GoodsI {

    @Autowired
    GoodsDao goodsDao;
    @Autowired
    AccountDao accountDao;

    @Override
    public int insert(Goods goods) {
        String mobile = goods.getMobile();
        Map list = accountDao.selectByMobile(mobile);
        if (list.size() != 0) {
            goods.setBelongName(StringUtil.parseString(list.get("nickname")));
            goods.setQq(StringUtil.parseInteger(list.get("qq")));
        }
        return goodsDao.insert(goods);
    }

    @Override
    public List getAll() {
        return goodsDao.getAll();
    }

    @Override
    public List getByTime() {
        return goodsDao.getByTime();
    }

    @Override
    public List getByDis(String flag) {
        return goodsDao.getByDis(flag);
    }

    @Override
    public List getByDiShare(String flag) {
        return goodsDao.getByDiShare(flag);
    }

    @Override
    public List getByTag(String tags) {
        return goodsDao.getByTag(tags);
    }

    @Override
    public List getByTagShare(String tags) {
        return goodsDao.getByTagShare(tags);
    }

    @Override
    public List getByTimeShare() {
        return goodsDao.getByTimeShare();
    }

    @Override
    public List<Goods> getByUuid(String uuid) {
        return goodsDao.getByUuid(uuid);
    }

    @Override
    public List<Goods> getByBelong(String belong_uuid) {
        return goodsDao.getByBelong(belong_uuid);
    }

    @Override
    public int updateImg(Map<String, Object> map) {
        return goodsDao.updateImg(map);
    }

    @Override
    public int delete(String uuid) {
        return goodsDao.delete(uuid);
    }

    @Override
    public Page<Goods> findByPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return goodsDao.findByPage();
    }
}
