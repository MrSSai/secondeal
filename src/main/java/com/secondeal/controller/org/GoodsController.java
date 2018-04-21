package com.secondeal.controller.org;

import com.github.pagehelper.Page;
import com.secondeal.controller.BaseController;
import com.secondeal.model.org.Goods;
import com.secondeal.service.org.GoodsI;
import com.secondeal.util.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goods")
public class GoodsController extends BaseController {

    @Autowired
    private GoodsI goodsI;

    @PostMapping("/add")
    public @ResponseBody
    Object addGoods(HttpServletRequest request) {
        Map<String, Object> pd = this.getParam();
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String imgs = FileUtil.handleFileUpload(files);

        String title = StringUtil.parseString(pd.get("title"));
        String tags = StringUtil.parseString(pd.get("tags"));
        Double price = StringUtil.parseDouble(pd.get("price"));
        Double discounted = StringUtil.parseDouble(pd.get("discounted"));
        String intro = StringUtil.parseString(pd.get("intro"));
        Integer flag = StringUtil.parseInteger(pd.get("flag"));
        String belong_uuid = StringUtil.parseString(pd.get("token"));

        Map<String, Object> m = JavaWebToken.parserJavaWebToken(belong_uuid);

        String mobile = StringUtil.parseString(m.get("username"));
        String buy_uuid = StringUtil.parseString(m.get("uuid"));

        Goods goods = new Goods();
        goods.setUuid(StringUtil.getUUID());
        goods.setTitle(title);
        goods.setTags(tags);
        goods.setImages(imgs);
        goods.setPrice(price);
        goods.setDiscounted(discounted);
        goods.setIntro(intro);
        goods.setMobile(mobile);
        goods.setBelongUuid(buy_uuid);
        goods.setIsDelete(0);
        goods.setState(0);
        goods.setFlag(flag);
        goods.setCreateTime(StringUtil.getNowDate());
        int res = goodsI.insert(goods);
        if (res == 1) {
            return Result.success("添加成功");
        }
        return Result.error(CodeMsg.NOT_FIND_DATA);

    }

    @GetMapping("/getall")
    public Object getAllGoods() {
        List map = goodsI.getAll();
        if (map.size() != 0) {
            return Result.success(map);
        }
        return Result.error(CodeMsg.NOT_FIND_DATA);
    }


    @GetMapping("/getbytime")
    public Object getByTime(){
        List map = goodsI.getByTime();
        if (map.size() != 0) {
            return Result.success(map);
        }
        return Result.error(CodeMsg.NOT_FIND_DATA);
    }
    /**
     * header中传flag  0:升序 从最低价开始   1:降序  从最高价开始
     * @return
     */
    @GetMapping("/getbydis")
    public Object getByDis(){
        String flag = this.getHeadersInfo();
        List map = goodsI.getByDis(flag);
        if (map.size() != 0) {
            return Result.success(map);
        }
        return Result.error(CodeMsg.NOT_FIND_DATA);
    }
    /**
     * 根据标签获取物品信息，默认降序
     * @param tag
     * @return
     */
    @PostMapping("/getbytag")
    public Object getByTag(){
        Map<String, Object> params = this.getParam();
        String tags = StringUtil.parseString(params.get("tag"));
        List map = goodsI.getByTag(tags);
        if (map.size() != 0) {
            return Result.success(map);
        }
        return Result.error(CodeMsg.NOT_FIND_DATA);
    }


    @GetMapping("/getbytimeshare")
    public Object getByTimeShare(){
        List map = goodsI.getByTimeShare();
        if (map.size() != 0) {
            return Result.success(map);
        }
        return Result.error(CodeMsg.NOT_FIND_DATA);
    }
    /**
     * header中传flag  0:升序 从最低价开始   1:降序  从最高价开始
     * @return
     */
    @GetMapping("/getbydishare")
    public Object getByDisShare(){
        String flag = this.getHeadersInfo();
        List map = goodsI.getByDiShare(flag);
        if (map.size() != 0) {
            return Result.success(map);
        }
        return Result.error(CodeMsg.NOT_FIND_DATA);
    }
    /**
     * 根据标签获取物品信息，默认降序
     * @param tag
     * @return
     */
    @PostMapping("/getbytagshare")
    public Object getByTagShare(){
        Map<String, Object> params = this.getParam();
        String tags = StringUtil.parseString(params.get("tag"));
        List map = goodsI.getByTagShare(tags);
        if (map.size() != 0) {
            return Result.success(map);
        }
        return Result.error(CodeMsg.NOT_FIND_DATA);
    }

    @PostMapping("/getModelByUuid")
    public Object getByUuid() {
        Map<String, Object> pd = this.getParam();
        String uuid = StringUtil.parseString(pd.get("uuid"));
        List<Goods> list = goodsI.getByUuid(uuid);
        if (list.size() != 0) {
            return Result.success(list);
        }
        return Result.error(CodeMsg.NOT_FIND_DATA);
    }


    @GetMapping("/getMyGoods")
    public Object getMyGoods(){
        String token = this.getHeadersInfo();
        Map<String, Object> m = JavaWebToken.parserJavaWebToken(token);
        String belong_uuid = StringUtil.parseString(m.get("uuid"));
//        List<Goods> list = goodsI.getByBelong("belong_uuid");
        List<Goods> list = goodsI.getByBelong(belong_uuid);
        if (list.size() != 0) {
            return Result.success(list);
        }
        return Result.error(CodeMsg.NOT_FIND_DATA);
    }

    @PostMapping("/delete")
    public Object delete(){
        Map<String, Object> params = this.getParam();
        String uuid = StringUtil.parseString(params.get("uuid"));
        int res = goodsI.delete(uuid);
        if (res == 1) {
            return Result.success("删除成功");
        }
        return Result.error(CodeMsg.NOT_FIND_DATA);
    }


//    @GetMapping("/getByPage/{pageNo}")
    @RequestMapping(value = "/getByPage/{pageNo}",method = RequestMethod.GET)
    public Object getByPage(@PathVariable("pageNo") int pageNo){
        Page<Goods> goods = goodsI.findByPage(pageNo, 8);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<Goods> pageInfo = new PageInfo<>(goods);
        return Result.success(pageInfo);
    }
}
