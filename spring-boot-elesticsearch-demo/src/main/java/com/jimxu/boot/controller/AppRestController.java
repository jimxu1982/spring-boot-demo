package com.jimxu.boot.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jimxu.boot.document.App;
import com.jimxu.boot.service.AppESService;

/**
 * 应用 Controller 实现 Restful HTTP 服务
 * Created by xuzhijing on 18/07/2017.
 */
@RestController
@RequestMapping(value="/app")
public class AppRestController {

    @Autowired
    private AppESService appESService;
    
    /**
     * 插入 ES 新应用
     *
     * @param App
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String createApp(@RequestBody App app) {
        return appESService.saveApp(app);
    }
    
    /**
     * 删除 ES应用
     *
     * @param appKey
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteApp(@RequestParam(value = "appkey") String appKey) {
        appESService.deleteApp(appKey);
        return "删除成功！";
    }

    /**
     * AND 语句查询
     *
     * @param description
     * @param score
     * @return
     */
    @RequestMapping(value = "/and/find", method = RequestMethod.GET)
    public List<App> findByDescriptionAndScore(@RequestParam(value = "desc") String description,
                                                @RequestParam(value = "score") Integer score) {
        return appESService.findByDescriptionAndScore(description, score);
    }

    /**
     * OR 语句查询
     *
     * @param description
     * @param score
     * @return
     */
    @RequestMapping(value = "/or/find", method = RequestMethod.GET)
    public List<App> findByDescriptionOrName(@RequestParam(value = "desc") String description,
                                               @RequestParam(value = "name") String name) {
        return appESService.findByDescriptionOrName(description, name);
    }

    /**
     * 查询应用描述
     *
     * @param description
     * @return
     */
    @RequestMapping(value = "/desc/find", method = RequestMethod.GET)
    public List<App> findByDescription(@RequestParam(value = "desc") String description) {
        return appESService.findByDescription(description);
    }
    
    /**
     * 查询应用描述
     *
     * @param description
     * @return
     */
    @RequestMapping(value = "/desc/likefind", method = RequestMethod.GET)
    public List<App> findByDescription2(@RequestParam(value = "desc") String description) {
        return appESService.findByDescriptionLike(description);
    }
    
    /**
     * NOT 语句查询
     *
     * @param description
     * @return
     */
    @RequestMapping(value = "/desc/not/find", method = RequestMethod.GET)
    public List<App> findByDescriptionNot(@RequestParam(value = "desc") String description) {
        return appESService.findByDescriptionNot(description);
    }

    /**
     * 列出所有应用
     * 
     */   
    @CrossOrigin
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Iterator<App> findAll() {
        return appESService.findAll();
    }
    /**
     * 按名称和描述模糊查询
     * 
     */
    @CrossOrigin
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public List<App> findByDescriptionOrName(@RequestParam(value = "skey") String skey) throws Exception {
    	 return  appESService.findByDescriptionOrName(skey, skey);
    }
}
