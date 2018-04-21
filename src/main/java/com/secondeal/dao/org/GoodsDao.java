package com.secondeal.dao.org;

import com.github.pagehelper.Page;
import com.secondeal.model.org.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsDao {

    int insert(Goods record);

    List getAll();

    List getByTime();
    List getByTimeShare();

    List getByDis(@Param("pd") String flag);
    List getByDiShare(@Param("pd") String flag);

    List getByTag(@Param("tag") String tag);
    List getByTagShare(@Param("tag") String tag);

    List<Goods> getByUuid(@Param("uuid") String uuid);

    List<Goods> getByBelong(@Param("belong_uuid") String belong_uuid);

    int updateImg(@Param("pd") Map<String, Object> map);

    int delete(@Param("uuid") String uuid);


    Page<Goods> findByPage();

}