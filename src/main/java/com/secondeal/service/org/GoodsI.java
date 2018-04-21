package com.secondeal.service.org;

import com.github.pagehelper.Page;
import com.secondeal.model.org.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsI {

    int insert(Goods goods);

    List getAll();

    List getByTime();
    List getByTimeShare();

    List getByDis(String flag);
    List getByDiShare(String flag);

    List getByTag(String tags);
    List getByTagShare(String tags);

    List<Goods> getByUuid(String uuid);

    List<Goods> getByBelong(String belong_uuid);

    int updateImg(Map<String, Object> str);

    int delete(String uuid);

    /**
     * 分页查询
     *
     * @param pageNo   页号
     * @param pageSize 每页显示记录数
     * @return
     */
    Page<Goods> findByPage(int pageNo, int pageSize);
}
