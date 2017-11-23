package com.jimxu.boot.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.scheduling.annotation.Async;

import com.jimxu.boot.document.App;


/**
 * 应用 ES 业务接口类
 *
 */
public interface AppESService {

    /**
     * 新增 ES 应用信息
     *
     * @param App
     * @return
     */
	@Async
    String saveApp(App App);
    /**
     * 删除ES 应用信息
     *
     * @param App
     * @return
     */
    void deleteApp(String id);

    /**
     * AND 语句查询
     *
     * @param description
     * @param score
     * @return
     */
    List<App> findByDescriptionAndScore(String description, Integer score);

    /**
     * OR 语句查询
     *
     * @param description
     * @param score
     * @return
     */
    List<App> findByDescriptionOrScore(String description, Integer score);
    /**
     * OR 语句查询
     *
     * @param description
     * @param name
     * @return
     */
    List<App> findByDescriptionOrName(String description, String name);
    
    /**
     * 查询应用描述
     *
     * @param description
     * @return
     */
    List<App> findByDescription(String description);
    
    /**
     * 查询应用名称
     *
     * @param name
     * @return
     */
    List<App> findByName(String name);

    /**
     * NOT 语句查询
     *
     * @param description
     * @return
     */
    List<App> findByDescriptionNot(String description);

    /**
     * LIKE 语句查询
     *
     * @param description
     * @return
     */
    List<App> findByDescriptionLike(String description);
    
    List<App> findByNameLike(String name);
    
    List<App> findByAuthorLike(String author);
    
    List<App> findByUploaderLike(String uploader);
    
    List<App> findByUploader(String uploader);
    
    List<App> findAll(Integer pageNum,Integer pageSize);
    
    Iterator<App> findAll();
    
    /**
     * 搜索词搜索，分页返回城市信息
     *
     * @param pageNumber 当前页码
     * @param pageSize 每页大小
     * @param searchContent 搜索内容
     * @return
     */
    List<App> searchCity(Integer pageNumber, Integer pageSize, String searchContent);
}